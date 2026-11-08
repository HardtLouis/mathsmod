package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystalBlock extends Block
{

	public BlockCrystalBlock(String color) 
	{
		super(Material.GLASS);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setRegistryName(color + "_crystal_block").setUnlocalizedName(color + "_crystal_block");
		this.setHardness(10.0F).setResistance(3.0F).setHarvestLevel("pickaxe", 1);
		this.setLightLevel(0.5F);
		this.setSoundType(SoundType.GLASS);
	}

	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
