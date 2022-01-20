package com.ohyea777.solitarytools.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public enum CommandRegistry {

	INSTANCE;

	private JavaPlugin plugin;
	
	private Map<String, String> commands;

	private CommandRegistry() {
		commands = new HashMap<String, String>();
	}
	
	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void registerCommand(String command, IModule module, String... aliases) {
		if (!commands.containsKey(command)) {
			commands.put(command, module.getName());
			if (getCommandMap().getCommand(command) == null)
				registerCommand(command, aliases);
		}
	}

	public void unRegisterCommand(String command) {
		if (commands.containsKey(command))
			commands.remove(command);
	}

	public void unRegisterAll(IModule module) {
		for (int i = 0; i < commands.size(); i ++) {
			if (commands.values().toArray(new IModule[commands.size()])[i].equals(module)) {
				unRegisterCommand(commands.keySet().toArray(new String[commands.size()])[i]);
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (commands.containsKey(command.getName()) && ModuleRegistry.INSTANCE.getModuleByName(commands.get(command.getName())) != null) {
			return ModuleRegistry.INSTANCE.getModuleByName(commands.get(command.getName())).onCommand(sender, command, label, args);
		} else {
			sender.sendMessage("Unknown command. Type \"help\" for help.");
		}
		return false;
	}

	public void registerCommand(String command, String... aliases) {
		PluginCommand cmd = getCommand(command, plugin);

		cmd.setAliases(Arrays.asList(aliases));
		getCommandMap().register(plugin.getDescription().getName(), cmd);
	}

	private static PluginCommand getCommand(String name, Plugin plugin) {
		PluginCommand command = null;

		try {
			Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			c.setAccessible(true);

			command = c.newInstance(name, plugin);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return command;
	}

	private static CommandMap getCommandMap() {
		CommandMap commandMap = null;

		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);

				commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return commandMap;
	}

}
