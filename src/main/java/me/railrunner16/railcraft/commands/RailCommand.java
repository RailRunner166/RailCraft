package me.railrunner16.railcraft.commands;

import me.railrunner16.railcraft.EnumRank;
import me.railrunner16.railcraft.RailCraft;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class RailCommand {
	public String label;
	public String rawUsage;
	public int minArgs;
	public int maxArgs;
	public EnumRank minRank;
	public List<String> aliases;

	public RailCommand(String label, String usageParam, String... alias) {
		this(label, usageParam, 0, 0, alias);
	}

	public RailCommand(String label, String usageParam, int maxArgs, int minArgs, String... alias) {
		this(label, usageParam, maxArgs, minArgs, EnumRank.PLAYER, alias);
	}

	public RailCommand(String label, String usageParam, EnumRank minRank, String... alias) {
		this(label, usageParam, 0, 0, minRank, alias);
	}

	public RailCommand(String label, String usageParam, int minArgs, int maxArgs, EnumRank minRank, String... aliases) {
		this.rawUsage = usageParam;
		this.label = label;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.minRank = minRank;
		this.aliases = Arrays.asList(aliases);

		RailCraft.getInstance().getCommand(label).setExecutor(new Commands());
		RailCraft.commands.put(label, this);
	}

	public String getUsage() {
		return "Usage: " + rawUsage;
	}

	public abstract void run(CommandSender sender, String[] args);
}
