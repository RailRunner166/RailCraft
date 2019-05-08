package me.railrunner16.railcraft.kit.kits;

import me.railrunner16.railcraft.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class KitTorch extends Kit {
	public KitTorch() {
		super("torch", Arrays.asList(
				new ItemStack(Material.TORCH, 64)
		));
	}
}
