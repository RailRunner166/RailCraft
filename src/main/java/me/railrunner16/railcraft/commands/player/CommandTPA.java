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
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
			return;
		}

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

			if (!RailCraft.getInstance().getTpaMap().containsKey(target)) {
				RailCraft.getInstance().getTpaMap().put(target, p);

				p.sendMessage(ChatColor.BLUE + "Requesting telportation to " + target.getName() + ".");
				target.sendMessage(ChatColor.BLUE + p.getName() + " is requesting to teleport to you. Use " + ChatColor.YELLOW + "/tpaccept" + ChatColor.BLUE + "to accept, or ignore to deny.");
			} else p.sendMessage(ChatColor.RED + "You are already requesting to teleport to this player!");
		}
	}
}
