package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class ItemLaunchedMissile extends Item
{	
	public ItemLaunchedMissile(String s)
	{
		this.setRegistryName(s);
		this.setUnlocalizedName(s);
		this.setMaxStackSize(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	

	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "need a missile launcher to be used");
    }
	
	public abstract EntityMissile getMissile(World wordl, BlockPos pos, BlockPos target);
}
