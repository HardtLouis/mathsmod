package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemGrenade;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRemoteGrenade extends EntityGrenade
{

	protected static final DataParameter<Integer> ID = EntityDataManager.<Integer>createKey(EntityStickyGrenade.class, DataSerializers.VARINT);
	public EntityGrenade ent;
	private ItemGrenade itemIn;
	
	public EntityRemoteGrenade(World worldIn)
    {
        super(worldIn);
        this.setSize(0.1F, 0.1F);
    }

    public EntityRemoteGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityRemoteGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public void setItem(ItemGrenade item, World world, EntityLivingBase player)
    {
    	ent = item.grenade(world, player);
    	itemIn = item;
    }
    
    protected void entityInit()
    {
    	this.dataManager.register(ID, -1);
    	super.entityInit();
    }
    
    protected float getGravityVelocity()
    {
    	if(this.hasNoGravity())
    	{
    		return 0.0F;
    	}
    	else
    	{
    		return 0.03F;
    	}
        
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.getTargetID() >= 0)
    	{
    		if(world.getEntityByID(this.getTargetID()) != null && world.getEntityByID(this.getTargetID()) instanceof EntityLivingBase)
    		{
    			this.posX = world.getEntityByID(this.getTargetID()).posX + world.getEntityByID(this.getTargetID()).width / 2;
    			this.posY = world.getEntityByID(this.getTargetID()).posY + world.getEntityByID(this.getTargetID()).getEyeHeight();
    			this.posZ = world.getEntityByID(this.getTargetID()).posZ;
    		}
    	}
    	
    	if(ent == null && this.ticksExisted > 4 && !world.isRemote)
    	{
    		this.setDead();
    	}
		
		if(this.getUser() != null && (this.getUser().getDistance(this) > 60 || this.getUser().getHealth() <= 0.0F))
		{
			this.setDead();
		}
		
		if(this.ticksExisted > 10 && !world.isRemote && this.getUser() == null)
		{
			this.setDead();
		}
    }
    
    @Override
    public boolean hasNoGravity()
    {
    	return world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.3F)) || world.getEntityByID(this.getTargetID()) != null;
    }
    
    public int timeToExplode()
    {
    	return 80;
    }

	@Override
	public void Explode() 
	{
		if(this.ent == null)
		{
			if(!world.isRemote)
			{
				if(this.getTargetID() >= 0)
		    	{
					for(int i = 0; i < 20; ++i)
					{
						world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F, this.rand.nextDouble() - 0.5F);
					}
					world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
					
					if(!world.isRemote)
					{
						for(Entity ent : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(this.power * 0.5F)))
			    		{
			    			if(ent instanceof EntityLivingBase)
			    			{
			        			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages * 1F);
			    			}
			    		}
						
						this.setDead();
						
						if(this.collidedVertically)
				    	{
				    		this.motionY = -this.motionY;
				    	}
					}
		    	}
				//BadExplosionS.newExplosion(world, null, posX, posY, posZ, power, power, true);
				this.setDead();
			}
		}
		else
		{
			if(this.getTargetID() >= 0 && world.getEntityByID(this.getTargetID()) != null)
			{
				RayTraceResult result = new RayTraceResult(world.getEntityByID(this.getTargetID()));
				ent.setPosition(this.posX, this.posY, this.posZ);
				ent.Explode();
				this.setDead();
			}
			else
			{

				ent.setPosition(this.posX, this.posY, this.posZ);
				ent.Explode();
				this.setDead();
			}
		}
		
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
    public void boing()
	{
    }

	@Override
	public void hitEntities(List<Entity> entity) 
	{
        if(!world.isRemote && entity.size() > 0 && entity.get(0) != this.getUser())
        {
        	if(entity.get(0) != null)
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