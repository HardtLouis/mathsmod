package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks;

import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ChauvoputChargeAttack extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 50;
	}

	@Override
	public int cooldown(EntityACBoss entity)
	{
		return 10;
	}

	@Override
	public boolean canUse(EntityACBoss entity)
	{
		return true;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree)
	{
		if(duree == 1)
		{
			entity.addPotionEffect(new PotionEffect(RegisterPotions.CHARGE, 50));
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target)
	{
	}

	@Override
	public String objectID() 
	{
		return "object.chauvoput.charge";
	}

	@Override
	public int chancesFactor() 
	{
		return 3;
	}

}