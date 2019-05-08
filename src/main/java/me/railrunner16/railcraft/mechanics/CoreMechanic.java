package me.railrunner16.railcraft.mechanics;

import me.railrunner16.railcraft.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import javax.imageio.ImageIO;
import java.net.URL;

public class CoreMechanic extends Mechanic {
	@Override
	public void onDisable() {
		return;
	}

	@Override
	public void onEnable() {
		return;
	}

	@EventHandler
	public void onServerListPing(ServerListPingEvent evt) {
		evt.setMotd(ChatColor.BLUE + "Welcome to " + ChatColor.GREEN + ChatColor.BOLD + "RailCraft" + ChatColor.RESET + ChatColor.BLUE + "!");

		try {
			evt.setServerIcon(Bukkit.loadServerIcon(ImageIO.read(new URL("https://cravatar.eu/avatar/RailRunner16/64"))));
		} catch (Exception e) {
			e.printStackTrace();
		}

		evt.setMaxPlayers(88);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player p = evt.getPlayer();
		if (p.hasPlayedBefore()) {
			p.sendTitle(ChatColor.BLUE + "Welcome Back!", null);
		} else {
			Utils.broadcastMessage(ChatColor.BLUE + "Welcome " + ChatColor.YELLOW + p.getName() + ChatColor.BLUE + " to " + ChatColor.GREEN + ChatColor.BOLD + "RailCraft" + ChatColor.RESET + ChatColor.BLUE + "!");
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent evt) {
		Player p = evt.getPlayer();

		String prefix = "";

		if (p.isOp()) prefix = ChatColor.BOLD.toString() + ChatColor.GOLD + "STAFF" + ChatColor.RESET;

		evt.setFormat(prefix + " %s: " + ChatColor.RESET + ChatColor.WHITE + "%s");
	}
}
