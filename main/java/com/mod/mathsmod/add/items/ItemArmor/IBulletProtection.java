package com.mod.mathsmod.add.items.ItemArmor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IBulletProtection {

	public float getBulletMultiplier();
	
	public default float getBulletMultiplier(ItemStack stack, EntityLivingBase user)
	{
		return this.getBulletMultiplier();
	}
	
	public default int damageItemOnShoot()
	{
		return 1;
	}
	
	public default boolean isMultiplierPassive()
	{
		return false;
	}
	
}
