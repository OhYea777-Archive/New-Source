package com.ohyea777.roleplay.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

public class RoleplayRegistry {

	private static Map<String, IRoleplayItem> items;
	
	static {
		items = new HashMap<String, IRoleplayItem>();
	}
	
	public static boolean registerItem(IRoleplayItem item) {
		if (!items.containsKey(wrapItem(item))) {
			items.put(wrapItem(item), item);
			return true;
		}
		
		return false;
	}
	
	public static void resetItems() {
		items.clear();
	}
	
	public static boolean contains(ItemStack item) {
		return contains(wrapItem(item));
	}
	
	public static boolean contains(String item) {
		return items.containsKey(item);
	}
	
	public static IRoleplayItem getItem(ItemStack item) {
		return getItem(wrapItem(item));
	}
	
	public static IRoleplayItem getItem(String item) {
		if (contains(item)) {
			return items.get(item);
		}
		
		return null;
	}
	
	public static List<IRoleplayItem> getItems() {
		return new ArrayList<IRoleplayItem>(items.values());
	}
	
	public static String wrapItem(IRoleplayItem item) {
		return item.getLore() + ":" + item.getMaterial() + ":" + item.getDamage();
	}
	
	public static String wrapItem(ItemStack item) {
		return (item.hasItemMeta() && item.getItemMeta().hasLore() ? item.getItemMeta().getLore().get(0) : "NULL") + ":" + item.getType() + ":" + item.getDurability();
	}
	
}
