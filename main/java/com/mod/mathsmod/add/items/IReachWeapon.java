package com.mod.mathsmod.add.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IReachWeapon {

	public float bonusReach(EntityPlayer entity);
	
	public default float bonusReach(EntityPlayer entity, ItemStack stack)
	{
		return bonusReach(entity);
	}
	
}
