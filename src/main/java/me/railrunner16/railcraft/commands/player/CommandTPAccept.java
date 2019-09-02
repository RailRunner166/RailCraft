package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.RailCraft;
import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPAccept extends RailCommand {
	public CommandTPAccept() {
		super("tpaccept", "/tpaccept <player>", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
			return;
		}

		Player p = (Player) sender;

		if (RailCraft.getInstance().getTpaMap().containsKey(p)) {
			Player teleporting = RailCraft.getInstance().getTpaMap().get(p);

			p.sendMessage(ChatColor.BLUE + "Accepted " + teleporting.getName() + "'s request.");
			teleporting.sendMessage(Utils.formatTeleportMessage(p.getName()));

			teleporting.teleport(p);
			RailCraft.getInstance().getTpaMap().remove(p);
		} else {
			p.sendMessage(ChatColor.RED + "That player is not requesting to teleport to you!");
		}
	}
}
