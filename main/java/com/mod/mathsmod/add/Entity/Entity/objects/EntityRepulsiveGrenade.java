package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityRepulsiveGrenade extends EntityGrenade
{
	
	protected static final DataParameter<Boolean> FALL_DAMAGES = EntityDataManager.<Boolean>createKey(EntityRepulsiveGrenade.class, DataSerializers.BOOLEAN);

	public EntityRepulsiveGrenade(World worldIn)
    {
        super(worldIn);
    }

    public EntityRepulsiveGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityRepulsiveGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    protected void entityInit()
    {
    	super.entityInit();
    	this.dataManager.register(FALL_DAMAGES, false);
    }
	
	public int rebondiLimite()
	{
		return 1;
	}
    
    public void setDoesFallDamages(boolean fallDamages)
    {
    	this.dataManager.set(FALL_DAMAGES, fallDamages);
    }
    
    public boolean doesFallDamages()
    {
    	return this.dataManager.get(FALL_DAMAGES);
    }
    
    public int timeToExplode()
    {
    	return 200;
    }

	@Override
	public void Explode() 
	{
		boolean flag = !this.doesFallDamages();
		
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 3))
		{
			if(!world.isRemote)
			{
				entity.posY += 1;
				entity.onGround = false;
				UtilsVoids.AttractEntity(this, entity, 30, -3.5);
				
				if(flag)
				{
					entity.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 10000));
				}
				
				if(entity instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
				}
			}
		}
		
		this.setDead();
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
    	super.writeEntityToNBT(compound);
    	compound.setBoolean("fall_damages", this.doesFallDamages());;
    }
	
	public void onUpdate()
	{
		super.onUpdate();
	}
	
	@Override
	public void boing()
    {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	this.setDoesFallDamages(compound.getBoolean("fall_damages"));
    }

	@Override
	public void hitEntities(List<Entity> entity) 
	{
		if(entity.size() > 0)
        {
			if(this.ticksExisted >= 3)
            {
				this.Explode();
            }
        }
	}

	@Override
	public void onImpactWithBlock() 
	{
		if(!world.isRemote)
		{
    		this.Explode();
		}
	}

}