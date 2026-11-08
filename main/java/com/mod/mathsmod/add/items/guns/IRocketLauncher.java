package com.mod.mathsmod.add.items.guns;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IRocketLauncher {

	public float explosionStrenght();
	
	public default float baseRockedSpeed()
	{
		return 1.0F;
	}
	
	public default float onRocketHit(EntityLivingBase entity, Entity target, ItemStack stack, ItemGun item)
	{
		return 1.0F;
	}
}
