package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSilverOre extends Block{

	public BlockSilverOre() {
		super(Material.IRON);
		this.setUnlocalizedName("silver_ore");
		this.setRegistryName("silver_ore");
		this.setResistance(10F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 3);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
