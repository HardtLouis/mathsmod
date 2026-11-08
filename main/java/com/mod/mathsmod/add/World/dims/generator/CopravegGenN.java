package com.mod.mathsmod.add.World.dims.generator;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateGiantPlant;
import com.mod.mathsmod.add.biomes.gen.GenNightmareSpike;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class CopravegGenN implements IChunkGenerator
{
	
	private NoiseGeneratorPerlin lperlinNoise1;
    private NoiseGeneratorPerlin lperlinNoise2;
    private NoiseGeneratorPerlin lperlinNoise3;
    private NoiseGeneratorPerlin lperlinNoise4;
    private NoiseGeneratorPerlin lperlinNoise5;
    private NoiseGeneratorPerlin lperlinVolcano;
    private NoiseGeneratorPerlin lperlinType;
    private NoiseGeneratorPerlin structs;
    private NoiseGeneratorPerlin biomeVectX;
    private NoiseGeneratorPerlin biomeVectZ;
    private final World world;
    private final Random rand;
    private final Random rand2;
    private final Random rand3;
    private final Random rand6;
    // temporary variables used during event handling
    private int chunkX = 0;
    private int chunkZ = 0;
    /** The biomes that are used to generate the chunk */
    private Biome[] biomesForGeneration;
	private final GenNightmareSpike Nightmare_spike = new GenNightmareSpike();
    double[] d1;
    
    public CopravegGenN(World worldIn, long seed, NoiseGeneratorPerlin biomeVec1, NoiseGeneratorPerlin biomeVec2) 
    {
    	world = worldIn;
        this.rand = new Random(seed);
        this.rand2 = new Random(seed * 2);
        this.rand3 = new Random(seed * 3);
        this.rand6 = new Random(seed * 30);
        this.lperlinNoise1 = new NoiseGeneratorPerlin(rand2, 1);
        this.lperlinNoise2 = new NoiseGeneratorPerlin(this.rand, 1);
        this.lperlinNoise3 = new NoiseGeneratorPerlin(rand2, 1);
        this.lperlinNoise4 = new NoiseGeneratorPerlin(rand3, 1);
        this.lperlinNoise5 = new NoiseGeneratorPerlin(rand3, 1);
        this.lperlinType = new NoiseGeneratorPerlin(this.rand, 1);
        this.structs = new NoiseGeneratorPerlin(this.rand, 1);
        this.lperlinVolcano = new NoiseGeneratorPerlin(this.rand6, 1);

        this.biomeVectX = biomeVec1;
        this.biomeVectZ = biomeVec2;
        
	}

	@Override
	public Chunk generateChunk(int x, int z) 
	{
		this.chunkX = x; this.chunkZ = z;
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.setBlocksInChunk(x, z, chunkprimer);
        
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        chunk.generateSkylightMap();
        return chunk;
	}

	private void setBlocksInChunk(int x, int z, ChunkPrimer chunkprimer) 
	{
		//d1 = this.lperlinNoise1.getRegion(this.d1, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D * 0.25, 0.0625D * 0.25, 1.0D);
		
		for(int i = 0; i < 16; ++i)
		{
			for(int j = 0; j < 16; ++j)
			{
				float t = MathHelper.sqrt((x * 16 + i) * (x * 16 + i) + (z * 16 + j) * (z * 16 + j));
				
				for(int k = 0; k <= 60; ++k)
				{
					chunkprimer.setBlockState(i, 1 + k, j, Blocks.WATER.getDefaultState());
				}
				
				//System.out.print(this.d1[i * 16 + j]);
				for(int k = 0; k <= 20 + this.lperlinNoise1.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 12; ++k)
				{
					if(this.rand.nextInt(4) == 0)
					{
						chunkprimer.setBlockState(i, 1 + k, j, Blocks.SAND.getDefaultState());
					}
					else
					{
						chunkprimer.setBlockState(i, 1 + k, j, RegisterBlocks.BLACK_STONE.getDefaultState());
					}
				}
				
				int islandValueL = -2 + (int) (this.lperlinNoise2.getValue((x * 16 + i) * 0.004, (z * 16 + j) * 0.004) * 6);
				float randPos = rand.nextFloat();
				
				float volval = (float) (((this.lperlinVolcano.getValue((x * 16 + i) * 0.00075, (z * 16 + j) * 0.00075) - 0.7)) / 0.3);
				if(volval >= 0)
				{
					
					for(int k = 0; k <= Math.max(this.volcanFunctionGenerator(volval, 140), (volval > 0.5 ? 130 : 0)) ; ++k)
					{
						if(k > this.volcanFunctionGenerator(volval, 140))
						{
							chunkprimer.setBlockState(i, 1 + k, j, RegisterBlocks.GREEN_LAVA.getDefaultState());
							if(k == 130 && this.rand.nextInt(20000) == 0)
							{
								chunkprimer.setBlockState(i, 132, j, RegisterBlocks.CHAUVOPUT_ALTAR.getDefaultState());
							}
						}
						else
						{
							if(this.rand.nextInt(5) == 0)
							{
								chunkprimer.setBlockState(i, 1 + k, j, Blocks.MAGMA.getDefaultState());
							}
							else
							{
								chunkprimer.setBlockState(i, 1 + k, j, RegisterBlocks.BLACK_STONE.getDefaultState());
							}
						}
					}
				}
				
				if(islandValueL + randPos >= 0)
				{
						for(int k = Math.max(-61, (int) -(Math.exp(islandValueL * 2) * 0.2F)); k <= islandValueL + Math.max(0, this.generationFun((float) ((1 + this.lperlinNoise4.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) / 2), (x * 16 + i), (z * 16 + j))); ++k)
						{
							if(this.rand.nextInt(10) == 0)
							{
								chunkprimer.setBlockState(i, 61 + k, j, Blocks.MAGMA.getDefaultState());
							}
							else
							{
								chunkprimer.setBlockState(i, 61 + k, j, RegisterBlocks.BLACK_STONE.getDefaultState());
							}
						}
						
						IBlockState state = RegisterBlocks.BLACK_STONE.getDefaultState();
						
						double x2 = 1 + biomeVectX.getValue((x * 16 + i) * 0.003, (z * 16 + j) * 0.003);
				    	double z2 = 1 + biomeVectZ.getValue((x * 16 + i) * 0.003, (z * 16 + j) * 0.003);
				    	float rot = UtilsVoids.getYawFromPos(Vec3d.ZERO, x2, z2) - 90;
				    	
				    	if(rot >= 67.5)
				    	{
				    		state = RegisterBlocks.BLACK_STONE.getDefaultState();
				    	}
				    	else if(rot >= 45)
				    	{
				    		state = RegisterBlocks.MOSSY_BLACK_STONE.getDefaultState();
				    	}
				    	else if(rot >= 22.5)
				    	{
				    		state = RegisterBlocks.RADIOACTIVE_BLACKSTONE.getDefaultState();
				    		int bob = this.rand.nextInt(5000);
				    		if(bob == 0)
				    		{
				    			state = RegisterBlocks.THORIUM_ORE.getDefaultState();
				    		}
				    		else if(bob <= 4 && i < 4 && j < 4)
				    		{
				    			this.placeThunderTower(chunkprimer, i, (int) (61 + islandValueL + Math.max(0, this.generationFun((float) ((1 + this.lperlinNoise4.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) / 2), (x * 16 + i), (z * 16 + j)))), j);;
				    		}
				    	}
				    	
				    	if(islandValueL >= 0.5)
				    	{
				    		chunkprimer.setBlockState(i, (int) (61 + islandValueL + Math.max(0, this.generationFun((float) ((1 + this.lperlinNoise4.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) / 2), (x * 16 + i), (z * 16 + j)))), j, state);
				    	}
						
						if(rand.nextInt(48000) == 0)
	                    {
							chunkprimer.setBlockState(i, (int) (62 + islandValueL + Math.max(0, this.generationFun((float) ((1 + this.lperlinNoise4.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) / 2), (x * 16 + i), (z * 16 + j)))), j, RegisterBlocks.BLOCKSHS.getDefaultState());
	                    }
						
						if(rand.nextInt(240000) == 0)
	                    {
							chunkprimer.setBlockState(i, (int) (62 + islandValueL + Math.max(0, this.generationFun((float) ((1 + this.lperlinNoise4.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) / 2), (x * 16 + i), (z * 16 + j)))), j, RegisterBlocks.POULOPOP_ALTAR.getDefaultState());
	                    }
				}
				else
				{
					double islandValueM = Math.abs(this.lperlinNoise3.getValue((x * 16 + i) * 0.03, (z * 16 + j) * 0.03) * 6) - 4 + rand.nextFloat() * 1;
					if(islandValueM >= 0)
					{
						for(int k = Math.max(-61, (int) -(Math.exp(islandValueM * 2) * 0.4F)); k <= islandValueM; ++k)
						{
							if(this.rand.nextInt(6) == 0)
							{
								chunkprimer.setBlockState(i, 61 + k, j, Blocks.MAGMA.getDefaultState());
							}
							else
							{
								chunkprimer.setBlockState(i, 61 + k, j, RegisterBlocks.BLACK_STONE.getDefaultState());
							}
						}
					}
				}
				
				chunkprimer.setBlockState(i, 0, j, Blocks.BEDROCK.getDefaultState());
			}
		}
	}
	
	public float generationFun(float height, int x, int z)
	{
		double type = lperlinType.getValue(x * 0.001f, z * 0.001f);
		if(type < -0.25)
		{
			return 1 + height * 4 + (float) Math.exp(height + 4) * 0.009F;
		}
		else if(type < 0)
		{
			return 5.0F + (float) Math.sin(height * 2) * 5;
		}
		if(type < 0.5)
		{
			return (float) (1 + Math.tanh(height * 0.1) * 10);
		}
		return height;
	}
	
	public float volcanFunctionGenerator(float genVal, int height)
	{
		if(genVal <= 0.5)
		{
			return (genVal / 0.5F) * height;
		}
		else
		{
			float f = (genVal - 0.5F) / 0.5F;
			float f1 = (float) (1.0F - Math.sqrt(f) * 0.4);
			return f1 * height;
		}
	}

	@Override
	public void populate(int x, int z) 
	{
		for(int i = 0; i < 16; ++i)
		{
			for(int j = 0; j < 16; ++j)
			{
				double x2 = 1 + biomeVectX.getValue((x * 16 + i) * 0.001, (z * 16 + j) * 0.001);
		    	double z2 = 1 + biomeVectZ.getValue((x * 16 + i) * 0.001, (z * 16 + j) * 0.001);
		    	float rot = UtilsVoids.getYawFromPos(Vec3d.ZERO, x2, z2) - 90;
		    	
		    	if(rot >= 45 && rot < 67.5 && rand.nextInt(70) == 0 && world.getHeight(new BlockPos((x * 16 + i), 0, (z * 16 + j))).getY() <= 80 && world.getBlockState(world.getHeight(new BlockPos((x * 16 + i), 0, (z * 16 + j))).down()).getBlock() == RegisterBlocks.MOSSY_BLACK_STONE)
		    	{
		    		GenerateGiantPlant.generate(world, rand, world.getHeight(new BlockPos((x * 16 + i), 0, (z * 16 + j))));
		    	}
		    	else if(rot >= 67.5 && rand.nextInt(70) == 0)
		    	{
		    		if(new Random().nextBoolean())
		            {
		                this.Nightmare_spike.generate(world, rand, world.getHeight(new BlockPos((x * 16 + i), 0, (z * 16 + j))));
		            }

		            if(new Random().nextInt(3) == 0)
		            {
		                this.Nightmare_spike.generate(world, rand, world.getHeight(new BlockPos((x * 16 + i), 0, (z * 16 + j))));
		            }
		    	}
			}
		}
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
	
	private void placeSkull(ChunkPrimer primer) 
	{
		for(int a = 1; a <= 7; ++a)
    	{
    		for(int b = 1; b <= 3; ++b)
        	{
    			primer.setBlockState(a, 41, b, Blocks.BONE_BLOCK.getDefaultState());
        	}
    	}
    	
    	for(int a = 1; a <= 7; ++a)
    	{
    		primer.setBlockState(a, 42, 4, Blocks.BONE_BLOCK.getDefaultState());
    	}
    	
    	for(int a = 1; a <= 6; ++a)
    	{
    		for(int b = 1; b <= 4; ++b)
        	{
    			primer.setBlockState(0, 41 + b, a, Blocks.BONE_BLOCK.getDefaultState());
    			primer.setBlockState(0 + 8, 41 + b, a, Blocks.BONE_BLOCK.getDefaultState());
        	}
    	}
    	
    	for(int a = 1; a <= 7; ++a)
    	{
    		for(int b = 1; b <= 4; ++b)
        	{
    			primer.setBlockState(a, 41 + b, 0, Blocks.BONE_BLOCK.getDefaultState());
        	}
    	}
    	
    	for(int a = 1; a <= 7; ++a)
    	{
    		for(int b = 1; b <= 6; ++b)
        	{
        		primer.setBlockState(a, 46, b, Blocks.BONE_BLOCK.getDefaultState());
        	}
    	}
    	
    	for(int a = 1; a <= 7; a += 3)
    	{
    		for(int b = 1; b <= 4; ++b)
        	{
        		primer.setBlockState(a, 41 + b, 7, Blocks.BONE_BLOCK.getDefaultState());
        	}
    	}
    	
    	for(int a = 1; a <= 7; ++a)
    	{
    		for(int b = 0; b <= 4; ++b)
        	{
    			if(b != 1 && b != 2)
    			{
    				primer.setBlockState(a, 41 + b, 7, Blocks.BONE_BLOCK.getDefaultState());
    				if(this.rand.nextInt(100) == 0)
    				{
    					primer.setBlockState(2, 42, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(3, 42, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(2, 43, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(3, 43, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					
    					primer.setBlockState(5, 42, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(6, 42, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(5, 43, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					primer.setBlockState(6, 43, 7, Blocks.REDSTONE_BLOCK.getDefaultState());
    					
    					primer.setBlockState(4, 43, 4, Blocks.CHEST.getDefaultState());
    				}
    			}
        	}
    	}
	}
	
	private void placeThunderTower(ChunkPrimer primer, int x, int y, int z) 
	{
		for(int i = 0; i <= 13; ++i)
		{
			primer.setBlockState(3, y + i, 3, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(5, y + i, 3, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(3, y + i, 5, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(5, y + i, 5, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(4, y + i, 4, Blocks.RED_NETHER_BRICK.getDefaultState());
		}
		
		for(int i = 0; i <= 3; ++i)
		{
			primer.setBlockState(1, y + 14 + i, 1, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(7, y + 14 + i, 7, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(1, y + 14 + i, 7, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(7, y + 14 + i, 1, Blocks.CONCRETE.getStateFromMeta(15));
			
			primer.setBlockState(0, y + 14 + i, 4, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(4, y + 14 + i, 0, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(8, y + 14 + i, 4, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(4, y + 14 + i, 8, Blocks.CONCRETE.getStateFromMeta(15));
		}
		
		if(this.rand.nextInt(40) == 0)
		{
			primer.setBlockState(4, y + 14, 4, RegisterBlocks.THORIUM_BLOCK.getDefaultState());
		}
		else
		{
			primer.setBlockState(4, y + 14, 4, RegisterBlocks.LIGHTNING_GEN.getDefaultState());
		}
		
		primer.setBlockState(2, y + 14, 1, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(1, y + 14, 2, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(0, y + 14, 3, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(3, y + 14, 0, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(0, y + 14, 5, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(1, y + 14, 6, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(2, y + 14, 7, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(3, y + 14, 8, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(5, y + 14, 8, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(6, y + 14, 7, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(7, y + 14, 6, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(8, y + 14, 5, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(8, y + 14, 3, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(7, y + 14, 2, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(6, y + 14, 1, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(5, y + 14, 0, Blocks.CONCRETE.getStateFromMeta(15));
		
		for(int i = 2; i <= 6; ++i)
		{
			for(int j = 2; j <= 6; ++j)
			{
				if(i == 2 || j == 2 || i == 6 || j == 6)
				{
					primer.setBlockState(i, y + 13, j, Blocks.STONE_SLAB.getStateFromMeta(13));
				}
			}
		}
		
		for(int i = 0; i <= 3; ++i)
		{
			primer.setBlockState(1, y + 13, 3 + i, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(3 + i, y + 13, 1, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(7, y + 13, 3 + i, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(3 + i, y + 13, 7, Blocks.STONE_SLAB.getStateFromMeta(13));
		}
	}

}
