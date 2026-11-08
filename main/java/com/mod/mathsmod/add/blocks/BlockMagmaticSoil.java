package com.mod.mathsmod.add.blocks;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMagmaticSoil extends Block
{

	public BlockMagmaticSoil() 
	{
		super(Material.GROUND);
		this.setSoundType(SoundType.GROUND);
		this.setRegistryName("magmatic_soil");
		this.setTickRandomly(true);
		this.setUnlocalizedName("magmatic_soil");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR && rand.nextInt(5) == 0)
        {
        	worldIn.setBlockState(pos.up(), RegisterBlocks.DENANIUM.getDefaultState());
        }
    }
}
