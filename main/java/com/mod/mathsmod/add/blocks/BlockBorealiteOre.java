package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBorealiteOre extends Block{

	public BlockBorealiteOre(boolean ore)
	{
		super(Material.IRON);
		if(ore)
		{
			this.setUnlocalizedName("borealite_ore");
			this.setRegistryName("borealite_ore");
		}
		else
		{
			this.setUnlocalizedName("borealite_block");
			this.setRegistryName("borealite_block");
		}
		this.setResistance(60F);
		this.setHardness(20F);
		this.setHarvestLevel("pickaxe", 5);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
}
