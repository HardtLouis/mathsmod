package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackPubExplosions extends BaseBossAttack
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
		return entity.getHealth() < entity.getMaxHealth() / 2;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		UtilsVoids.setLookEntity(target, entity);
		if(!world.isRemote)
		{
			for(int i = 3; i < 10; ++i)
			{
				Vec3d vec = entity.getPositionVector().add(UtilsVoids.laser(i * 2, entity.rotationYaw, entity.rotationPitch));
				world.createExplosion(entity, vec.x, vec.y, vec.z, 2.5F, true);
			}
		}
	}

	@Override
	public String objectID() 
	{
		return "pub_attack_explosion";
	}

	@Override
	public int chancesFactor() 
	{
		return 1;
	}

}