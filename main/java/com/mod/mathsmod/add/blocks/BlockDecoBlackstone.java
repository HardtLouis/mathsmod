package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDecoBlackstone extends Block
{

	public BlockDecoBlackstone(String str)
    {
        super(Material.ROCK);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setHardness(10.0F).setResistance(4.0F).setUnlocalizedName(str).setRegistryName(str);
    }

}
