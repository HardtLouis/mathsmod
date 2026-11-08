package com.mod.mathsmod.add.blocks;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockStoneWeird extends Block
{

	public BlockStoneWeird()
    {
        super(Material.ROCK);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
        this.setHardness(5.0F).setResistance(4.0F).setUnlocalizedName("weird_stone").setRegistryName("weird_stone");
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(0.1F);
    }

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.COBBLESTONE);
    }
}