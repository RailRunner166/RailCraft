package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSethome extends RailCommand {
	public CommandSethome() {
		super("sethome", "/sethome <name>", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

		if (args.length > 0) {
			System.out.println("test");
			String home = args[0];

			Location homeLocation = p.getLocation();

			double x = homeLocation.getX();
			double y = homeLocation.getY();
			double z = homeLocation.getZ();
			float pitch = homeLocation.getPitch();
			float yaw = homeLocation.getYaw();

			if (yml.contains("homes." + home)) {
				p.sendMessage(ChatColor.RED + "A home with that name already exists. Use " + ChatColor.BOLD + "/delhome" + ChatColor.RESET + ChatColor.RED + " to remove it.");
				return;
			}

			yml.add("homes." + home + ".x", x);
			yml.add("homes." + home + ".y", y);
			yml.add("homes." + home + ".z", z);
			yml.add("homes." + home + ".pitch", (double) pitch);
			yml.add("homes." + home + ".yaw", (double) yaw);

			yml.save();

			p.sendMessage(ChatColor.BLUE + "Created home with name: " + ChatColor.GREEN + ChatColor.BOLD + home + ChatColor.RESET + ChatColor.BLUE + ".");
		}
	}
}
