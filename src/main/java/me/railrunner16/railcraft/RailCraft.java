package me.railrunner16.railcraft;

import lombok.Getter;
import me.railrunner16.railcraft.commands.RailCommand;
import me.railrunner16.railcraft.commands.player.*;
import me.railrunner16.railcraft.commands.staff.CommandFly;
import me.railrunner16.railcraft.kit.Kits;
import me.railrunner16.railcraft.kit.kits.KitTorch;
import me.railrunner16.railcraft.mechanics.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class RailCraft extends JavaPlugin {
	@Getter
	private static RailCraft instance;
	public static HashMap<String, RailCommand> commands = new HashMap<>();

	@Override
	public void onEnable() {
		instance = this;

		registerMechanic(new CoreMechanic());
		registerMechanic(new PlayerDataMechanic());
		registerMechanic(new SpawnerMechanic());
		registerMechanic(new LeashMechanic());
		registerMechanic(new Kits());

		registerCommands();
		registerKits();
	}

	private void registerCommands() {
		new CommandExtinguish();
		new CommandRTP();
		new CommandSpawn();
		new CommandFly();
		new CommandSkull();
		new CommandTPA();
		new CommandHome();
		new CommandSethome();
		new CommandDelhome();
		new CommandEnderchest();
		new CommandWorkbench();
		new CommandShovel();
		new CommandStack();
		new CommandKit();
	}

	private void registerKits() {
		new KitTorch();
	}

	@Override
	public void onDisable() {
		instance = null;
		commands.clear();
	}

	private void registerMechanic(Mechanic mechanic) {
		getServer().getPluginManager().registerEvents(mechanic, this);
	}
}
