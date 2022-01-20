package com.ohyea777.drugs.util;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.api.Drug;

public class DrugLoader {

	private ArrayList<Drug> Drugs;

	private void checkDrugs() {
		if (Drugs == null)
			Drugs = new ArrayList<Drug>();
	}

	public void addDrug(Drug drug) {
		checkDrugs();

		Drugs.add(drug);
	}

	public boolean isDrug(ItemStack item) {
		return item != null && isDrug(item.getType(), item.getDurability(), item);
	}

	public boolean isDrug(Material material, short damage, ItemStack item) {
		for (Drug d : Drugs) {
			if (d.getMaterial().equals(material) && d.getDamage().equals(damage)) {
				if (item != null && d.usesLore()) {
					if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
						for (String lore : item.getItemMeta().getLore()) {
							if (d.getLore().equals(ChatColor.stripColor(lore))) {
								return true;
							}
						}
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public Drug getDrug(ItemStack item) {
		return item != null ? getDrug(item.getType(), item.getDurability(), item) : null;
	}

	public Drug getDrug(Material material, short damage, ItemStack item) {
		if (isDrug(material, damage, item)) {
			for (Drug d : Drugs) {
				if (d.getMaterial().equals(material) && d.getDamage().equals(damage)) {
					return d;
				}
			}
		}
		return null;
	}

}
