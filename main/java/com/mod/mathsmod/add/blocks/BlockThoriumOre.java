package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockThoriumOre extends Block{

	public BlockThoriumOre()
	{
		super(Material.IRON);
		this.setUnlocalizedName("thorium_ore");
		this.setRegistryName("thorium_ore");
		this.setResistance(100F);
		this.setHardness(40F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
