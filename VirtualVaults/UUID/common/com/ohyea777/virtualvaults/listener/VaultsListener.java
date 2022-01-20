package com.ohyea777.virtualvaults.listener;

import static com.ohyea777.virtualvaults.util.VaultMessages.BUY_VAULT_SIGN;
import static com.ohyea777.virtualvaults.util.VaultMessages.BUY_VAULT_SIGN_1;
import static com.ohyea777.virtualvaults.util.VaultMessages.BUY_VAULT_SIGN_2;
import static com.ohyea777.virtualvaults.util.VaultMessages.BUY_VAULT_SIGN_3;
import static com.ohyea777.virtualvaults.util.VaultMessages.NO_PREM_PLACE;
import static com.ohyea777.virtualvaults.util.VaultMessages.PERM_PLACE;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT_SIGN;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT_SIGN_1;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT_SIGN_2;
import static com.ohyea777.virtualvaults.util.VaultMessages.UP_VAULT_SIGN_3;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_NONE;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_SIGN;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_SIGN_1;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_SIGN_2;
import static com.ohyea777.virtualvaults.util.VaultMessages.VAULT_SIGN_3;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ohyea777.virtualvaults.VirtualVaults;
import com.ohyea777.virtualvaults.player.VaultUser;
import com.ohyea777.virtualvaults.util.VaultLoader;
import com.ohyea777.virtualvaults.util.VaultMessages;

public class VaultsListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!VirtualVaults.getVaultsInstance().getLoader().contains(event.getPlayer().getUniqueId()))
			VirtualVaults.getVaultsInstance().getLoader().put(event.getPlayer(), new VaultUser(event.getPlayer()));
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Integer id = getLoader().removeInv(event.getPlayer().getName() + ":" + event.getInventory().getTitle());
		if (id != null) {
			getLoader().get(event.getPlayer().getUniqueId()).getVault(id).setItems(Arrays.asList(event.getInventory().getContents()));
			getLoader().get(event.getPlayer().getUniqueId()).save();
			((Player) event.getPlayer()).sendMessage(VaultMessages.VAULT_SAVED.sub(getLoader().get(event.getPlayer().getUniqueId()).getVault(id)));
			((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.CHEST_CLOSE, 1F, 1F);
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (event.getLine(0) == null || event.getLine(0).isEmpty())
			return;
		
		if (event.getLine(0).equals(BUY_VAULT_SIGN.getNoPrefix())) {
			if (event.getPlayer().hasPermission(PERM_PLACE.getNoPrefix())) {
				if (!event.getLine(1).isEmpty() || !event.getLine(2).isEmpty() || !event.getLine(3).isEmpty()) {
					event.setLine(0, BUY_VAULT_SIGN.getNoPrefixColoured());
				} else {
					event.setLine(0, BUY_VAULT_SIGN.getNoPrefixColoured());
					event.setLine(1, BUY_VAULT_SIGN_1.getNoPrefixColoured());
					event.setLine(2, BUY_VAULT_SIGN_2.getNoPrefixColoured());
					event.setLine(3, BUY_VAULT_SIGN_3.getNoPrefixColoured());
				}
			} else {
				event.getPlayer().sendMessage(NO_PREM_PLACE.get());
				event.setCancelled(true);
				event.getBlock().breakNaturally();
			}
		} else if (event.getLine(0).equals(UP_VAULT_SIGN.getNoPrefix())) {
			if (event.getPlayer().hasPermission(PERM_PLACE.getNoPrefix())) {
				if (!event.getLine(1).isEmpty() || !event.getLine(2).isEmpty() || !event.getLine(3).isEmpty()) {
					event.setLine(0, UP_VAULT_SIGN.getNoPrefixColoured());
				} else {
					event.setLine(0, UP_VAULT_SIGN.getNoPrefixColoured());
					event.setLine(1, UP_VAULT_SIGN_1.getNoPrefixColoured());
					event.setLine(2, UP_VAULT_SIGN_2.getNoPrefixColoured());
					event.setLine(3, UP_VAULT_SIGN_3.getNoPrefixColoured());
				}
			} else {
				event.getPlayer().sendMessage(NO_PREM_PLACE.get());
				event.setCancelled(true);
				event.getBlock().breakNaturally();
			}
		} else if (event.getLine(0).equals(VAULT_SIGN.getNoPrefix())) {
			if (event.getPlayer().hasPermission(PERM_PLACE.getNoPrefix())) {
				if (!event.getLine(1).isEmpty() || !event.getLine(2).isEmpty() || !event.getLine(3).isEmpty()) {
					event.setLine(0, VAULT_SIGN.getNoPrefixColoured());
				} else {
					event.setLine(0, VAULT_SIGN.getNoPrefixColoured());
					event.setLine(1, VAULT_SIGN_1.getNoPrefixColoured());
					event.setLine(2, VAULT_SIGN_2.getNoPrefixColoured());
					event.setLine(3, VAULT_SIGN_3.getNoPrefixColoured());
				}
			} else {
				event.getPlayer().sendMessage(NO_PREM_PLACE.get());
				event.setCancelled(true);
				event.getBlock().breakNaturally();
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType().equals(Material.WALL_SIGN) || event.getClickedBlock().getType().equals(Material.SIGN) || event.getClickedBlock().getType().equals(Material.SIGN_POST)) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				String prefix = ChatColor.stripColor(sign.getLine(0));
				
				if (prefix.isEmpty())
					return;
				
				if (prefix.equals(BUY_VAULT_SIGN.getNoPrefix())) {
					if (getLoader().get(event.getPlayer()).canBuyVault()) {
						getLoader().get(event.getPlayer()).buyVault();
					}
				} else if (prefix.equals(UP_VAULT_SIGN.getNoPrefix())) {
					if (getLoader().get(event.getPlayer()).canUpgradeVault()) {
						getLoader().get(event.getPlayer()).upgradeVault();
					}
				} else if (prefix.equals(VAULT_SIGN.getNoPrefix())) {
					if (getLoader().get(event.getPlayer()).hasVaults()) {
						getLoader().get(event.getPlayer()).getDefaultVault().openInventory(event.getPlayer(), getLoader().get(event.getPlayer()).getDefaultVaultIndex());
					} else {
						event.getPlayer().sendMessage(VAULT_NONE.get());
					}
				}
			}
		}
	}
	
	private VaultLoader getLoader() {
		return VirtualVaults.getVaultsInstance().getLoader();
	}
	
}
