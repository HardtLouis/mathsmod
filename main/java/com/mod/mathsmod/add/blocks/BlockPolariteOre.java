package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPolariteOre extends Block{

	public BlockPolariteOre()
	{
		super(Material.IRON);
		this.setUnlocalizedName("polarite_ore");
		this.setRegistryName("polarite_ore");
		this.setResistance(100F);
		this.setHardness(50F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
