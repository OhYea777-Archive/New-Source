package com.ohyea777.craftcast;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftCast extends JavaPlugin {

	private static CraftCast instance;
	private String nowPlaying = "";

	@Override
	public void onEnable() {
		instance = this;

		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			getLogger().info("Saving Default Config");
			saveDefaultConfig();
		}
		getConfig().options().copyDefaults(true);

		reload();
	}

	public static CraftCast getInstance() {
		return instance;
	}

	public void setNowPlaying(String playing) {
		nowPlaying = playing;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equals("craftcast")) {
			if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("cc.reload")) {
					reloadConfig();
					reload();
					sender.sendMessage(Messages.RELOAD.get());
					return true;
				} else {
					sender.sendMessage(Messages.NO_PERM.get());
					return true;
				}
			} else if (args.length == 1 && args[0].equalsIgnoreCase("about")) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "Plugin by &3OhYea777&7!"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "Version&8: &3" + getDescription().getVersion() + "&7!"));
				return true;
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "To Check the Song Playing: &3/song"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "To Request a Song: &3/request <song>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "For Info About the Plugin: &3/cc about"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.PREFIX.getNoPrefixColoured() + "To Reload the Plugin's Configs: &3/cc reload"));
				return true;
			}
		} else if (command.getName().equals("song")) {
			if (sender.hasPermission("cc.song")) {
				sender.sendMessage(Messages.BROADCAST.sub("{Playing}", nowPlaying));
				return true;
			} else {
				sender.sendMessage(Messages.NO_PERM.get());
				return true;
			}
		} else if (command.getName().equals("request")) {
			if (sender.hasPermission("cc.request")) {
				if (args.length > 0) {
					getServer().broadcast(Messages.REQUEST_RECEIVE.sub("{Player}", sender.getName(), "{Request}", get(args)), "cc.request.receive");
					sender.sendMessage(Messages.REQUEST.sub("{Request}", get(args)));
					return true;
				} else {

					return true;
				}
			} else {
				sender.sendMessage(Messages.NO_PERM.get());
				return true;
			}
		}

		return false;
	}

	private String get(String... args) {
		StringBuilder s = new StringBuilder();

		for (String string : args) {
			s.append(string).append(" ");
		}

		return s.toString().trim();
	}

	private void reload() {
		getServer().getScheduler().cancelTasks(getInstance());
		getServer().getScheduler().scheduleSyncRepeatingTask(getInstance(), new NowPlaying(), 1, Messages.CHECK_INTERVAL.getAsInt());
	}

}
