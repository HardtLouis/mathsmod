package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPoulopopMiniSun extends Entity
{

	public EntityPoulopopMiniSun(World worldIn) 
	{
		super(worldIn);
		this.setSize(2, 2);
	}

	public void shoot(float rotationPitch, float rotationYaw, float speed)
	{
		Vec3d vec = UtilsVoids.laser(speed, rotationYaw, rotationPitch);
		this.motionX = vec.x;
		this.motionY = vec.y;
		this.motionZ = vec.z;
	}
	
	@Override
	protected void entityInit() 
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
	}
	
	public void onUpdate()
	{
		this.noClip = true;
		super.onUpdate();
		
		if(!this.world.isRemote)
		{
			for(Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))
			{
				if(entity instanceof EntityLivingBase && !(entity instanceof EntityPoulopop))
				{
					entity.attackEntityFrom(DamageSource.MAGIC, 20);
					this.setDead();
				}
			}
			
			if(this.ticksExisted > 100)
			{
				this.setDead();
			}
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}
}
