package com.ohyea777.endlessdays;

import net.minecraft.server.Container;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemStack;
import net.minecraft.server.mod_EE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import ee.EEMaps;
import ee.core.GuiIds;
import ee.lib.Sounds;
import forge.IGuiHandler;
import forge.MinecraftForge;
import forge.NetworkMod;
import forge.packets.PacketOpenGUI;

public class EndlessDays extends JavaPlugin {

	@Override
	public void onEnable() {
		EEMaps.addAlchemicalValue(new ItemStack(30216, 1, 0), 24);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2) {
			EntityHuman h = ((CraftPlayer) Bukkit.getOfflinePlayer(args[0]).getPlayer()).getHandle();
			int data = Integer.valueOf(args[1]);
			
			IGuiHandler handler = MinecraftForge.getGuiHandler(mod_EE.getInstance());
			Container container = (Container) handler.getGuiElement(GuiIds.ALCH_BAG, h, h.world, data, 0, 0);
			
			EntityPlayer p = (EntityPlayer) h;
			p.realGetNextWidowId();
			PacketOpenGUI openGui = new PacketOpenGUI(p.getCurrentWindowIdField(), MinecraftForge.getModID((NetworkMod) mod_EE.getInstance()), GuiIds.ALCH_BAG, data, 0, 0);
			p.netServerHandler.sendPacket(openGui.getPacket());
			p.activeContainer = container;
			p.activeContainer.windowId = openGui.WindowID;
			p.activeContainer.addSlotListener(p);
			
			h.world.makeSound(h, Sounds.CHARGE_TICK, 1.0F, 1.0F);
		}
		
		return false;
	}
	
}
