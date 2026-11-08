package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ChauvoputEarthquakeAttack extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 80;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 0;
	}

	@Override
	public boolean canUse(EntityACBoss entity)
	{
		return entity.getAttackTarget() != null && entity.getDistance(entity.getAttackTarget()) < 20;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		if(duree == 1)
		{
			entity.motionY = 2;
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
		
	}

	@Override
	public String objectID() 
	{
		return "object.chauvoput.earthquake";
	}

	@Override
	public int chancesFactor() 
	{
		return 4;
	}

}