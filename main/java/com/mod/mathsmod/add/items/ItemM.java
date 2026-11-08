package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemM extends Item
{
	
	String[] str;
	
	public ItemM(String name)
	{
		this.setUnlocalizedName(name).setRegistryName(name).setCreativeTab(Mathsmod.MATHSMOD_TAB);
		str = null;
	}

	public ItemM(String name, String[] desc)
	{
		this.setUnlocalizedName(name).setRegistryName(name).setCreativeTab(Mathsmod.MATHSMOD_TAB);
		str = desc;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(str != null)
		{
			for(String str2 : str)
			{
				tooltip.add(str2);
			}
		}
    }
}
