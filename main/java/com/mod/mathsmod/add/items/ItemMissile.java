package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;

import net.minecraft.item.Item;

public abstract class ItemMissile extends Item
{
	public ItemMissile(String name)
	{
		this.setMaxStackSize(8);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}
	
	public abstract EntityMissile PlaneMissile(EntityPlane plane);
	
	
}
