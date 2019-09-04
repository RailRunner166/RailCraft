package me.railrunner16.railcraft.commands.staff;

import me.railrunner16.railcraft.EnumRank;
import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetRank extends RailCommand {
	public CommandSetRank() {
		super("setrank", "/setrank <player> <rank>", 2, 2);
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		EnumRank newRank = EnumRank.getByName(args[1]);
		EnumRank myRank = Utils.getRank(sender);

		if (newRank == null) {
			sender.sendMessage(ChatColor.RED + "Unknown rank '" + args[1] + "'.");
			return;
		}

		if (!myRank.isAtLeast(newRank)) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to set players to this rank.");
			return;
		}

		Player player = Bukkit.getPlayer(args[0]);

		YamlConfig yml = PlayerDataManager.getPlayerYaml(player);

		if (!myRank.isAtLeast(EnumRank.getByName(yml.getString("rank")))) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to manage this user.");
			return;
		}

		yml.set("rank", newRank.getName());
		yml.save();

		sender.sendMessage(ChatColor.GREEN + "Updated " + player.getName() + "'s rank to " + newRank.getName() + ".");
	}
}
