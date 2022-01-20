package com.ohyea777.drugs.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;
import com.ohyea777.drugs.Drugs;
import com.ohyea777.drugs.util.Messages;

public class Drug {

	private String Name;
	@NotNull private Material Material;
	private Short Damage;
	private String UsageMessage;
	private String DruggedMessage;
	private String Permission;
	private String Lore;
	private List<Effect> Effects;
	private List<RandomEffect> RandomEffects;
	private List<RandomGroupEffect> RandomGroupEffects;
	private List<Swirl> Swirls;
	private List<ParticleEffect> ParticleEffects;
	private List<CustomSound> Sounds;
	private List<String> Commands;
	private List<String> Worlds;

	public Drug() { }

	public Drug(Material material) {
		Material = material;
	}

	public String getName() {
		if (Name == null) {
			Name = Material.name() + ":" + getDamage();
		}

		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Material getMaterial() {
		return Material;
	}

	public void setMaterial(Material material) {
		Material = material;
	}

	public Short getDamage() {
		if (Damage == null) {
			Damage = 0;
		}

		return Damage;
	}

	public void setDamage(short damage) {
		Damage = damage;
	}

	public String getUsageMessage() {
		return UsageMessage;
	}

	public void setUsageMessage(String usageMessage) {
		UsageMessage = usageMessage;
	}

	public String getDruggedMessage() {
		return DruggedMessage;
	}

	public void setDruggedMessage(String druggedMessage) {
		DruggedMessage = druggedMessage;
	}

	public String getPermission() {
		if (Permission == null) {
			Permission = "";
		}

		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public boolean usesPermission() {
		return (Drugs.getInstance().getConfig().isBoolean("Options.Use_Custom_Perms") && Drugs.getInstance().getConfig().getBoolean("Options.Use_Custom_Perms")) || 
				(Drugs.getInstance().getConfig().isBoolean("Options.UseCustomPerms") && Drugs.getInstance().getConfig().getBoolean("Options.UseCustomPerms"));
	}

	public boolean hasPermission(Player player) {
		if (player != null) {
			if (usesPermission()) {
				return player.hasPermission("drugs.use." + (getPermission().isEmpty() ? getName() : getPermission()));
			} else {
				return player.hasPermission("drugs.use");
			}
		}

		return true;
	}
	
	public String getLore() {
		return Lore;
	}
	
	public void setLore(String lore) {
		Lore = lore;
	}
	
	public boolean usesLore() {
		return getLore() != null;
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

	public List<RandomEffect> getRandomEffects() {
		if (RandomEffects == null) {
			RandomEffects = new ArrayList<RandomEffect>();
		}

		return RandomEffects;
	}

	public void setRandomEffects(List<RandomEffect> randomEffects) {
		RandomEffects = randomEffects;
	}

	public List<RandomGroupEffect> getRandomGroupEffects() {
		if (RandomGroupEffects == null) {
			RandomGroupEffects = new ArrayList<RandomGroupEffect>();
		}

		return RandomGroupEffects;
	}

	public void setRandomGroupEffects(List<RandomGroupEffect> randomGroupEffects) {
		RandomGroupEffects = randomGroupEffects;
	}

	public List<Swirl> getSwirls() {
		if (Swirls == null) {
			Swirls = new ArrayList<Swirl>();
		}

		return Swirls;
	}

	public void setSwirls(List<Swirl> swirls) {
		Swirls = swirls;
	}

	public List<ParticleEffect> getParticleEffects() {
		if (ParticleEffects == null) {
			ParticleEffects = new ArrayList<ParticleEffect>();
		}

		return ParticleEffects;
	}

	public void setParticleEffects(List<ParticleEffect> particleEffects) {
		ParticleEffects = particleEffects;
	}

	public List<CustomSound> getSounds() {
		if (Sounds == null) {
			Sounds = new ArrayList<CustomSound>();
		}

		return Sounds;
	}

	public void setSounds(List<CustomSound> sounds) {
		Sounds = sounds;
	}

	public List<String> getCommands() {
		if (Commands == null) {
			Commands = new ArrayList<String>();
		}

		return Commands;
	}

	public void setCommands(List<String> commands) {
		Commands = commands;
	}

	public List<String> getWorlds() {
		if (Worlds == null) {
			Worlds = new ArrayList<String>();
		}

		return Worlds;
	}

	public void setWorlds(List<String> worlds) {
		Worlds = worlds;
	}

	public boolean enabledInWorld(World world) {
		for (String w : getWorlds()) {
			if (w.equalsIgnoreCase(world.getName())) {
				return true;
			}
		}

		return getWorlds().isEmpty();
	}

	public void doDrug(LivingEntity entity, boolean drugged, Player druggedBy) {
		if (!enabledInWorld(entity.getWorld())) return;

		Player player = null;

		if (entity instanceof Player) {
			player = (Player) entity;
		}

		if (hasPermission(player)) {
			for (Effect e : getEffects()) {
				e.doEffect(entity);
			}

			for (RandomEffect e : getRandomEffects()) {
				e.doEffect(entity);
			}

			for (RandomGroupEffect e : getRandomGroupEffects()) {
				e.doEffects(entity);
			}

			for (Swirl s : getSwirls()) {
				s.doEffect(entity);
			}

			for (ParticleEffect e : getParticleEffects()) {
				e.doEffect(entity);
			}

			for (CustomSound s : getSounds()) {
				s.doSound(player);
			}
		} else {
			player.sendMessage(Messages.NO_PERM.toString());
			return;
		}

		if (player != null) {
			for (String c : getCommands()) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), c.replace("{player}", player.getName()).replace("{drug}", getName()));
			}

			if (drugged && druggedBy != null) {
				if (getDruggedMessage() == null) {
					player.sendMessage(Messages.DRUGGED.toString().replace("{player}", druggedBy.getName()).replace("{drug}", getName()));
				} else {
					player.sendMessage(Messages.PREFIX.get(getDruggedMessage().replace("{player}", druggedBy.getName()).replace("{drug}", getName())));
				}
			} else {
				if (getUsageMessage() == null) {
					player.sendMessage(Messages.DRUG_USE.toString().replace("{drug}", getName()));
				} else {
					player.sendMessage(Messages.PREFIX.get(getUsageMessage().replace("{drug}", getName())));
				}
			}
		}
	}

}
