package me.railrunner16.railcraft.mechanics;

import me.railrunner16.railcraft.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

/**
 * Handles mining of mob spawners with silk touch.
 *
 * @author RailRunner16
 */
public class SpawnerMechanic extends Mechanic {
	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {}

	@EventHandler
	public void onSpawnerMine(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		Material material = block.getType();

		if (material == Material.SPAWNER) {
			CreatureSpawner spawner = (CreatureSpawner) block.getState();
			if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
				// Cancel exp drop
				e.setExpToDrop(0);

				ItemStack item = new ItemStack(material);
				ItemMeta meta = item.getItemMeta();
				meta.setLore(Collections.singletonList(ChatColor.YELLOW + "type: " + ChatColor.GRAY + spawner.getSpawnedType().toString().toLowerCase()));
				item.setItemMeta(meta);

				Utils.giveItem(player, item);
				player.playSound(block.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
				player.sendMessage(ChatColor.BLUE + "Picked up the spawner. (Creature: " + ChatColor.GREEN + ChatColor.BOLD + spawner.getSpawnedType().toString().toLowerCase() + ChatColor.RESET + ChatColor.BLUE + ")");

				block.getDrops().clear();
			}
		}
	}

	@EventHandler
	public void onSpawnerPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Material material = block.getType();

		if (material == Material.SPAWNER) {
			ItemStack placed = e.getItemInHand();
			ItemMeta meta = placed.getItemMeta();
			try {
				EntityType entity = EntityType.valueOf(meta.getLore().toString().split(": §7")[1].split("]")[0].toUpperCase());
				CreatureSpawner spawner = (CreatureSpawner) block.getState();
				spawner.setSpawnedType(entity);
				spawner.update();
			} catch (NullPointerException exception) {
				System.out.println(exception.getMessage());
			}
		}
	}
}
