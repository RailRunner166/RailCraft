package me.railrunner16.railcraft.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
	public static ItemStack createItem(Material material, String name, String... lore) {
		return createItem(material, (short) 0, name, lore);
	}

	/**
	 * Build an ItemStack from the supplied parameters.
	 * @param material
	 * @param data
	 * @param name
	 * @param lore
	 * @return item
	 */
	public static ItemStack createItem(Material material, short data, String name, String... lore) {
		ItemStack itemStack = new ItemStack(material, 1, data);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + name);
		List<String> loreList = new ArrayList<>();
		for (String loreLine : lore)
			loreList.add(ChatColor.GRAY + loreLine);
		meta.setLore(loreList);
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	public static ItemStack makeSkull(String username) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);

		return Bukkit.getUnsafe().modifyItemStack(item,"{SkullOwner:\"" + username + "\"}");
	}

	public static ItemStack makeShovel() {
		return createItem(Material.GOLDEN_SHOVEL, ChatColor.YELLOW + "Claim Shovel", "Use this to claim your things!");
	}
}
