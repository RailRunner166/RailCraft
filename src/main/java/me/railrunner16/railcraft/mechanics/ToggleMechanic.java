package me.railrunner16.railcraft.mechanics;

import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class ToggleMechanic extends Mechanic {
	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player p = evt.getPlayer();
		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

		if (yml.contains("toggles.fly")) p.setAllowFlight(yml.getBoolean("toggles.fly"));
	}
}
