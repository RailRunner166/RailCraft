package me.railrunner16.railcraft.commands.staff;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly extends RailCommand {
	public CommandFly() {
		super("fly", "/fly", true);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		boolean b = !p.getAllowFlight();

		p.setAllowFlight(b);

		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);
		yml.set("toggles.fly", b);

		sender.sendMessage(Utils.formatToggle("Flight", p.getAllowFlight()));
	}
}
