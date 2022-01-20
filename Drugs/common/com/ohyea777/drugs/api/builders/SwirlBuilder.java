package com.ohyea777.drugs.api.builders;

import com.ohyea777.drugs.api.Swirl;

public class SwirlBuilder {

	private Swirl effect;
	
	public SwirlBuilder(Swirl effect) {
		this.effect = effect;
	}
	
	public SwirlBuilder(String color) {
		this(new Swirl(color));
	}
	
	public SwirlBuilder withColor(String color) {
		effect.setColor(color);
		return this;
	}
	
	public SwirlBuilder withDuration(int duration) {
		effect.setDuration(duration);
		return this;
	}
	
	public Swirl build() {
		return effect;
	}
	
}
