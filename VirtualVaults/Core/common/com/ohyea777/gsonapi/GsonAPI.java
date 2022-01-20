package com.ohyea777.gsonapi;

import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.gsonapi.libs.com.google.gson.Gson;
import com.ohyea777.gsonapi.libs.com.google.gson.GsonBuilder;

public class GsonAPI extends JavaPlugin {

	private static Gson gson;
	
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		gson = builder.create();
	}
	
	public static Gson getGson() {
		return gson;
	}
	
}
