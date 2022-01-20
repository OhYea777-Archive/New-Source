package com.ohyea777.drugs.api.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import com.ohyea777.drugs.api.Drug;

public class DrugEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	
	private boolean cancelled;
	private DrugEventType type;
	private Drug drug;
	private ItemStack drugItem;
	
	public DrugEvent(DrugEventType type, Drug drug, ItemStack drugItem) {
		this.type = type;
		this.drug = drug;
		this.drugItem = drugItem;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public DrugEventType getEventType() {
		return type;
	}
	
	public Drug getDrug() {
		return drug;
	}
	
	public ItemStack getDrugItem() {
		return drugItem;
	}
	
}
