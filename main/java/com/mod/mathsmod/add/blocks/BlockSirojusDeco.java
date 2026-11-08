package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSirojusDeco extends Block
{
	public BlockSirojusDeco(Material materialIn, String name, float hardness, float resistance, boolean ice) 
	{
		super(materialIn);
		this.setRegistryName(name).setUnlocalizedName(name).setHardness(hardness).setResistance(resistance);
		if(ice)
		{
			this.slipperiness = 0.99F;
		}
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		if(materialIn == Material.IRON || materialIn == Material.ROCK)
		{
			this.setHarvestLevel("pickaxe", 1);
		}
	}
	
}
