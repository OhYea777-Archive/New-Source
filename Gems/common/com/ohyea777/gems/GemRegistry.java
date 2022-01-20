package com.ohyea777.gems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class GemRegistry implements Listener {

	private List<IGem> gems;
	
	public GemRegistry() {
		gems = new ArrayList<IGem>();
	}
	
	public void registerGem(IGem gem) {
		if (!gems.contains(gem))
			gems.add(gem);
	}
	
	public boolean isArmourGemValid(IGem gem, ItemStack item) {
		return gem.getGemType().isArmourValid(item.getType()) && gem.isGemValid(item);
	}
	
	public boolean isGemValid(IGem gem, ItemStack item) {
		return gem.getGemType().isMaterialValid(item.getType()) && gem.isGemValid(item);
	}
	
	@EventHandler
	public void onEntityHitEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof LivingEntity) {
			if (event.getDamager() instanceof Player) {
				Player damager = (Player) event.getDamager();
				for (IGem gem : gems) {
					for (ItemStack item : damager.getInventory().getArmorContents()) {
						if (item != null && isArmourGemValid(gem, item)) {
							gem.onPlayerHit(damager, (LivingEntity) event.getEntity(), event);
							break;
						}
					}
					if (damager.getItemInHand() != null) {
						if (isGemValid(gem, damager.getItemInHand())) {
							gem.onPlayerHit(damager, (LivingEntity) event.getEntity(), event);
						}
					}
				}
			}
			if (event.getEntity() instanceof Player) {
				Player damaged = (Player) event.getEntity();
				for (IGem gem : gems) {
					for (ItemStack item : damaged.getInventory().getArmorContents()) {
						if (item != null && isArmourGemValid(gem, item)) {
							gem.onEntityHit((LivingEntity) event.getEntity(), damaged, event);
							break;
						}
					}
					if (damaged.getItemInHand() != null) {
						if (isGemValid(gem, damaged.getItemInHand())) {
							gem.onEntityHit((LivingEntity) event.getEntity(), damaged, event);
						}
					}
				}
			}
		}
	}
	
}
