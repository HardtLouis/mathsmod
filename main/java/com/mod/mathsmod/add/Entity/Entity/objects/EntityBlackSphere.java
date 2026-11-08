package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBlackSphere extends Entity
{
	
	int id;

	public EntityBlackSphere(World worldIn) 
	{
		super(worldIn);
		this.setSize(2, 2);
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
	
	public List<AxisAlignedBB> getHitBoxes()
	{
		List<AxisAlignedBB> list = Lists.newArrayList();
		double velocity = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int n = (int) (velocity / 2F) + 1;
		for(int i = 0; i < Math.min(n, 8); ++i)
		{
			Vec3d pos = UtilsVoids.laser(2 * i, this.rotationYaw, this.rotationPitch).add(this.getPositionVector());
			list.add(new AxisAlignedBB(pos.x - 1, pos.y, pos.z - 1, pos.x + 1, pos.y + 2, pos.z + 1));
		}
		
		if(n > 8)
		{
			this.setDead();
		}
		
		return list;
	}
	
	@Override
	protected void entityInit() 
	{
	}
	
	@Nullable
	public Entity getThrower()
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
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("thrower_id", id);
	}
	
	public void onUpdate()
	{
		this.noClip = true;
		super.onUpdate();
		
		if(!this.world.isRemote)
		{
			try
			{
				for(AxisAlignedBB box : this.getHitBoxes())
				{
					for(Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, box))
					{
						if(entity instanceof EntityLivingBase && entity.getEntityId() != id)
						{
							if(this.getThrower() instanceof EntityLivingBase && entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()).setDamageBypassesArmor(), 20))
							{
								((EntityLivingBase) this.getThrower()).heal(4);
							}
						}
					}
				}
			}
			catch(java.util.ConcurrentModificationException except)
			{
				//Ptn cette exception de golmon, baise ta mere
			}
			
			if(this.ticksExisted > 200)
			{
				this.setDead();
			}
		}
		this.motionX *= 1.3F;
		this.motionY *= 1.3F;
		this.motionZ *= 1.3F;
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}
}