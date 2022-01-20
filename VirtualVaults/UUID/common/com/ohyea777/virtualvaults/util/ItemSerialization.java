package com.ohyea777.virtualvaults.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.virtualvaults.util.nbt.NBTUtils;

public class ItemSerialization {

	public static String toBase64(ItemStack item) {
		if (item.getType().equals(Material.AIR))
			return "AIR";
		return NBTUtils.toBase64(item);
	}

	public static ItemStack fromBase64(String data) {
		if (data.equals("AIR"))
			return new ItemStack(Material.AIR);
		return NBTUtils.fromBase64(data);
	}

}
