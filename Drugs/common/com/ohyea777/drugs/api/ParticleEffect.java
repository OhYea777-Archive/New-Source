package com.ohyea777.drugs.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;
import com.ohyea777.drugs.Drugs;

public class ParticleEffect {

	@NotNull private String ParticleEffect;
	private Integer Duration;
	private Integer Type;
	private Boolean PlayerOnly;
	
	private transient Map<UUID, Integer> scheduleIDs = new HashMap<UUID, Integer>();
	
	public ParticleEffect() { }
	
	public ParticleEffect(String particleEffect) {
		ParticleEffect = particleEffect;
	}

	public String getParticleEffect() {
		return ParticleEffect;
	}

	public void setParticleEffect(String particleEffect) {
		ParticleEffect = particleEffect;
	}

	public Integer getDuration() {
		if (Duration == null) {
			Duration = 10;
		}
		
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	public Integer getType() {
		if (Type == null) {
			Type = 0;
		}
		
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}
	
	public boolean isPlayerOnly() {
		if (PlayerOnly == null) {
			PlayerOnly = false;
		}
		
		return PlayerOnly;
	}
	
	public void doEffect(final LivingEntity entity) {
		if (entity == null || EffectTypes.toEffect(getParticleEffect()) == null) return;
		
		int scheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Drugs.getInstance(), new Runnable() {
			
			private int count = 1;
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if (isPlayerOnly() && entity instanceof Player) {
					((Player) entity).playEffect(entity.getLocation(), EffectTypes.toEffect(getParticleEffect()), getType().intValue());
				} else {
					entity.getWorld().playEffect(entity.getLocation(), EffectTypes.toEffect(getParticleEffect()), getType().intValue());
				}
				
				if (count >= getDuration() * 4 ) {
					Bukkit.getScheduler().cancelTask(scheduleIDs.get(entity.getUniqueId()));
					return;
				}
				
				count ++;
			}
		}, 5, 5);
		
		scheduleIDs.put(entity.getUniqueId(), scheduleID);
	}
	
}
