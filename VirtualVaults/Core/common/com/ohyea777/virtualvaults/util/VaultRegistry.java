package com.ohyea777.virtualvaults.util;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;

import com.ohyea777.virtualvaults.VirtualVaults;

public class VaultRegistry {

	private Economy economy;

	public boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = VirtualVaults.getVaultsInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return (economy != null);
	}
	
	public Economy getEconomy() {
		return economy;
	}

}
