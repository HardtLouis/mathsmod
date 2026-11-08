package com.mod.mathsmod.add.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface ICustomColor 
{
	public int getColorMultiplier(ItemStack stack, int tintIndex);
	
	public default int getColorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) 
	{
		return -1;
	}
}
