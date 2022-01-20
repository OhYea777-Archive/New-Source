package com.ohyea777.drugs.api.builders;

import java.util.Arrays;
import java.util.List;

import com.ohyea777.drugs.api.Effect;
import com.ohyea777.drugs.api.RandomGroupEffect;

public class RandomGroupEffectBuilder {

	private RandomGroupEffect effect;
	
	public RandomGroupEffectBuilder(RandomGroupEffect effect) {
		this.effect = effect;
	}
	
	public RandomGroupEffectBuilder() {
		this(new RandomGroupEffect());
	}
	
	public RandomGroupEffectBuilder(int chance) {
		this(new RandomGroupEffect(chance));
	}
	
	public RandomGroupEffectBuilder(List<Effect> effects) {
		this(new RandomGroupEffect(effects));
	}
	
	public RandomGroupEffectBuilder(Effect... effects) {
		this(Arrays.asList(effects));
	}
	
	public RandomGroupEffectBuilder(List<Effect> effects, int chance) {
		this(new RandomGroupEffect(effects, chance));
	}
	
	public RandomGroupEffectBuilder(int chance, Effect... effects) {
		this(Arrays.asList(effects), chance);
	}
	
	public RandomGroupEffectBuilder withChance(int chance) {
		effect.setChance(chance);
		return this;
	}
	
	public RandomGroupEffectBuilder withEffect(Effect effect) {
		this.effect.getEffects().add(effect);
		return this;
	}
	
	public RandomGroupEffectBuilder withEffects(List<Effect> effects) {
		for (Effect effect : effects) {
			withEffect(effect);
		}
		
		return this;
	}
	
	public RandomGroupEffectBuilder withEffects(Effect... effects) {
		return withEffects(Arrays.asList(effects));
	}
	
	public RandomGroupEffect build() {
		return effect;
	}
	
}
