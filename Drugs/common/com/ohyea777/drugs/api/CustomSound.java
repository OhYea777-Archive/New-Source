package com.ohyea777.drugs.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;
import com.ohyea777.drugs.Drugs;

public class CustomSound {

	@NotNull private String Sound;
	private Float Volume;
	private Float Pitch;
	private Integer Duration;
	private Integer Repetitions;
	
	private transient Map<UUID, Integer> scheduleIDs = new HashMap<UUID, Integer>();
	
	public CustomSound() { }
	
	public CustomSound(String sound) {
		Sound = sound;
	}
	
	public String getSound() {
		return Sound;
	}
	
	public void setSound(String sound) {
		Sound = sound;
	}
	
	public Float getVolume() {
		if (Volume == null) {
			Volume = 1F;
		}
		
		return Volume;
	}
	
	public void setVolume(float volume) {
		Volume = volume;
	}
	
	public Float getPitch() {
		if (Pitch == null) {
			Pitch = 1F;
		}
		
		return Pitch;
	}
	
	public void setPitch(float pitch) {
		Pitch = pitch;
	}
	
	public Integer getDuration() {
		if (Duration == null) {
			Duration = 1;
		}
		
		return Duration * 20;
	}
	
	public void setDuration(int duration) {
		Duration = duration;
	}
	
	public Integer getRepetitions() {
		if (Repetitions == null) {
			Repetitions = 1;
		}
		
		return Repetitions;
	}
	
	public void setRepetitions(int repetitions) {
		Repetitions = repetitions;
	}
	
	public void doSound(final Player player) {
		if (player == null) return;
		
		int scheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Drugs.getInstance(), new Runnable() {
			
			int repetitions = 1;
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				player.playSound(player.getLocation(), getSound(), getPitch(), getVolume());
				
				if (repetitions >= getRepetitions()) {
					Bukkit.getScheduler().cancelTask(scheduleIDs.get(player.getUniqueId()));
					return;
				}
				
				repetitions ++;
			}
		}, getDuration(), getDuration());
		
		scheduleIDs.put(player.getUniqueId(), scheduleID);
	}
	
}
