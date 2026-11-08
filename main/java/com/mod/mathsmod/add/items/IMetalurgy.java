package com.mod.mathsmod.add.items;

import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IMetalurgy 
{
	public static void setAlloy(ItemStack stack, Item mat, Item mat2)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString() + "%" + mat2.getRegistryName().toString());
	}
	
	public static void setAlloyFromString(ItemStack stack, String str)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", str);
	}
	
	public static void setMetal(ItemStack stack, Item mat)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setString("metal", mat.getRegistryName().toString());
	}
	
	public static String getMetalString(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
			return null;
		}
		
		return stack.getTagCompound().getString("metal");
	}
	
	public static Molten_metal getMetal(ItemStack stack)
	{
		return MeltingRegistry.getMetal(getMetalString(stack));
	}
}
