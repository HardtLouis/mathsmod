package com.mod.mathsmod.add.biomes;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityFrec;
import com.mod.mathsmod.add.Entity.Entity.EntityFrostBlal;
import com.mod.mathsmod.add.Entity.Entity.EntityNightGolem;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideZombie;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateFrozenSpruce;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeNightmare extends Biome
{

    public BiomeNightmare(Biome.BiomeProperties properties)
    {
        super(properties);
        this.setRegistryName("nightmare");
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityNightGolem.class, 3, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySteroideZombie.class, 3, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityFrec.class, 3, 1, 2));
        
    }
    
	@SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(new Color(0, 0, 0).getRGB());
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
    }

    @Override
    public void addDefaultFlowers()
    {
    }
    
    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
    }
}