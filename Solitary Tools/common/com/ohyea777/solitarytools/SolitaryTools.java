package com.ohyea777.solitarytools;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.solitarytools.api.CommandRegistry;
import com.ohyea777.solitarytools.api.ModuleRegistry;

public class SolitaryTools extends JavaPlugin {

	public static SolitaryTools INSTANCE;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		File modules = new File(getDataFolder(), "Modules");
		File scripts = new File(getDataFolder(), "Scripts");
		
		modules.mkdirs();
		scripts.mkdirs();
		
		ModuleRegistry.INSTANCE.preInit(modules);
		CommandRegistry.INSTANCE.setPlugin(INSTANCE);
		ModuleScriptLoader.INSTANCE.preInit(scripts);
		
		getServer().getScheduler().runTaskLater(this, new Runnable() {
			@Override
			public void run() {
				ModuleRegistry.INSTANCE.init(INSTANCE);
			}
		}, 1);
	}
	
	@Override
	public void onDisable() {
		ModuleRegistry.INSTANCE.deInit(INSTANCE, new File(getDataFolder(), "Modules"));
		ModuleScriptLoader.INSTANCE.deInit();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("sreload") && sender.isOp()) {
			File modules = new File(getDataFolder(), "Modules");
			File scripts = new File(getDataFolder(), "Scripts");
			
			modules.mkdirs();
			scripts.mkdirs();
			
			ModuleRegistry.INSTANCE.deInit(INSTANCE, modules);
			ModuleScriptLoader.INSTANCE.deInit();
			
			ModuleRegistry.INSTANCE.preInit(modules);
			ModuleScriptLoader.INSTANCE.preInit(scripts);
			ModuleRegistry.INSTANCE.init(INSTANCE);
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6SolitaryTools&8] &7Reloaded!"));
			
			return false;
		} else {
			System.out.println("Dafuq?");
		}
		
		return CommandRegistry.INSTANCE.onCommand(sender, command, label, args);
	}
	
}
