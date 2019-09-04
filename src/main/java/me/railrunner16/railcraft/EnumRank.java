package me.railrunner16.railcraft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EnumRank {
	PLAYER("PLAYER", ChatColor.LIGHT_PURPLE, ChatColor.GRAY),

	DONATOR("DONATOR", ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE),

	BUILDER("BUILDER", ChatColor.GOLD, ChatColor.YELLOW),
	DEV("DEV", ChatColor.BLUE, ChatColor.BLUE),
	MOD("MOD", ChatColor.DARK_GREEN, ChatColor.GREEN),
	ADMIN("ADMIN", ChatColor.DARK_RED,  ChatColor.RED);

	private final String rankSymbol;
	private final ChatColor color;
	private final ChatColor nameColor;

	public static final EnumRank[] VALUES = values();

	public static EnumRank getByName(String name) {
		return Arrays.stream(values()).filter(rank -> rank.name().equalsIgnoreCase(name)).findAny().orElse(null);
	}

	/**
	 * Is this rank at least the lineage of another rank?
	 * @param other
	 * @return
	 */
	public boolean isAtLeast(EnumRank other) {
		return ordinal() >= other.ordinal();
	}

	/**
	 * Is this a staff rank?
	 * @return staff
	 */
	public boolean isStaff() {
		return isAtLeast(BUILDER);
	}

	/**
	 * Gets the display of this rank's full name.
	 * @return fullName
	 */
	public String getFullName() {
		return getColor().toString() + ChatColor.BOLD + getName();
	}

	/**
	 * Get the display name for this rank.
	 * @return
	 */
	public String getName() {
		return Utils.Text.capitalize(name());
	}

	public String getChatPrefix() {
		return getColor() + (getRankSymbol().length() > 1 ? ChatColor.BOLD.toString() : "") + getRankSymbol() + getNameColor();
	}
}
