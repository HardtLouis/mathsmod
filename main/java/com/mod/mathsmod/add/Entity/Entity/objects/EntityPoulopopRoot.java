package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityACBoss;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPoulopopRoot extends Entity
{
	private static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityPoulopopRoot.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BPOSX = EntityDataManager.<Float>createKey(EntityPoulopopRoot.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> BPOSZ = EntityDataManager.<Float>createKey(EntityPoulopopRoot.class, DataSerializers.FLOAT);
	
	public EntityPoulopopRoot(World worldIn)
	{
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(SIZE, 0.1F);
		this.dataManager.register(BPOSX, 0.0F);
		this.dataManager.register(BPOSZ, 0.0F);
	}
	
	public float getRootSize()
	{
		return this.dataManager.get(SIZE);
	}
	
	public void setRootSize(float i)
	{
		this.dataManager.set(SIZE, i);
	}
	
	public float basePosX()
	{
		return this.dataManager.get(BPOSX);
	}
	
	public float basePosZ()
	{
		return this.dataManager.get(BPOSZ);
	}
	
	public void setBasePos(float x, float z)
	{
		this.dataManager.set(BPOSX, x);
		this.dataManager.set(BPOSZ, z);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		this.setRootSize(compound.getFloat("root_size"));
		this.setBasePos(compound.getFloat("base_x"), compound.getFloat("base_z"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setFloat("root_size", this.getRootSize());
		compound.setFloat("base_x", this.basePosX());
		compound.setFloat("base_z", this.basePosZ());
	}
	
	public void onUpdate()
	{
		super.onUpdate();

		this.posX = this.basePosX();
		this.posZ = this.basePosZ();
		
		if(this.ticksExisted <= 10)
		{
			this.setRootSize(0.15F * this.ticksExisted);
			this.setSize(0.1F * this.ticksExisted, 0.15F * this.ticksExisted);
		}
		else
		{
			int t = this.ticksExisted - 10;
			if(t <= 10)
			{
				this.setRootSize(1.5F + t);
				this.setSize(1F, 1.5F + t * 1F);
				
				for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()))
				{
					if(!(entity instanceof EntityPoulopop))
					{
						entity.attackEntityFrom(DamageSource.MAGIC, 30);
						if(entity instanceof EntityLivingBase)
						{
							entity.addVelocity(0, 2, 0);
						}
					}
				}
			}
			
			if(this.ticksExisted >= 60)
			{
				this.setDead();
			}
		}
	}

}
