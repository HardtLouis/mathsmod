package com.mod.mathsmod.add.World.dims.generator.structures;

import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GenerateGiantPlant 
{
	public static void generate(World world, Random rand, BlockPos pos)
	{
		boolean flag = rand.nextInt(100) == 0;
		boolean flag2 = rand.nextInt(5) < 2;
		int height = 7 + rand.nextInt(10);
		
		for(int i = 0; i < height; ++i)
		{
			world.setBlockState(pos.add(0, i, 0), RegisterBlocks.COPRAVEG_PLANT.getDefaultState());
		}
		
		if(flag)
		{
			world.setBlockState(pos.add(0, height, 0), Blocks.GLOWSTONE.getDefaultState());
			
			for(int i = 0; i < 6; ++i)
			{
				world.setBlockState(pos.add(0, height, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(0, height, -i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i, height, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-i, height, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
			}
			
			for(int i = 0; i < 3; ++i)
			{
				world.setBlockState(pos.add(1, height, i + 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(1, height, -i - 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-1, height, i + 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-1, height, -i - 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i + 1, height, 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-i - 1, height, 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i + 1, height, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-i - 1, height, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
			}
			
			world.setBlockState(pos.add(0, height, 1), RegisterBlocks.KURAYUM_BLOCK.getDefaultState());
			world.setBlockState(pos.add(0, height, -1), RegisterBlocks.KURAYUM_BLOCK.getDefaultState());
			world.setBlockState(pos.add(1, height, 0), RegisterBlocks.KURAYUM_BLOCK.getDefaultState());
			world.setBlockState(pos.add(-1, height, 0), RegisterBlocks.KURAYUM_BLOCK.getDefaultState());
		}
		else
		{
			if(flag2)
			{
				for(int i = 0; i < 5; ++i)
				{
					world.setBlockState(pos.add(0, height + i, 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(0, height + i, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(1, height + i, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(-1, height + i, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				}
				
				for(int i = 0; i < 2; ++i)
				{

					world.setBlockState(pos.add(0, height + i + 1, 2), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(1, height + i + 1, 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(1, height + i + 1, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(2, height + i + 1, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
					
					world.setBlockState(pos.add(0, height + i + 1, -2), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(1, height + i + 1, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(-1, height + i + 1, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
					world.setBlockState(pos.add(-2, height + i + 1, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				}
				
				world.setBlockState(pos.add(0, height + 5, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(0, height + 6, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
			}
			else
			{
				world.setBlockState(pos.add(0, height + 1, 1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(0, height + 1, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(1, height + 1, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(-1, height + 1, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());

				for(int i = 0; i < 4; ++i)
				{
					world.setBlockState(pos.add(0, height + i, 0), Blocks.NETHER_WART_BLOCK.getDefaultState());
				}
			}
		}
		
		/*for(int i = 0; i <= 1; ++i)
		{
			world.setBlockState(pos.add(-1, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
			world.setBlockState(pos.add(2, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
			world.setBlockState(pos.add(i, height - 1, -1), Blocks.NETHER_WART_BLOCK.getDefaultState());
			world.setBlockState(pos.add(i, height - 1, 2), Blocks.NETHER_WART_BLOCK.getDefaultState());
			if(flag)
			{
				world.setBlockState(pos.add(-2, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(3, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i, height - 1, -2), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i, height - 1, 3), Blocks.NETHER_WART_BLOCK.getDefaultState());
				
				world.setBlockState(pos.add(-3, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(4, height - 1, i), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i, height - 1, -3), Blocks.NETHER_WART_BLOCK.getDefaultState());
				world.setBlockState(pos.add(i, height - 1, 4), Blocks.NETHER_WART_BLOCK.getDefaultState());
			}
		}*/
	}
}
