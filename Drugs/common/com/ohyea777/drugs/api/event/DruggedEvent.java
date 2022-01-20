package com.ohyea777.drugs.api.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.api.Drug;

public class DruggedEvent extends DrugEvent {

	private LivingEntity drugged;
	private Player druggedBy;
	
	public DruggedEvent(Drug drug, ItemStack drugItem, LivingEntity drugged, Player druggedBy) {
		super(DrugEventType.DRUGGED, drug, drugItem);
		
		this.drugged = drugged;
		this.druggedBy = druggedBy;
	}
	
	public LivingEntity getDrugged() {
		return drugged;
	}
	
	public Player getDruggedBy() {
		return druggedBy;
	}
	
}
