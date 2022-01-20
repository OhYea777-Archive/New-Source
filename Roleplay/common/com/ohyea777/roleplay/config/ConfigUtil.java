package com.ohyea777.roleplay.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.ohyea777.roleplay.Roleplay;

public class ConfigUtil {
	
	public static FileConfiguration getConfig() {
		return Roleplay.getInstance().getConfig();
	}
	
	public static String getString(String loc, String def) {
		return getString(loc, def, ConfigSection.MESSAGES);
	}
	
	public static String getString(String loc, String def, ConfigSection section) {
		ConfigurationSection config = getConfig().getConfigurationSection(section.getPath());
		
		if (config == null || config.contains(loc) || config.isString(loc)) {
			if (config == null)
				config = getConfig().createSection(section.getPath());
			config.set(loc, def);
			Roleplay.getInstance().saveConfig();
		}
		
		return config.getString(loc);
	}
	
	public static String getStringFormatted(String loc, String def) {
		return format(getString(loc, def, ConfigSection.MESSAGES));
	}
	
	public static String getStringFormatted(String loc, String def, ConfigSection section) {
		return format(getString(loc, def, section));
	}
	
	private static String format(String s) {
		return _(s.replace("{prefix}", getString("Prefix", "&8[&6Roleplay&8]&7", ConfigSection.OPTIONS)));
	}
	
	public static String _(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
