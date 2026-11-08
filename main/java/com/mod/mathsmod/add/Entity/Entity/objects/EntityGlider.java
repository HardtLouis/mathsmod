package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityGlider extends Entity
{
	
	protected static final DataParameter<Integer> USER_ID = EntityDataManager.<Integer>createKey(EntityGlider.class, DataSerializers.VARINT);
	
	public EntityGlider(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(USER_ID, -1);
	}
	
	public void setUser(Entity passenger)
	{
		this.setUserId(passenger.getEntityId());
	}
	
	public void setUserId(int id)
	{
		this.dataManager.set(USER_ID, id);
	}
	
	public int getUserId()
	{
		return this.dataManager.get(USER_ID);
	}
	
	@Override
	public double getMountedYOffset()
    {
        return 0D;
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setUserId(compound.getInteger("user_id"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("user_id", this.getUserId());
	}
	
	@Nullable
    public EntityLivingBase getUser()
    {	
		if(this.getUserId() > 0)
		{
			if(world.getEntityByID(this.getUserId()) instanceof EntityLivingBase)
			{
				return (EntityLivingBase) world.getEntityByID(this.getUserId());
			}
			
		}
        return null;
    }
	
	public void onUpdate()
	{
		float st = 0;
		float fo = 0;
		
		if(this.getUser() != null && (this.getUser().getRidingEntity() == null || this.getUser().getRidingEntity() == this))
		{
			if(this.getUser().getHealth() <= 0)
			{
				this.setDead();
			}
			
			if(!this.getUser().isRiding() && this.ticksExisted < 30)
			{
				this.getUser().startRiding(this);
			}
			else if(!world.isRemote && !this.getUser().isRiding())
			{
				this.setDead();
			}
			
			if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
			{
				this.setRotation(this.getControllingPassenger().rotationYaw, 0);
				
				EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
				
				st = entity.moveStrafing;
				fo = entity.moveForward;
				entity.fallDistance = 0;
			}
		}
		else
		{
			this.setDead();
		}
		
		if(this.onGround || this.inWater || this.isInLava())
		{
			if(!world.isRemote)
			{
				this.setDead();
			}
		}
		
		this.moveLikeEntity(st, 0, fo);
		this.fallDistance = 0;
		super.onUpdate();
	}
	
	@Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
	
	public void moveLikeEntity(float strafe, float up, float forward)
    {
		motionY = 0;
		motionX *= 0;
		motionZ *= 0;
		this.moveRelative(strafe, 0, forward + 0.4F, 0.3F);
		motionY = -0.05F;
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }
	
	public void fall(float distance, float damageMultiplier)
	{
		if(distance > 0)
		{
			this.setDead();
		}
    }
}
