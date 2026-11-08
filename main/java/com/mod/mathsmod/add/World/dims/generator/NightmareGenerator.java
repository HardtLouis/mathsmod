package com.mod.mathsmod.add.World.dims.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBiomes;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateMazeTest;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureCustom;
import com.mod.mathsmod.add.World.dims.generator.structures.StructureCustomChunk;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class NightmareGenerator implements IChunkGenerator
{
    private final StructureCustomChunk nightlab1;
    private final StructureCustomChunk nightlab2;
    private final StructureCustomChunk nightlab3;

    private final StructureCustomChunk nightc1;
    private final StructureCustomChunk nightc2;
    private final StructureCustomChunk nightc3;
    
    private int chunkX = 0;
    private int chunkZ = 0;
    private Biome[] biomesForGeneration;
    
	private final World world;
	private Random rand = new Random();
	
	private final HashMap<String, List<List<Integer>>> labs;

    public NightmareGenerator(World worldIn, long seed, boolean mapFeaturesEnabledIn, BlockPos blockPos, HashMap<String, List<List<Integer>>> labsIn)
    {
    	world = worldIn;
    	nightlab1 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab"), MathsDims.nightmareID, worldIn);
    	nightlab2 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab_asc"), MathsDims.nightmareID, worldIn);
    	nightlab3 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab_fon"), MathsDims.nightmareID, worldIn);
    	nightc1 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab_c1"), MathsDims.nightmareID, worldIn);
    	nightc2 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab_c2"), MathsDims.nightmareID, worldIn);
    	nightc3 = new StructureCustomChunk(new ResourceLocation(Mathsmod.MODID, "nightmare/nightmare_lab_c3"), MathsDims.nightmareID, worldIn);
    	labs = labsIn;
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
    	String tup = ((int) (x/20) + "/" + (int) (z/20));
    	
    	if(!labs.containsKey(tup))
    	{
    		labs.put(tup, GenerateMazeTest.genererLab(rand));
    	}
        
        for(int i = 0; i < 16; ++i)
        {
        	for(int j = 0; j < 16; ++j)
            {
        		primer.setBlockState(i, 0, j, Blocks.BEDROCK.getDefaultState());
        		primer.setBlockState(i, 1, j, RegisterBlocks.BLACKSTONE_BRICK.getDefaultState());
            }
        }
        
        int i = chunkX % 10;
        int j = chunkZ % 10;
        
        if(i < 0)
        {
        	i += 10;
        }
        
        if(j < 0)
        {
        	j += 10;
        }
        
        for(int div1 = 0; div1 < 2; ++div1)
        {
        	for(int div2 = 0; div2 < 2; ++div2)
            {
                if(labs.get(tup).get(i * 2 + div1).get(j * 2 + div2) == 0)
                {
                	for(int a = 0; a < 8; ++a)
                	{
                		for(int b = 0; b < 8; ++b)
                    	{
                			for(int k = 0; k < 64; ++k)
                        	{
                				Block block = RegisterBlocks.BLACKSTONE_BRICK;
                				if(rand.nextInt(3) == 0)
                				{
                					block = RegisterBlocks.BLACK_STONE;
                				}
                				
                				primer.setBlockState(a + 8 * div1, 1 + k, b + 8 * div2, block.getDefaultState());
                				
                				if(((a == 3 || a == 4) || (b == 3 || b == 4)) && (1 + k) % 3 == 2)
                				{
                					primer.setBlockState(a + 8 * div1, 1 + k, b + 8 * div2, Blocks.GLOWSTONE.getDefaultState());
                				}
                        	}
                    	}
                	}
                }
                else
                {
                	if(i * 2 + div1 == 1 && j * 2 + div2 == 1)
    				{
    					primer.setBlockState(4 + 8 * div1, 1, 4 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(3 + 8 * div1, 1, 4 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(4 + 8 * div1, 1, 3 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(3 + 8 * div1, 1, 3 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    				}
    				
    				if(i * 2 + div1 == 17 && j * 2 + div2 == 17)
    				{
    					primer.setBlockState(4 + 8 * div1, 1, 4 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(3 + 8 * div1, 1, 4 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(4 + 8 * div1, 1, 3 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    					primer.setBlockState(3 + 8 * div1, 1, 3 + 8 * div2, Blocks.SEA_LANTERN.getDefaultState());
    				}
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
    {
    }

    /**
     * Generates the chunk at the specified position, from scratch
     */
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

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     */
    public void populate(int x, int z)
    {
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = RegisterBiomes.NIGHTMARE;

        return biome.getSpawnableList(creatureType);
    }
    /**
     * Called to generate additional structures after initial worldgen, used by ocean monuments
     */
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Nullable
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        return false;
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     */
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    }
    
    
}