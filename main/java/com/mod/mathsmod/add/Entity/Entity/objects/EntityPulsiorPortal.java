package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Random;

import com.mod.mathsmod.add.Entity.Entity.EntityDBoss;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPulsiorPortal extends Entity
{
	protected static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityBlackHole.class, DataSerializers.FLOAT);
	int ticksMaxSize;
	
	public EntityPulsiorPortal(World worldIn) 
	{
		super(worldIn);
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(SIZE, 0.0F);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setSize(compound.getFloat("size"));
		ticksMaxSize = compound.getInteger("ticksMaxSize");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setFloat("size", this.getSize());
		compound.setFloat("ticksMaxSize", ticksMaxSize);
	}
	
	public float getEyeHeight()
    {
        return 0;
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		
		//this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if(this.getSize() < this.maxSize())
		{
			this.setSize(this.getSize() + 0.1F);
		}
		else
		{
			if(this.ticksMaxSize < 60)
			{
				if(this.ticksMaxSize == 0)
				{
					EntityDBoss entity = new EntityDBoss(world);
					entity.setPosition(this.posX, this.posY - 4, this.posZ);
					entity.setSpawnTime(60);
					entity.rotationYaw = new Random().nextInt(360) - 180;
					if(!world.isRemote)
					{
						world.spawnEntity(entity);
					}
				}
				
				this.ticksMaxSize += 1;
			}
			else
			{
				this.setDead();
			}
		}

	}
	
	public void setSize(float size)
	{
		this.dataManager.set(SIZE, size);
	}
	
	public float getSize()
	{
		return this.dataManager.get(SIZE);
	}
	
	public float maxSize()
	{
		return 5;
	}
}
