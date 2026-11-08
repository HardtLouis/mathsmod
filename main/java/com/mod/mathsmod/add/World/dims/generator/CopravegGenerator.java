package com.mod.mathsmod.add.World.dims.generator;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityShark;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateDeadTree;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateGiantPlant;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateMagmaPlant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenEndGateway;
import net.minecraft.world.gen.feature.WorldGenEndIsland;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenEndCity;

public class CopravegGenerator implements IChunkGenerator
{
    /** RNG. */
    private final Random rand;
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    private final WorldGenerator thoriumGen = new WorldGenMinable(RegisterBlocks.THORIUM_ORE.getDefaultState(), 3, BlockMatcher.forBlock(RegisterBlocks.BLACK_STONE));
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen5;
    /** A NoiseGeneratorOctaves used in generating terrain */
    public NoiseGeneratorOctaves noiseGen6;
    /** Reference to the World object. */
    private final World world;
    /** are map structures going to be generated (e.g. strongholds) */
    private final boolean mapFeaturesEnabled;
    private final BlockPos spawnPoint;
    private NoiseGeneratorSimplex islandNoise;
    private double[] buffer;
    /** The biomes that are used to generate the chunk */
    private Biome[] biomesForGeneration;
    double[] pnr;
    double[] ar;
    double[] br;
    // temporary variables used during event handling
    private int chunkX = 0;
    private int chunkZ = 0;

