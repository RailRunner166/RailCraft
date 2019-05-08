package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.RailCraft;
import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPA extends RailCommand {
	public CommandTPA() {
		super("tpa", "/tpa <player>", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (args.length > 0) {
			final Player target = RailCraft.getInstance().getServer().getPlayer(args[0]);

			if (target == null) {
				sender.sendMessage(ChatColor.RED + "You can only send a teleport request to online players!");
				return;
			}

			if (target == p) {
				sender.sendMessage(ChatColor.RED + "You can't teleport to yourself!");
				return;
			}

			Location targetLocation = target.getLocation();

			p.sendMessage(ChatColor.BLUE + "Teleporting to " + ChatColor.GREEN + ChatColor.BOLD + target.getName() + ChatColor.RESET + ChatColor.BLUE + "...");
			target.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + p.getName() + ChatColor.RESET + ChatColor.BLUE + " is teleporting to you.");

			p.teleport(targetLocation);
		}
	}
}
