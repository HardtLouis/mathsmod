package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockCherryStairs extends BlockStairs
{
	public BlockCherryStairs()
	{
		super(RegisterBlocks.CHERRY_PLANKS.getDefaultState());
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB).setRegistryName("cherry_stairs").setUnlocalizedName("cherry_stairs");
	}

}
