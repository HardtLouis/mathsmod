package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBurntPlanks extends Block
{

	public BlockBurntPlanks() 
	{
		super(Material.WOOD);
		this.setHardness(2.0F).setResistance(5.0F).setUnlocalizedName("burnt_planks").setRegistryName("burnt_planks");
		this.setSoundType(SoundType.WOOD).setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
