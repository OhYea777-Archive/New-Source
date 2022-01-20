package com.ohyea777.solitarytools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import com.ohyea777.solitarytools.api.IModule;
import com.ohyea777.solitarytools.api.ModuleRegistry;

public enum ModuleScriptLoader {

	INSTANCE;
	
	private Map<String, ModuleScript> scripts;
	
	private ModuleScriptLoader() {
		scripts = new HashMap<String, ModuleScript>();
	}
	
	public void preInit(File file) {
		loadScripts(file);
	}
	
	private void loadScripts(File file) {
		for (File scr : file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.endsWith(".js") || name.endsWith(".javascript");
			}
		})) {
			try {
				FileReader reader = new FileReader(scr);
				ModuleScript script = new ModuleScript(reader, "js");
				
				if (script.invoke()) {
					IModule module = script.getModule();
					
					if (module != null) {
						ModuleRegistry.INSTANCE.registerModule(module.getName(), module);
						
						scripts.put(module.getName(), script);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean contains(IModule module) {
		return scripts.containsKey(module.getName());
	}
	
	public ModuleScript get(IModule module) {
		return scripts.get(module.getName());
	}
	
	public void deInit() {
		scripts.clear();
	}
	
}
