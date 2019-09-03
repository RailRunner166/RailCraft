package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.Utils;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSkull extends RailCommand {
	public CommandSkull() {
		super("skull", "/skull <player>", 1, 1,"head");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;

		if (args.length < 0) {
			ItemStack skull = ItemManager.makeSkull(args[0]);
			Utils.giveItem(p, skull);
			p.sendMessage(ChatColor.BLUE + "Gave you the skull!");
		}
	}
}
