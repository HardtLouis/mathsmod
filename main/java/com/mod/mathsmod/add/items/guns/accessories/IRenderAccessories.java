package com.mod.mathsmod.add.items.guns.accessories;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public interface IRenderAccessories {

	public Vec3d scopeLocation(ItemStack stack);
	
	public Vec3d mufflerLocation(ItemStack stack);
	
	public Vec3d lampLocation(ItemStack stack);
	
}
