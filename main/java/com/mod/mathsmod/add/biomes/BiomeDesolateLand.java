package com.mod.mathsmod.add.biomes;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.Entity.Entity.EntityPyromancer;
import com.mod.mathsmod.add.World.dims.generator.structures.GenerateDeadTree;
import com.mod.mathsmod.add.World.dims.generator.structures.WorldGenDTree;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeDesolateLand  extends Biome
{
	
	protected static final WorldGenDTree BURNED_TREE = new WorldGenDTree();

    public BiomeDesolateLand(Biome.BiomeProperties properties)
    {
        super(properties);
        this.setRegistryName("desolate_lands");
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPyromancer.class, 30, 3, 4));
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
    	for (int i = 0; i < 22; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            int l = worldIn.getHeight(pos.add(j, 0, k)).getY();
            if(Blocks.FIRE.canPlaceBlockAt(worldIn, pos.add(j, l, k)))
            {
                worldIn.setBlockState(pos.add(j, l, k), RegisterBlocks.ETERNAL_FIRE.getDefaultState());
            }
        }
        
        if(rand.nextInt(6) == 0)
        {
        	for (int i = 0; i < 3; ++i)
            {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = worldIn.getHeight(pos.add(j, 0, k)).getY();
                if(worldIn.getBlockState(pos.add(j, l - 1, k)).isFullCube())
                {
                	BURNED_TREE.generate(worldIn, rand, pos.add(j, l, k));
                }
                
            }
        }
        
        

        super.decorate(worldIn, rand, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
    	return BURNED_TREE;
    }
}