package me.railrunner16.railcraft.kit.kits;

import me.railrunner16.railcraft.items.ItemManager;
import me.railrunner16.railcraft.kit.Kit;

import java.util.Arrays;

public class KitShovel extends Kit {
	public KitShovel() {
		super("shovel", Arrays.asList(ItemManager.makeShovel()));
	}
}
