package com.ohyea777.craftcast;

import org.bukkit.ChatColor;

public enum Messages {

	PREFIX("Messages.Prefix"),
	BROADCAST("Messages.Broadcast"),
	REQUEST("Messages.Request"),
	REQUEST_RECEIVE("Messages.RequestReceive"),
	RELOAD("Messages.Reload"),
	NO_PERM("Messages.NoPerm"),
	SHOULD_BROADCAST("Options.Broadcast"),
	CHECK_INTERVAL("Options.CheckInterval"),
	URL("Options.URL");

	private String loc;

	private Messages(String loc) {
		this.loc = loc;
	}

	public String get() {
		return PREFIX.getNoPrefixColoured() + getNoPrefixColoured();
	}

	public String getNoPrefixColoured() {
		return _(getString());
	}

	public String getNoPrefix() {
		return ChatColor.stripColor(getNoPrefixColoured());
	}

	public String sub(String... args) {
		String s = get();

		if (args.length % 2 == 0) {
			for (int i = 0; i < args.length; i ++) {
				if (i % 2 != 0) {
					s = s.replace(args[i - 1], args[i]);
				}
			}
		}

		return s;
	}

	public boolean getAsBoolean() {
		return CraftCast.getInstance().getConfig().get(loc) != null && CraftCast.getInstance().getConfig().get(loc) instanceof Boolean ? CraftCast.getInstance().getConfig().getBoolean(loc) : true;
	}

	public int getAsInt() {
		return CraftCast.getInstance().getConfig().get(loc) != null && CraftCast.getInstance().getConfig().get(loc) instanceof Integer ? CraftCast.getInstance().getConfig().getInt(loc) : 20;
	}

	private String getString() {
		return CraftCast.getInstance().getConfig().getString(loc) != null ? CraftCast.getInstance().getConfig().getString(loc) : "";
	}

	private String _(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
