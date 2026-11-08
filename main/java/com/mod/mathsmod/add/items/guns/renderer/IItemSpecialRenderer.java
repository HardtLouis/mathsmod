package com.mod.mathsmod.add.items.guns.renderer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IItemSpecialRenderer {

	public void render(ItemStack stack, EntityPlayer entity, float partialTicks);
	
}
