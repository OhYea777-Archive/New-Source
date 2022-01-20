package com.ohyea777.drugs.api.builders;

import com.ohyea777.drugs.api.Effect;

public class EffectBuilder {

	private Effect effect;
	
	public EffectBuilder(Effect effect) {
		this.effect = effect;
	}
	
	public EffectBuilder(String effectType) {
		this(new Effect(effectType));
	}
	
	public EffectBuilder withEffectType(String effectType) {
		effect.setEffectType(effectType);
		return this;
	}
	
	public EffectBuilder withDuration(int duration) {
		effect.setDuration(duration);
		return this;
	}
	
	public EffectBuilder withStrength(int strength) {
		effect.setStrength(strength);
		return this;
	}
	
	public EffectBuilder withAmbience(boolean ambient) {
		effect.setAmbient(ambient);
		return this;
	}
	
	public Effect build() {
		return effect;
	}
	
}
