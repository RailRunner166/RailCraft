package me.railrunner16.railcraft.kit;

import me.railrunner16.railcraft.mechanics.Mechanic;

import java.util.HashMap;

public class Kits extends Mechanic {
	public static HashMap<String, Kit> kits = new HashMap<>();

	@Override
	public void onEnable() {}

	@Override
	public void onDisable() {
		kits.clear();
	}
}
