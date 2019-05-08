package me.railrunner16.railcraft.mechanics;

import org.bukkit.event.Listener;

public abstract class Mechanic implements Listener {
	public abstract void onEnable ();
	public abstract void onDisable();
}
