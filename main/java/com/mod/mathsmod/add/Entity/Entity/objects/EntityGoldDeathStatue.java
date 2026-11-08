package com.mod.mathsmod.add.Entity.Entity.objects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityGoldDeathStatue extends Entity
{
	
	protected static final DataParameter<String> ENTITY = EntityDataManager.<String>createKey(EntityGoldDeathStatue.class, DataSerializers.STRING);
	protected static final DataParameter<String> NAME = EntityDataManager.<String>createKey(EntityGoldDeathStatue.class, DataSerializers.STRING);
	protected static final DataParameter<Boolean> FROZEN = EntityDataManager.<Boolean>createKey(EntityGoldDeathStatue.class, DataSerializers.BOOLEAN);
	
	public EntityGoldDeathStatue(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(ENTITY, "null");
		this.dataManager.register(NAME, " ");
		this.dataManager.register(FROZEN, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setEntityName(compound.getString("entity_gold"));
		this.setPName(compound.getString("entity_name"));
		this.ticksExisted = compound.getInteger("ticks");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setString("entity_gold", this.getEntityName());
		compound.setString("entity_name", this.getName());
		compound.setInteger("ticks", this.ticksExisted);
	}

	public void setEntityName(String entity)
	{
		this.dataManager.set(ENTITY, entity);
	}
	
	public String getEntityName()
	{
		return this.dataManager.get(ENTITY);
	}
	
	public void setPName(String name)
	{
		this.dataManager.set(NAME, name);
	}
	
	public String getPName()
	{
		return this.dataManager.get(NAME);
	}
	
	public void setFrozen(boolean frozen)
	{
		this.dataManager.set(FROZEN, frozen);
	}
	
	public boolean getFrozen()
	{
		return this.dataManager.get(FROZEN);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		this.motionY -= 0.02;
		this.motionY *= 0.9900000190734863D;
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if(this.ticksExisted > 200)
		{
			this.setDead();
		}
	}
}
