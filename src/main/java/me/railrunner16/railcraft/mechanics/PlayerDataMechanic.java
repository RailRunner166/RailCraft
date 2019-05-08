package me.railrunner16.railcraft.mechanics;

import me.railrunner16.railcraft.config.YamlConfig;
import me.railrunner16.railcraft.data.managers.PlayerDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataMechanic extends Mechanic {
	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		YamlConfig yml = PlayerDataManager.getPlayerYaml(evt.getPlayer());
		yml.add("uuid", evt.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onBed(PlayerBedEnterEvent evt) {
		Player p = evt.getPlayer();
		Block bed = evt.getBed();

		YamlConfig yml = PlayerDataManager.getPlayerYaml(p);

		Location bedLocation = bed.getLocation();

		int x = bedLocation.getBlockX();
		int y = bedLocation.getBlockY();
		int z = bedLocation.getBlockZ();

		if (yml.contains("bed")) {
			if (yml.getInteger("bed.x") == x && yml.getInteger("bed.y") == y && yml.getInteger("bed.z") == z) return;

			yml.set("bed.x", x);
			yml.set("bed.y", y);
			yml.set("bed.z", z);
		} else {
			yml.add("bed.x", x);
			yml.add("bed.y", y);
			yml.add("bed.z", z);
		}

		yml.save();

		p.sendMessage(ChatColor.BLUE + "Set your bed location to " + ChatColor.GREEN + ChatColor.BOLD + x + " / " + y + " / " + z + ChatColor.RESET + ChatColor.BLUE + ". Use " + ChatColor.YELLOW + ChatColor.BOLD + "/home bed" + ChatColor.RESET + ChatColor.BLUE + " to return.");
	}
}
