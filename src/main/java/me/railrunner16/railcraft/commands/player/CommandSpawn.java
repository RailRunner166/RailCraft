package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn extends RailCommand {
	public CommandSpawn() {
		super("spawn", "/spawn");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Location spawnLoc = p.getWorld().getSpawnLocation();

		p.teleport(spawnLoc);
	}
}
