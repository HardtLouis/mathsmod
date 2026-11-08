package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.Random;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageParticleEffects;
import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityGazGrenade extends EntityGrenade
{

	public EntityGazGrenade(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityGazGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityGazGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.timer > 0)
    	{
    		for(int i = 0; i < 2; ++i)
    		{
    			world.spawnParticle(EnumParticleTypes.SLIME, posX, posY, posZ, 0, 0.2, 0);
    		}
    	}
    }

	@Override
	public void Explode() 
	{
		Mathsmod.network.sendToAllAround(new MessageParticleEffects(0, this.getPositionVector(), 2), new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 40));
		
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
		
		if(!world.isRemote)
		{
			for(Entity ent : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(this.power * 0.7F)))
    		{
    			if(ent instanceof EntityLivingBase)
    			{
        			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages * 0.45F);
    			}
    		}
			
			if(this.collidedVertically)
	    	{
	    		this.motionY = -this.motionY;
	    	}
			
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, this.power))
			{
				entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
			}
			this.setDead();
		}
	}

	@Override
	public void hitEntities(List<Entity> entity)
	{
		if(entity.size() > 0)
        {
        	if(entity.get(0) != null && (entity.get(0) != this.getUser() || this.ticksExisted >= 3 || this.rebondi > 0))
            {
        		for(Entity ent : entity)
        		{
        			if(ent instanceof EntityLivingBase)
        			{
            			UtilsVoids.attackEntityFrom((EntityLivingBase) ent, DamageSource.causeExplosionDamage(getUser()), this.damages * 0.45F);
        			}
        		}
        		this.Explode();
            }
        }
	}

	@Override
	public void onImpactWithBlock() {
		
	}

}
