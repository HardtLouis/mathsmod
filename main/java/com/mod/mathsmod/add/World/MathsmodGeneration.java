package com.mod.mathsmod.add.World;

import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class MathsmodGeneration
{
	
	public static void generatePrettyMountains(int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, ChunkPrimer primer) 
	{
		Random rand1 = new Random(world.getSeed());
		NoiseGeneratorSimplex lperlinNoise1 = new NoiseGeneratorSimplex(rand1);

        char[] data = ObfuscationReflectionHelper.getPrivateValue(ChunkPrimer.class, primer, 1);
        
	    for(int i = 0; i < 16; i++)
	    {
	    	for(int j = 0; j < 16; j++)
		    {
	    		double genValue = (lperlinNoise1.getValue((chunkX * 16 + i) * 0.0015, (chunkZ * 16 + j) * 0.0015));
	    		double param = 0.4;
	    		if(genValue > 1.0 - param);
	    		{
	    			int bx = (int) ((Math.exp(Math.pow((genValue - (1.0 - param)) / param, 3.0)) - 1.0F) * 75);
	    			
	    			int height = MathsmodGeneration.findGroundBlockIdx(i, j, data);
	    			int h = height + bx > 220 ? 220 : height + bx;
	    			if(primer.getBlockState(i, height, j).getBlock() != Blocks.WATER)
	    			{
		    			for(int z = height; z < h; z++)
		    			{
		    				primer.setBlockState(i, z, j, Blocks.STONE.getDefaultState());
		    			}
	    			}
	    		}
		    }
	    }
	}
	
	public static void genererGrotteType1(int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator) 
	{
		Random rand0 = new Random(world.getSeed() * 3);
		Random rand1 = new Random(world.getSeed());
		Random rand2 = new Random(world.getSeed() * 2);
		Random rand3 = new Random(world.getSeed() / 2);
		Random rand4 = new Random(world.getSeed() / 3);
		
		NoiseGeneratorPerlin lperlinNoise0 = new NoiseGeneratorPerlin(rand0, 1);
	    NoiseGeneratorPerlin lperlinNoise1 = new NoiseGeneratorPerlin(rand1, 1);
	    NoiseGeneratorPerlin lperlinNoise2 = new NoiseGeneratorPerlin(rand2, 1);
	    
	    NoiseGeneratorPerlin lperlinNoise3 = new NoiseGeneratorPerlin(rand3, 1);
	    
	    NoiseGeneratorPerlin lperlinNoise4 = new NoiseGeneratorPerlin(rand4, 1);
	    
	    int rvx1 = (int) (Math.abs((chunkX + world.getSeed())) % 31);
	    int rvz1 = (int) (Math.abs((chunkZ + world.getSeed())) % 18);
	    
	    int rvxn = (int) (Math.abs((chunkX + world.getSeed())) % 31);
	    int rvzn = (int) (Math.abs((chunkZ + 1 + world.getSeed())) % 18);
	    
	    int rvxs = (int) (Math.abs((chunkX + world.getSeed())) % 31);
	    int rvzs = (int) (Math.abs((chunkZ - 1 + world.getSeed())) % 18);
	    
	    int rvxe = (int) (Math.abs((chunkX + 1 + world.getSeed())) % 31);
	    int rvze = (int) (Math.abs((chunkZ + world.getSeed())) % 18);
	    
	    int rvxo = (int) (Math.abs((chunkX - 1 + world.getSeed())) % 31);
	    int rvzo = (int) (Math.abs((chunkZ + world.getSeed())) % 18);
	    
	    Vec3d centre = new Vec3d(8 + ((rvx1 * rvx1 + rvx1) % 5) - 2, 0, 8 + ((rvz1 * rvz1 + rvz1) % 5) - 2);
	    Vec3d pn = new Vec3d(8 + ((rvxn * rvxn + rvxn) % 5) - 2, 0, 16 + ((rvzn * rvzn + rvzn) % 5) - 2);
	    Vec3d ps = new Vec3d(8 + ((rvxs * rvxs + rvxs) % 5) - 2, 0, ((rvzs * rvzs + rvzs) % 5) - 2);
	    Vec3d pe = new Vec3d(16 + ((rvx1 * rvx1 + rvx1) % 5) - 2, 0, 8 + ((rvze * rvze + rvze) % 5) - 2);
	    Vec3d po = new Vec3d(((rvxo * rvxo + rvxo) % 5) - 2, 0, 8 + ((rvzo * rvzo + rvzo) % 5) - 2);
	    
	    for(int i = 0; i < 16; ++i)
	    {
	    	for(int j = 0; j < 16; ++j)
		    {
	    		double genVal0 = (lperlinNoise0.getValue((chunkX * 16 + i) * 0.006, (chunkZ * 16 + j) * 0.006) + 1.0) / 2.0;
	    		double genVal1 = (lperlinNoise1.getValue((chunkX * 16 + i) * 0.006, (chunkZ * 16 + j) * 0.006) + 1.0) / 2.0;
	    		
	    		int b0 = genVal0 > 0.5 ? 1 : 0; 
		    	int b1 = genVal1 > 0.5 ? 1 : 0; 
		    	int type = b0 + b1 * 2;
		    	
		    	if(type == 0) // was used differently, now i have a big flemme to change it
		    	{
		    		int h = (int) (lperlinNoise4.getValue((chunkX * 16 + i) * 0.0003, (chunkZ * 16 + j) * 0.0003) * 5);
		    		double genValue = ((0.5 - genVal0) * (0.5 - genVal1) * 4);
		    		
		    		if(true)
		    		{
		    			int genInfo = (int) (MathHelper.sqrt(genValue) * 20);
		    			
		    			int genY = 24;
		    			for(int k = -genInfo; k < genInfo; k++)
			    		{
			    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h + k,  chunkZ * 16 + j), Blocks.AIR.getDefaultState(), 2 | 16);
			    			//world.setBlockState(new BlockPos(i + chunkX * 16, 30 + k, j + chunkZ * 16), Blocks.AIR.getDefaultState());
			    			//world.getChunkFromChunkCoords(chunkX, chunkZ).setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
			    		}
			    		
			    		Block[] CHOICES = new Block[] {RegisterBlocks.BLUE_CRYSTAL_BLOCK, RegisterBlocks.GREEN_CRYSTAL_BLOCK, RegisterBlocks.RED_CRYSTAL_BLOCK, RegisterBlocks.PURPLE_CRYSTAL_BLOCK};
			    		Block block = Blocks.STONE;
			    		int meta = 0;
			    		
			    		Block[] CHOICES_CRYST = new Block[] {RegisterBlocks.BLUE_CRYSTAL, RegisterBlocks.GREEN_CRYSTAL, RegisterBlocks.RED_CRYSTAL, RegisterBlocks.PURPLE_CRYSTAL, RegisterBlocks.SILVER_CRYSTAL};
			    		
			    		double v = lperlinNoise1.getValue((chunkX * 16 + i) * 0.03, (chunkZ * 16 + j) * 0.03); 
			    		
			    		if(v > 0.45 && v < 0.55)
			    		{
			    			block = CHOICES[rand1.nextInt(4)];
			    		}
			    		else if(rand1.nextInt(2) == 0)
			    		{
			    			meta = 3 + 2 * rand1.nextInt(2);
			    		}
			    		
			    		if(rand2.nextInt(8) == 0 && genInfo > 1)
			    		{
			    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo,  chunkZ * 16 + j), CHOICES_CRYST[rand1.nextInt(5)].getStateFromMeta(1), 2 | 16);
				    		world.setBlockState(new BlockPos(chunkX * 16 + i, genY - 1 + h + genInfo,  chunkZ * 16 + j), CHOICES_CRYST[rand1.nextInt(5)].getStateFromMeta(0), 2 | 16);
			    		}
			    		
			    		double w1 = lperlinNoise1.getValue((chunkX * 16 + i) * 0.1, (chunkZ * 16 + j) * 0.1); 
			    		double w2 = lperlinNoise2.getValue((chunkX * 16 + i) * 0.1, (chunkZ * 16 + j) * 0.1);
			    		double w3 = lperlinNoise3.getValue((chunkX * 16 + i) * 0.1, (chunkZ * 16 + j) * 0.1);
			    		
			    		double w = (w1+w2+w3) / 3;
			    		
			    		if(w > 0.6)
			    		{
			    			int b = (int) (Math.exp(((w - 0.6) * 2.5) - 1.0) * 10);
			    			for(int g = 0; g <= b; g++)
			    			{
			    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo + g,  chunkZ * 16 + j), CHOICES[rand1.nextInt(4)].getDefaultState(), 2 | 16);
			    			}
			    			
			    		}
			    		
			    		if(genInfo > 1)
			    		{
			    			int rt = rand0.nextInt(120);
			    			if(rt == 0)
			    			{
			    				block = Blocks.DIAMOND_ORE;
			    				meta = 0;
			    			}
			    			else if(rt < 4)
			    			{
			    				block = Blocks.COAL_ORE;
			    				meta = 0;
			    			}
			    			else if(rt < 7)
			    			{
			    				block = Blocks.IRON_ORE;
			    				meta = 0;
			    			}
			    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo - 1,  chunkZ * 16 + j), block.getStateFromMeta(meta), 2 | 16);
				    		world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h + genInfo,  chunkZ * 16 + j), block.getStateFromMeta(meta), 2 | 16);
			    		}
		    		}
		    	}
		    	else if(type == 1)
		    	{
		    		double genValue = ((genVal0 - 0.5) * (0.5 - genVal1) * 4);
		    		int h = (int) (lperlinNoise4.getValue((chunkX * 16 + i) * 0.001, (chunkZ * 16 + j) * 0.001) * 4);
		    		
		    		int genInfo = (int) (MathHelper.sqrt(genValue) * 25 + 5 * Math.sin((chunkX * 16 + i) * 0.1 + (chunkZ * 16 + j) * 0.1));
	    			
	    			int genY = 34;
	    			
	    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h + genInfo * 0.4,  chunkZ * 16 + j), Blocks.STONE.getDefaultState(), 2 | 16);
	    			
	    			for(int k = -genInfo; k < genInfo * 0.4 - 1; k++)
		    		{
		    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h + k,  chunkZ * 16 + j), Blocks.AIR.getDefaultState(), 2 | 16);
		    			//world.setBlockState(new BlockPos(i + chunkX * 16, 30 + k, j + chunkZ * 16), Blocks.AIR.getDefaultState());
		    			//world.getChunkFromChunkCoords(chunkX, chunkZ).setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
		    		}
	    			
	    			double v = lperlinNoise1.getValue((chunkX * 16 + i) * 0.08, (chunkZ * 16 + j) * 0.08); 
	    			if(v < 0.7 && v > 0.3)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h + genInfo * 0.4 - 1,  chunkZ * 16 + j), rand0.nextInt(10) == 0 ? RegisterBlocks.GLOWBERRY_BUSH.getDefaultState() : Blocks.LEAVES.getStateFromMeta(12), 2 | 16);
	    			}
	    			
	    			if(h - genInfo < - 8)
	    			{
	    				for(int k = genY + h - genInfo + 1; k < genY - 8; k++)
	    				{
	    					world.setBlockState(new BlockPos(chunkX * 16 + i, k,  chunkZ * 16 + j), Blocks.WATER.getDefaultState(), 2 | 16);
	    				}
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo,  chunkZ * 16 + j), (h - genInfo ==  - 9) ? RegisterBlocks.GRASSY_STONE.getDefaultState() : Blocks.STONE.getDefaultState(), 2 | 16);
	    			}
	    			else
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo,  chunkZ * 16 + j), RegisterBlocks.GRASSY_STONE.getDefaultState(), 2 | 16);
	    				if(rand0.nextInt(30) == 0)
	    				{
	    					world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo + 1,  chunkZ * 16 + j), Blocks.LOG.getDefaultState(), 2 | 16);
	    					for(int j2 = 0; j2 < 2 + rand0.nextInt(3); j2++)
	    					{

		    					world.setBlockState(new BlockPos(chunkX * 16 + i, genY + h - genInfo + 2 + j2,  chunkZ * 16 + j), rand0.nextInt(10) == 0 ? RegisterBlocks.GLOWBERRY_BUSH.getDefaultState() : Blocks.LEAVES.getDefaultState(), 2 | 16);
	    					}
	    				}
	    			}
		    	}
		    	else if(type == 2)
		    	{
		    		double genValue = ((genVal1 - 0.5) * (0.5 - genVal0) * 4);
		    		//int h = (int) (lperlinNoise4.getValue((chunkX * 16 + i) * 0.001, (chunkZ * 16 + j) * 0.001) * 5) + 5;
		    		
		    		int genInfo = (int) (genValue * 22);
	    			
	    			int genY = 27;
	    			
	    			for(int k = -genInfo; k <= genInfo; k++)
		    		{
		    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + k,  chunkZ * 16 + j), Blocks.AIR.getDefaultState(), 2 | 16);
		    			//world.setBlockState(new BlockPos(i + chunkX * 16, 30 + k, j + chunkZ * 16), Blocks.AIR.getDefaultState());
		    			//world.getChunkFromChunkCoords(chunkX, chunkZ).setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
		    		}
	    			
	    			double genValue2 = lperlinNoise3.getValue((chunkX * 16 + i) * 0.2, (chunkZ * 16 + j) * 0.2) - 0.5;
	    			int val = (int) (Math.exp(genValue2 * 2) * 6);
	    			
	    			double genValue3 = lperlinNoise4.getValue((chunkX * 16 + i) * 0.2, (chunkZ * 16 + j) * 0.2) - 0.5;
	    			int val2 = (int) (Math.exp(genValue3 * 2) * 6);
	    			
	    			for(int k = 0; k <= Math.max(0, val - 8); k++)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + genInfo - k,  chunkZ * 16 + j), Blocks.STONE.getStateFromMeta(k == 0 ? 0 : 3 + 2 * rand1.nextInt(2)), 2 | 16);
	    			}
	    			
	    			for(int k = 0; k <= Math.max(0, val2 - 8); k++)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY - genInfo + k,  chunkZ * 16 + j), Blocks.STONE.getStateFromMeta(k == 0 ? 0 : 3 + 2 * rand1.nextInt(2)), 2 | 16);
	    			}
	    			
	    			if(rand0.nextInt(4) == 0)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY - genInfo,  chunkZ * 16 + j), Blocks.MAGMA.getDefaultState(), 2 | 16);
	    			}
		    	}
		    	else
		    	{
		    		double genValue = ((genVal1 - 0.5) * (genVal0 - 0.5) * 4);
		    		
		    		
		    		//int h = (int) (lperlinNoise4.getValue((chunkX * 16 + i) * 0.001, (chunkZ * 16 + j) * 0.001) * 5) + 5;
		    		
		    		int genInfo = (int) ((Math.log(genValue + 0.1) + 2.31) * 6);
	    			
	    			int genY = 16;
	    			
	    			for(int k = -genInfo; k <= genInfo; k++)
		    		{
		    			world.setBlockState(new BlockPos(chunkX * 16 + i, genY + k,  chunkZ * 16 + j), Blocks.AIR.getDefaultState(), 2 | 16);
		    			//world.setBlockState(new BlockPos(i + chunkX * 16, 30 + k, j + chunkZ * 16), Blocks.AIR.getDefaultState());
		    			//world.getChunkFromChunkCoords(chunkX, chunkZ).setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
		    		}
	    			
	    			double genValue2 = lperlinNoise3.getValue((chunkX * 16 + i) * 0.2, (chunkZ * 16 + j) * 0.2) - 0.5;
	    			int val = (int) (Math.exp(genValue2 * 2) * 6);
	    			
	    			double genValue3 = lperlinNoise4.getValue((chunkX * 16 + i) * 0.2, (chunkZ * 16 + j) * 0.2) - 0.5;
	    			int val2 = (int) (Math.exp(genValue3 * 2) * 6);
	    			
	    			for(int k = genInfo + 1; k > 1; --k)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY + k,  chunkZ * 16 + j), RegisterBlocks.WEIRD_WATER.getDefaultState(), 2 | 16);
	    			}
	    			
	    			if(rand0.nextInt(6) == 0)
	    			{
	    				world.setBlockState(new BlockPos(chunkX * 16 + i, genY - genInfo - 1,  chunkZ * 16 + j), RegisterBlocks.WEIRD_STONE.getDefaultState(), 2 | 16);
	    			}
		    	}
		    }
	    }
	}
	
	private static boolean insideChunk(BlockPos pos)
	{
		return pos.getX() >= 0 && pos.getX() < 16 && pos.getZ() >= 0 && pos.getZ() < 16 && pos.getY() >= 0 && pos.getY() < 256;
	}
	
    private static int getBlockIndex(int x, int y, int z)
    {
        return x << 12 | z << 8 | y;
    }
    
    public static int findGroundBlockIdx(int x, int z, char[] data)
    {
        int i = MathsmodGeneration.getBlockIndex(x, 255, z);

        for (int j = 255; j >= 0; --j)
        {
            IBlockState iblockstate = Block.BLOCK_STATE_IDS.getByValue(data[MathsmodGeneration.getBlockIndex(x, j, z)]);

            if (iblockstate != null && iblockstate != Blocks.AIR.getDefaultState())
            {
                return j;
            }
        }

        return 0;
    }
}
