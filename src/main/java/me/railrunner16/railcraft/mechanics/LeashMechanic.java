package me.railrunner16.railcraft.mechanics;

import me.railrunner16.railcraft.RailCraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

/**
 * Handles leashing of entities and teleporting with them
 *
 * @author RailRunner16
 */
public class LeashMechanic extends Mechanic {
	private final double RADIUS = 100.0;
	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
		Iterator iter = event.getFrom().getWorld().getNearbyEntities(event.getFrom(), RADIUS, RADIUS, RADIUS).iterator();

		while(true) {
			Entity ent;
			do {
				do {
					if (!iter.hasNext()) {
						return;
					}

					ent = (Entity)iter.next();
				} while(!(ent instanceof Animals));

				if (((Animals)ent).isLeashed() && ((Animals)ent).getLeashHolder().equals(event.getPlayer())) {
					this.teleportEnt(ent, event.getFrom(), event.getTo(), event.getPlayer());
				}
			} while(!(ent instanceof Parrot) && !(ent instanceof Wolf) && !(ent instanceof Ocelot));

			if (((Tameable)ent).isTamed() && ((Tameable)ent).getOwner().equals(event.getPlayer())) {
				this.teleportEnt(ent, event.getFrom(), event.getTo(), event.getPlayer());
			}
		}
	}

	public void teleportEnt(final Entity ent, final Location from, final Location to, final Player p) {
		(new BukkitRunnable() {
			public void run() {
				((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 5));
				ent.teleport(to);
			}
		}).runTaskLater(RailCraft.getInstance(), 1L);
		(new BukkitRunnable() {
			public void run() {
				List<Entity> nearbyEntities = (List)from.getWorld().getNearbyEntities(from, RADIUS, RADIUS, RADIUS);
				Iterator iter = nearbyEntities.iterator();

				while(iter.hasNext()) {
					Entity item = (Entity)iter.next();
					if (item instanceof Item && ((Item)item).getItemStack().getType().equals(Material.LEAD)) {
						item.remove();
					}
				}

				((Animals)ent).setLeashHolder(p);
			}
		}).runTaskLater(RailCraft.getInstance(), 1L);
	}
}
