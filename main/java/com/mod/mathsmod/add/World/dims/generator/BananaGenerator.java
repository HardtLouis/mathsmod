package com.mod.mathsmod.add.World.dims.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityBananaStrongman;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateGiantPlant;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureCustom;
import com.mod.mathsmod.add.biomes.gen.GenNightmareSpike;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class BananaGenerator implements IChunkGenerator
{
	
	private NoiseGeneratorPerlin lperlinNoise1;
    private NoiseGeneratorPerlin lperlinNoise2;
    private NoiseGeneratorPerlin lperlinNoise3;
    private NoiseGeneratorPerlin lperlinNoise4;
    private NoiseGeneratorPerlin lperlinNoise5;
    private NoiseGeneratorPerlin lperlinVolcano;
    private NoiseGeneratorPerlin lperlinType;
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
    private StructureCustom genBananaTower = new StructureCustom(new ResourceLocation(Mathsmod.MODID, "huge_tower"), MathsDims.bananaID);
    
    public BananaGenerator(World worldIn, long seed) 
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
				
				for(int k = 0; k <= 40; ++k)
				{
					chunkprimer.setBlockState(i, 1 + k, j, Blocks.LAVA.getDefaultState());
				}
				
				for(int k = 0; k < 40 + Math.sin(this.lperlinNoise1.getValue((x * 16 + i) * 0.01F, (z * 16 + j) * 0.01F) * 1.57) * 20; ++k)
				{
					chunkprimer.setBlockState(i, k, j, Blocks.SAND.getDefaultState());
				}
				
				chunkprimer.setBlockState(i, 0, j, Blocks.BEDROCK.getDefaultState());
			}
		}
	}

	@Override
	public void populate(int x, int z) 
	{
        if(this.rand.nextInt(30) == 0)
        {
        	BlockPos pos = new BlockPos(x * 16, world.getHeight(x * 16 + 12, z * 16 - 12), z * 16);
        	if(world.getBlockState(pos.up()).getMaterial() == Material.AIR)
        	{
        		int y =  world.getHeight(x * 16 + 12, z * 16 - 12);
        		if(y >= 59)
        		{
                    this.genBananaTower.generateRandomRotation(world, rand, new BlockPos(x * 16 + 12, y - 1, z * 16 - 12));
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
}