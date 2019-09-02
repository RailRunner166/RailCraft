package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.command.CommandSender;

public class CommandMsg extends RailCommand {
	public CommandMsg() {
		super("msg", "/msg <player> <message>", 2, 2, "m");
	}

	@Override
	public void run(CommandSender sender, String[] args) {

	}
}
