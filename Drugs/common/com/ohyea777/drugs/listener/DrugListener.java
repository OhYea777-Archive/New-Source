package com.ohyea777.drugs.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.Drugs;
import com.ohyea777.drugs.api.event.DrugUseEvent;
import com.ohyea777.drugs.api.event.DruggedEvent;

public class DrugListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null && event.getPlayer().isSneaking() && Drugs.getLoader().isDrug(event.getItem())) {
			DrugUseEvent e = new DrugUseEvent(Drugs.getLoader().getDrug(event.getItem()), event.getItem(), event.getPlayer());
			Drugs.getInstance().getServer().getPluginManager().callEvent(e);

			if (!e.isCancelled()) {
				e.getDrug().doDrug(event.getPlayer(), false, null);

				event.setCancelled(true);

				if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if (event.getPlayer().getItemInHand().getAmount() > 1) {
						event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
					} else {
						event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
					}
				}
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof LivingEntity && event.getPlayer().getItemInHand() != null && event.getPlayer().isSneaking()) {
			if (event.getRightClicked() instanceof Player) {
				if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasLore() && event.getPlayer().getItemInHand().getItemMeta().getLore().get(0).contains(":")) {
					ItemStack item = event.getPlayer().getItemInHand();
					short damage = 0;
					
					try {
						damage = Short.valueOf(item.getItemMeta().getLore().get(0).split(":")[1]);
					} catch (NumberFormatException e) {
						return;
					}
					
					Material material = Material.getMaterial(item.getItemMeta().getLore().get(0).split(":")[0]);
					
					if (material != null && !material.equals(Material.AIR) && Drugs.getLoader().isDrug(material, damage, null)) {
						DruggedEvent e = new DruggedEvent(Drugs.getLoader().getDrug(material, damage, null), item, (LivingEntity) event.getRightClicked(), event.getPlayer());
						Drugs.getInstance().getServer().getPluginManager().callEvent(e);

						if (!e.isCancelled()) {
							e.getDrug().doDrug(e.getDrugged(), true, event.getPlayer());

							event.setCancelled(true);

							if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
								if (event.getPlayer().getItemInHand().getAmount() > 1) {
									event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
								} else {
									event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
								}
							}
						}
					}
				}
			} else if (Drugs.getLoader().isDrug(event.getPlayer().getItemInHand())) {
				DrugUseEvent e = new DrugUseEvent(Drugs.getLoader().getDrug(event.getPlayer().getItemInHand()), event.getPlayer().getItemInHand(), event.getPlayer());
				Drugs.getInstance().getServer().getPluginManager().callEvent(e);

				if (!e.isCancelled()) {
					e.getDrug().doDrug(e.getDruggie(), false, null);

					event.setCancelled(true);

					if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
						if (event.getPlayer().getItemInHand().getAmount() > 1) {
							event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
						} else {
							event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
						}
					}
				}
			}
		}
	}

}
