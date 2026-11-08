package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBlackStone extends Block
{

	public BlockBlackStone()
    {
        super(Material.ROCK);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setHardness(10.0F).setResistance(4.0F).setUnlocalizedName("black_stone").setRegistryName("black_stone");
        this.setHarvestLevel("pickaxe", 1);
    }

}
