package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemGrapple;
import com.mod.mathsmod.add.items.ItemHarpoon;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityHarpoon extends EntityThrowable
{
	
	public boolean doesDamages;
	protected static final DataParameter<Integer> ID = new DataParameter<Integer>(253, DataSerializers.VARINT);

	public EntityHarpoon(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.2F, 0.2F);
	}
	
	public EntityHarpoon(World worldIn, EntityLivingBase entity) 
	{
		super(worldIn, entity);
		this.setPosition(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
		this.shoot(entity, entity.rotationPitch, entity.rotationYaw, 0, 5, 0);
		this.doesDamages = true;
	}
	
	protected void entityInit()
    {
		this.dataManager.register(ID, -1);
		super.entityInit();
    }
	
	public void setID(int entityID)
    {
    	this.dataManager.set(ID, entityID);
    }
    
    public int getID()
    {
    	return this.dataManager.get(ID);
    }
    
    protected float reach()
    {
    	return 20;
    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.getID() > 0 && world.getEntityByID(this.getID()) != null)
    	{
    		Entity entity = world.getEntityByID(this.getID());
    		if(entity instanceof EntityLivingBase)
    		{
    			if(this.getDistance(entity) > this.reach())
    			{
    				this.setDead();
    			}
    		}
    		else
    		{
    			this.setDead();
    		}
    	}
    	else
    	{
    		this.setDead();
    	}
    }

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setID(compound.getInteger("entity_id"));
		this.doesDamages = compound.getByte("doesDamages") == 1;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("entity_id", this.getID());
		compound.setByte("doesDamages", (byte) (this.doesDamages ? 1 : 0));
	}
	
	@Override
	protected void onImpact(RayTraceResult result) 
	{
		if(world.getEntityByID(this.getID()) != null && world.getEntityByID(this.getID()) instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) world.getEntityByID(this.getID());
			
			if(entity.getHeldItemMainhand().getItem() instanceof ItemHarpoon)
			{
				if(doesDamages)
				{
					if(result.entityHit != null && result.typeOfHit == Type.ENTITY && result.entityHit != entity)
					{
						entity.getHeldItemMainhand().damageItem(1, entity);
						
						if(result.entityHit instanceof EntityLivingBase)
						{
							entity.swingArm(EnumHand.MAIN_HAND);
							UtilsVoids.grapple(entity, result.entityHit, 2D);
							if(result.entityHit instanceof EntityPlayerMP)
							{
								EntityPlayerMP target = (EntityPlayerMP) result.entityHit;
								Mathsmod.instance.network.sendTo(new MessageModifyVelocity(new Vec3d(target.motionX, target.motionY, target.motionZ)), target);
							}
							UtilsVoids.attackEntityFrom((EntityLivingBase) result.entityHit, DamageSource.causeMobDamage(entity).setDamageBypassesArmor(), 8);
						}
						this.setDead();
					}
				}
				else
				{
					if(doesDamages)
					{
						entity.getHeldItemMainhand().damageItem(1, entity);
					}
					this.setDead();
				}
			}
		}
	}
}