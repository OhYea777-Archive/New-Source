package com.ohyea777.monopoly;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Monopoly extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().getType().equals(Material.EMERALD)) {
			getServer().broadcastMessage("Wut");
			GameBoardGeometry.INSTANCE.test(event.getClickedBlock().getLocation());
		}
	}
	
}
