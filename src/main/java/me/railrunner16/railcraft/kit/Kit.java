package me.railrunner16.railcraft.kit;

import lombok.Getter;
import me.railrunner16.railcraft.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public abstract class Kit {
	private String name;
	private List<ItemStack> items;
	public boolean requiresOp;

	public Kit(String nameParam, List<ItemStack> itemsParam) {
		this(nameParam, itemsParam, false);
	}

	public Kit(String nameParam, List<ItemStack> itemsParam, boolean requiresOpParam) {
		name = nameParam;
		items = itemsParam;
		requiresOp = requiresOpParam;

		Kits.kits.put(name, this);
	}

	public void give(Player p) {
		for (ItemStack item : items) {
			Utils.giveItem(p, item);
		}
	}
}
