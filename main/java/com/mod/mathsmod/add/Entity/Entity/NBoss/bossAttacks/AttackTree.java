package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopRoot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class AttackTree extends BaseBossAttack 
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
			for(int i = 0; i < 4; ++i)
			{
				//TODO à implementer
				EntityPoulopopRoot ent = new EntityPoulopopRoot(world);
				double posX = (entity.getRNG().nextFloat() * 3) - 1.5F + target.posX + target.motionX * 6;
				double posZ = (entity.getRNG().nextFloat() * 3) - 1.5F + target.posZ + target.motionZ * 6;
				ent.setBasePos((float)posX, (float)posZ);
				double posY = world.getHeight((int)posX, (int)posZ);
				ent.setPosition(posX, posY, posZ);
				world.spawnEntity(ent);
			}
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
	}

	@Override
	public String objectID() 
	{
		return "object.poulopopAttack.roots";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}
