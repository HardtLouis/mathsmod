package com.mod.mathsmod.add.Entity.Entity.objects;

import com.mod.mathsmod.utils.BadExplosionS;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTankRocket extends EntityRocket
{

	public EntityTankRocket(World worldIn)
	{
		super(worldIn);
		this.setSize(1F, 1F);
	}
	
	public EntityTankRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
		super(worldIn, throwerIn, damagesIn);
    }
	
	public EntityTankRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
	{
		super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
	}
	
	public EntityTankRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenght, float rotationYaw, float rotationPitch)
	{
		super(worldIn, throwerIn, damagesIn, rotationYaw, rotationPitch);
		this.strenght = strenght;
	}
	
	@Override
	protected float Explosion() {
		
		return 3F;
	}

	@Override
	protected float getSpeed() 
	{
		return MathHelper.clamp(3F - this.ticksExisted * 0.01F, 0, 3F);
	}

	@Override
	protected double gravity() {
		return this.getSpeed() == 0 ? 0.04 : 0;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.6F;
	}
	
	public boolean hasNoGravity()
    {
        return true;
    }
	
	protected void onTankCollided(EntityTank entity)
	{
		BadExplosionS.newExplosion(world, null, posX, posY, posZ, 4, 100, true);
		entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()), entity.health() / 30);
		this.setDead();
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		if(this.getSpeed() == 0)
		{
			this.setDead();
		}
	}
}
