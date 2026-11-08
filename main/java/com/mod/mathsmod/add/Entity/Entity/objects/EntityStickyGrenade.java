package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityStickyGrenade extends EntityGrenade
{

	protected static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityStickyGrenade.class, DataSerializers.VARINT);
	
	public EntityStickyGrenade(World worldIn)
    {
        super(worldIn);
    }

    public EntityStickyGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityStickyGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    protected void entityInit()
    {
    	this.dataManager.register(ID, -1);
    	super.entityInit();
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.getTargetID() >= 0)
    	{
    		if(world.getEntityByID(this.getTargetID()) != null)
    		{
    			this.posX = world.getEntityByID(this.getTargetID()).posX + world.getEntityByID(this.getTargetID()).width / 2;
    			this.posY = world.getEntityByID(this.getTargetID()).posY + world.getEntityByID(this.getTargetID()).getEyeHeight();
    			this.posZ = world.getEntityByID(this.getTargetID()).posZ;
    			this.timer += 1;
    		}
    	}
    }
    
    public int timeToExplode()
    {
    	return 80;
    }

	@Override
	public void Explode() 
	{
		for(int i = 0; i < 20; ++i)
		{
			world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F);
		}
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
		
		if(!world.isRemote)
		{
			for(Entity ent : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(this.power * 0.4F)))
    		{
    			if(ent instanceof EntityLivingBase)
    			{
        			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages);
    			}
    		}
			
			this.setDead();
			
			if(this.collidedVertically)
	    	{
	    		this.motionY = -this.motionY;
	    	}
		}
	}
	
	@Override
	public void boing()
    {
    }
	
	public void setTargetID(int id)
	{
		this.dataManager.set(ID, id);
	}
	
	public int getTargetID()
	{
		return this.dataManager.get(ID);
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
		super.writeEntityToNBT(compound);
		compound.setInteger("target_id", this.getTargetID());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    	super.readEntityFromNBT(compound);
    	this.setTargetID(compound.getInteger("target_id"));
    }

	@Override
	public void hitEntities(List<Entity> entity) 
	{
		if(!world.isRemote && entity.size() > 0)
        {
        	if(entity.get(0) != null && entity.get(0) != this.getUser())
            {
            	this.setTargetID(entity.get(0).getEntityId());
            }
        }
	}

	@Override
	public void onImpactWithBlock()
	{
	}

}
