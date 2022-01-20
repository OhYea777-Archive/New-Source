package com.ohyea777.drugs.api.builders;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.ohyea777.drugs.api.CustomSound;
import com.ohyea777.drugs.api.Drug;
import com.ohyea777.drugs.api.Effect;
import com.ohyea777.drugs.api.ParticleEffect;
import com.ohyea777.drugs.api.RandomEffect;
import com.ohyea777.drugs.api.RandomGroupEffect;
import com.ohyea777.drugs.api.Swirl;

public class DrugBuilder {

	private Drug drug;
	
	public DrugBuilder(Drug drug) {
		this.drug = drug;
	}
	
	public DrugBuilder(Material material) {
		this(new Drug(material));
	}
	
	public DrugBuilder withName(String name) {
		drug.setName(name);
		return this;
	}
	
	public DrugBuilder withMaterial(Material material) {
		drug.setMaterial(material);
		return this;
	}
	
	public DrugBuilder withDamage(short damage) {
		drug.setDamage(damage);
		return this;
	}
	
	public DrugBuilder withUsageMessage(String usageMessage) {
		drug.setUsageMessage(usageMessage);
		return this;
	}
	
	public DrugBuilder withDruggedMessage(String druggedMessage) {
		drug.setDruggedMessage(druggedMessage);
		return this;
	}
	
	public DrugBuilder withPermission(String permission) {
		drug.setPermission(permission);
		return this;
	}
	
	public DrugBuilder withLore(String lore) {
		drug.setLore(lore);
		return this;
	}
	
	public DrugBuilder withEffect(Effect effect) {
		drug.getEffects().add(effect);
		return this;
	}
	
	public DrugBuilder withEffects(List<Effect> effects) {
		for (Effect e : effects) {
			withEffect(e);
		}
		
		return this;
	}
	
	public DrugBuilder withEffects(Effect... effects) {
		return withEffects(Arrays.asList(effects));
	}
	
	public DrugBuilder withEffects(EffectBuilder... effects) {
		for (EffectBuilder b : effects) {
			withEffect(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withRandomEffect(RandomEffect effect) {
		drug.getRandomEffects().add(effect);
		return this;
	}
	
	public DrugBuilder withRandomEffects(List<RandomEffect> effects) {
		for (RandomEffect e : effects) {
			withRandomEffect(e);
		}
		
		return this;
	}
	
	public DrugBuilder withRandomEffects(RandomEffect... effects) {
		return withRandomEffects(Arrays.asList(effects));
	}
	
	public DrugBuilder withRandomEffects(RandomEffectBuilder... effects) {
		for (RandomEffectBuilder b : effects) {
			withRandomEffect(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withRandomGroupEffect(RandomGroupEffect effect) {
		drug.getRandomGroupEffects().add(effect);
		return this;
	}
	
	public DrugBuilder withRandomGroupEffects(List<RandomGroupEffect> effects) {
		for (RandomGroupEffect e : effects) {
			withRandomGroupEffect(e);
		}
		
		return this;
	}
	
	public DrugBuilder withRandomGroupEffects(RandomGroupEffect... effects) {
		return withRandomGroupEffects(Arrays.asList(effects));
	}
	
	public DrugBuilder withRandomGroupEffects(RandomGroupEffectBuilder... effects) {
		for (RandomGroupEffectBuilder b : effects) {
			withRandomGroupEffect(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withSwirl(Swirl swirl) {
		drug.getSwirls().add(swirl);
		return this;
	}
	
	public DrugBuilder withSwirls(List<Swirl> swirls) {
		for (Swirl e : swirls) {
			withSwirl(e);
		}
		
		return this;
	}
	
	public DrugBuilder withSwirls(Swirl... swirls) {
		return withSwirls(Arrays.asList(swirls));
	}
	
	public DrugBuilder withSwirls(SwirlBuilder... swirls) {
		for (SwirlBuilder b : swirls) {
			withSwirl(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withParticleEffect(ParticleEffect particleEffect) {
		drug.getParticleEffects().add(particleEffect);
		return this;
	}
	
	public DrugBuilder withParticleEffects(List<ParticleEffect> particleEffects) {
		for (ParticleEffect e : particleEffects) {
			withParticleEffect(e);
		}
		
		return this;
	}
	
	public DrugBuilder withParticleEffects(ParticleEffect... particleEffects) {
		return withParticleEffects(Arrays.asList(particleEffects));
	}
	
	public DrugBuilder withParticleEffects(ParticleEffectBuilder... particleEffects) {
		for (ParticleEffectBuilder b : particleEffects) {
			withParticleEffect(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withSound(CustomSound sound) {
		drug.getSounds().add(sound);
		return this;
	}
	
	public DrugBuilder withSounds(List<CustomSound> sounds) {
		for (CustomSound e : sounds) {
			withSound(e);
		}
		
		return this;
	}
	
	public DrugBuilder withSounds(CustomSound... sounds) {
		return withSounds(Arrays.asList(sounds));
	}
	
	public DrugBuilder withSounds(CustomSoundBuilder... sounds) {
		for (CustomSoundBuilder b : sounds) {
			withSound(b.build());
		}
		
		return this;
	}
	
	public DrugBuilder withWorld(String world) {
		drug.getWorlds().add(world);
		return this;
	}
	
	public DrugBuilder withWorlds(List<String> worlds) {
		for (String e : worlds) {
			withWorld(e);
		}
		
		return this;
	}
	
	public DrugBuilder withWorlds(String... worlds) {
		return withWorlds(Arrays.asList(worlds));
	}
	
	public DrugBuilder withCommand(String command) {
		drug.getCommands().add(command);
		return this;
	}
	
	public DrugBuilder withCommands(List<String> commands) {
		for (String e : commands) {
			withCommand(e);
		}
		
		return this;
	}
	
	public DrugBuilder withCommands(String... commands) {
		return withCommands(Arrays.asList(commands));
	}
	
	public Drug build() {
		return drug;
	}
	
}
