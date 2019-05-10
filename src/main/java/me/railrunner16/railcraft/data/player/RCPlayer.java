package me.railrunner16.railcraft.data.player;

import me.railrunner16.railcraft.RailCraft;
import me.railrunner16.railcraft.config.YamlConfig;

import org.bukkit.entity.Player;

import java.io.File;

public class RCPlayer extends YamlConfig {
    public RCPlayer(Player player) {
        super(RailCraft.getInstance().getDataFolder().getAbsolutePath() + File.separator + "data" + File.separator + "players" + File.separator + player.getUniqueId() + ".yml");
    }
}
