package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenerateMagmaPlant
{


	private static boolean areAllNeighborsEmpty(World worldIn, BlockPos pos, EnumFacing excludingSide, boolean inWater)
    {
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
        {
        	if(inWater)
        	{
        		if (enumfacing != excludingSide && worldIn.getBlockState(pos.offset(enumfacing)).getMaterial() != Material.WATER)
                {
                    return false;
                }
        	}
        	else
        	{
                if (enumfacing != excludingSide && !worldIn.isAirBlock(pos.offset(enumfacing)))
                {
                    return false;
                }
        	}
        }

        return true;
    }
	
	public static void placePlantLava(World worldIn, BlockPos pos, Random rand, BlockPos pos2, int p_185601_4_, int p_185601_5_)
	{
		int i = rand.nextInt(4) + 1;
		
		if (p_185601_5_ == 0)
        {
            ++i;
        }

        for (int j = 0; j < i; ++j)
        {
            BlockPos blockpos = pos.up(j + 1);

            if (!areAllNeighborsEmpty(worldIn, blockpos, (EnumFacing)null, false))
            {
                return;
            }

            worldIn.setBlockState(blockpos, Blocks.NETHER_WART_BLOCK.getDefaultState(), 2);
        }

        boolean flag = false;

        if (p_185601_5_ < 4)
        {
            int l = rand.nextInt(4);

            if (p_185601_5_ == 0)
            {
                ++l;
            }

            for (int k = 0; k < l; ++k)
            {
                EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                BlockPos blockpos1 = pos.up(i).offset(enumfacing);

                if (Math.abs(blockpos1.getX() - pos2.getX()) < p_185601_4_ && Math.abs(blockpos1.getZ() - pos2.getZ()) < p_185601_4_ && worldIn.isAirBlock(blockpos1) && worldIn.isAirBlock(blockpos1.down()) && areAllNeighborsEmpty(worldIn, blockpos1, enumfacing.getOpposite(), false))
                {
                    flag = true;
                    worldIn.setBlockState(blockpos1, Blocks.NETHER_WART_BLOCK.getDefaultState(), 2);
                    placePlantLava(worldIn, blockpos1, rand, pos2, p_185601_4_, p_185601_5_ + 1);
                }
            }
        }
        
        if (!flag)
        {
            worldIn.setBlockState(pos.up(i), Blocks.NETHER_WART_BLOCK.getDefaultState());
        }
	}
	
	
	public static void placePlantUnderWater(World worldIn, BlockPos pos, Random rand, BlockPos pos2, int p_185601_4_, int p_185601_5_)
	{
		int i = rand.nextInt(4) + 1;
		
		if (p_185601_5_ == 0)
        {
            ++i;
        }

        for (int j = 0; j < i; ++j)
        {
            BlockPos blockpos = pos.up(j + 1);

            if (!areAllNeighborsEmpty(worldIn, blockpos, (EnumFacing)null, true))
            {
                return;
            }

            worldIn.setBlockState(blockpos, Blocks.CONCRETE.getStateFromMeta(5), 2);
        }

        boolean flag = false;

        if (p_185601_5_ < 4)
        {
            int l = rand.nextInt(4);

            if (p_185601_5_ == 0)
            {
                ++l;
            }

            for (int k = 0; k < l; ++k)
            {
                EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                BlockPos blockpos1 = pos.up(i).offset(enumfacing);

                if (Math.abs(blockpos1.getX() - pos2.getX()) < p_185601_4_ && Math.abs(blockpos1.getZ() - pos2.getZ()) < p_185601_4_ && worldIn.getBlockState(blockpos1).getMaterial() == Material.WATER && worldIn.getBlockState(blockpos1.down()).getMaterial() == Material.WATER && areAllNeighborsEmpty(worldIn, blockpos1, enumfacing.getOpposite(), true))
                {
                    flag = true;
                    worldIn.setBlockState(blockpos1, Blocks.CONCRETE.getStateFromMeta(5), 2);
                    placePlantLava(worldIn, blockpos1, rand, pos2, p_185601_4_, p_185601_5_ + 1);
                }
            }
        }
        
        if (!flag)
        {
            worldIn.setBlockState(pos.up(i), Blocks.CONCRETE.getStateFromMeta(5));
        }
	}
}
