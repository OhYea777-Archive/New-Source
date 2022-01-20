package com.ohyea777.drugs.api;

import org.bukkit.Effect;
import org.bukkit.potion.PotionEffectType;

public class EffectTypes {

	public static PotionEffectType toPotion(String s){
		switch(s.toLowerCase()){
		case "absorbtion":
			return PotionEffectType.ABSORPTION;
		case "blind":
			return PotionEffectType.BLINDNESS;
		case "blindness":
			return PotionEffectType.BLINDNESS;
		case "confusion":
			return PotionEffectType.CONFUSION;
		case "nausea":
			return PotionEffectType.CONFUSION;
		case "resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damage resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damageresistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "damage_resistance":
			return PotionEffectType.DAMAGE_RESISTANCE;
		case "fastdig":
			return PotionEffectType.FAST_DIGGING;
		case "fastdigging":
			return PotionEffectType.FAST_DIGGING;
		case "fast_digging":
			return PotionEffectType.FAST_DIGGING;
		case "speeddigging":
			return PotionEffectType.FAST_DIGGING;
		case "speeddig":
			return PotionEffectType.FAST_DIGGING;
		case "speed_digging":
			return PotionEffectType.FAST_DIGGING;
		case "haste":
			return PotionEffectType.FAST_DIGGING;
		case "fire":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fireres":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fire_res":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fireresistance":
			return PotionEffectType.FIRE_RESISTANCE;
		case "fire_resistance":
			return PotionEffectType.FIRE_RESISTANCE;
		case "harm":
			return PotionEffectType.HARM;
		case "heal":
			return PotionEffectType.HEAL;
		case "health":
			return PotionEffectType.HEAL;
		case "healthboost":
			return PotionEffectType.HEALTH_BOOST;
		case "health_boost":
			return PotionEffectType.HEALTH_BOOST;
		case "health boost":
			return PotionEffectType.HEALTH_BOOST;
		case "boost":
			return PotionEffectType.HEALTH_BOOST;
		case "hunger":
			return PotionEffectType.HUNGER;
		case "highdmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high_dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "highdamage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "high_damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increasedmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase_dmg":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increasedamage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "increase_damage":
			return PotionEffectType.INCREASE_DAMAGE;
		case "strength":
			return PotionEffectType.INCREASE_DAMAGE;
		case "invisible":
			return PotionEffectType.INVISIBILITY;
		case "invisibility":
			return PotionEffectType.INVISIBILITY;
		case "jump":
			return PotionEffectType.JUMP;
		case "jumpboost":
			return PotionEffectType.JUMP;
		case "jump boost":
			return PotionEffectType.JUMP;
		case "jump_boost":
			return PotionEffectType.JUMP;
		case "nightvision":
			return PotionEffectType.NIGHT_VISION;
		case "night vision":
			return PotionEffectType.NIGHT_VISION;
		case "night_vision":
			return PotionEffectType.NIGHT_VISION;
		case "poison":
			return PotionEffectType.POISON;
		case "regeneration":
			return PotionEffectType.REGENERATION;
		case "saturation":
			return PotionEffectType.SATURATION;
		case "slow":
			return PotionEffectType.SLOW;
		case "slowdigging":
			return PotionEffectType.SLOW_DIGGING;
		case "slow digging":
			return PotionEffectType.SLOW_DIGGING;
		case "slow_digging":
			return PotionEffectType.SLOW_DIGGING;
		case "speed":
			return PotionEffectType.SPEED;
		case "waterbreathing":
			return PotionEffectType.WATER_BREATHING;
		case "water breathing":
			return PotionEffectType.WATER_BREATHING;
		case "water_breathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwaterbreathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwater breathing":
			return PotionEffectType.WATER_BREATHING;
		case "underwater_breathing":
			return PotionEffectType.WATER_BREATHING;
		case "weak":
			return PotionEffectType.WEAKNESS;
		case "weakness":
			return PotionEffectType.WEAKNESS;
		case "wither":
			return PotionEffectType.WITHER;
		case "withereffect":
			return PotionEffectType.WITHER;
		case "wither effect":
			return PotionEffectType.WITHER;
		case "wither_effect":
			return PotionEffectType.WITHER;
		default:
			return PotionEffectType.getByName(s.toUpperCase());
		}
	}
	
	public static Effect toEffect(String effect) {
		switch (effect.toLowerCase()) {
		case "ender":
			return Effect.ENDER_SIGNAL;
		case "endersignal":
			return Effect.ENDER_SIGNAL;
		case "ender signal":
			return Effect.ENDER_SIGNAL;
		case "ender_signal":
			return Effect.ENDER_SIGNAL;
		case "spawner":
			return Effect.MOBSPAWNER_FLAMES;
		case "mobspawner":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob spawner":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob_spawner":
			return Effect.MOBSPAWNER_FLAMES;
		case "flame":
			return Effect.MOBSPAWNER_FLAMES;
		case "flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mobspawnerflames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob spawnerflames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob_spawnerflames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mobspawner flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob spawner flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob_spawner flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mobspawner_flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob spawner_flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "mob_spawner_flames":
			return Effect.MOBSPAWNER_FLAMES;
		case "potionswirl":
			return Effect.POTION_SWIRL;
		case "potion swirl":
			return Effect.POTION_SWIRL;
		case "potion_swirl":
			return Effect.POTION_SWIRL;
		case "potionbreak":
			return Effect.POTION_BREAK;
		case "potion break":
			return Effect.POTION_BREAK;
		case "potion_break":
			return Effect.POTION_BREAK;
		default:
			return Effect.getByName(effect.toUpperCase()) != null ? Effect.getByName(effect.toUpperCase()) : Effect.SMOKE;
		}
	}
	
	public static int toColor(String color) {
		switch (color.toLowerCase()) {
		case "darkblue":
			return 16384;
		case "dark blue":
			return 16384;
		case "dark_blue":
			return 16384;
		case "blue":
			return 16386;
		case "aqua":
			return 16386;
		case "pink":
			return 16385;
		case "yellow":
			return 16387;
		case "orange":
			return 16387;
		case "red":
			return 16389;
		case "darkred":
			return 16389;
		case "dark red":
			return 16389;
		case "dark_red":
			return 16389;
		case "gray":
			return 16430;
		case "white":
			return 16430;
		case "lightgray":
			return 16430;
		case "light gray":
			return 16430;
		case "light_gray":
			return 16430;
		case "green":
			return 16388;
		case "lightgreen":
			return 16388;
		case "light green":
			return 16388;
		case "light_green":
			return 16388;
		case "darkgreen":
			return 16388;
		case "dark green":
			return 16388;
		case "dark_green":
			return 16388;
		case "black":
			return 16392;
		case "darkgray":
			return 16392;
		case "dark gray":
			return 16392;
		case "dark_gray":
			return 16392;
		case "cyan":
			return 16394;
		case "darkaqua":
			return 16394;
		case "dark aqua":
			return 16394;
		case "dark_aqua":
			return 16394;
		default:
			return 0;
		}
	}
	
}
