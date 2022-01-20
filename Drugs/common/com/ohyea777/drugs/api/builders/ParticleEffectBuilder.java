package com.ohyea777.drugs.api.builders;

import com.ohyea777.drugs.api.ParticleEffect;

public class ParticleEffectBuilder {

	private ParticleEffect effect;
	
	public ParticleEffectBuilder(ParticleEffect effect) {
		this.effect = effect;
	}
	
	public ParticleEffectBuilder(String particleEffect) {
		this(new ParticleEffect(particleEffect));
	}
	
	public ParticleEffectBuilder withParticleEffect(String particleEffect) {
		effect.setParticleEffect(particleEffect);
		return this;
	}
	
	public ParticleEffectBuilder withDuration(int duration) {
		effect.setDuration(duration);
		return this;
	}
	
	public ParticleEffectBuilder withType(int type) {
		effect.setType(type);
		return this;
	}
	
	public ParticleEffect build() {
		return effect;
	}
	
}
