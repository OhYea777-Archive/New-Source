package com.ohyea777.solitarytools;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.ohyea777.solitarytools.api.IModule;

public enum ScriptEventRegistry implements Listener {

	INSTANCE;
	
	public <T extends Event> void registerEvent(Class<T> eventType, final String function, final IModule module) {
		ScriptEventExecutor<T> executor = new ScriptEventExecutor<T>(new EventCallback<T>() {
			@Override
			public void callback(T t) {
				if (ModuleScriptLoader.INSTANCE.contains(module)) {
					ModuleScriptLoader.INSTANCE.get(module).invoke(function, t);
				}
			}
		}, eventType);
		
		Bukkit.getPluginManager().registerEvent(eventType, executor, EventPriority.NORMAL, executor, SolitaryTools.INSTANCE);
	}
	
}
