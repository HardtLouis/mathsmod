package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySuperHunterDeathOrb extends EntityThrowable
{

	private int live;
	
	public EntitySuperHunterDeathOrb(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
	}
	
	public EntitySuperHunterDeathOrb(World worldIn, EntityLivingBase throwerIn, float rotationPitch, float rotationYaw, float velocity) 
	{
		super(worldIn, throwerIn);
		this.shoot(throwerIn, rotationPitch, rotationYaw, -1F, velocity, 0);
		this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - 1, throwerIn.posZ);
	}
	
	public void onUpdate()
	{
		
		super.onUpdate();
		
		if(this.live < 100)
		{
			++live;
		}
		else
		{
			this.setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) 
	{
		if(result.entityHit != null)
		{
			if(result.entityHit instanceof EntityLivingBase)
			{
				UtilsVoids.attackEntityFrom((EntityLivingBase) result.entityHit, DamageSource.MAGIC, 100);
			}
		}
		else
		{
			this.setDead();
		}
	}
	
	protected float getGravityVelocity()
    {
        return 0F;
    }

}
