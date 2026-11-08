package com.mod.mathsmod.add.blocks;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCherryLog extends BlockRotatedPillar
{
	
	public BlockCherryLog() 
	{
		super(Material.WOOD);
		this.setRegistryName("cherry_log").setUnlocalizedName("cherry_log");
		this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

}
