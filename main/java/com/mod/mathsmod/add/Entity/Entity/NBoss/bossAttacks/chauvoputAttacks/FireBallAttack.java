package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityChauvoputBlock;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class FireBallAttack extends BaseBossAttack
{
	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 10;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 0;
	}

	@Override
	public boolean canUse(EntityACBoss entity) 
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree) 
	{
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
		if(target != null)
		{
			EntityChauvoputBlock proj = new EntityChauvoputBlock(world, entity, 35.0F);
			proj.rotationPitch = UtilsVoids.getPitchFromPos(target.getPositionEyes(1.0F), entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
			proj.rotationYaw = UtilsVoids.getYawFromPos(target.getPositionEyes(1.0F), entity.posX, entity.posZ);
			proj.setMultiplier(0.9F);
			world.spawnEntity(proj);
		}
	}

	@Override
	public String objectID()
	{
		return "object.chauvoput.explosion";
	}

	@Override
	public int chancesFactor() 
	{
		return 3;
	}

}
