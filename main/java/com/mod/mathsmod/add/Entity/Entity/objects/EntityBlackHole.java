package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.concurrent.CopyOnWriteArrayList;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDBoss;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBlackHole extends Entity
{
	protected static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityBlackHole.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> MAX_SIZE = EntityDataManager.<Float>createKey(EntityBlackHole.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> ATTRACT_MULTIPLIER = EntityDataManager.<Float>createKey(EntityBlackHole.class, DataSerializers.FLOAT);
	public boolean isPulsior;
	public float attractMultiplier;
	public float damageMultiplier;
	public boolean doesntAffectOthers;
	
	public EntityBlackHole(World worldIn) 
	{
		super(worldIn);
		this.isPulsior = false;
		this.setSize(1, 1);
		this.attractMultiplier = 1;
		this.damageMultiplier = 1;
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(SIZE, 1.0F);
		this.dataManager.register(MAX_SIZE, 20.0F);
		this.dataManager.register(ATTRACT_MULTIPLIER, attractMultiplier);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setSize(compound.getFloat("size"));
		this.setMaxSize(compound.getFloat("maxSize"));
		this.isPulsior = compound.getByte("isPulsior") == 0;
		this.attractMultiplier = compound.getFloat("attractMultiplier");
		this.damageMultiplier = compound.getFloat("damageMultiplier");
		this.doesntAffectOthers = !compound.getBoolean("affectOthers");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setFloat("size", this.getSize());
		compound.setFloat("maxSize", this.maxSize());
		compound.setByte("isPulsior", (byte) (this.isPulsior ? 0 : 1));
		compound.setFloat("attractMultiplier", this.attractMultiplier);
		compound.setFloat("damageMultiplier", this.damageMultiplier);
		compound.setBoolean("affectOthers", !this.doesntAffectOthers);
	}
	
	public float getEyeHeight()
    {
        return 0;
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		if(!world.isRemote && this.attractMultiplier() != this.attractMultiplier)
		{
			this.setAttrMultiplier(this.attractMultiplier);
		}
		
		//this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if(this.getSize() < this.maxSize())
		{
			this.setSize(!this.isPulsior ? this.getSize() + 0.1F : this.getSize() + 0.25F);
			for(Entity entity : UtilsVoids.getEntities(world, this, this.maxSize()))
			{
				if(entity != this && (!(entity instanceof EntityLivingBase) || ((EntityLivingBase) entity).getMaxHealth() < 200))
				{
					if(this.doesntAffectOthers && entity instanceof EntityBlackHole)
					{
					}
					else
					{
						if(this.attractMultiplier() > 0)
						{
							UtilsVoids.EasyShootEntitySoft(entity, 0, UtilsVoids.getYawFromPos(entity.getPositionVector(), this.posX, this.posZ) + 90, (float) (0.00625F * this.maxSize() / 20F * this.attractMultiplier() * (1 / (entity.getDistance(this) / this.maxSize() * 1.5))), 0);
						}
						
						UtilsVoids.AttractEntity(this, entity, this.maxSize(), 0.3F * this.maxSize() / 20F * this.attractMultiplier() * (this.getSize() / this.maxSize()) * (this.getSize() / this.maxSize()));
					}
					
					/*if(entity instanceof EntityPlayerMP)
					{
						Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
					}*/
				}
			}
		}
		else
		{
			if(!world.isRemote)
			{
				if(this.damageMultiplier > 0)
				{
					PerfectExplosion.EntityBoom(Blocks.AIR, this, this.maxSize() / 2, true, true, false);
				}
				
				for(Entity entity : UtilsVoids.getEntities(world, this, this.maxSize() / 2))
				{
					if(entity != this && (!(entity instanceof EntityLivingBase) || ((EntityLivingBase) entity).getMaxHealth() < 200))
					{
						entity.attackEntityFrom(DamageSource.causeExplosionDamage((EntityLivingBase)null), ((this.maxSize() * 4) - (entity.getDistance(this) * (this.maxSize() / 2.5F))) * this.damageMultiplier);
					}
				}
				
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

	public void setMaxSize(float size)
	{
		this.dataManager.set(MAX_SIZE, size);
	}
	
	public float maxSize()
	{
		return this.dataManager.get(MAX_SIZE);
	}
	
	public void setAttrMultiplier(float att)
	{
		this.dataManager.set(ATTRACT_MULTIPLIER, att);
	}
	
	public float attractMultiplier()
	{
		return this.dataManager.get(ATTRACT_MULTIPLIER);
	}
}
