package com.ohyea777.roleplay.items;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import com.ohyea777.roleplay.api.EventType;
import com.ohyea777.roleplay.api.RoleplayItemHandler;
import com.ohyea777.roleplay.config.ConfigUtil;

public class LockPick extends RoleplayItemHandler {
	
	@Override
	public String getName() {
		return "LockPick";
	}
	
	@Override
	public Material getMaterial() {
		return Material.TRIPWIRE_HOOK;
	}

	@Override
	public Permission getPermission() {
		return new Permission("roleplay.lockpick", PermissionDefault.OP);
	}
	
	@Override
	public String getPermissionDenied() {
		return ConfigUtil.getStringFormatted("PermissionDenied", "{prefix} &cYou Do Not Have Permission to Use a LockPick!", getMessageSection());
	}

	@Override
	public EventType[] getSubscribedEvents() {
		return new EventType[] { EventType.PLAYER_INTERACT };
	}

	@Override
	public void onEvent(EventType type, Event event) {
		if (type.equals(EventType.PLAYER_INTERACT)) {
			onPlayerInteract((PlayerInteractEvent) event);
		}
	}
	
	private void onPlayerInteract(PlayerInteractEvent event) {
		
	}

}
