package com.mod.mathsmod.add.Entity.Entity.objects;

import net.minecraft.entity.MoverType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTitank extends EntityTank
{

	public EntityTitank(World worldIn) 
	{
		super(worldIn);
		this.setSize(8F, 4F);
	}

	public double getCameraChanges(boolean scope)
	{
		return scope ? -7.5 : 12;
	}
	
	protected int reloadTime()
    {
    	return 10;
    }
	
	protected float health()
	{
		return 5000;
	}
	
	protected int explodeTime()
    {
    	return 20;
    }
	
	protected float getSpeed() 
	{
		return 1F;
	}
	
	public void moveLikeEntity(float strafe, float up, float forward)
    {
		
        this.dis += forward;
        
        if(forward != 0)
        {
        	this.move = true;
        }
        else
        {
        	this.move = false;
        }
		
    	if(forward < 0)
    	{
    		forward *= 0.5F;
    	}
    	if(this.getControllingPassenger() != null)
    	{
    		
    		this.rotationYaw += strafe * -2;
    		this.getControllingPassenger().rotationYaw += strafe * -2;
    		
    	}
    	
        if (this.canPassengerSteer())
        {
            if (!this.isInWater())
            {
                if (!this.isInLava())
                {
                    float f8 = this.getSpeed();
                    this.moveRelative(0, up, forward, f8);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    
                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.08D;
                    }
                    
                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= (double)0;
                    this.motionZ *= (double)0;
                    
                }
                else
                {
                    double d4 = this.posY;
                    this.moveRelative(0, up, forward, this.getSpeed() * 0.5F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.04D;
                    }
                }
            }
            else
            {
                double d0 = this.posY;
                float f2 = 0.02F;
                
                if (!this.hasNoGravity())
                {
                    this.motionY -= 0.04D;
                }
                
                this.moveRelative(0, up, forward, this.getSpeed() * 0.5F);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            }
        }

        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;
        this.motionX *= 0.2F;
        this.motionZ *= 0.2F;

        if (f10 > 1.0F)
        {
            f10 = 1.0F;
        }

    }
}
