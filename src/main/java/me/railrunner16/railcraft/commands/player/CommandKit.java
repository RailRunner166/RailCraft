package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.kit.Kit;
import me.railrunner16.railcraft.kit.Kits;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKit extends RailCommand {
	public CommandKit() {
		super("kit", "/kit", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (args.length > 0) {
			String kitStr = args[0].toLowerCase();

			if (!Kits.kits.containsKey(kitStr)) {
				p.sendMessage(ChatColor.RED + "No kit exists with that name.");
				return;
			}

			Kit kit = Kits.kits.get(kitStr);

			if (kit.requiresOp && !p.isOp()) {
				p.sendMessage(ChatColor.RED + "You must be a server operator to obtain that kit.");
				return;
			}

			kit.give(p);
			p.sendMessage(ChatColor.BLUE + "Received kit " + ChatColor.GREEN + ChatColor.BOLD + kit.getName() + ChatColor.RESET + ChatColor.BLUE + ".");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(ChatColor.BLUE + "Available Kits: ");
			for (String kitName : Kits.kits.keySet()) sb.append(kitName + " ");

			p.sendMessage(sb.toString());
		}
	}
}
