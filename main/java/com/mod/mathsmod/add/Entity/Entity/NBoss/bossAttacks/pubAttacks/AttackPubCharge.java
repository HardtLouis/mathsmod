package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackPubCharge extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity)
	{
		return 30;
	}

	@Override
	public int cooldown(EntityACBoss entity) 
	{
		if(entity.getHealth() < entity.getMaxHealth() / 2)
		{
			return 0;
		}
		return 20;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		if(entity instanceof EntityPub)
		{
			Entity target = ((EntityPub) entity).getTarget();
			if(target != null && UtilsVoids.distanceIgnoreY(target.posX, target.posZ, entity) < 20)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		if(duree == 1)
		{
			Vec3d vec = UtilsVoids.laser(0.7, UtilsVoids.getYawFromPos(target.getPositionVector(), entity.posX, entity.posZ), 0);
			entity.motionX = vec.x;
			entity.setJumping(true);
			entity.motionZ = vec.z;
			if(entity instanceof EntityPub)
			{
				((EntityPub) entity).chMotionX = ((EntityPub) entity).motionX;
				((EntityPub) entity).chMotionZ = ((EntityPub) entity).motionZ;
			}
		}
		else if(entity instanceof EntityPub)
		{
			entity.motionX = ((EntityPub) entity).chMotionX;
			entity.setJumping(true);
			entity.motionZ = ((EntityPub) entity).chMotionZ;
		}
		
		if(entity instanceof EntityPub)
		{
			if(world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().grow(1F, 0, 1F)).contains(target))
			{
				world.playSound(null, entity.posX, entity.posY + 2, entity.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 20.0F, 1.0F);
				target.attackEntityFrom(DamageSource.causeMobDamage(entity), 18);
				this.cancel((EntityACBoss) entity);
			}
			else if(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ <= 0.1 && duree > 1)
			{
				this.onStop(entity, world, target);
				this.cancel((EntityACBoss) entity);
			}
		}
	}
	
	public void cancel(EntityACBoss entity)
	{
		super.cancel(entity);
		((EntityPub) entity).setProtecTime(0);
		((EntityPub) entity).setAttackAnim(3);
		((EntityPub) entity).setStrikeTime(15);
		((EntityPub) entity).chMotionX = 0;
		((EntityPub) entity).chMotionZ = 0;
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(entity instanceof EntityPub)
		{
			((EntityPub) entity).setCoolTime(60);
		}
	}

	@Override
	public String objectID() 
	{
		return "attack.pub.charge";
	}

	@Override
	public int chancesFactor()
	{
		return 3;
	}

}
