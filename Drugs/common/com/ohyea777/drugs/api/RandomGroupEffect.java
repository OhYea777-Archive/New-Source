package com.ohyea777.drugs.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.LivingEntity;

public class RandomGroupEffect {

	private List<Effect> Effects;
	private Integer Chance;

	public RandomGroupEffect() { }
	
	public RandomGroupEffect(int chance) {
		Chance = chance;
	}

	public RandomGroupEffect(List<Effect> effects) {
		Effects = effects;
	}

	public RandomGroupEffect(List<Effect> effects, int chance) {
		this(effects);

		Chance = chance;
	}

	public List<Effect> getEffects() {
		if (Effects == null) {
			Effects = new ArrayList<Effect>();
		}

		return Effects;
	}

	public void setEffects(List<Effect> effects) {
		Effects = effects;
	}

	public void addEffect(Effect effect) {
		getEffects().add(effect);
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

	public void doEffects(LivingEntity entity) {
		if (getRandPercent(getChance())) {
			for (Effect e : getEffects()) {
				e.doEffect(entity);
			}
		}
	}

}
