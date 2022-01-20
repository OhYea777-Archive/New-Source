package com.ohyea777.ohyeacore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.ohyea777.ohyeacore.util.NMSAttributes;
import com.ohyea777.ohyeacore.util.NMSAttributes.Attribute;
import com.ohyea777.ohyeacore.util.NMSAttributes.AttributeType;

public class OhYeaCore extends JavaPlugin implements Listener {

	private static OhYeaCore instance;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(getInstance(), getInstance());
	}

	public static OhYeaCore getInstance() {
		return instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.getItemInHand().getType() != Material.AIR) {
				NMSAttributes attributes = new NMSAttributes(player.getItemInHand());
				attributes.add(Attribute.newBuilder().name("Damage").type(AttributeType.GENERIC_ATTACK_DAMAGE).amount(10).build());
				player.setItemInHand(attributes.getStack());
			}
		}
		
		return false;
	}

}
