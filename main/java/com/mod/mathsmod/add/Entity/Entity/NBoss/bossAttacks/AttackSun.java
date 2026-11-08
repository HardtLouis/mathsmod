package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopMiniSun;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class AttackSun extends BaseBossAttack 
{
	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 20;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 2;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree) 
	{
		
		if(duree == 19 && target != null)
		{
			EntityPoulopopMiniSun proj = new EntityPoulopopMiniSun(world);
			proj.setPosition(entity.posX, entity.posY + 4, entity.posZ);
			double predX = target.motionX * (proj.getDistance(target) / 0.7);
			double predY = 0;
			
			if(target.fallDistance > 3)
			{
				predY = target.motionY * (proj.getDistance(target) / 0.7) * 1.5;
			}
			
			double predZ = target.motionZ * (proj.getDistance(target) / 0.7);
			proj.shoot(UtilsVoids.getPitchFromPos(target.getPositionEyes(1.0F).addVector(predX, predY, predZ), entity.posX, entity.posY + 4, entity.posZ), UtilsVoids.getYawFromPos(target.getPositionEyes(1.0F), entity.posX, entity.posZ), 0.5F + (0.5F * (1 - entity.getHealth() / entity.getMaxHealth())));
			world.spawnEntity(proj);
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
	}

	@Override
	public String objectID()
	{
		return "object.poulopopAttack.sun";
	}

	@Override
	public int chancesFactor() 
	{
		return 3;
	}

}
