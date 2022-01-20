package com.ohyea777.ohyeadownloader;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class OhYeaDownloader extends JavaPlugin implements Listener {

	private static OhYeaDownloader instance;
	protected DownloadManager downloadManager;

	@Override
	public void onEnable() {
		instance = this;
		
		getServer().getScheduler().runTaskLater(getInstance(), new Runnable() {
			@Override
			public void run() {
				downloadManager = new DownloadManager();
			}
		}, 1);
	}
	
	public static OhYeaDownloader getInstance() {
		return instance;
	}

}
