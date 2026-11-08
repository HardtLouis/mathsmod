package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTMissile extends EntityAMissile
{
	public EntityTMissile(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityTMissile(World worldIn, BlockPos pos) 
	{
		super(worldIn, pos);
	}

	protected void onImpact()
	{
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 30))
		{
			float damages = (40 - entity.getDistance(this)) * 4;
			UtilsVoids.attackEntityFrom(entity, DamageSource.causeExplosionDamage((EntityLivingBase)null), damages);
		}
		PerfectExplosion.EntityBoom(null, this, 25, true, true, false);
		this.setDead();
	}
	
	@Override
	protected float getSpeed() 
	{
		return 1F;
	}
}
