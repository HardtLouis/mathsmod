package com.mod.mathsmod.add.World;

import java.util.Random;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.config.MConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OresGen implements IWorldGenerator
{

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{

		switch ( world.provider.getDimension() )
		{

			case -1:
				generateNether(world, random, chunkX * 16, chunkZ * 16);
				break;


			case 0:
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
				break;


			case 1:
				generateEnd(world, random, chunkX * 16, chunkZ * 16);
				break;
	
		}

	}

	private void generateEnd(World world, Random random, int x, int z)
	{
		this.generateOre(RegisterBlocks.KURAYUM_ORE, Blocks.END_STONE, world, random, 2 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "kurayum vein size bonus", 0).getInt(), 4 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "kurayum in chunks bonus", 0).getInt(), x, z, 40, 60);
	}

	private void generateSurface(World world, Random random, int x, int z)
	{
		if(random.nextInt(4) == 0)
		{
			this.generateOre(RegisterBlocks.SILVER_ORE, Blocks.STONE, world, random, 6 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "silver vein size bonus", 0).getInt(), 4 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "silver in chunks bonus", 0).getInt(), x, z, 3, 30);
		}
		else
		{
			this.generateOre(RegisterBlocks.SILVER_ORE, Blocks.STONE, world, random, 4 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "silver vein size bonus", 0).getInt(), 3 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "silver in chunks bonus", 0).getInt(), x, z, 3, 30);
		}
		
	}

	private void generateNether(World world, Random random, int x, int z)
	{
		for(int i = 0; i < 3; ++i)
		{
			int y = random.nextInt(60) + 20;
			int x2 = x + random.nextInt(16);
			int z2 = z + random.nextInt(16);
			
			if(world.getBlockState(new BlockPos(x2, y + 1, z2)).getBlock() == Blocks.AIR && world.getBlockState(new BlockPos(x2, y, z2)).getBlock() == Blocks.NETHERRACK)
			{
				world.setBlockState(new BlockPos(x2, y + 1, z2), RegisterBlocks.HELL_FLOWER.getDefaultState());
			}
		}
		this.generateOre(RegisterBlocks.TFOSIUM_ORE, Blocks.NETHERRACK, world, random, 6 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "tfosium vein size bonus", 0).getInt(), 2 + MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "tfosium in chunks bonus", 0).getInt(), x, z, 3, 126);
		//this.generateRareOre(RegisterBlocks.TFOSIUM_BLOCK, Blocks.NETHERRACK, world, random, 4, 10, 1, x, z, 3, 126);
	}

	public void generateOre(Block ore, Block block, World world, Random random, int maxVeinSize, int chancesToSpawn,int x, int z, int minY, int maxY)
	{
	    for(int i = 0; i < chancesToSpawn; i++)
	    {
	        assert maxY > minY;
		    assert minY > 0;
		    assert maxY < 256 && maxY > 0;
		    
		    int posY = random.nextInt(maxY - minY);
	
		    WorldGenerator oreGen = new WorldGenMinable(ore.getDefaultState(), maxVeinSize, BlockMatcher.forBlock(block));
		    oreGen.generate(world, random, new BlockPos(x + random.nextInt(16), minY + posY, z + random.nextInt(16)));
	    }

	}
	
	public void generateRareOre(Block ore, Block block, World world, Random random, int maxVeinSize, int chancetoSpawnChunk, int chancesToSpawn,int x, int z, int minY, int maxY)
	{
		for(int i = 0; i < chancesToSpawn; i++)
	    {
			if(random.nextInt(chancetoSpawnChunk) == 0)
			{
					
		        assert maxY > minY;
			    assert minY > 0;
			    assert maxY < 256 && maxY > 0;
			    
			    int posY = random.nextInt(maxY - minY);
		
			    WorldGenerator oreGen = new WorldGenMinable(ore.getDefaultState(), maxVeinSize, BlockMatcher.forBlock(block));
			    oreGen.generate(world, random, new BlockPos(x + random.nextInt(16), minY + posY, z + random.nextInt(16)));
		    }
		}
	}
	
	public static void generateFlower(World world, Random random, int rarity, int perChunks, int minX, int maxX, Block base, IBlockState flower, int x, int z)
	{
		if(random.nextInt(rarity) == 0)
		{
			for(int i = 0; i < perChunks; ++i)
			{
				for(int j = minX; j <= maxX; ++j)
				{
					int posX = x + random.nextInt(16);
					int posZ = z + random.nextInt(16);
					int posY = j;
					
					if(world.getBlockState(new BlockPos(posX, posY - 1, posZ)).getBlock() == base)
					{
						world.setBlockState(new BlockPos(posX, posY, posZ), flower);
					}
				}
				
			}
		}
	}

}