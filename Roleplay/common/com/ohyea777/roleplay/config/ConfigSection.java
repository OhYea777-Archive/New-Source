package com.ohyea777.roleplay.config;

public class ConfigSection {
	
	public static ConfigSection MESSAGES = new ConfigSection("Messages");
	public static ConfigSection OPTIONS = new ConfigSection("Options");
	
	private String loc;
	
	public ConfigSection(ConfigSection parent, String loc) {
		this(parent.getPath() + "." + loc);
	}
	
	public ConfigSection(String loc) {
		this.loc = loc;
	}
	
	public String getPath() {
		return loc;
	}
	
}
