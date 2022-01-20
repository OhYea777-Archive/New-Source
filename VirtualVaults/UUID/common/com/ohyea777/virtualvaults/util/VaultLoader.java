package com.ohyea777.virtualvaults.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.gsonapi.GsonLoadClass;
import com.ohyea777.virtualvaults.VirtualVaults;
import com.ohyea777.virtualvaults.player.VaultUser;

public class VaultLoader extends GsonLoadClass<VaultUser> {

	private Map<String, Integer> inventories;
	
	public VaultLoader() {
		super(VaultUser.class);
		inventories = new ConcurrentHashMap<String, Integer>();
	}
	
	public boolean contains(UUID uuid) {
		return contains(uuid.toString());
	}
	
	public void put(Player player, VaultUser user) {
		put(player.getUniqueId().toString(), user);
	}
	
	public VaultUser get(Player player) {
		return get(player.getUniqueId());
	}
	
	public VaultUser get(UUID uuid) {
		return get(uuid.toString());
	}
	
	public void saveVault(UUID uuid) {
		save(uuid.toString());
	}
	
	public void saveVault(Player player) {
		saveVault(player.getUniqueId());
	}

	@Override
	protected JavaPlugin getPlugin() {
		return VirtualVaults.getVaultsInstance();
	}

	@Override
	protected Boolean defaultSave() {
		return true;
	}
	
	public void add(String string, int id) {
		if (!inventories.containsKey(string)) {
			inventories.put(string, id);
		}
	}
	
	public Integer removeInv(String string) {
		return inventories.remove(string);
	}

}
