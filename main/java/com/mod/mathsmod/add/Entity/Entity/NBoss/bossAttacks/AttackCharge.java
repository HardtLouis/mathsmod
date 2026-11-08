package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import com.mod.mathsmod.add.Entity.Entity.EntityCrackerMiniboss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopMiniSun;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackCharge extends BaseBossAttack 
{
	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 40;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 50;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree) 
	{
		if(duree == 0)
		{
			UtilsVoids.setLookEntity(entity, target);
			((EntityCrackerMiniboss) entity).chargeYaw = entity.rotationYaw;
		}
		if(entity instanceof EntityCrackerMiniboss)
		{
			entity.rotationYaw = ((EntityCrackerMiniboss) entity).chargeYaw;
			Vec3d vec = UtilsVoids.laser(0.4, entity.rotationYaw, 0);
			entity.motionX = vec.x;
			entity.motionZ = vec.z;
			
			for(Entity vic : world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox(), null))
			{
				vic.attackEntityFrom(DamageSource.causeMobDamage(entity).setDamageBypassesArmor(), 60);
			}
			
			if(entity.prevPosX == entity.posX && entity.prevPosZ == entity.posZ)
			{
				((EntityCrackerMiniboss) entity).setAttackTo(null);
			}
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
	}

	@Override
	public String objectID()
	{
		return "cracker.charge";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}