package com.ohyea777.ohyeadownloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

import com.ohyea777.ohyeadownloader.Payload.PayloadPlugin;
import com.ohyea777.ohyeadownloader.util.Utils;

public class DownloadManager {

	private final String jsonCheckSite = "http://ohyea777.com/core/OhYeaCore";
	private final String jsonCheck;
	
	public DownloadManager() {
		jsonCheck = readJsonFromUrl(jsonCheckSite);
		checkPlugins();
	}
	
	private void checkPlugins() {
		Payload payload = Utils.getGson().fromJson(jsonCheck, Payload.class);
		
		for (PayloadPlugin p : payload.getPluginsToUpdate()) {
			OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + p.getName() + "] - Is Updating!");
			installPlugin(p);
		}
	}
	
	private void installPlugin(final PayloadPlugin plugin) {
		plugin.deleteOldPlugin();
		Bukkit.getScheduler().runTaskAsynchronously(OhYeaDownloader.getInstance(), new Runnable() {
			@Override
			public void run() {
				downloadPlugin(plugin);
			}
		});
	}
	
	private void downloadPlugin(final PayloadPlugin plugin) {
		try {
			OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + plugin.getName() + "] - Download Has Begun!");
			
			final File file = new File("plugins", plugin.getFileName());
			ReadableByteChannel rbc = Channels.newChannel(plugin.getDownloadUrl().openStream());
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + plugin.getName() + "] - Download Has Finished!");
			
			Bukkit.getScheduler().runTaskLater(OhYeaDownloader.getInstance(), new Runnable() {
				@Override
				public void run() {
					if (file.exists()) {
						try {
							OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + plugin.getName() + "] - Loading Updated Version!");
							Plugin pl = Bukkit.getPluginManager().loadPlugin(file);
							Bukkit.getPluginManager().enablePlugin(pl);
							OhYeaDownloader.getInstance().getLogger().info("Plugin - [" + plugin.getName() + "] - Update Version Loaded and Enabled!");
						} catch (UnknownDependencyException | InvalidPluginException | InvalidDescriptionException e) {
							e.printStackTrace();
							OhYeaDownloader.getInstance().getLogger().severe("Plugin - [" + plugin.getName() + "] - Failed to Load Updated Version!");
						}
					}
				}
			}, 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readJsonFromUrl(String url) {
		try {
			InputStream is = new URL(url).openStream();
			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				return readAll(rd);
			} finally {
				is.close();
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private String readAll(Reader rd) {
		StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
