package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySuperNuclearMissile extends EntityAMissile
{
	public EntitySuperNuclearMissile(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntitySuperNuclearMissile(World worldIn, BlockPos pos) 
	{
		super(worldIn, pos);
	}

	protected void onImpact()
	{
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 75))
		{
			float damages = (75 - entity.getDistance(this)) * 4;
			UtilsVoids.attackEntityFrom(entity, DamageSource.causeExplosionDamage((EntityLivingBase)null), damages);
		}
		PerfectExplosion.EntityBoom(null, this, 100, true, true, false);
		this.setDead();
	}
	
	@Override
	protected float getSpeed() 
	{
		return 0.8F;
	}
}
