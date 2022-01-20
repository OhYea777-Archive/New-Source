package com.ohyea777.solitarytools.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public interface IModule {

	public String getName();
	
	public void init(JavaPlugin plugin);
	
	public void deInit(JavaPlugin plugin);
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args);
	
}
