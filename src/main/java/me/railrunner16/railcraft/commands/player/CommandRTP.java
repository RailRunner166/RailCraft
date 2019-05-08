package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRTP extends RailCommand {
	public CommandRTP() {
		super("rtp", "/rtp", "wild");
	}

	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		if (player.getWorld().getEnvironment() != World.Environment.NORMAL) {
			sender.sendMessage(ChatColor.RED + "You may only randomly teleport in the overworld.");
			return;
		}

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 0 15000 false " + player.getName());
		player.teleport(Utils.findSafe(player.getLocation())); // Prevent suffocation.
	}
}
