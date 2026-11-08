package com.mod.mathsmod.add;

import java.awt.Color;

import com.mod.mathsmod.add.biomes.BiomeBanana;
import com.mod.mathsmod.add.biomes.BiomeCopravegNightmare;
import com.mod.mathsmod.add.biomes.BiomeCopravegRad;
import com.mod.mathsmod.add.biomes.BiomeDesolateLand;
import com.mod.mathsmod.add.biomes.BiomeExtremMountain;
import com.mod.mathsmod.add.biomes.BiomeFrozenForest;
import com.mod.mathsmod.add.biomes.BiomeGreenPlains;
import com.mod.mathsmod.add.biomes.BiomeHighMountains;
import com.mod.mathsmod.add.biomes.BiomeNightmare;
import com.mod.mathsmod.add.biomes.BiomePTree;
import com.mod.mathsmod.add.biomes.BiomeUnusualForest;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class RegisterBiomes
{
	public static final Biome COPRANIGHT = new BiomeCopravegNightmare(new BiomeProperties("coptaveg_nightmare").setBaseHeight(0).setTemperature(300F).setRainDisabled());
	public static final Biome COPRARAD = new BiomeCopravegRad(new BiomeProperties("coptaveg_radioactive").setBaseHeight(0).setTemperature(300F).setRainDisabled());
	
	public static final Biome EXTREM_MOUNTAINS = new BiomeExtremMountain(new BiomeProperties("extem_mountain").setBaseHeight(0.1F).setTemperature(-20F).setSnowEnabled().setHeightVariation(2F).setWaterColor(new Color(0, 0, 20).getRGB()), "extem_mountain");
	public static final Biome FROZEN_PLAINS = new BiomeExtremMountain(new BiomeProperties("frozen_plains").setBaseHeight(0.1F).setTemperature(-17F).setSnowEnabled().setHeightVariation(0.2F).setWaterColor(new Color(0, 0, 20).getRGB()), "frozen_plains");
	public static final Biome FROZEN_FOREST = new BiomeFrozenForest(new BiomeProperties("frozen_forest").setBaseHeight(0.1F).setTemperature(-19F).setSnowEnabled().setHeightVariation(0.3F).setWaterColor(new Color(0, 0, 20).getRGB()));
	
	public static final Biome NIGHTMARE = new BiomeNightmare(new BiomeProperties("nightmare").setBaseHeight(0F).setTemperature(0F).setRainDisabled().setHeightVariation(0.1F).setWaterColor(new Color(107, 200, 100).getRGB()));
	
	public static final Biome HIGH_MOUNTAINS = new BiomeHighMountains(new BiomeProperties("high_mountain").setBaseHeight(0.1F).setTemperature(-20F).setSnowEnabled().setHeightVariation(1.4F).setWaterColor(new Color(100, 100, 250).getRGB()), "high_mountain");
	public static final Biome FLOWER_PLAINS = new BiomeGreenPlains(new BiomeProperties("green_plain").setBaseHeight(0.1F).setTemperature(37F).setHeightVariation(0.01F).setWaterColor(new Color(100, 200, 250).getRGB()));
	
	public static final Biome DESOLATE_LANDS = new BiomeDesolateLand(new BiomeProperties("desolate_lands").setBaseHeight(0.1F).setTemperature(40F).setRainDisabled().setHeightVariation(0.1F).setWaterColor(new Color(100, 100, 200).getRGB()));
	
	public static final Biome PINK_TREE = new BiomePTree(new BiomeProperties("pink_forest").setBaseHeight(0.1F).setTemperature(20F).setRainDisabled().setHeightVariation(0.1F).setWaterColor(new Color(107, 150, 200).getRGB()));
	public static final Biome UNUSUAL_FOREST = new BiomeUnusualForest(new BiomeProperties("unusual_forest").setBaseHeight(0.1F).setTemperature(18F).setRainDisabled().setHeightVariation(0.1F).setWaterColor(new Color(157, 150, 240).getRGB()));
	
	public static final Biome BANANA = new BiomeBanana(new BiomeProperties("banana").setBaseHeight(0.1F).setTemperature(30F).setRainDisabled().setHeightVariation(0.1F).setWaterColor(new Color(10, 10, 200).getRGB()));
	
	public static void register()
	{
		BiomeDictionary.addTypes(COPRANIGHT, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);
		BiomeDictionary.addTypes(COPRARAD, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);
		BiomeDictionary.addTypes(BANANA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);
		BiomeDictionary.addTypes(FROZEN_PLAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(EXTREM_MOUNTAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MOUNTAIN);
		BiomeDictionary.addTypes(FROZEN_FOREST, BiomeDictionary.Type.COLD, BiomeDictionary.Type.FOREST);
		
		BiomeDictionary.addTypes(HIGH_MOUNTAINS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MOUNTAIN);
		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(HIGH_MOUNTAINS, 5));
		BiomeManager.addStrongholdBiome(HIGH_MOUNTAINS);
		BiomeManager.addSpawnBiome(HIGH_MOUNTAINS);
		
		BiomeDictionary.addTypes(FLOWER_PLAINS, BiomeDictionary.Type.PLAINS);
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(FLOWER_PLAINS, 4));
		BiomeManager.addVillageBiome(FLOWER_PLAINS, true);
		BiomeManager.addStrongholdBiome(FLOWER_PLAINS);
		BiomeManager.addSpawnBiome(FLOWER_PLAINS);
		
		BiomeDictionary.addTypes(DESOLATE_LANDS, BiomeDictionary.Type.PLAINS);
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(DESOLATE_LANDS, 8));
		BiomeManager.addStrongholdBiome(DESOLATE_LANDS);
		BiomeManager.addSpawnBiome(DESOLATE_LANDS);
		
		BiomeDictionary.addTypes(PINK_TREE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HOT);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(PINK_TREE, 8));
		BiomeManager.addStrongholdBiome(PINK_TREE);
		BiomeManager.addSpawnBiome(PINK_TREE);
		
		
		BiomeDictionary.addTypes(UNUSUAL_FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.CONIFEROUS);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(UNUSUAL_FOREST, 7));
		BiomeManager.addStrongholdBiome(UNUSUAL_FOREST);
		BiomeManager.addSpawnBiome(UNUSUAL_FOREST);
	}
}
