package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityTrapHarpoon extends Entity
{
	protected static final DataParameter<Integer> ENT_ID = EntityDataManager.<Integer>createKey(EntityTrapHarpoon.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> HIT_ID = EntityDataManager.<Integer>createKey(EntityTrapHarpoon.class, DataSerializers.VARINT);
	int hitTicks;
	
	public EntityTrapHarpoon(World worldIn) 
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F);
	}
	
	public EntityTrapHarpoon(World worldIn, EntityLivingBase thrower) 
	{
		this(worldIn);
		this.setOwnerId(thrower.getEntityId());
		this.setPositionAndRotation(thrower.posX, thrower.posY - 0.5 + thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(ENT_ID, -1);
		this.dataManager.register(HIT_ID, -1);
	}
	
	public void setOwnerId(int id)
	{
		this.dataManager.set(ENT_ID, id);
	}
	
	public int getOwnerId()
	{
		return this.dataManager.get(ENT_ID);
	}
	
	public void setHitId(int id)
	{
		this.dataManager.set(HIT_ID, id);
	}
	
	public int getHitId()
	{
		return this.dataManager.get(HIT_ID);
	}
	
	public Entity getThrower()
	{
		return this.getOwnerId() != -1 ? world.getEntityByID(this.getOwnerId()) : null;
	}
	
	public Entity getEntityHit()
	{
		return this.getHitId() != -1 ? world.getEntityByID(this.getHitId()) : null;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setOwnerId(compound.getInteger("owner_id"));
		this.setHitId(compound.getInteger("hit_id"));
		hitTicks = compound.getInteger("hit_ticks");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("owner_id", this.getOwnerId());
		compound.setInteger("hit_id", this.getHitId());
		compound.setInteger("hit_ticks", this.hitTicks);
	}
	
	public float getEyeHeight()
    {
        return this.height * 0.5F;
    }

	public void onUpdate()
	{
		super.onUpdate();
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		if(this.getThrower() != null)
		{
			if(this.getEntityHit() == null)
			{
				Vec3d vec = UtilsVoids.laser(0.9F, this.rotationYaw, this.rotationPitch);
				this.motionX = vec.x;
				this.motionY = vec.y;
				this.motionZ = vec.z;
				
				List<Entity> ent = world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.2), null);
				if((!ent.isEmpty() && !ent.contains(this.getThrower())) || ent.size() > 1)
				{
					for(Entity enti : ent)
					{
						if(enti != this.getThrower())
						{
							this.setHitId(enti.getEntityId());
							System.out.print("enculé" + System.lineSeparator());
							this.motionX = 0;
							this.motionY = 0;
							this.motionZ = 0;
						}
					}
				}
			}
			else
			{
				if(hitTicks == 10)
				{
					if(this.getThrower() != null && this.getEntityHit().getDistance(this.getThrower()) < 3)
					{
						this.setDead();
					}
					
					Vec3d vec = this.getThrower().getPositionVector().subtract(this.getEntityHit().getPositionVector()).normalize();
					
					this.getEntityHit().motionX = vec.x * 4;
					this.getEntityHit().motionY = vec.y * 4 + 0.4F;
					this.getEntityHit().motionZ = vec.z * 4; 
					
					this.setDead();
				}
				
				this.posX = this.getEntityHit().posX;
				this.posZ = this.getEntityHit().posZ;
				this.posY = this.getEntityHit().posY + this.getEntityHit().height / 2 - 0.5F;
				++hitTicks;
			}
			
			if(this.getDistance(this.getThrower()) > 15)
			{
				this.setDead();
			}
		}
		
		if(this.collided && !world.isRemote)
		{
			this.setDead();
		}
	}
	
	public boolean canBeCollidedWith()
    {
        return false;
    }
    
    public boolean canBePushed()
    {
        return false;
    }
	
}
