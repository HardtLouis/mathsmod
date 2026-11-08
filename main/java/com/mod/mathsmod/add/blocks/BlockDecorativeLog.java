package com.mod.mathsmod.add.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;

public class BlockDecorativeLog extends BlockRotatedPillar
{
	public BlockDecorativeLog(Material materialIn, float hardness, float resistance, String name)
 	{
 		super(materialIn);
 		this.setRegistryName(name).setUnlocalizedName(name).setHardness(hardness).setResistance(resistance);
 	}
}
