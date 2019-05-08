package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShovel extends RailCommand {
	public CommandShovel() {
		super("shovel", "/shovel");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;

		if (player.getInventory().contains(Material.GOLDEN_SHOVEL)) {
			sender.sendMessage(ChatColor.RED + "You already have a shovel.");
			return;
		}

		Utils.giveItem(player, ItemManager.makeShovel());
		sender.sendMessage(ChatColor.GREEN + "Shovel spawned.");
	}
}
