package com.mod.mathsmod.add.items.guns.renderer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IGoldenAnimatedGun extends IAnimatedGun 
{
	

	public default ResourceLocation texture(ItemStack stack)
	{
		return this.getLayer(stack) == null ? null : new ResourceLocation(this.getLayer(stack).getResourceDomain(), "textures/" + this.getLayer(stack).getResourcePath() + ".png");
	}
	
	public default ResourceLocation getLayer(ItemStack stack)
	{
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("layer"))
		{
			return new ResourceLocation(stack.getTagCompound().getString("layer"));
		}
		
		return null;
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
