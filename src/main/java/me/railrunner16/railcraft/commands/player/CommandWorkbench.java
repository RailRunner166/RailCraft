package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorkbench extends RailCommand {
	public CommandWorkbench() {
		super("workbench", "/workbench", "ct", "ctable", "craft");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		p.sendMessage(ChatColor.BLUE + "Opened your workbench.");
		p.openWorkbench(null, true);
	}
}
