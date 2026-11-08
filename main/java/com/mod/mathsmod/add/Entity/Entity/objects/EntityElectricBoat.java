package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.utils.DamageSourceGun;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityElectricBoat extends Entity
{

	public EntityElectricBoat(World worldIn) {
		super(worldIn);
		this.setSize(1.5F, 0.5F);
		this.stepHeight = 1F;
		this.isImmuneToFire = true;
	}
	
	public boolean isPushedByWater()
    {
        return false;
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
	
	public float getSpeed()
	{
		return 0.2F;
	}
	
	public void onUpdate()
	{
		
		super.onUpdate();
		
		this.rideOnCollide();
		
		if(this.getControllingPassenger() != null)
		{
			if(this.getControllingPassenger() instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
				this.moveLikeEntity(entity.moveStrafing, 0, (entity.moveForward));
			}
			else
			{
				this.moveLikeEntity(0, 0, 0);
			}
		}
		else
		{
			this.moveLikeEntity(0, 0, 0);
		}
	}
	
	public double getMountedYOffset()
    {
        return 0.5D;
    }
	
	protected boolean canTriggerWalking()
    {
        return false;
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
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage() && !world.isRemote && !this.isDead)
    	{
    		((EntityPlayer)source.getTrueSource()).dropItem(new ItemStack(RegisterItems.ELECTRIC_BOAT), true);
    		this.setDead();
    	}
    	else if(source.isExplosion())
    	{
    	}
    	else if(source instanceof DamageSourceGun)
    	{
    	}
    	else
    	{
    	}
    	
		return true;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote)
            {
                player.startRiding(this);
            }

            return true;
        }
    }
    
    @Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
    
    public void moveLikeEntity(float strafe, float up, float forward)
    {
    	if(forward < 0)
    	{
    		forward *= 0.25F;
    	}
    	if(this.getControllingPassenger() != null)
    	{
    		this.rotationYaw = this.getControllingPassenger().rotationYaw;
    	}
    	
        if (this.canPassengerSteer())
        {
            if (!this.isInWater())
            {
                if (!this.isInLava())
                {
                    
                    float f8 = this.getSpeed() / 8;
                    this.moveRelative(strafe, up, forward, 0.2F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.08D;
                    }
                    else
                    {
                        this.motionY = 0.0D;
                    }
                    
                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= (double)0;
                    this.motionZ *= (double)0;
                    
                }
                else
                {
                    double d4 = this.posY;
                    this.moveRelative(strafe, up, forward, this.getSpeed() * 3F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.02D;
                    }
                    if(this.canMount())
                    {
                    	this.motionY = 0.5F;
                    }
                    else
                    {
                    	this.motionY = 0;
                    }

                }
            }
            else
            {
                double d0 = this.posY;
                float f2 = 0.02F;

                this.moveRelative(strafe, up, forward, this.getSpeed() * 3F);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                if(this.canMount())
                {
                	this.motionY = 0.5F;
                }
                else
                {
                	this.motionY = 0;
                }

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
	
	public void fall(float distance, float damageMultiplier)
    {
    }
	
	public void setDead()
    {
		if(this.getControllingPassenger() != null)
		{
			this.world.updateEntityWithOptionalForce(getControllingPassenger(), true);
		}
        super.setDead();
        
    }
	
	public float getEyeHeight()
    {
        return 0.75F;
    }
	
	private boolean canMount()
	{
		for(float i = -0.5F; i <= 0.5F; i += 1)
		{
			for(float j = -0.5F; j <= 0.5F; j += 1)
			{
				if(world.getBlockState(new BlockPos(posX + j, posY, posZ + i)).getMaterial() == Material.WATER)
                {
					if(world.getBlockState(new BlockPos(posX + j, posY + this.getEyeHeight(), posZ + i)).getMaterial() == Material.WATER)
	                {
	                	return true;
	                }
                }
				if(world.getBlockState(new BlockPos(posX + j, posY, posZ + i)).getMaterial() == Material.LAVA)
                {
					if(world.getBlockState(new BlockPos(posX + j, posY + this.getEyeHeight(), posZ + i)).getMaterial() == Material.LAVA)
	                {
	                	return true;
	                }
                }
			}
		}
		return false;
	}
	
	public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            float f = 0.0F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

            if (this.getPassengers().size() > 1)
            {
                int i = this.getPassengers().indexOf(passenger);

                if (i == 0)
                {
                    f = 0.2F;
                }
                else
                {
                    f = -0.8F;
                }

                if (passenger instanceof EntityAnimal)
                {
                    f = (float)((double)f + 0.2D);
                }
            }

            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
            passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
        }
    }
	
	@Override
	protected boolean canFitPassenger(Entity passenger)
    {
        return this.getPassengers().size() < 2;
    }
	
	protected void removePassenger(Entity passenger)
    {
		world.updateEntity(this);
		super.removePassenger(passenger);
		passenger.posY += 0.5;
    }
	
	private void rideOnCollide()
    {
    	List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

        if (!list.isEmpty())
        {
            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity = list.get(j);

                if (!entity.isPassenger(this))
                {
                    if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob) && !(entity instanceof EntityPlayer))
                    {
                        entity.startRiding(this);
                    }
                    else
                    {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }
    }
	
}
