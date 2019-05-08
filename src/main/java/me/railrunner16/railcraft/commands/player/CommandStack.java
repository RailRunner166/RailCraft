package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandStack extends RailCommand {
	public CommandStack() {
		super("stack", "/stack");
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		ItemStack[] items = player.getInventory().getContents();
		int len = items.length;
		int affected = 0;

		for(int i = 0; i < len; ++i) {
			ItemStack item = items[i];
			if (item != null && item.getAmount() > 0) {
				int max = 64;
				if (item.getAmount() < max) {
					int needed = max - item.getAmount();

					for(int j = i + 1; j < len; ++j) {
						ItemStack item2 = items[j];
						if (item2 != null && item2.getAmount() > 0 && item2.getType() == item.getType() && item.getDurability() == item2.getDurability() && (item.getItemMeta() == null && item2.getItemMeta() == null || item.getItemMeta() != null && item.getItemMeta().equals(item2.getItemMeta()))) {
							if (item2.getAmount() > needed) {
								item.setAmount(max);
								item2.setAmount(item2.getAmount() - needed);
								break;
							}

							items[j] = null;
							item.setAmount(item.getAmount() + item2.getAmount());
							needed = max - item.getAmount();
							++affected;
						}
					}
				}
			}
		}

		if (affected > 0) {
			player.getInventory().setContents(items);
		}

		player.sendMessage(ChatColor.BLUE + "Items stacked!");
	}
}
