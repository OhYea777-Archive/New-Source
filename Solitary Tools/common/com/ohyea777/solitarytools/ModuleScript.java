package com.ohyea777.solitarytools;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.ohyea777.solitarytools.api.CommandRegistry;
import com.ohyea777.solitarytools.api.IModule;

public class ModuleScript {

	private final ScriptEngineManager manager;
	private final ScriptEngine engine;
	private final Invocable invocable;
	private final FileReader script;
	
	public ModuleScript(FileReader script, String language) {
		manager = new ScriptEngineManager();
		engine = manager.getEngineByName(language);
		invocable = (Invocable) engine;
		this.script = script;
		
		init();
	}
	
	private void init() {
		engine.put("script", this);
		engine.put("plugin", SolitaryTools.INSTANCE);
		engine.put("server", SolitaryTools.INSTANCE.getServer());
		engine.put("commandRegistry", CommandRegistry.INSTANCE);
		engine.put("eventRegistry", ScriptEventRegistry.INSTANCE);
	}
	
	public boolean invoke() {
		try {
			engine.eval(script);
			engine.eval("function getModule() { return script.getModule(); }");
			return true;
		} catch (ScriptException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void invoke(String function, Object... args) {
		try {
			invocable.invokeFunction(function, args);
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public IModule getModule() {
		return invocable.getInterface(IModule.class);
	}
	
}
