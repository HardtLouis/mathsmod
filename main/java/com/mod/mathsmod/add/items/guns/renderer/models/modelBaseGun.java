package com.mod.mathsmod.add.items.guns.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public abstract class modelBaseGun extends ModelBase
{
	public abstract void renderModel(ItemStack stack, Entity entity);
}
