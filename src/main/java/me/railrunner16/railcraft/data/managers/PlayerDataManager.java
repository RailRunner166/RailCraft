package me.railrunner16.railcraft.data.managers;

import me.railrunner16.railcraft.RailCraft;
import me.railrunner16.railcraft.config.YamlConfig;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerDataManager {
	public static YamlConfig getPlayerYaml(Player player) {
		return new YamlConfig(RailCraft.getInstance().getDataFolder().getAbsolutePath() + File.separator + "data" + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");
	}
}