    public CopravegGenerator(World p_i47241_1_, boolean p_i47241_2_, long p_i47241_3_, BlockPos p_i47241_5_)
    {
        this.world = p_i47241_1_;
        this.mapFeaturesEnabled = p_i47241_2_;
        this.spawnPoint = p_i47241_5_;
        this.rand = new Random(p_i47241_3_);
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.islandNoise = new NoiseGeneratorSimplex(this.rand);

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd(lperlinNoise1, lperlinNoise2, perlinNoise1, noiseGen5, noiseGen6, islandNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(p_i47241_1_, this.rand, ctx);
        this.lperlinNoise1 = ctx.getLPerlin1();
        this.lperlinNoise2 = ctx.getLPerlin2();
        this.perlinNoise1 = ctx.getPerlin();
        this.noiseGen5 = ctx.getDepth();
        this.noiseGen6 = ctx.getScale();
        this.islandNoise = ctx.getIsland();
    }

    /**
     * Generates a bare-bones chunk of nothing but stone or ocean blocks, formed, but featureless.
     */
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        int i = 2;
        int j = 3;
        int k = 33;
        int l = 3;
        this.buffer = this.getHeights(this.buffer, x * 2, 0, z * 2, 7, 20, 7);

        for (int i1 = 0; i1 < 2; ++i1)
        {
            for (int j1 = 0; j1 < 2; ++j1)
            {
                for (int k1 = 0; k1 < 10; ++k1)
                {
                    double d0 = 0.25D;
                    double d1 = this.buffer[((i1 + 0) * 7 + j1 + 0) * 20 + k1 + 0];
                    double d2 = this.buffer[((i1 + 0) * 7 + j1 + 1) * 20 + k1 + 0];
                    double d3 = this.buffer[((i1 + 1) * 7 + j1 + 0) * 20 + k1 + 0];
                    double d4 = this.buffer[((i1 + 1) * 7 + j1 + 1) * 20 + k1 + 0];
                    double d5 = (this.buffer[((i1 + 0) * 7 + j1 + 0) * 20 + k1 + 1] - d1) * 0.25D;
                    double d6 = (this.buffer[((i1 + 0) * 7 + j1 + 1) * 20 + k1 + 1] - d2) * 0.25D;
                    double d7 = (this.buffer[((i1 + 1) * 7 + j1 + 0) * 20 + k1 + 1] - d3) * 0.25D;
                    double d8 = (this.buffer[((i1 + 1) * 7 + j1 + 1) * 20 + k1 + 1] - d4) * 0.25D;

                    for (int l1 = 0; l1 < 6; ++l1)
                    {
                        double d9 = 0.125D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for (int i2 = 0; i2 < 8; ++i2)
                        {
                            double d14 = 0.125D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for (int j2 = 0; j2 < 8; ++j2)
                            {
                                IBlockState iblockstate = AIR;

                                if (d15 > 0.0D)
                                {
                                	i = rand.nextInt(7);
                                	if(i == 0)
                                	{
                                		iblockstate = Blocks.MAGMA.getDefaultState();
                                	}
                                	else
                                	{
                                		iblockstate = RegisterBlocks.BLACK_STONE.getDefaultState();
                                		if(rand.nextInt(1000) == 0)
                                		{
                                			iblockstate = RegisterBlocks.GREEN_LAVA.getDefaultState();
                                		}
                                	}
                                }
                                
                                int k2 = i2 + i1 * 8;
                                int l2 = l1 + k1 * 4;
                                int i3 = j2 + j1 * 8;
                                
                                if(rand.nextInt(200000) == 0)
                            	{
                            		for(int ik = 1; ik< 256; ++ik)
                            		{
                            			primer.setBlockState(k2, ik, i3, Blocks.NETHER_BRICK_FENCE.getDefaultState());
                            		}
                            	}
                                
                                if(l2 <= 31 && iblockstate != AIR)
                                {
                                	i = rand.nextInt(4);
                                	if(i == 0)
                                	{
                                		iblockstate = Blocks.MOSSY_COBBLESTONE.getDefaultState();
                                	}
                                	if(i == 1)
                                	{
                                		iblockstate = Blocks.CLAY.getDefaultState();
                                	}
                                	if(i == 2)
                                	{
                                		iblockstate = Blocks.ICE.getDefaultState();
                                	}
                                	if(i == 3)
                                	{
                                		iblockstate = Blocks.PACKED_ICE.getDefaultState();
                                	}
                                }
                                
                                if(rand.nextInt(100) == 0 && l2 == 87 && iblockstate != Blocks.AIR.getDefaultState())
                            	{
                            		iblockstate = RegisterBlocks.GREEN_LAVA.getDefaultState();
                            	}
                                
                                if(l2 < 87 && iblockstate.isFullCube() && rand.nextInt(51200) == 0)
                            	{
                            		iblockstate = RegisterBlocks.COPRAVEG_RG.getDefaultState();
                            	}
                                
                                if(l2 > 0)
                                {
                                	primer.setBlockState(k2, l2, i3, iblockstate);
                                }
                                
                                if(l2 > 0 && l2 + 1 == 42 && primer.getBlockState(k2, l2, i3) != AIR && primer.getBlockState(k2, l2 + 1, i3) == AIR && rand.nextInt(64000) == 0)
                                {
                                	primer.setBlockState(k2, l2 + 1, i3, RegisterBlocks.BLOCKSHS.getDefaultState());
                                }
                                
                                if(l2 > 0 && l2 + 1 == 42 && primer.getBlockState(k2, l2, i3) != AIR && primer.getBlockState(k2, l2 + 1, i3) == AIR && rand.nextInt(640000) == 0)
                                {
                                	primer.setBlockState(k2, l2 + 1, i3, RegisterBlocks.POULOPOP_ALTAR.getDefaultState());
                                }
                                
                                for(int op = 1; op <= 31; ++op)
                                {
                                	k2 = MathHelper.clamp(k2, 0, 16);
                                	i3 = MathHelper.clamp(i3, 0, 16);
                                	
                                	if(primer.getBlockState(k2, op, i3).getMaterial() == Material.AIR)
                                	{
                                		primer.setBlockState(k2, op, i3, Blocks.WATER.getDefaultState());
                                	}
                                }
                                
                                d15 += d16;
                                
                                if(primer.getBlockState(0, 41, 0).isFullCube() && this.rand.nextInt(1024000) == 0)
                                {
                                	this.placeSkull(primer);
                                }
                                
                                
                                primer.setBlockState(k2, 0, i3, Blocks.BEDROCK.getDefaultState());
                                
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
        
        if(primer.getBlockState(0, 41, 0).isFullCube() && this.rand.nextInt(40) == 0 && primer.getBlockState(4, 46, 4).getBlock() != Blocks.BONE_BLOCK)
        {
        	this.placeThunderTower(primer, x * 16, z * 16);
        }
        
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
        //this.buildSurfaces(chunkprimer);
        
        

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        
        if(chunk.getBlockState(4, 43, 4).getBlock() == Blocks.CHEST)
        {
        	TileEntity te = Blocks.CHEST.createTileEntity(world, chunk.getBlockState(4, 43, 4));
    		te.setPos(new BlockPos(x * 16 + 4, 43, z * 16 + 4));
    		if(te instanceof TileEntityChest)
    		{
    			((TileEntityChest) te).setInventorySlotContents(13, new ItemStack(RegisterItems.SKY_SWORD));
    		}
    		chunk.addTileEntity(new BlockPos(x * 16 + 4, 43, z * 16 + 4), te);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_)
    {
        float f = (float)(p_185960_1_ * 2 + p_185960_3_);
        float f1 = (float)(p_185960_2_ * 2 + p_185960_4_);
        float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;

        if (f2 > 80.0F)
        {
            f2 = 80.0F;
        }

        if (f2 < -100.0F)
        {
            f2 = -100.0F;
        }

        for (int i = -12; i <= 12; ++i)
        {
            for (int j = -12; j <= 12; ++j)
            {
                long k = (long)(p_185960_1_ + i);
                long l = (long)(p_185960_2_ + j);

                if (k * k + l * l > 4096L && this.islandNoise.getValue((double)k, (double)l) < -0.8999999761581421D)
                {
                    float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
                    f = (float)(p_185960_3_ - i * 2);
                    f1 = (float)(p_185960_4_ - j * 2);
                    float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;

                    if (f4 > 80.0F)
                    {
                        f4 = 80.0F;
                    }

                    if (f4 < -100.0F)
                    {
                        f4 = -100.0F;
                    }

                    if (f4 > f2)
                    {
                        f2 = f4;
                    }
                }
            }
        }

        return f2;
    }

    public boolean isIslandChunk(int p_185961_1_, int p_185961_2_)
    {
        return true;
    }

    private double[] getHeights(double[] p_185963_1_, int p_185963_2_, int p_185963_3_, int p_185963_4_, int p_185963_5_, int p_185963_6_, int p_185963_7_)
    {

        if (p_185963_1_ == null)
        {
            p_185963_1_ = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];
        }

        double d0 = 684.412D;
        double d1 = 684.412D;
        d0 = d0 * 2.0D;
        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
        int i = p_185963_2_ / 2;
        int j = p_185963_4_ / 2;
        int k = 0;

        for (int l = 0; l < p_185963_5_; ++l)
        {
            for (int i1 = 0; i1 < p_185963_7_; ++i1)
            {
                float f = this.getIslandHeightValue(i, j, l, i1);

                for (int j1 = 0; j1 < p_185963_6_; ++j1)
                {
                    double d2 = this.ar[k] / 1.0D;
                    double d3 = this.br[k] / 512.0D;
                    double d5 = (this.pnr[k] / 10.0D + 1.0D) / 2.0D;
                    double d4;

                    if (d5 < 0.0D)
                    {
                        d4 = d2;
                    }
                    else if (d5 > 1.0D)
                    {
                        d4 = d3;
                    }
                    else
                    {
                        d4 = d2 + (d3 - d2) * d5;
                    }

                    d4 = d4 - 8.0D;
                    d4 = d4 + (double)f;
                    int k1 = 2;

                    if (j1 > p_185963_6_ / 2 - k1)
                    {
                        double d6 = (double)((float)(j1 - (p_185963_6_ / 2 - k1)) / 64.0F);
                        d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d6) + -3000.0D * d6;
                    }

                    k1 = 8;

                    if (j1 < k1)
                    {
                        double d7 = (double)((float)(k1 - j1) / ((float)k1 - 1.0F));
                        d4 = d4 * (1.0D - d7) + -30.0D * d7;
                    }

                    p_185963_1_[k] = d4;
                    ++k;
                }
            }
        }

        return p_185963_1_;
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     */
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
        BlockPos blockpos = new BlockPos(x * 16, 0, z * 16);

        this.world.getBiome(blockpos.add(16, 0, 16)).decorate(this.world, this.world.rand, blockpos);
        long i = (long)x * (long)x + (long)z * (long)z;

        if (i > 4096L)
        {
            float f = this.getIslandHeightValue(x, z, 1, 1);

            if (this.getIslandHeightValue(x, z, 1, 1) > 50.0F)
            {
                int j = this.rand.nextInt(3);

                for (int k = 0; k < j; ++k)
                {
                    int l = this.rand.nextInt(16) + 8;
                    int i1 = this.rand.nextInt(16) + 8;
                    int j1 = this.world.getHeight(blockpos.add(l, 0, i1)).getY();

                    if (j1 > 32)
                    {
                        int k1 = j1 - 1;

                        if (this.world.isAirBlock(blockpos.add(l, k1 + 1, i1)) && this.world.getBlockState(blockpos.add(l, k1, i1)).getBlock() == RegisterBlocks.BLACK_STONE)
                        {
                            GenerateMagmaPlant.placePlantLava(this.world, blockpos.add(l, k1, i1), this.rand, blockpos.add(l, k1, i1), 8, 0);
                        }
                    }
                }
            }
            
            if (this.getIslandHeightValue(x, z, 1, 1) < 10.0F)
            {
                int j = this.rand.nextInt(2);

                for (int k = 0; k < j; ++k)
                {
                    int l = this.rand.nextInt(16) + 8;
                    int i1 = this.rand.nextInt(16) + 8;
                    int j1 = this.world.getHeight(blockpos.add(l, 0, i1)).getY();

                    if (j1 > 32)
                    {
                        int k1 = j1 - 1;

                        if (this.world.isAirBlock(blockpos.add(l, k1 + 1, i1)) && this.world.getBlockState(blockpos.add(l, k1, i1)).isFullCube())
                        {
                            new GenerateDeadTree(false).generate(this.world, this.rand, blockpos.add(l, k1, i1));
                        }
                    }
                }
            }
            else if (this.getIslandHeightValue(x, z, 1, 1) < 30.0F)
            {
            	int j = this.rand.nextInt(2);

                for (int k = 0; k < j; ++k)
                {
                    int l = this.rand.nextInt(16) + 8;
                    int i1 = this.rand.nextInt(16) + 8;
                    int j1 = this.world.getHeight(blockpos.add(l, 0, i1)).getY();

                    if (j1 > 32)
                    {
                        int k1 = j1 - 1;

                        if (this.world.isAirBlock(blockpos.add(l, k1 + 1, i1)) && this.world.getBlockState(blockpos.add(l, k1, i1)).getBlock() == RegisterBlocks.BLACK_STONE)
                        {
                            //GenerateGiantPlant.generate(this.world, this.rand, blockpos.add(l, k1, i1));
                        }
                    }
                }
            }
            
            
            for (int k = 0; k < this.rand.nextInt(3); ++k)
            {
                int l = this.rand.nextInt(16) + 8;
                int i1 = this.rand.nextInt(16) + 8;
                
                
                for(int j1 = 1; j1 < 16; ++j1)
                {
                	int k1 = j1 - 1;
                    BlockPos pos = blockpos.add(l, k1, i1);
                    
                    if(this.world.getBlockState(blockpos.add(l, k1, i1)).isFullCube() && this.world.getBlockState(blockpos.add(l, j1, i1)).getMaterial() == Material.WATER)
                    {
                        GenerateMagmaPlant.placePlantUnderWater(this.world, pos, this.rand, pos, 8, 0);
                    }
                    
                    /*EntityShark entity = new EntityShark(world);
                    entity.setPositionAndRotation(pos.getX() + this.rand.nextInt(8) - 4 , 7 + this.rand.nextInt(10), pos.getZ() + this.rand.nextInt(8) - 4, rand.nextFloat() * 360 - 180, rand.nextFloat() * 180 - 90);
                    world.spawnEntity(entity);*/
                }
            }
            	
        }
        
        if(this.rand.nextInt(3) == 0)
        {
        	for (int l1 = 0; l1 < 4; ++l1)
            {
                this.thoriumGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(50) + 31, this.rand.nextInt(16)));
            }
        }

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
        BlockFalling.fallInstantly = false;
    }

    /**
     * Called to generate additional structures after initial worldgen, used by ocean monuments
     */
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     */
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    }

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
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
	
	private void placeThunderTower(ChunkPrimer primer, int x, int z) 
	{
		for(int i = 0; i <= 13; ++i)
		{
			primer.setBlockState(3, 42 + i, 3, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(5, 42 + i, 3, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(3, 42 + i, 5, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(5, 42 + i, 5, Blocks.STONEBRICK.getDefaultState());
			primer.setBlockState(4, 42 + i, 4, Blocks.RED_NETHER_BRICK.getDefaultState());
		}
		
		for(int i = 0; i <= 3; ++i)
		{
			primer.setBlockState(1, 56 + i, 1, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(7, 56 + i, 7, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(1, 56 + i, 7, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(7, 56 + i, 1, Blocks.CONCRETE.getStateFromMeta(15));
			
			primer.setBlockState(0, 56 + i, 4, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(4, 56 + i, 0, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(8, 56 + i, 4, Blocks.CONCRETE.getStateFromMeta(15));
			primer.setBlockState(4, 56 + i, 8, Blocks.CONCRETE.getStateFromMeta(15));
		}
		
		if(this.rand.nextInt(40) == 0)
		{
			//primer.setBlockState(4, 56, 4, RegisterBlocks.PULSIOR_ALTAR.getDefaultState());
			primer.setBlockState(4, 56, 4, RegisterBlocks.LIGHTNING_GEN.getDefaultState());
		}
		else
		{
			primer.setBlockState(4, 56, 4, RegisterBlocks.LIGHTNING_GEN.getDefaultState());
		}
		
		primer.setBlockState(2, 56, 1, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(1, 56, 2, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(0, 56, 3, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(3, 56, 0, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(0, 56, 5, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(1, 56, 6, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(2, 56, 7, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(3, 56, 8, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(5, 56, 8, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(6, 56, 7, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(7, 56, 6, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(8, 56, 5, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(8, 56, 3, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(7, 56, 2, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(6, 56, 1, Blocks.CONCRETE.getStateFromMeta(15));
		primer.setBlockState(5, 56, 0, Blocks.CONCRETE.getStateFromMeta(15));
		
		for(int i = 2; i <= 6; ++i)
		{
			for(int j = 2; j <= 6; ++j)
			{
				if(i == 2 || j == 2 || i == 6 || j == 6)
				{
					primer.setBlockState(i, 55, j, Blocks.STONE_SLAB.getStateFromMeta(13));
				}
			}
		}
		
		for(int i = 0; i <= 3; ++i)
		{
			primer.setBlockState(1, 55, 3 + i, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(3 + i, 55, 1, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(7, 55, 3 + i, Blocks.STONE_SLAB.getStateFromMeta(13));
			primer.setBlockState(3 + i, 55, 7, Blocks.STONE_SLAB.getStateFromMeta(13));
		}
	}
	
	
}
