package com.ohyea777.ohyeacore;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldGuardManager {

	private Plugin worldGuard;
	
	public WorldGuardManager() {
		init();
	}
	
	private void init() {
		if (isWorldGuardEnabled()) {
			worldGuard = Bukkit.getPluginManager().getPlugin("WorldGuard");
		}
	}
	
	public boolean isWorldGuardEnabled() {
		return Bukkit.getPluginManager().isPluginEnabled("WorldGuard");
	}
	
	public boolean canPlayerBuild(Player player, Location location) {
		if (isWorldGuardEnabled()) {
			Object obj = get("canBuild", new Class[] { player.getClass(), location.getClass() }, player, location);
			if (obj != null && obj instanceof Boolean)
				return ((Boolean) obj).booleanValue();
		}
		return true;
	}
	
	public Plugin getWorldGuard() {
		if (isWorldGuardEnabled()) {
			return worldGuard;
		}
		return null;
	}
	
	private Object get(String method, Class<?>[] classes, Object... args) {
		if (isWorldGuardEnabled() && getWorldGuard() != null) {
			try {
				Method meth = getWorldGuard().getClass().getDeclaredMethod(method, classes);
				meth.setAccessible(true);
				if (classes.length == args.length)
					return meth.invoke(getWorldGuard(), args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
