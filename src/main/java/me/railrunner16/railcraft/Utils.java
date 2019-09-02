package me.railrunner16.railcraft;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	public static World getDefaultWorld() {
		return Bukkit.getServer().getWorlds().get(0);
	}

	public static World getNetherWorld() {
		return Bukkit.getServer().getWorlds().get(1);
	}

	public static void broadcastMessage(String text) {
		for (Player player : Bukkit.getOnlinePlayers()) player.sendMessage(text);
	}


	public static Location findSafe(Location origin) {
		int maxHeight = origin.getWorld().getMaxHeight();
		Location safe = origin.clone();
		safe.setY(maxHeight); // Default location

		Location temp = origin.clone();
		temp.setY(0);
		while(temp.getBlockY() < maxHeight - 1) { // Don't go to the very max height because we check the block above tempY.
			temp.setY(temp.getBlockY() + 1);
			if (isSafe(temp) && origin.distanceSquared(safe) > origin.distanceSquared(temp))
				safe = temp.clone(); // This location is closer than the saved one, and it's safe.
		}
		return safe.add(0, 0.5, 0);
	}


	private static boolean isSafe(Location loc) {
		Block bk = loc.getBlock();
		Block below = bk.getRelative(BlockFace.DOWN);
		return !isSolid(bk) && !isSolid(bk.getRelative(BlockFace.UP)) && below.getType() != Material.AIR  && !below.isLiquid();
	}
	public static boolean isSolid(Block bk) {
		return isSolid(bk.getType()) && !bk.isLiquid();
	}
	public static boolean isSolid(Material mat) {
		return mat.isSolid() && mat.isOccluding();
	}

	public static String formatToggle(String name, boolean value) {
		return (name != null ? ChatColor.GRAY + name + ": " : "") + (value ? ChatColor.GREEN : ChatColor.RED) + value;
	}

	public static void giveItem(Player player, ItemStack itemStack) {
		if (player.getInventory().firstEmpty() > -1) {
			player.getInventory().addItem(itemStack);
		} else {
			player.getWorld().dropItem(player.getLocation(), itemStack);
			player.sendMessage(ChatColor.RED + "Your inventory was full, so you dropped the item.");
		}
	}

	public static class Text {
		public static int CHAT_SIZE = 320;
		public static int BOOK_SIZE = 120;

		private static String padCenter(String text, int lineSize) {
			if (text == null || text.length() == 0)
				return "";

			String fillWith = " ";
			// Perform calculations.
			int centerPixel = (lineSize / 2) - 6;
			int pixelSize = getPixelWidth(text);
			int blankPixels = centerPixel - (pixelSize / 2);
			int spaceSize = getPixelWidth(fillWith);

			String padding = "";
			for (int i = 0; i < blankPixels; i += spaceSize)
				padding += fillWith;

			return padding;
		}

		public static String capitalize(String sentence) {
			String[] split = sentence.replaceAll("_", " ").split(" ");
			List<String> out = new ArrayList<>();
			for (String s : split)
				out.add(s.length() > 0 ? s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() : "");
			return String.join(" ", out);
		}

		public static String centerChat(String text) {
			return centerText(text, CHAT_SIZE);
		}
		public static String centerBook(String text) {
			return centerText(text, BOOK_SIZE);
		}

		public static String centerText(String text, int lineSize) {
			return text != null || text.length() > 0 ? padCenter(text, lineSize) + text : "";
		}

		public static int getPixelWidth(String text) {
			if (text == null || text.length() == 0)
				return 0;

			int pixelSize = 0;
			boolean bold = false;

			// Determine the pixel width of the string.
			for (int i = 0; i < text.length(); i++) {
				String c = text.substring(i, i + 1);
				if (c.equals(ChatColor.COLOR_CHAR)) { // Changing color character.
					bold = text.substring(i + 1, i + 2).equalsIgnoreCase("l");
				} else {
					pixelSize += MinecraftFont.getWidth(c, bold);
				}
			}

			return pixelSize;
		}
	}
}