package me.railrunner16.railcraft.commands.staff;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly extends RailCommand {
	public CommandFly() {
		super("fly", "/fly", true);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		p.setAllowFlight(!p.getAllowFlight());
		sender.sendMessage(Utils.formatToggle("Flight", p.getAllowFlight()));
	}
}
