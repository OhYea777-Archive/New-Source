package com.ohyea777.solitarytools.api;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public enum ModuleRegistry {

	INSTANCE;
	
	private Map<String, IModule> modules;
	
	private ModuleRegistry() {
		modules = new HashMap<String, IModule>();
	}
	
	public void preInit(File file) {
		loadClasses(file);
	}
	
	private void loadClasses(File file) {
		try {
			
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };
			
			@SuppressWarnings("resource")
			ClassLoader loader = new URLClassLoader(urls, getClass().getClassLoader());
			for (File f : file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File clz, String name) {
					return name.endsWith(".class");
				}
			})) {
				registerModule(loader.loadClass(f.getName().replace(".class", "")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(JavaPlugin plugin) {
		for (IModule module : modules.values()) {
			module.init(plugin);
		}
	}
	
	public void deInit(JavaPlugin plugin, File file) {
		HandlerList.unregisterAll(plugin);
		
		for (IModule module : modules.values()) {
			module.deInit(plugin);
		}
		
		modules.clear();
	}
	
	public IModule getModuleByName(String name) {
		if (modules.containsKey(name)) {
			return modules.get(name);
		}
		return null;
	}
	
	public void registerModule(String name, IModule module) {
		if (!modules.containsKey(name))
			modules.put(name, module);
	}
	
	public void registerModule(Class<?> clz) {
		if (IModule.class.isAssignableFrom(clz)) {
			try {
				IModule module = (IModule) clz.newInstance();
				registerModule(module.getName(), module);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
