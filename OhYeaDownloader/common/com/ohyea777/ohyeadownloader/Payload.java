package com.ohyea777.ohyeadownloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.ohyea777.ohyeadownloader.util.PluginUtils;

public class Payload {
	
	private List<PayloadPlugin> subPlugins;
	
	public List<PayloadPlugin> getPluginsToUpdate() {
		List<PayloadPlugin> plugins = new ArrayList<PayloadPlugin>();
		for (PayloadPlugin p : subPlugins) {
			if (p.needsToUpdate())
				plugins.add(p);
		}
		return plugins;
	}
	
	public class PayloadPlugin {
		
		private String name;
		private URL downloadLocation;
		private String version;
		
		public String getName() {
			return name;
		}
		
		public String getFileName() {
			return name + "-" + version + ".jar";
		}
		
		public URL getDownloadUrl() {
			try {
				return new URL(downloadLocation, getFileName());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public boolean needsToUpdate() {
			if (Bukkit.getPluginManager().isPluginEnabled(name)) {
				return !Bukkit.getPluginManager().getPlugin(name).getDescription().getVersion().equals(version);
			}
			return true;
		}
		
		public void deleteOldPlugin() {
			if (Bukkit.getPluginManager().isPluginEnabled(name)) {
				Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
				final File file = new File("plugins", name + "-" + plugin.getDescription().getVersion() + ".jar");
				if (file.exists() && PluginUtils.unload(Bukkit.getPluginManager().getPlugin(getName()))) {
					file.delete();
					OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + name + "] - has been unloaded for update");
				}
			}
		}
		
	}
	
}
