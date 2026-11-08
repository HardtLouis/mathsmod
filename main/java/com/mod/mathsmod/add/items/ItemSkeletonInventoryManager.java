package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSkeletonInventoryManager extends Item
{
	public ItemSkeletonInventoryManager()
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName("skeleton_manager").setRegistryName("skeleton_manager");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "right click on a skeleton to change its inventory");
    }
}
