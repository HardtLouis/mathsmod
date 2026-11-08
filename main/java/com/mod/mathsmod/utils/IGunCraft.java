package com.mod.mathsmod.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IGunCraft {

	public abstract NonNullList<ItemStack> Craft();
	
	public abstract int size();
	
	public boolean displayRecipe();
	
}
