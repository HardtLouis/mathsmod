package com.mod.mathsmod.add.items.guns.stats;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ICustomGunStats {

	public List<statBase> stats(ItemStack stack);
	
}
