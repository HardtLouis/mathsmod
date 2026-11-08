package com.mod.mathsmod.add.items.metaltools;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ICustomColor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemMetalicTool extends Item implements ICustomColor
{
	public ItemMetalicTool(String name)
	{
		this.setMaxStackSize(1).setCreativeTab(Mathsmod.MATHSMOD_MET_TAB).setUnlocalizedName(name).setRegistryName(name);
	}
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(this.getCreativeTab() == tab)
		{
			for(Item metal : MeltingRegistry.getMeltingProperties().keySet())
			{
				ItemStack stack = new ItemStack(this);
				setMetal(stack, metal);
				items.add(stack);
			}
			
			for(String metal : MeltingRegistry.getAlloyMap().keySet())
			{
				alloy al = MeltingRegistry.getAlloyMap().get(metal);
				ItemStack stack = new ItemStack(this);
				
				setAlloyFromString(stack, metal);
				items.add(stack);
			}
		}
    }
	

	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(this.getMetalString(stack).replace("%", " + "));
    }

	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		if(this.getMetal(stack) != null)
		{
			return this.getMetal(stack).color;
		}
		return -1;
	}
	
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
