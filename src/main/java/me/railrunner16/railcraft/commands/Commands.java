package me.railrunner16.railcraft.commands;

import me.railrunner16.railcraft.RailCraft;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmdParam, String label, String[] args) {
		try {
			RailCommand cmd = RailCraft.commands.values().stream().filter((c) -> c.aliases.contains(label.toLowerCase()) || c.label.equalsIgnoreCase(label)).findFirst().orElseThrow(() -> new UnknownCommandException());

			if (cmd.requiresOp && !sender.isOp()) {
				sender.sendMessage(ChatColor.RED + "You must be a server operator to perform this command.");
				return true;
			}

			if (args.length >= cmd.minArgs && args.length <= cmd.maxArgs) cmd.run(sender, args);
			else sender.sendMessage(ChatColor.RED + cmd.getUsage());

			return true;
		} catch (UnknownCommandException exc) {
			return false;
		}
	}
}
