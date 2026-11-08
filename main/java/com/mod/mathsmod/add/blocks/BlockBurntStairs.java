package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.BlockStairs;

public class BlockBurntStairs extends BlockStairs
{
	public BlockBurntStairs()
	{
		super(RegisterBlocks.CHERRY_PLANKS.getDefaultState());
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB).setRegistryName("burnt_stairs").setUnlocalizedName("burnt_stairs");
	}

}
