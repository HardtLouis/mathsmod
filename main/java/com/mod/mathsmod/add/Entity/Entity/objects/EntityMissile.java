package com.mod.mathsmod.add.Entity.Entity.objects;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityMissile extends Entity
{

	public EntityMissile(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}

	public void MoveLikeEntity(float strafe, float up, float forward)
    {
        if (this.canPassengerSteer())
        {
            if (!this.isInWater())
            {
                if (!this.isInLava())
                {
                        float f8 = this.getSpeed();
                        this.moveRelative(strafe, up, forward, f8);
                        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                        if (!this.world.isRemote)
                        {
                            if (!this.hasNoGravity())
                            {
                                this.motionY -= 0.08D;
                            }
                        }
                        else if (this.posY > 0.0D)
                        {
                            this.motionY = -0.1D;
                        }
                        else
                        {
                            this.motionY = 0.0D;
                        }

                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= 0.5;
                    this.motionZ *= 0.5;
                }
                else
                {
                    double d4 = this.posY;
                    this.moveRelative(strafe, up, forward, 0.02F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.02D;
                    }

                    if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
                    {
                        this.motionY = 0.30000001192092896D;
                    }
                }
            }
            else
            {
                double d0 = this.posY;
                float f1 = this.getWaterSlowDown();
                float f2 = 0.02F;

                this.moveRelative(strafe, up, forward, f2);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double)f1;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= (double)f1;
            }
        }

        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;

        if (f10 > 1.0F)
        {
            f10 = 1.0F;
        }

    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source instanceof EntityDamageSource && !(source.getImmediateSource() instanceof EntityArrow))
    	{
    		this.setDead();
    		return true;
    	}
    	else
    	{
    		return false;
    		
    	}
    }

	public boolean canBePushed()
    {
        return true;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }

    /**
     * Returns the collision bounding box for this entity
     */
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
    
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
	
	protected abstract float Explosion();
	
	protected abstract float getSpeed();

	protected abstract double gravity();

	protected abstract float getWaterSlowDown();
	
	public void fall(float distance, float damageMultiplier)
    {
		if(distance > 5)
		{
			this.explode();
		}
    }
	
	public void explode()
	{
		this.setDead();
        world.newExplosion(null, posX, posY, posZ, Explosion(), true, true);
	}
	
	public void updateExplode()
	{
        Vec3d vec3d = this.getLookVec();
        float f = this.rotationPitch * 0.017453292F;
        double d6 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
        double d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double d1 = vec3d.lengthVector();
        float f4 = MathHelper.cos(f);
        f4 = (float)((double)f4 * (double)f4 * Math.min(1.0D, d1 / 0.4D));


        if (this.collidedHorizontally && !this.world.isRemote)
        {
            double d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            double d3 = d8 - d11;
            float f5 = (float)(d3 * 10.0D - 3.0D);

            if (f5 < 0.0F)
            {
                this.explode();
            }
        }
        
    }
}
