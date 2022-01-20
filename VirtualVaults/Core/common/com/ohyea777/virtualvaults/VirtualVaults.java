package com.ohyea777.virtualvaults;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.virtualvaults.command.VaultCommandDefault;
import com.ohyea777.virtualvaults.command.VaultCommandRegistry;
import com.ohyea777.virtualvaults.command.VaultCommandRename;
import com.ohyea777.virtualvaults.listener.VaultsListener;
import com.ohyea777.virtualvaults.util.VaultLoader;
import com.ohyea777.virtualvaults.util.VaultRegistry;

public class VirtualVaults extends JavaPlugin {

	private static VirtualVaults instance;
	private VaultLoader loader;
	private VaultRegistry vault;

	@Override
	public void onEnable() {
		instance = this;
		vault = new VaultRegistry();

		if (!vault.setupEconomy()) {
			VirtualVaults.getVaultsInstance().getLogger().severe("Failed to Load Economy! Disabling Plugin...");
			Bukkit.getScheduler().runTaskLater(VirtualVaults.getVaultsInstance(), new Runnable() {
				@Override
				public void run() {
					Bukkit.getPluginManager().disablePlugin(VirtualVaults.getVaultsInstance());
				}
			}, 1);
		} else {
			loader = new VaultLoader();
			
			saveDefaultConfig();
			getConfig().options().copyDefaults(true);

			getServer().getPluginManager().registerEvents(new VaultsListener(), instance);
			
			VaultCommandRegistry.register(new VaultCommandDefault());
			VaultCommandRegistry.register(new VaultCommandRename());
		}
	}

	public static VirtualVaults getVaultsInstance() {
		return instance;
	}

	public VaultLoader getLoader() {
		return loader;
	}

	public VaultRegistry getVault() {
		return vault;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return VaultCommandRegistry.onCommand(sender, cmd, label, args);
	}

}
