package com.ohyea777.virtualvaults.player;

import static com.ohyea777.virtualvaults.util.VaultMessages.BUY_VAULT;
import static com.ohyea777.virtualvaults.util.VaultMessages.ECON_FAIL;
import static com.ohyea777.virtualvaults.util.VaultMessages.NO_PREM_BUY;
import static com.ohyea777.virtualvaults.util.VaultMessages.PERM_BUY;
import static com.ohyea777.virtualvaults.util.VaultMessages.PERM_UPGRADE;
import static com.ohyea777.virtualvaults.util.VaultMessages.PRICE_BUY;
import static com.ohyea777.virtualvaults.util.VaultMessages.PRICE_UPGRADE;
import static com.ohyea777.virtualvaults.util.VaultMessages.TOO_POOR;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT_UPGRADED;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_NONE;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import com.ohyea777.virtualvaults.VirtualVaults;
import com.ohyea777.virtualvaults.inventory.Vault;

public class VaultUser {

	private String Name;
	private Integer DefaultVault;
	private Map<Integer, Vault> Vaults;
	private Map<String, Integer> NamedVaults;

	public VaultUser(String name) {
		Name = name;
		DefaultVault = 0;
		Vaults = new HashMap<Integer, Vault>();
		NamedVaults = new HashMap<String, Integer>();
	}

	public VaultUser(Player player) {
		this(player.getName());
	}

	public VaultUser() { }

	public String getUsername() {
		return Name;
	}

	public Vault rename(String vault, String name) {
		if (containsVault(vault) && !containsVault(name)) {
			return rename(getVaultIndex(vault), name);
		}
		return null;
	}
	
	public Vault rename(int id, String name) {
		if (containsVault(id) && !containsVault(name)) {
			Map<Integer, String> map = invert(NamedVaults);
			if (map.containsKey(id)) {
				NamedVaults.remove(map.get(id));
				NamedVaults.put(name, id);
				save();
				return Vaults.get(id);
			}
		}
		return null;
	}
	
	private <V, K> Map<V, K> invert(Map<K, V> map) {
	    Map<V, K> inv = new HashMap<V, K>();

	    for (Entry<K, V> entry : map.entrySet())
	        inv.put(entry.getValue(), entry.getKey());

	    return inv;
	}
	
	public boolean containsVault(int id) {
		return Vaults.containsKey(id);
	}

	public boolean hasVaults() {
		return !Vaults.isEmpty();
	}
	
	public boolean containsVault(String name) {
		for (String s : NamedVaults.keySet()) {
			if (s.equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	public Vault setDefaultVault(String name) {
		if (containsVault(name)) {
			return setDefaultVault(getVaultIndex(name));
		}
		return null;
	}
	
	public Vault setDefaultVault(int id) {
		if (containsVault(id)) {
			DefaultVault = id;
			save();
		}
		return getDefaultVault();
	}
	
	public int getVaultIndex(String name) {
		for (String s : NamedVaults.keySet()) {
			if (s.equalsIgnoreCase(name))
				return NamedVaults.get(s);
		}
		return getDefaultVaultIndex();
	}

	public Integer getDefaultVaultIndex() {
		if (DefaultVault == null) {
			DefaultVault = 0;
			save();
		}
		return DefaultVault;
	}

	public Vault getDefaultVault() {
		if (hasVaults() && containsVault(getDefaultVaultIndex())) {
			return Vaults.get(getDefaultVaultIndex());
		}
		return null;
	}

	public Vault getVault(String name) {
		if (containsVault(name)) {
			for (String s : NamedVaults.keySet()) {
				if (s.equalsIgnoreCase(name))
					return Vaults.get(NamedVaults.get(s));
			}
		}
		return null;
	}
	
	public Vault getVault(int id) {
		if (containsVault(id)) {
			return Vaults.get(id);
		}
		return null;
	}

	public Vault create() {
		return create("Normal" + (Vaults.size() + 1));
	}

	public Vault create(String name) {
		if (!NamedVaults.containsKey(name)) {
			Vault vault = new Vault(name);
			NamedVaults.put(name, NamedVaults.size());
			Vaults.put(Vaults.size(), vault);
			save();
			return vault;
		}
		return null;
	}

	public boolean upgrade() {
		return upgrade(getDefaultVaultIndex());
	}

	public boolean upgrade(String name) {
		return NamedVaults.containsKey(name) && upgrade(NamedVaults.get(name));
	}

	public boolean upgrade(int i) {
		if (Vaults.containsKey(i) && Vaults.get(i).getSize() <= InventoryType.CHEST.getDefaultSize()) {
			Vaults.get(i).setSize(InventoryType.CHEST.getDefaultSize() * 2);
			save();
			return true;
		}
		return false;
	}

	public boolean isUpgraded() {
		return isUpgraded(getDefaultVaultIndex());
	}

	public boolean isUpgraded(String name) {
		return NamedVaults.containsKey(name) && isUpgraded(NamedVaults.get(name));
	}

	public boolean isUpgraded(int i) {
		return Vaults.containsKey(i) && Vaults.get(i).getSize() > InventoryType.CHEST.getDefaultSize();
	}

	public void save() {
		VirtualVaults.getVaultsInstance().getLoader().save(getUsername());
	}

	public boolean canBuyVault() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		Player p = Bukkit.getPlayer(Name);
		if (p.hasPermission(PERM_BUY.getNoPrefix())) {
			if (getEconomy().has(p.getName(), PRICE_BUY.getAsDouble())) {
				return true;
			} else {
				p.sendMessage(TOO_POOR.sub(formatter.format(PRICE_BUY.getAsDouble())));
				return false;
			}
		} else {
			p.sendMessage(NO_PREM_BUY.get());
			return false;
		}
	}

	public boolean buyVault() {
		Player p = Bukkit.getPlayer(Name);
		if (canBuyVault()) {
			if (getEconomy().withdrawPlayer(p.getName(), PRICE_BUY.getAsDouble()).transactionSuccess()) {
				p.sendMessage(BUY_VAULT.sub(create()));
				return true;
			} else {
				p.sendMessage(ECON_FAIL.get());
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean canUpgradeVault() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		Player p = Bukkit.getPlayer(Name);
		if (p.hasPermission(PERM_UPGRADE.getNoPrefix())) {
			if (getEconomy().has(p.getName(), PRICE_UPGRADE.getAsDouble())) {
				if (hasVaults()) {
					if (isUpgraded()) {
						p.sendMessage(UP_VAULT_UPGRADED.sub(getDefaultVault()));
						return false;
					} else {
						return true;
					}
				} else {
					p.sendMessage(VAULT_NONE.get());
					return false;
				}
			} else {
				p.sendMessage(TOO_POOR.sub(formatter.format(PRICE_UPGRADE.getAsDouble())));
				return false;
			}
		} else {
			p.sendMessage(NO_PREM_BUY.get());
			return false;
		}
	}

	public boolean upgradeVault() {
		Player p = Bukkit.getPlayer(Name);
		if (canUpgradeVault()) {
			if (getEconomy().withdrawPlayer(p.getName(), PRICE_UPGRADE.getAsDouble()).transactionSuccess()) {
				p.sendMessage(UP_VAULT.sub(getDefaultVault()));
				upgrade();
				return true;
			} else {
				p.sendMessage(ECON_FAIL.get());
				return false;
			}
		} else {
			return false;
		}
	}

	private Economy getEconomy() {
		return VirtualVaults.getVaultsInstance().getVault().getEconomy();
	}

}
