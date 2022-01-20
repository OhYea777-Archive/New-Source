package com.ohyea777.drugs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.drugs.listener.DrugListener;
import com.ohyea777.drugs.util.DrugLoader;
import com.ohyea777.drugs.util.Messages;
import com.ohyea777.drugs.util.OldConfigConvert;
import com.ohyea777.drugs.util.Utils;

public class Drugs extends JavaPlugin {

	private static Drugs instance;
	private static DrugLoader loader;

	@Override
	public void onEnable() {
		instance = this;

		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			getLogger().info("Saving Default Config");
			saveDefaultConfig();
		} else {
			if (getConfig().getConfigurationSection("Drugs") != null) {
				getLogger().info("Converting Old Config");
				new OldConfigConvert();
			}
		}
		
		getLogger().info("Saving Default Drugs JSON");
		if (!Utils.loaderExists()) {
			Utils.saveDefaultLoader();
		} else {
			loader = Utils.loadDrugLoader();
		}

		getServer().getPluginManager().registerEvents(new DrugListener(), instance);

		getLogger().info(String.format("Version: %s by OhYea777 Has Been Loaded!", getDescription().getVersion()));
	}

	@Override
	public void onDisable() {
		getLogger().info(String.format("Version: %s by OhYea777 Has Been Disabled!", getDescription().getVersion()));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if (sender.hasPermission("drugs.reload")) {
				reload();
				loader = null;
				loader = Utils.loadDrugLoader();
				sender.sendMessage(Messages.RELOAD.toString());
				return true;
			} else {
				sender.sendMessage(Messages.NO_PERM.toString());
				return false;
			}
		} else if (args.length == 1 && args[0].equalsIgnoreCase("about")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX + "&fPlugin by &3OhYea777&f!"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX + "&fVersion: &3" + getDescription().getVersion() + "&f!"));
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX + "&fFor Info About the Plugin: &3/drugs about"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX + "&fTo Reload the Plugin's Configs: &3/drugs reload"));
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		if (args.length == 1) {
			String s = args[0].toLowerCase();
			if ((s.length() <= "about".length() || s.length() <= "reload".length()) && s.length() > 0) {
				if ("about".substring(0, s.length()).equals(s)) {
					list.add("about");
				} else if ("reload".substring(0, s.length()).equals(s)) {
					list.add("reload");
				} else {
					list.add("about");
					list.add("reload");
				}
			}  else {
				list.add("about");
				list.add("reload");
			}
		}
		return list;
	}
	
	private void reload() {
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			getLogger().info("Saving Default Config");
			saveDefaultConfig();
		} else {
			if (getConfig().getConfigurationSection("Drugs") != null) {
				getLogger().info("Converting Old Config");
				new OldConfigConvert();
			} else {
				reloadConfig();
			}
		}
	}
	
	public static Drugs getInstance() {
		return instance;
	}
	
	public static DrugLoader getLoader() {
		return loader;
	}
	
	public static void setLoader(DrugLoader loader) {
		Drugs.loader = loader;
	}
	
}
