package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class AttackThunder extends BaseBossAttack 
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 80 + (entity.getHealth() < entity.getMaxHealth() / 2 ? 40 : 0);
	}

	@Override
	public int cooldown(EntityACBoss entity) 
	{
		return 2;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;//entity.getHealth() < entity.getMaxHealth() / 2;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		if(duree >= 60 && duree % 10 == 0)
		{
			if(world.getHeight((int)target.posX, (int)target.posZ) < target.posY + target.getEyeHeight())
			{
				target.attackEntityFrom(DamageSource.LIGHTNING_BOLT.setDamageBypassesArmor(), 5);
			}
			world.addWeatherEffect(new EntityLightningBolt(world, target.posX, world.getHeight((int)target.posX, (int)target.posZ), target.posZ, true));
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(world.getHeight((int)target.posX, (int)target.posZ) < target.posY + target.getEyeHeight())
		{
			target.attackEntityFrom(DamageSource.LIGHTNING_BOLT.setDamageBypassesArmor(), 8);
		}
		world.addWeatherEffect(new EntityLightningBolt(world, target.posX, world.getHeight((int)target.posX, (int)target.posZ), target.posZ, true));
	}

	@Override
	public String objectID() 
	{
		return "object.poulopopAttack.thunder";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}