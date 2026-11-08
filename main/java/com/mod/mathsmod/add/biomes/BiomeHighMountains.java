package com.mod.mathsmod.add.biomes;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityFrostBlal;

import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeHighMountains extends Biome
{


	WorldGenMinable frozen_stone = new WorldGenMinable(RegisterBlocks.PERMICE_STONE.getDefaultState(), 20);
	private static final WorldGenTaiga1 PINE = new WorldGenTaiga1();

	public BiomeHighMountains(BiomeProperties properties, String id) 
	{
		super(properties);
		this.setRegistryName(id);
		
		this.decorator.treesPerChunk = 2;
	}
	
    public BiomeDecorator createBiomeDecorator()
    {
        return new BiomeHighMountains.Decorator();
    }
	
	@SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(new Color(170, 170, 255).getRGB());
    }
	
	public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return PINE;
    }
	
	public float getSpawningChance()
    {
        return 0.4F;
    }
    
    class Decorator extends BiomeDecorator
    {
        private Decorator()
        {
        }

        /**
         * Generates ores in the current chunk
         */
        protected void generateOres(World worldIn, Random random)
        {
            super.generateOres(worldIn, random);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, random, frozen_stone, chunkPos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM))
            this.genStandardOre1(worldIn, random, 10, frozen_stone, 32, 160);
        }
    }

}