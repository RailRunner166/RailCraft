# RailCraft
An simple, easy to use survival plugin for [Paper Spigot](https://papermc.io).
You must have Java 8 to run this plugin.

## Development
### Building A Jar:
Use IntelliJ to run the Maven `package` goal, or run `mvn clean package`.
The JAR can then be found in the `target` directory.

### Testing:
Soon there will be tests, make sure to write and run tests for any utilities you create.

### Coding:
#### Making a Command:
A basic command goes as follows:
```java
package me.railrunner16.railcraft.commands.player;

import me.railrunner16.railcraft.commands.RailCommand;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

public class PingCommand extends RailCommand {
	public PingCommand() {
		super("ping", "/ping");
	}
	
	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		p.sendMessage("Pong!");
	}
}
```

Then, you need to register it in the main plugin class' `registerCommands` method, by calling its constructor. A command's constructor should contain __**NO PARAMETERS**__
