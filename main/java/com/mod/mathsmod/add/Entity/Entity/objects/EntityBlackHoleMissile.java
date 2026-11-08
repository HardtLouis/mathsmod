package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.utils.CustomExplosion;
import com.mod.mathsmod.utils.DamageSourceGun;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBlackHoleMissile extends EntityAMissile
{

	public EntityBlackHoleMissile(World worldIn) 
	{
		super(worldIn);
	}
	
	public EntityBlackHoleMissile(World worldIn, BlockPos pos) 
	{
		super(worldIn, pos);
	}

	protected void onImpact()
	{
		EntityBlackHole entity = new EntityBlackHole(world);
		entity.setPosition(this.posX, this.posY, this.posZ);
		entity.setMaxSize(80F);
		world.spawnEntity(entity);
		this.setDead();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if(source instanceof DamageSourceGun)
		{
			if(!world.isRemote)
			{
				this.onImpact();
			}
			return true;
		}
		else if(source instanceof EntityDamageSource && !(source.getImmediateSource() instanceof EntityArrow))
    	{
    		this.setDead();
    		return true;
    	}
    	else
    	{
    		return false;
    		
    	}
    }
	
	@Override
	protected float getSpeed() 
	{
		return 1.2F;
	}

}
