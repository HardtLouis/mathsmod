package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCaveFinder extends Block{

	public BlockCaveFinder() {
		super(Material.IRON);
		this.setUnlocalizedName("cave_finder");
		this.setRegistryName("cave_finder");
		this.setResistance(2F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
