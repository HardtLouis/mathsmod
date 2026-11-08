package com.mod.mathsmod.add.Entity.Entity.objects;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPubProj extends Entity
{
	
	int id;
	public float damages;

	public EntityPubProj(World worldIn) 
	{
		super(worldIn);
		this.setSize(2, 2);
		damages = 12;
		id = -1;
	}

	public void shoot(float rotationPitch, float rotationYaw, float speed, EntityLivingBase entity)
	{
		Vec3d vec = UtilsVoids.laser(speed, rotationYaw, rotationPitch);
		this.motionX = vec.x;
		this.motionY = vec.y;
		this.motionZ = vec.z;
		this.id = entity.getEntityId();
	}
	
	@Override
	protected void entityInit() 
	{
	}
	
	@Nullable
	public Entity getTarget()
	{
		if(id >= 0 && this.world.getEntityByID(id) != null)
		{
			return this.world.getEntityByID(id);
		}
		else
		{
			return null;
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		id = compound.getInteger("thrower_id");
		damages = compound.getFloat("damages");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("thrower_id", id);
		compound.setFloat("damages", damages);
	}
	
	public void onUpdate()
	{
		this.noClip = true;
		super.onUpdate();
		
		if(!this.world.isRemote)
		{
			for(Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))
			{
				if(entity instanceof EntityLivingBase && entity.getEntityId() != id)
				{
					entity.attackEntityFrom(DamageSource.MAGIC, damages);
					UtilsVoids.AttractEntity(this, entity, 0.01F, -1);
					this.setDead();
				}
			}
			
			if(this.ticksExisted > 200)
			{
				this.setDead();
			}
		}
		this.motionX *= 1.1F;
		this.motionY *= 1.1F;
		this.motionZ *= 1.1F;
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}
}
