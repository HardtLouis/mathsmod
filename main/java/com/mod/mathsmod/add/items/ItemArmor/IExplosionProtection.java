package com.mod.mathsmod.add.items.ItemArmor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IExplosionProtection {

	public float explosionMultiplier(EntityLivingBase entity, Item item, ItemStack stack);
	
}
