package com.ohyea777.drugs.api.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.api.Drug;

public class DrugUseEvent extends DrugEvent {

	private Player druggie;
	
	public DrugUseEvent(Drug drug, ItemStack drugItem, Player druggie) {
		super(DrugEventType.DRUG_USE, drug, drugItem);
		
		this.druggie = druggie;
	}
	
	public Player getDruggie() {
		return druggie;
	}
	
}
