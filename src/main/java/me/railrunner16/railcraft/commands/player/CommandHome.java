package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHome extends RailCommand {
	public CommandHome() {
		super("home", "/home <name|bed>", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if(args.length > 0) {
			String home = args[0];

			YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

			if (!yml.contains("homes." + home)) {
				p.sendMessage(ChatColor.RED + "You do not have a home with that name.");
				return;
			}

			double x = yml.getDouble("homes." + home + ".x");
			double y = yml.getDouble("homes." + home + ".y");
			double z = yml.getDouble("homes." + home + ".z");
			double pitch = yml.getDouble("homes." + home + ".pitch");
			double yaw = yml.getDouble("homes." + home + ".yaw");

			p.sendMessage(ChatColor.BLUE + "Teleporting to home: " + ChatColor.GREEN + ChatColor.BOLD + home + ChatColor.RESET + ChatColor.BLUE + ".");
			p.teleport(new Location(p.getWorld(), x, y, z, (float) pitch, (float) yaw));
		}
	}
}
