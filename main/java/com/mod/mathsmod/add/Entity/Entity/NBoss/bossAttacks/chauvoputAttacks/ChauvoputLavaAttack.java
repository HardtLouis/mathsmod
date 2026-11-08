package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.chauvoputAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks.BaseBossAttack;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityChauvoputBlock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ChauvoputLavaAttack extends BaseBossAttack
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 40;
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
		if(duree == 30)
		{
			for(int i = 0; i <= 20; ++i)
			{
				EntityChauvoputBlock block = new EntityChauvoputBlock(world, entity, 20.0F);
				block.rotationPitch = 90;
				block.setMultiplier(0.14F);
				block.setLocationAndAngles(target.posX + entity.getRNG().nextFloat() * 10 - 5, target.posY + 10 + entity.getRNG().nextFloat() * 10, target.posZ + entity.getRNG().nextFloat() * 10 - 5, 0, 90);
				if(!world.isRemote)
				{
					world.spawnEntity(block);
				}
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
		return "object.chauvoput.lava_attack";
	}

	@Override
	public int chancesFactor() 
	{
		return 3;
	}

}
