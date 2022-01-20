package com.ohyea777.solitarytools;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

public class ScriptEventExecutor<T extends Event> implements EventExecutor, Listener {
	
	private final EventCallback<T> callback;
	private final Class<T> eventType;
	
	public ScriptEventExecutor(EventCallback<T> callback, Class<T> eventType) {
		this.callback = callback;
		this.eventType = eventType;
	}
	
	@Override
	public void execute(Listener listener, Event event) throws EventException {
		if (eventType.isInstance(event)) {
			T t = eventType.cast(event);
			
			try {
				callback.callback(t);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}

}
