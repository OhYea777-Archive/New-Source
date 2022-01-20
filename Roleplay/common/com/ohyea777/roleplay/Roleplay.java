package com.ohyea777.roleplay;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.roleplay.api.RoleplayRegistry;
import com.ohyea777.roleplay.items.ItemEventHandler;
import com.ohyea777.roleplay.items.LockPick;

public class Roleplay extends JavaPlugin {

	private static Roleplay instance;

	@Override
	public void onEnable() {
		instance = this;

		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists())
			saveDefaultConfig();
		reloadConfig();

		getServer().getPluginManager().registerEvents(new ItemEventHandler(), instance);

		registerDefaults();
	}

	@Override
	public void onDisable() {
		RoleplayRegistry.resetItems();
	}

	public static Roleplay getInstance() {
		return instance;
	}

	private void registerDefaults() {
		RoleplayRegistry.registerItem(new LockPick());
	}

}
