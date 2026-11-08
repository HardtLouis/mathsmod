package com.mod.mathsmod.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class PerfectExplosion extends Explosion{

	/** whether or not the explosion sets fire to blocks around it */
    private final Block BlockPosed;
    /** whether or not this explosion spawns smoke particles */
    private final boolean damagesTerrain;
    private final BlockPos pos;
    private final Random random;
    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final Entity exploder;
    private final float size;
    /** A list of ChunkPositions of blocks affected by this explosion */
    private final List<BlockPos> affectedBlockPositions;
    /** Maps players to the knockback vector applied by the explosion, to send to the client */
    private final Map<EntityPlayer, Vec3d> playerKnockbackMap;
    private final Vec3d position;
    private final boolean damages;
    
    public static Explosion EntityBoom(@Nullable Block block, Entity entity, float size, boolean damages, boolean particles, boolean damageEntities)
    {
    	PerfectExplosion explosion = new PerfectExplosion(entity.world, entity, block, entity.posX, entity.posY, entity.posZ, size, damages, damageEntities);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entity.world, explosion)) return explosion;
        explosion.Explode();
        explosion.particle(particles);
        return explosion;
    }
    
    public static Explosion EntityBoom(@Nullable Block block, Entity entity, double x, double y, double z, float size, boolean damages, boolean particles, boolean damageEntities)
    {
    	PerfectExplosion explosion = new PerfectExplosion(entity.world, entity, block, x, y, z, size, damages, damageEntities);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entity.world, explosion)) return explosion;
        explosion.Explode();
        explosion.particle(particles);
        return explosion;
    }
    
    public static Explosion explode(@Nullable Block block, World world, Vec3d pos, float size, boolean damages, boolean particles, boolean damageEntities)
    {
    	PerfectExplosion explosion = new PerfectExplosion(world, null, block, pos.x, pos.y, pos.z, size, damages, damageEntities);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, explosion)) return explosion;
        explosion.Explode();
        explosion.particle(particles);
        return explosion;
    }
	
	public PerfectExplosion(World worldIn, Entity entityIn, Block block, double x, double y, double z, float size, boolean damagesTerrain, boolean damageEntities)
    {
		super(worldIn, entityIn, x,  y, z, size, false, damagesTerrain);
        this.random = new Random();
        damages = damageEntities;
        this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
        this.playerKnockbackMap = Maps.<EntityPlayer, Vec3d>newHashMap();
        this.world = worldIn;
        this.exploder = entityIn;
        this.size = size;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pos = new BlockPos(x, y, z);
        BlockPosed = block;
        this.damagesTerrain = damagesTerrain;
        this.position = new Vec3d(this.x, this.y, this.z);
    }
	
	private void Explode()
	{
		if(this.damagesTerrain)
		{
			for(int x = (int) (size * -1); x < size ; ++x)
			{
				for(int y = (int) (size * -1); y < size ; ++y)
				{
					for(int z = (int) (size * -1); z < size ; ++z)
					{
						if(pos.east(x).up(y).north(z).getDistance(pos.getX(), pos.getY(), pos.getZ()) <= size - 0.5D && world.getBlockState(pos.east(x).up(y).north(z)).getBlock().getExplosionResistance(world, pos.east(x).up(y).north(z), exploder, this) <= size)
						{
							world.getBlockState(pos.east(x).up(y).north(z)).getBlock().onBlockExploded(world, pos.east(x).up(y).north(z), this);
							if(BlockPosed != null)
							{
								if(world.getBlockState(pos.east(x).up(y).north(z).down(1)) != Blocks.AIR.getDefaultState() && BlockPosed.canPlaceBlockAt(world, pos.east(x).up(y).north(z)))
								{
									int r = random.nextInt(5);
									if(r == 0)
									{
										world.setBlockState(pos.east(x).up(y).north(z), BlockPosed.getDefaultState());
									}
								}
							}
							
						}
					}
				}
			}
		}
		if(this.damages)
		{
			for(EntityLivingBase entity : UtilsVoids.getEntitiesPos(EntityLivingBase.class, world, pos, size))
			{
				entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float) (size * 1.2 - entity.getDistanceSq(pos)));
			}
		}
	}
	
	private void particle(boolean spawnParticles)
	{
		this.world.playSound((EntityPlayer)null, this.x, this.y, this.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
		
		if (this.size >= 2.0F && this.damagesTerrain)
        {
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
        }
        else
        {
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
        }
		
		if (spawnParticles)
        {
            double d0 = (double)((float)pos.getX() + this.world.rand.nextFloat());
            double d1 = (double)((float)pos.getY() + this.world.rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + this.world.rand.nextFloat());
            double d3 = d0 - this.x;
            double d4 = d1 - this.y;
            double d5 = d2 - this.z;
            double d6 = (double)MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 = d3 / d6;
            d4 = d4 / d6;
            d5 = d5 / d6;
            double d7 = 0.5D / (d6 / (double)this.size + 0.1D);
            d7 = d7 * (double)(this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
            d3 = d3 * d7;
            d4 = d4 * d7;
            d5 = d5 * d7;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.x) / 2.0D, (d1 + this.y) / 2.0D, (d2 + this.z) / 2.0D, d3, d4, d5);
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);
        }
	}
}
