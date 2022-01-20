package com.ohyea777.drugs.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import com.avaje.ebean.validation.NotNull;

public class Effect {

	@NotNull private String EffectType;
	private Integer Duration;
	private Integer Strength;
	private Boolean Ambient;
	
	public Effect() { }
	
	public Effect(String effectType) {
		EffectType = effectType;
	}

	public String getStringEffectType() {
		return EffectType;
	}
	
	public void setEffectType(String effectType) {
		EffectType = effectType;
	}

	public Integer getDuration() {
		if (Duration == null) {
			Duration = 10;
		}
		
		return Duration * 20;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	public Integer getStrength() {
		if (Strength == null) {
			Strength = 0;
		}
		
		return Strength;
	}

	public void setStrength(int strength) {
		Strength = strength;
	}
	
	public Boolean isAmbient() {
		if (Ambient == null) {
			Ambient = false;
		}
		
		return Ambient;
	}
	
	public void setAmbient(boolean ambient) {
		Ambient = ambient;
	}
	
	public void doEffect(LivingEntity entity) {
		if (entity == null || EffectTypes.toPotion(getStringEffectType()) == null) return;
		
		entity.addPotionEffect(new PotionEffect(EffectTypes.toPotion(getStringEffectType()), getDuration(), getStrength(), isAmbient()));
	}
	
}
