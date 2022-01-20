package com.ohyea777.gsonapi;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.gsonapi.libs.org.apache.commons.io.FileUtils;

public abstract class GsonLoadClass <T> {

	private Map<String, T> data;
	private final Class<T> clz;
	
	public GsonLoadClass(Class<T> clz) {
		this.clz = clz;
		data = new ConcurrentHashMap<String, T>();
		loadAll();
	}
	
	public void put(String key, T value) {
		if (!contains(key)) {
			data.put(key, value);
			save(key);
		}
	}
	
	public Set<String> getKeys() {
		return data.keySet();
	}
	
	public T get(String key) {
		if (contains(key))
			return data.get(key);
		return null;
	}
	
	public T remove(String key) {
		if (contains(key))
			return data.remove(key);
		return null;
	}
	
	public boolean contains(String key) {
		return data.containsKey(key);
	}
	
	protected abstract JavaPlugin getPlugin();
	
	protected abstract Boolean defaultSave();
	
	protected void saveAll() {
		for (String s : data.keySet()) {
			save(s);
		}
	}
	
	public void save(final String key) {
		if (defaultSave() == null || !defaultSave() || !contains(key) || getPlugin() == null)
			return;
		Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
			@Override
			public void run() {
				File file = new File(getPlugin().getDataFolder(), "Data" + File.separator + key + ".json");
				file.getParentFile().mkdirs();
				String json = GsonAPI.getGson().toJson(get(key));
				try {
					FileUtils.writeStringToFile(file, json);
				} catch (IOException e) {
					Bukkit.getLogger().severe("[GsonAPI] Failed to Save Json File: '" + file.getName() + "', Stacktrace Follows - ");
					e.printStackTrace();
				}
			}
		});
	}
	
	private void load(String key) {
		if (getPlugin() == null)
			return;
		load(key, new File(getPlugin().getDataFolder(), "Data" + File.separator + key + ".json"));
	}
	
	private void load(final String key, final File file) {
		if (!file.exists() || file.isDirectory())
			return;
		Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
			@Override
			public void run() {
				try {
					String json = FileUtils.readFileToString(file);
					put(key, GsonAPI.getGson().fromJson(json, clz));
				} catch (IOException e) {
					Bukkit.getLogger().severe("[GsonAPI] Failed to Load Json File: '" + file.getName() + "', Stacktrace Follows - ");
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void loadAll() {
		if (getPlugin() == null || !defaultSave())
			return;
		File file = new File(getPlugin().getDataFolder(), "Data");
		file.mkdirs();
		if (file.listFiles(new JsonFilter()) != null) {
			for (File f : file.listFiles(new JsonFilter())) {
				load(f.getName().replace(".json", ""));
			}
		}
	}
	
	private static class JsonFilter implements FileFilter {

		@Override
		public boolean accept(File pathname) {
			return !pathname.isDirectory() && pathname.getName().endsWith(".json");
		}
		
	}
	
}
