package com.ohyea777.gems;

import org.bukkit.plugin.java.JavaPlugin;

public class Gems extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new GemRegistry(), this);
	}
	
}
