package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelhome extends RailCommand {
	public CommandDelhome() {
		super("delhome", "/delhome <name>", 1, 1);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

		if (args.length > 0) {
			String home = args[0];

			if (yml.contains("homes." + home)) {
				yml.remove("homes." + home);
				yml.save();

				p.sendMessage(ChatColor.BLUE + "Successfully deleted home: " + ChatColor.GREEN + ChatColor.BOLD + home + ChatColor.RESET + ChatColor.BLUE + ".");
			} else {
				p.sendMessage(ChatColor.RED + "You do not have a home set with this name!");
				return;
			}
		}
	}
}
