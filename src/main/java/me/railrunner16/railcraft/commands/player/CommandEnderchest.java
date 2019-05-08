package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEnderchest extends RailCommand {
	public CommandEnderchest() {
		super("enderchest", "/enderchest", "ec");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		p.sendMessage(ChatColor.BLUE + "Opened your ender chest.");
		p.openInventory(p.getEnderChest());
	}
}
