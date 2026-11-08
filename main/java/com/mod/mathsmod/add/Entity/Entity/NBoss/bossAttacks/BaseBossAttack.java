package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public abstract class BaseBossAttack 
{
	public abstract int timeToUse(EntityACBoss entity);
	
	public abstract int cooldown(EntityACBoss entity);
	
	public abstract boolean canUse(EntityACBoss entity);
	
	public abstract void onUseTick(EntityLivingBase entity, World world, @Nullable Entity target, int duree);
	
	public abstract void onStop(EntityLivingBase entity, World world, @Nullable Entity target);
	
	public abstract String objectID();
	
	public abstract int chancesFactor();
	
	public void cancel(EntityACBoss entity)
	{
		entity.setAttackTo("null");
		entity.setTimer(-this.cooldown(entity));
	}
}
