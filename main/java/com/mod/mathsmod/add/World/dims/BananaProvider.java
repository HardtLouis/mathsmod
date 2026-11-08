package com.mod.mathsmod.add.World.dims;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBiomes;
import com.mod.mathsmod.add.World.dims.generator.BananaGenerator;
import com.mod.mathsmod.add.World.dims.generator.NightmareGenerator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProvider.WorldSleepResult;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BananaProvider extends WorldProvider {
	
	public HashMap<String, List<List<Integer>>> labs = Maps.newHashMap();
	
	@Override
	public DimensionType getDimensionType() {
		return MathsDims.Banana;
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
        return WorldSleepResult.BED_EXPLODES;
    }
    
    public IChunkGenerator createChunkGenerator()
    {
        return new BananaGenerator(world, getSeed());
    }
    
    protected void init()
    {
        this.biomeProvider = new BiomeProviderSingle(RegisterBiomes.BANANA);
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks)
    {
        return new Vec3d(1.0, 1.0, 0);
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
    }
    
    public int getRespawnDimension(net.minecraft.entity.player.EntityPlayerMP player)
    {
        return 0;
    }
}