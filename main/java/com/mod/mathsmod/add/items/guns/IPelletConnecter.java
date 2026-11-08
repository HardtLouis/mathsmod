package com.mod.mathsmod.add.items.guns;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface IPelletConnecter 
{
	public void onPelletsHit(ItemStack stack, Entity shooter, Entity target, int pelletsMissed);
}
