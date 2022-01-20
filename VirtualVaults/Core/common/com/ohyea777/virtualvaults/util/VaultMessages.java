package com.ohyea777.virtualvaults.util;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.ohyea777.virtualvaults.VirtualVaults;
import com.ohyea777.virtualvaults.inventory.Vault;

public enum VaultMessages implements IMessageGet {

	PREFIX("Messages.Prefix"),
	BUY_VAULT_SIGN("Signs.BuyVault.0"),
	BUY_VAULT_SIGN_1("Signs.BuyVault.1"),
	BUY_VAULT_SIGN_2("Signs.BuyVault.2"),
	BUY_VAULT_SIGN_3("Signs.BuyVault.3"),
	BUY_VAULT("Messages.BuyVault"),
	UP_VAULT_SIGN("Signs.UpVault.0"),
	UP_VAULT_SIGN_1("Signs.UpVault.1"),
	UP_VAULT_SIGN_2("Signs.UpVault.2"),
	UP_VAULT_SIGN_3("Signs.UpVault.3"),
	UP_VAULT("Messages.UpVault"),
	UP_VAULT_UPGRADED("Messages.UpVault_Upgraded"),
	VAULT_SIGN("Signs.Vault.0"),
	VAULT_SIGN_1("Signs.Vault.1"),
	VAULT_SIGN_2("Signs.Vault.2"),
	VAULT_SIGN_3("Signs.Vault.3"),
	VAULT_NONE("Messages.Vault_None"),
	VAULT_SAVED("Messages.Vault_Saved"),
	TOO_POOR("Messages.TooPoor"),
	ECON_FAIL("Messages.EconFail"),
	NO_PERM("Messages.NoPerm"),
	NO_PREM_PLACE("Messages.NoPerm_Place"),
	NO_PREM_BUY("Messages.NoPerm_Buy"),
	NO_PREM_UP("Messages.NoPerm_Up"),
	NO_PREM_OPEN("Messages.NoPerm_Open"),
	PERM_PLACE("Permissions.Place"),
	PERM_BUY("Permissions.Buy"),
	PERM_UPGRADE("Permissions.Up"),
	PERM_OPEN("Permissions.Open"),
	PRICE_BUY("Prices.Buy"),
	PRICE_UPGRADE("Prices.Up");
	
	private String loc;
	
	private VaultMessages(String s) {
		loc = s;
	}
	
	public String sub(Object... args) {
		String s = get();
		for (int i = 0; i < args.length; i ++) {
			s = s.replace("{" + i + "}", match(args[i]));
		}
		return _(s);
	}
	
	public String get() {
		return _(PREFIX.getNoPrefixColoured() + (VirtualVaults.getVaultsInstance().getConfig().getString(loc) != null ? VirtualVaults.getVaultsInstance().getConfig().getString(loc) : "")); 
	}
	
	public String getNoPrefix() {
		return ChatColor.stripColor(_((VirtualVaults.getVaultsInstance().getConfig().getString(loc) != null ? VirtualVaults.getVaultsInstance().getConfig().getString(loc) : "")));
	}
	
	public String getNoPrefixColoured() {
		return _((VirtualVaults.getVaultsInstance().getConfig().getString(loc) != null ? VirtualVaults.getVaultsInstance().getConfig().getString(loc) : ""));
	}
	
	public String match(Object o) {
		if (o instanceof Vault) {
			return ((Vault) o).getName();
		} else if (o instanceof World) {
			return ((World) o).getName();
		} else if (o instanceof Player) {
			return ((Player) o).getName();
		} else {
			return o.toString();
		}
	}
	
	public double getAsDouble() {
		return VirtualVaults.getVaultsInstance().getConfig().get(loc) != null && (VirtualVaults.getVaultsInstance().getConfig().get(loc) instanceof Double || VirtualVaults.getVaultsInstance().getConfig().get(loc) instanceof Integer) ? VirtualVaults.getVaultsInstance().getConfig().getDouble(loc) : 1000;
	}
	
	public String _(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
