package com.mod.mathsmod.utils;

import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contracts.gui.GuiContracts;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ContractsUtils {

	public static boolean alreadyDone(EntityPlayer player, ContractsBase contract)
	{
		if(player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(contract))
			{
				return player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(contract) < 0;
			}
		}
		return false;
	}
	
	public static boolean isUnlocked(EntityPlayer player, ContractsBase contract)
	{
		if(player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(contract.isUnlocked(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player), player))
			{
				return true;
			}
		}
		return false;
	}
	
	public static EntityPlayer relay()
	{
		return Minecraft.getMinecraft().player;
	}
}
