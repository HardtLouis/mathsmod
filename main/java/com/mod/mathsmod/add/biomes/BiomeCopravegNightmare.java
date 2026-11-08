package com.mod.mathsmod.add.biomes;

import java.awt.Color;
import java.util.Random;

import com.mod.mathsmod.add.Entity.Entity.*;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.biomes.gen.GenNightmareSpike;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeCopravegNightmare extends Biome
{
	
	//private final GenNightmareSpike Nightmare_spike = new GenNightmareSpike();

	public BiomeCopravegNightmare(BiomeProperties properties) 
	{
		super(properties);
		this.setRegistryName("copraveg");
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityDeadHunter.class, 2, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCCreeper.class, 7, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityBlal.class, 10, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCopracrab.class, 10, 3, 3));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 30, 3, 5));
	}
	
	public float getSpawningChance()
    {
        return 0.7F;
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        /*if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM))
        {
            if(new Random().nextBoolean())
            {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                this.Nightmare_spike.generate(worldIn, rand, worldIn.getHeight(pos.add(j, 0, k)));
            }

            if(new Random().nextInt(3) == 0)
            {
                int i1 = rand.nextInt(16) + 8;
                int j1 = rand.nextInt(16) + 8;
                this.Nightmare_spike.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
            }
            
        }*/

        super.decorate(worldIn, rand, pos);
    }
    
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
    	double x = 1 + client_Proxy.biomeVectX.getValue(pos.getX() * 0.005, pos.getZ() * 0.005);
    	double z = 1 + client_Proxy.biomeVectZ.getValue(pos.getX() * 0.005, pos.getZ() * 0.005);
    	float rot = UtilsVoids.getYawFromPos(Vec3d.ZERO, x, z);
        return RenderLaser.getColorFromAngleQuarter(rot);
    }

}
