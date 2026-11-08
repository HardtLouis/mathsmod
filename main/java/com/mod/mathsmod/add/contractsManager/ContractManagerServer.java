package com.mod.mathsmod.add.contractsManager;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.contracts.ContractsBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class ContractManagerServer
{
	public static void replace(EntityPlayer player, ContractsBase byName, int i) 
	{
		byName.setProgress(player, byName, i);
	}

	public static void addProgress(EntityPlayer player, ContractsBase contract, int i)
	{
		contract.addProgress(player, contract, i);
	}

}
