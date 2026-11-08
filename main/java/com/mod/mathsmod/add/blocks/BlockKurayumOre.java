package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockKurayumOre extends Block{

	public BlockKurayumOre() {
		super(Material.ROCK);
		this.setUnlocalizedName("kurayum_ore");
		this.setRegistryName("kurayum_ore");
		this.setResistance(50F);
		this.setHardness(15F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
