package com.mod.mathsmod.add.items.guns.stats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IGunScope 
{
	public ResourceLocation getScope();
	
	boolean hadCustomGuiScope();
	
	boolean keepBaseScope(ItemStack stack);
	
	public int timeToFullScope();
	
}
