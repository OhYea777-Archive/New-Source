package com.ohyea777.drugs.api;

import com.avaje.ebean.validation.NotNull;

public class Swirl extends ParticleEffect {
	
	@NotNull public String Color;
	
	public Swirl() {
		super("potion_break");
	}
	
	public Swirl(String color) {
		this();
		
		Color = color;
	}
	
	public String getColor() {
		return Color;
	}
	
	public void setColor(String color) {
		Color = color;
	}
	
	@Override
	public String getParticleEffect() {
		return "potion_break";
	}
	
	@Override
	public Integer getType() {
		return EffectTypes.toColor(getColor()) == 0 ? 16384 : EffectTypes.toColor(getColor());
	}
	
}
