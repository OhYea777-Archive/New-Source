package com.ohyea777.ohyeadownloader.util;

import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.GsonBuilder;

public class Utils {

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
