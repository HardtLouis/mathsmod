package com.mod.mathsmod.add.Entity.Entity.NBoss.bossAttacks;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AttackWater extends BaseBossAttack 
{

	@Override
	public int timeToUse(EntityACBoss entity) 
	{
		return 200;
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
		if(entity instanceof EntityPoulopop)
		{
			if(duree > 100 && target != null)
			{
				if(world.rayTraceBlocks(entity.getPositionEyes(1.0F), target.getPositionVector().addVector(0, target.height * 0.5F, 0), false, true, false) == null)
				{
					target.attackEntityFrom(DamageSource.causeIndirectDamage(null, entity).setDamageBypassesArmor(), 4 + 4 * (1 - (entity.getHealth() / entity.getMaxHealth())));
				}
				
				if(entity.getHealth() < entity.getMaxHealth() * 0.5F && duree % 40 == 0)
				{
					if(world.rayTraceBlocks(entity.getPositionEyes(1.0F), target.getPositionVector().addVector(0, target.height * 0.5F, 0), false, true, false) == null)
					{
						world.createExplosion(entity, target.posX, target.posY, target.posZ, 3, true);
					}
					else
					{
						Vec3d vec = world.rayTraceBlocks(entity.getPositionEyes(1.0F), target.getPositionVector().addVector(0, target.height * 0.5F, 0), false, true, false).hitVec;
						world.createExplosion(entity, vec.x, vec.y, vec.z, 3, true);
					}
				}
			}
		}
	}

	@Override
	public void onStop(EntityLivingBase entity, World world, Entity target) 
	{
		if(entity instanceof EntityPoulopop)
		{
			if(target != null && entity.getHealth() < entity.getMaxHealth() * 0.5F)
			{
				if(world.rayTraceBlocks(entity.getPositionEyes(1.0F), target.getPositionVector().addVector(0, target.height * 0.5F, 0), false, true, false) == null)
				{
					world.createExplosion(entity, target.posX, target.posY, target.posZ, 3, true);
				}
				else
				{
					Vec3d vec = world.rayTraceBlocks(entity.getPositionEyes(1.0F), target.getPositionVector().addVector(0, target.height * 0.5F, 0), false, true, false).hitVec;
					world.createExplosion(entity, vec.x, vec.y, vec.z, 3, true);
				}
			}
		}
	}

	@Override
	public String objectID() 
	{
		return "object.poulopopAttack.water";
	}

	@Override
	public int chancesFactor() 
	{
		return 2;
	}

}
