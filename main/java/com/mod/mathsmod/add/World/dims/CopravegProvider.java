package com.mod.mathsmod.add.World.dims;

import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBiomes;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.generator.CopravegGenN;
import com.mod.mathsmod.add.World.dims.generator.CopravegGenerator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProvider.WorldSleepResult;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CopravegProvider extends WorldProvider 
{
	
	public NoiseGeneratorPerlin noise1;
	public NoiseGeneratorPerlin noise2;
	
	@Override
	public DimensionType getDimensionType() 
	{
		return MathsDims.Copraveg;
	}

    public boolean doesWaterVaporize()
    {
        return false;
    }

    public boolean hasSkyLight()
    {
        return false;
    }

    public boolean isNether()
    {
        return false;
    }
    
    public boolean canRespawnHere()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor()
    {
        return 0;
    }
    
    public WorldSleepResult canSleepAt(net.minecraft.entity.player.EntityPlayer player, BlockPos pos)
    {
        return WorldSleepResult.DENY;
    }
    
    public IChunkGenerator createChunkGenerator()
    {
        //return new CopravegGenerator(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed(), this.getSpawnCoordinate());
    	
    	return new CopravegGenN(this.world, this.world.getSeed(), noise1, noise2);
    }
    
    protected void init()
    {
        this.biomeProvider = new CopravegBiomeProvider();
        noise1 = new NoiseGeneratorPerlin(new Random(world.getSeed() * 20), 1);
        noise2 = new NoiseGeneratorPerlin(new Random(world.getSeed() * 30), 1);
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks)
    {
        return new Vec3d(100, 100, 100);
    }
    
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.0F;
    }

    /**
     * Returns array with sunrise/sunset colors
     */
    @Nullable
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
    {
        return new float[] {45F, 32F, 67F, 61F};
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 256;
    }
    
    /*@SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        int i = 10518688;
        float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.627451F;
        float f2 = 0.5019608F;
        float f3 = 0.627451F;
        f1 = f1 * (f * 0.0F + 0.15F);
        f2 = f2 * (f * 0.0F + 0.15F);
        f3 = f3 * (f * 0.0F + 0.15F);
        return new Vec3d((double)f1, (double)f2, (double)f3);
    }*/
    
    public void onWorldUpdateEntities()
    {
    	for(Entity entity : this.world.loadedEntityList)
    	{
    		entity.fallDistance = 0;
    		if(entity.motionY < 0)
    		{
    			entity.motionY *= 0.8F;
    		}
    	}
    }
    
    public Biome getBiomeForCoords(BlockPos pos)
    {
    	double x2 = 1 + noise1.getValue(pos.getX() * 0.003, pos.getZ() * 0.003);
    	double z2 = 1 + noise2.getValue(pos.getX() * 0.003, pos.getZ() * 0.003);
    	float rot = UtilsVoids.getYawFromPos(Vec3d.ZERO, x2, z2) - 90;
    	
    	if(rot >= 22.5 && rot < 45)
    	{
    		return RegisterBiomes.COPRARAD;
    	}
    	
    	return RegisterBiomes.COPRANIGHT;
    }
    
    public int getRespawnDimension(net.minecraft.entity.player.EntityPlayerMP player)
    {
        return 0;
    }
}
