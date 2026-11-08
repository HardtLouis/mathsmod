package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.add.messages.MessageParticleEffects;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityBasicGrenade extends EntityGrenade
{

	public EntityBasicGrenade(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityBasicGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBasicGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.timer > 0)
    	{
    		world.spawnParticle(EnumParticleTypes.FLAME, posX, posY, posZ, 0, 0.1, 0);
    	}
    }

	@Override
	public void Explode() 
	{
		Mathsmod.network.sendToAllAround(new MessageParticleEffects(0, this.getPositionVector(), 2), new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 40));
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
		
		if(!world.isRemote)
		{
			for(Entity ent : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(this.power)))
    		{
    			if(ent instanceof EntityLivingBase)
    			{
    				UtilsVoids.AttractEntity(this, ent, 10, -0.7F);
    				if(ent instanceof EntityPlayerMP)
    				{
    					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(ent.motionX, ent.motionY, ent.motionZ)), (EntityPlayerMP) ent);
    				}
        			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages * 0.5F);
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
	public void hitEntities(List<Entity> entity) 
	{
		if(entity.size() > 0)
        {
			if(this.ticksExisted >= 2 || this.rebondi > 0)
            {
        		for(Entity ent : entity)
        		{
        			if(ent instanceof EntityLivingBase)
        			{
            			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages * 0.5F);
        			}
        		}
        		this.Explode();
            }
        }
		
	}

	@Override
	public void onImpactWithBlock() 
	{
	}
}
