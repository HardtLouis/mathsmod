package com.mod.mathsmod.add.biomes;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityBananaCreeper;
import com.mod.mathsmod.add.Entity.Entity.EntityBananman;
import com.mod.mathsmod.add.Entity.Entity.EntityMininana;
import com.mod.mathsmod.add.Entity.Entity.EntityMosquito;
import com.mod.mathsmod.add.Entity.Entity.EntityPyromancer;
import com.mod.mathsmod.add.World.dims.generator.structures.WorldGenDTree;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeBanana extends Biome
{

    public BiomeBanana(Biome.BiomeProperties properties)
    {
        super(properties);
        this.setRegistryName("banana");
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBananman.class, 1000, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBananaCreeper.class, 500, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMininana.class, 1, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMosquito.class, 400, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBananman.class, 1000, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMininana.class, 1, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBananaCreeper.class, 500, 2, 4));
        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.2F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;
        
    }
    
	@SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(new Color(70, 70, 70).getRGB());
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);
    }
}