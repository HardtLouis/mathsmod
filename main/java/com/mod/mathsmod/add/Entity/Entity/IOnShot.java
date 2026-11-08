package com.mod.mathsmod.add.Entity.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IOnShot {

	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount);
	
}
