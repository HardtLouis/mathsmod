package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackPubJumpAttack extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 26;
	}

	@Override
	public int cooldown(EntityACBoss entity) 
	{
		if(entity.getHealth() < entity.getMaxHealth() / 2)
		{
			return 0;
		}
		return 30;
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
			entity.motionY = 1;
		}
		
		else if(target != null && entity instanceof EntityPub)
		{
			if(duree == 10)
			{
				((EntityPub) entity).onVec = target.getPositionVector();
				((EntityPub) entity).chMotionX = (target.posX - entity.posX) / 15;
				((EntityPub) entity).chMotionY = (target.posY - entity.posY) / 15;
				((EntityPub) entity).chMotionZ = (target.posZ - entity.posZ) / 15;
			}
			else if(duree > 10)
			{
				entity.motionX = ((EntityPub) entity).chMotionX;
				entity.motionY = ((EntityPub) entity).chMotionY;
				entity.motionZ = ((EntityPub) entity).chMotionZ;
			}
			
			if(world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().grow(1, 1, 1)).contains(target))
			{
				((EntityPub)entity).setAttackAnim(4);
				((EntityPub)entity).setStrikeTime(7);
				this.cancel((EntityACBoss) entity);
				entity.motionX = 0;
				entity.motionZ = 0;
				world.playSound(null, entity.posX, entity.posY + 2, entity.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 20.0F, 1.0F);
				target.attackEntityFrom(DamageSource.causeMobDamage(entity), 20);
			}
		}
		else
		{
			((EntityPub) entity).setCoolTime(10);
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(!world.isRemote && entity instanceof EntityPub)
		{
			((EntityPub) entity).setCoolTime(20);
			((EntityPub)entity).setAttackAnim(4);
			((EntityPub)entity).setStrikeTime(7);
		}
	}
	
	public void cancel(EntityACBoss entity)
	{
		super.cancel(entity);
	}

	@Override
	public String objectID() 
	{
		return "attack.pub.jump_attack";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}
