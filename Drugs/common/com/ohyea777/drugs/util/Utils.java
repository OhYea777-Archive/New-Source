package com.ohyea777.drugs.util;

import java.io.File;
import java.io.FileNotFoundException;

import com.ohyea777.drugs.Drugs;
import com.ohyea777.drugs.libs.com.google.gson.Gson;
import com.ohyea777.drugs.libs.com.google.gson.GsonBuilder;
import com.ohyea777.drugs.libs.org.apache.commons.io.FileUtils;

public class Utils {

	private static Gson gson;
	private static File file;

	static {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		gson = builder.create();
	}

	public static Gson getGson() {
		return gson;
	}

	private static Drugs getPlugin() {
		return Drugs.getInstance();
	}

	private static File getFile() {
		if (file == null) {
			file = new File(getPlugin().getDataFolder(), "Drugs.json");
		}
		
		return file;
	}

	public static void saveDrugLoader(DrugLoader loader) {
		try {
			String json = getGson().toJson(loader);
			FileUtils.writeStringToFile(getFile(), json);
		} catch (FileNotFoundException e) {
			saveDefaultLoader();
		} catch (Exception e) {
			getPlugin().getLogger().severe("Failed to Save Drugs JSON - " + e.getMessage() + "! Printing Stacktrace: ");
			e.printStackTrace();
		}
	}

	public static void saveDefaultLoader() {
		if (!loaderExists())
			getPlugin().saveResource("Drugs.json", true);
	}

	public static DrugLoader loadDrugLoader() {
		try {
			if (!getFile().exists())
				saveDefaultLoader();
			String json = FileUtils.readFileToString(getFile());
			return getGson().fromJson(json, DrugLoader.class);
		} catch (Exception e) {
			getPlugin().getLogger().severe("Failed to Load Drugs JSON - " + e.getMessage() + "! Printing Stacktrace: ");
			e.printStackTrace();
		}
		return null;
	}

	public static DrugLoader loadDrugLoader(File file) {
		try {
			String json = FileUtils.readFileToString(file);
			return getGson().fromJson(json, DrugLoader.class);
		} catch (Exception e) {
			getPlugin().getLogger().severe("Failed to Load Drugs JSON - " + e.getMessage() + "! Printing Stacktrace: ");
			e.printStackTrace();
		}
		return null;
	}

	public static boolean loaderExists() {
		return getFile().exists();
	}

	public static int getMaxParticles() {
		return getPlugin().getConfig().isInt("Options.Max_Particle_Effects") ? getPlugin().getConfig().getInt("Options.Max_Particle_Effects") : 10;
	}

}
