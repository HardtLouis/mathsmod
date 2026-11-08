package com.mod.mathsmod.add.items.metaltools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IShieldHit {

	public void onShieldHit(EntityLivingBase attacker, EntityLivingBase target, ItemStack stack);

	public void onShieldCharge(EntityLivingBase ent, ItemStack heldItemOffhand);
	
}
