package com.ohyea777.drugs.api;

import java.util.Random;

import org.bukkit.entity.LivingEntity;

public class RandomEffect extends Effect {

	private Integer Chance;
	
	public RandomEffect() { }
	
	public RandomEffect(String effectType) {
		super(effectType);
	}
	
	public RandomEffect(String effectType, int chance) {
		this(effectType);
		
		Chance = chance;
	}
	
	public Integer getChance() {
		if (Chance == null) {
			Chance = 50;
		}
		
		return Chance;
	}
	
	public void setChance(int chance) {
		Chance = chance;
	}
	
	private boolean getRandPercent(int percent) {
	    Random rand = new Random();
	    return rand.nextInt(100) <= percent;
	}
	
	@Override
	public void doEffect(LivingEntity entity) {
		if (getRandPercent(getChance())) {
			super.doEffect(entity);
		}
	}
	
}
