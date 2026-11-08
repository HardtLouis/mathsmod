package com.mod.mathsmod.add.items.guns.renderer;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IGoldenWeapon 
{
	@SideOnly(Side.CLIENT)
	public Item model();
	
	public default ResourceLocation getLayer(ItemStack stack)
	{
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("layer"))
		{
			return new ResourceLocation(stack.getTagCompound().getString("layer"));
		}
		
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public default ResourceLocation getBaseTexture()
	{
		return new ResourceLocation(Mathsmod.MODID, "misc/guns_gold");
	}
	
	public default void setLayer(ItemStack stack, ResourceLocation loc)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if(loc == null)
		{
			stack.getTagCompound().removeTag("layer");
		}
		else
		{
			stack.getTagCompound().setString("layer", loc.toString());
		}
		
	}
	
	public default boolean getAnimated(ItemStack stack)
	{
		if(stack.hasTagCompound())
		{
			return stack.getTagCompound().getBoolean("animated");
		}
		return false;
	}
	
	public default void setAnimated(ItemStack stack, boolean anim)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setBoolean("animated", anim);
	}
}
