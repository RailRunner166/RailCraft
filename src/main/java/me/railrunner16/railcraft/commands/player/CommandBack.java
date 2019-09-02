package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBack extends RailCommand {
	public CommandBack() {
		super("back", "/back");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
			return;
		}

		Player p = (Player) sender;

		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

		double x = yml.getDouble("last_location.x");
		double y = yml.getDouble("last_location.y");
		double z = yml.getDouble("last_location.z");

		p.sendMessage(Utils.formatTeleportMessage("Last Location"));
		p.teleport(new Location(Bukkit.getServer().getWorld(yml.getString("last_location.world")), x, y, z));
	}
}
