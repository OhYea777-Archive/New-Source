package com.ohyea777.drugs.api.builders;

import com.ohyea777.drugs.api.RandomEffect;

public class RandomEffectBuilder {

	private RandomEffect effect;
	
	public RandomEffectBuilder(RandomEffect effect) {
		this.effect = effect;
	}
	
	public RandomEffectBuilder(String effectType) {
		this(new RandomEffect(effectType));
	}
	
	public RandomEffectBuilder(String effectType, int chance) {
		this(new RandomEffect(effectType, chance));
	}
	
	public RandomEffectBuilder withChance(int chance) {
		effect.setChance(chance);
		return this;
	}
	
	public RandomEffectBuilder withEffectType(String effectType) {
		effect.setEffectType(effectType);
		return this;
	}
	
	public RandomEffectBuilder withDuration(int duration) {
		effect.setDuration(duration);
		return this;
	}
	
	public RandomEffectBuilder withStrength(int strength) {
		effect.setStrength(strength);
		return this;
	}
	
	public RandomEffectBuilder withAmbience(boolean ambient) {
		effect.setAmbient(ambient);
		return this;
	}
	
	public RandomEffect build() {
		return effect;
	}
	
}
