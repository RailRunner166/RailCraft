package me.railrunner16.railcraft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EnumRank {
	MU("μ", ChatColor.LIGHT_PURPLE, 5, 3, 0, 0),
	PHI("Φ", ChatColor.BLUE, 5, 5, 1, 10),
	SIGMA("Σ", ChatColor.AQUA, 4, 10, 12, 20),
	GAMMA("Γ", ChatColor.GREEN, 4, 15, 24, 30),
	BETA("β", ChatColor.YELLOW, 3, 20, 96, 40),
	ALPHA("α", ChatColor.RED, 3, 25, 240, 50),
	OMEGA("Ω", ChatColor.DARK_RED, 2, 30, 480, 80),

	THETA("Θ", ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE),
	VOTER("VOTER", ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE), // We don't actually set players to this rank.
	MEDIA("∈", ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE),

	JR_BUILDER("JR BUILDER", ChatColor.YELLOW, ChatColor.YELLOW),
	JR_DEV("JR DEV", ChatColor.AQUA, ChatColor.AQUA),
	JR_MOD("JR MOD", ChatColor.GREEN, ChatColor.GREEN),
	BUILDER("BUILDER", ChatColor.GOLD, ChatColor.YELLOW),
	DEV("DEV", ChatColor.BLUE, ChatColor.BLUE),
	MOD("MOD", ChatColor.DARK_GREEN, ChatColor.GREEN),
	ADMIN("ADMIN", ChatColor.DARK_RED,  ChatColor.RED);

	private final String rankSymbol;
	private final ChatColor color;
	private final ChatColor nameColor;
	private final int tpTime;
	private final int homes;
	private final int hoursNeeded;
	private final int accomplishmentsNeeded;

	public static final EnumRank[] VALUES = values();

	EnumRank(String rankSymbol, ChatColor color, ChatColor nameColor) {
		this(rankSymbol, color, nameColor, 2, 50, -1, -1);
	}

	EnumRank(String rankSymbol, ChatColor color, int tpTime, int homes, int hours, int acc) {
		this(rankSymbol, color, ChatColor.GRAY, tpTime, homes, hours, acc);
	}

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
		return isAtLeast(JR_BUILDER);
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
