package com.ohyea777.roleplay.items;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.ohyea777.roleplay.api.EventType;
import com.ohyea777.roleplay.api.IRoleplayItem;
import com.ohyea777.roleplay.api.RoleplayRegistry;

public class ItemEventHandler implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasLore()) {
			if (RoleplayRegistry.contains(event.getItem())) {
				IRoleplayItem item = RoleplayRegistry.getItem(event.getItem());
				boolean flag = false;
				
				for (EventType type : item.getSubscribedEvents()) {
					if (type.equals(EventType.PLAYER_INTERACT))
						flag = true;
				}
				
				if (!flag)
					return;
				
				if (item.getPermission() != null) {
					if (!event.getPlayer().hasPermission(item.getPermission())) {
						if (item.getPermissionDenied() != null) {
							event.getPlayer().sendMessage(item.getPermissionDenied());
						}
						
						return;
					}
				}
				
				item.onEvent(EventType.PLAYER_INTERACT, event);
			} else {
				Bukkit.broadcastMessage(RoleplayRegistry.wrapItem(event.getItem()));
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasLore()) {
			if (RoleplayRegistry.contains(event.getPlayer().getItemInHand())) {
				IRoleplayItem item = RoleplayRegistry.getItem(event.getPlayer().getItemInHand());
				boolean flag = false;
				
				for (EventType type : item.getSubscribedEvents()) {
					if (type.equals(EventType.PLAYER_INTERACT_ENTITY))
						flag = true;
				}
				
				if (!flag)
					return;
				
				if (item.getPermission() != null) {
					if (!event.getPlayer().hasPermission(item.getPermission())) {
						if (item.getPermissionDenied() != null) {
							event.getPlayer().sendMessage(item.getPermissionDenied());
						}
						
						return;
					}
				}
				
				item.onEvent(EventType.PLAYER_INTERACT_ENTITY, event);
			}
		}
	}
	
}
