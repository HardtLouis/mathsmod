package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCherryPlanks extends Block
{

	public BlockCherryPlanks() 
	{
		super(Material.WOOD);
		this.setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("cherry_planks").setRegistryName("cherry_planks");
		this.setSoundType(SoundType.WOOD).setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

}
