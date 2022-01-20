package com.ohyea777.solitarytools;

import org.bukkit.event.Event;

public interface EventCallback<T extends Event> {

	public void callback(T t);
	
}
