package com.ohyea777.gems;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public interface IGem {

	public String getGemName();

	public GemType getGemType();
	
	public boolean isGemValid(ItemStack item);

	public void onPlayerHit(Player damager, LivingEntity damaged, EntityDamageByEntityEvent event);

	public void onEntityHit(LivingEntity damager, Player damaged, EntityDamageByEntityEvent event);

	public enum GemType {

		ARMOUR, WEAPON, TOOL, WEAPON_AND_TOOL;

		public boolean isMaterialValid(Material material) {
			switch (ordinal()) {
			case 0:
				return isArmourValid(material);
			case 1:
				return isWeaponValid(material);
			case 2:
				return isToolValid(material);
			case 3:
				return isWeaponOrToolValid(material);
			default:
				return false;
			}
		}

		public boolean isArmourValid(Material material) {
			switch (material) {
			case LEATHER_BOOTS:
				return true;
			case LEATHER_LEGGINGS:
				return true;
			case LEATHER_CHESTPLATE:
				return true;
			case LEATHER_HELMET:
				return true;
			case IRON_BOOTS:
				return true;
			case IRON_LEGGINGS:
				return true;
			case IRON_CHESTPLATE:
				return true;
			case IRON_HELMET:
				return true;
			case GOLD_BOOTS:
				return true;
			case GOLD_LEGGINGS:
				return true;
			case GOLD_CHESTPLATE:
				return true;
			case GOLD_HELMET:
				return true;
			case DIAMOND_BOOTS:
				return true;
			case DIAMOND_LEGGINGS:
				return true;
			case DIAMOND_CHESTPLATE:
				return true;
			case DIAMOND_HELMET:
				return true;
			case CHAINMAIL_BOOTS:
				return true;
			case CHAINMAIL_LEGGINGS:
				return true;
			case CHAINMAIL_CHESTPLATE:
				return true;
			case CHAINMAIL_HELMET:
				return true;
			default:
				return false;
			}
		}

		public boolean isWeaponValid(Material material) {
			switch (material) {
			case WOOD_SWORD:
				return true;
			case STONE_SWORD:
				return true;
			case IRON_SWORD:
				return true;
			case GOLD_SWORD:
				return true;
			case DIAMOND_SWORD:
				return true;
			default:
				return false;
			}
		}

		public boolean isToolValid(Material material) {
			switch (material) {
			case WOOD_PICKAXE:
				return true;
			case WOOD_SPADE:
				return true;
			case WOOD_AXE:
				return true;
			case WOOD_HOE:
				return true;
			case STONE_PICKAXE:
				return true;
			case STONE_SPADE:
				return true;
			case STONE_AXE:
				return true;
			case STONE_HOE:
				return true;
			case IRON_PICKAXE:
				return true;
			case IRON_SPADE:
				return true;
			case IRON_AXE:
				return true;
			case IRON_HOE:
				return true;
			case GOLD_PICKAXE:
				return true;
			case GOLD_SPADE:
				return true;
			case GOLD_AXE:
				return true;
			case GOLD_HOE:
				return true;
			case DIAMOND_PICKAXE:
				return true;
			case DIAMOND_SPADE:
				return true;
			case DIAMOND_AXE:
				return true;
			case DIAMOND_HOE:
				return true;
			default:
				return false;
			}
		}

		public boolean isWeaponOrToolValid(Material material) {
			return isWeaponValid(material) || isToolValid(material);
		}

	}
}
