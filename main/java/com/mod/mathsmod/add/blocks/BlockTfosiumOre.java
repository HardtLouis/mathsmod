package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockTfosiumOre extends Block{

	public BlockTfosiumOre() {
		super(Material.IRON);
		this.setUnlocalizedName("tfosium_ore");
		this.setRegistryName("tfosium_ore");
		this.setResistance(10F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 4);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
