package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCopravegPlant extends Block
{
	public BlockCopravegPlant() 
	{
		super(Material.WOOD);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setRegistryName("copraveg_plant").setUnlocalizedName("copraveg_plant");
        this.setResistance(3F);
        this.setHardness(4F);
        this.setLightLevel(1F);
	}
}
