package com.ohyea777.roleplay.api;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;

import com.ohyea777.roleplay.config.ConfigSection;

public interface IRoleplayItem {

	public String getName();
	
	public String getLore();
	
	public Material getMaterial();
	
	public int getDamage();
	
	public Permission getPermission();
	
	public String getPermissionDenied();
	
	public EventType[] getSubscribedEvents();
	
	public void onEvent(EventType type, Event event);
	
	public ConfigSection getMessageSection();
	
	public ConfigSection getOptionSection();
	
}
