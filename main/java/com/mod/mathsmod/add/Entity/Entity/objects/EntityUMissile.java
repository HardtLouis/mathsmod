package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityUMissile extends EntityRocket
{

	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityUMissile.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Boolean> IS_CONTROLED = EntityDataManager.<Boolean>createKey(EntityUMissile.class, DataSerializers.BOOLEAN);
	
	public EntityUMissile(World worldIn) {
		super(worldIn);
		this.setSize(1, 1);
	}
	
	public EntityUMissile(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	super(worldIn, throwerIn, damagesIn);
    	if(throwerIn instanceof EntityPlayer)
    	{
    		this.setOwner((EntityPlayer) throwerIn);
    		this.setControlled(true);
    	}
    }
    
    public EntityUMissile(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	super(worldIn, throwerIn, damagesIn, strenghtIn);
    	if(throwerIn instanceof EntityPlayer)
    	{
    		this.setOwner((EntityPlayer) throwerIn);
    		this.setControlled(true);
    	}
    }
    
    public EntityUMissile(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
    {
    	super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
    	if(throwerIn instanceof EntityPlayer)
    	{
    		this.setOwner((EntityPlayer) throwerIn);
    		this.setControlled(true);
    	}
    }
    
	@Override
	protected void entityInit()
    {
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(IS_CONTROLED, true);
    }

	@Override
	protected float Explosion() {
		return 8;
	}

	@Override
	protected float getSpeed() {
		return 0.4F;
	}

	@Override
	protected double gravity() {
		return 0;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}
	
	public float getControlDistance()
	{
		return 100;
	}
	
	@Nullable
    public boolean isControlled()
    {
        return this.dataManager.get(IS_CONTROLED);
    }
	
	@Nullable
    public void setControlled(boolean is)
    {
        this.dataManager.set(IS_CONTROLED, is);
    }
	
	@Nullable
    public UUID getOwnerId()
    {
        return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }
    
	@Nullable
    public Entity getOwner()
    {
    	if(this.getOwnerId() == null)
    	{
    		return null;
    	}
    	else
    	{
    		return world.getPlayerEntityByUUID(getOwnerId());
    	}
        
    }

    public void setOwnerId(@Nullable UUID p_184754_1_)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
    }
    
    public void setOwner(EntityPlayer player)
    {
    	this.setOwnerId(player.getUniqueID());
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.getOwner() != null)
		{
			if(this.getOwner().isSneaking())
			{
				this.setOwnerId(null);
			}
			Entity entity = this.getOwner();
			
			if(entity != null && entity.getDistance(this.getOwner()) > this.getControlDistance())
			{
				if(entity == this.getOwner())
				{
					this.setControlled(false);
					//this.setRotation(this.getOwner().rotationYaw, this.getOwner().rotationPitch);
				}
			}
		}
		
		this.moveRelative(0, 0, 1, this.getSpeed());
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
	}

}
