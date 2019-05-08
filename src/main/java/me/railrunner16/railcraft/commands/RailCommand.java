package me.railrunner16.railcraft.commands;

import me.railrunner16.railcraft.RailCraft;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class RailCommand {
	public String label;
	public String rawUsage;
	public int minArgs;
	public int maxArgs;
	public boolean requiresOp;
	public List<String> aliases;

	public RailCommand(String label, String usageParam, String... alias) {
		this(label, usageParam, 0, 0, false, alias);
	}

	public RailCommand(String label, String usageParam, int maxArgs, int minArgs, String... alias) {
		this(label, usageParam, maxArgs, minArgs, false, alias);
	}

	public RailCommand(String label, String usageParam, boolean requiresOp, String... alias) {
		this(label, usageParam, 0, 0, requiresOp, alias);
	}

	public RailCommand(String label, String usageParam, int minArgs, int maxArgs, boolean requiresOp, String... aliases) {
		this.rawUsage = usageParam;
		this.label = label;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.requiresOp = requiresOp;
		this.aliases = Arrays.asList(aliases);

		RailCraft.getInstance().getCommand(label).setExecutor(new Commands());
		RailCraft.commands.put(label, this);

//		for (String alias : this.aliases) {
//			Commands.aliases.put(alias, this);
//		}
	}

	public String getUsage() {
		return "Usage: " + rawUsage;
	}

	public abstract void run(CommandSender sender, String[] args);
}
