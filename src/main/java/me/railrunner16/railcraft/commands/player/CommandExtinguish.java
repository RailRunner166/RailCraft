package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExtinguish extends RailCommand {
	public CommandExtinguish() {
		super("extinguish", "/extinguish", "ext");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (p.getFireTicks() < 0) {
			sender.sendMessage(ChatColor.GRAY + "You are not on fire.");
			return;
		}

		p.setFireTicks(0);
		p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
		sender.sendMessage(ChatColor.GRAY + "The fire has been extinguished.");
	}
}
