package com.ohyea777.drugs.api.builders;

import com.ohyea777.drugs.api.CustomSound;

public class CustomSoundBuilder {

	private CustomSound sound;
	
	public CustomSoundBuilder(CustomSound sound) {
		this.sound = sound;
	}
	
	public CustomSoundBuilder(String sound) {
		this(new CustomSound(sound));
	}
	
	public CustomSoundBuilder withSound(String sound) {
		this.sound.setSound(sound);
		return this;
	}
	
	public CustomSoundBuilder withVolume(float volume) {
		sound.setVolume(volume);
		return this;
	}
	
	public CustomSoundBuilder withPitch(float pitch) {
		sound.setPitch(pitch);
		return this;
	}
	
	public CustomSoundBuilder withDuration(int duration) {
		sound.setDuration(duration);
		return this;
	}
	
	public CustomSoundBuilder withRepetitions(int repetitions) {
		sound.setRepetitions(repetitions);
		return this;
	}
	
	public CustomSound build() {
		return sound;
	}
	
}
