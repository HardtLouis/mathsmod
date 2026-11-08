package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRocketSquid extends EntityRocket
{

	public EntityRocketSquid(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityRocketSquid(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	super(worldIn, throwerIn, damagesIn);
    }
    
    public EntityRocketSquid(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	super(worldIn, throwerIn, damagesIn, strenghtIn);
    }
    
    public EntityRocketSquid(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
    {
    	super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
    }
    
    @Override
	public void explode()
	{
    	for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.2)))
    	{
    		if(entity instanceof EntityLivingBase && entity != null)
    		{
        		entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()).setDamageBypassesArmor(), damages);
    		}
    	}
		if(!world.isRemote)
		{
			EntitySquid entity = new EntitySquid(world);
			entity.setPosition(this.posX, this.posY, this.posZ);
			world.spawnEntity(entity);
		}
		this.setDead();
	}
    
    @Override
	protected float getSpeed() 
    {
		return 3F;
	}
}
