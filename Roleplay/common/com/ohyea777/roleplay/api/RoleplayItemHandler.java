package com.ohyea777.roleplay.api;

import com.ohyea777.roleplay.config.ConfigSection;

public abstract class RoleplayItemHandler implements IRoleplayItem {

	@Override
	public String getLore() {
		return "[" + getName() + "]";
	}
	
	@Override
	public int getDamage() {
		return 0;
	}
	
	@Override
	public ConfigSection getMessageSection() {
		return new ConfigSection(ConfigSection.MESSAGES, getName());
	}
	
	@Override
	public ConfigSection getOptionSection() {
		return new ConfigSection(ConfigSection.OPTIONS, getName());
	}
	
}
