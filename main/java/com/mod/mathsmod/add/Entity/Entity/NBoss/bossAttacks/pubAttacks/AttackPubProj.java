package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.pubAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPubProj;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class AttackPubProj extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity)
	{
		return 41;
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
		return entity.getHealth() < entity.getMaxHealth() / 2;
	}

	@Override
	public void onUseTick(EntityLivingBase entity, World world, Entity target, int duree) 
	{
		entity.motionX *= 0.00001;
		entity.motionZ *= 0.00001;
		if(!world.isRemote && duree % 20 == 0 && entity instanceof EntityPub && ((EntityPub) entity).getTarget() != null)
		{
			EntityPubProj ent = new EntityPubProj(world);
			UtilsVoids.setLookEntity(((EntityPub) entity).getTarget(), entity);
			ent.setPosition(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
			ent.shoot(entity.rotationPitch, entity.rotationYaw, 0.1F, entity);
			world.spawnEntity(ent);
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		/*if(!world.isRemote)
		{
			EntityPubProj ent = new EntityPubProj(world);
			Utils.setLookEntity(((EntityPub) entity).getTarget(), entity);
			ent.setPosition(ent.posX, ent.posY + ent.getEyeHeight(), ent.posZ);
			ent.shoot(entity.rotationPitch, entity.rotationYaw, 0.4F, entity);
		}*/
	}

	@Override
	public String objectID()
	{
		return "pub_proj";
	}

	@Override
	public int chancesFactor()
	{
		return 2;
	}

}
