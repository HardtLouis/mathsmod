package com.mod.mathsmod.add.Entity.Entity.objects;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityRagdoll extends Entity implements IEntityMultiPart
{

    public MultiPartEntityPart[] partArray;
	
	public MultiPartEntityPart head = new MultiPartEntityPart(this, "head", 0.5F, 0.5F);

	public MultiPartEntityPart left_arm = new MultiPartEntityPart(this, "left_arm", 0.5F, 0.5F);
	public MultiPartEntityPart right_arm = new MultiPartEntityPart(this, "right_arm", 0.5F, 0.5F);
	
	public MultiPartEntityPart left_leg = new MultiPartEntityPart(this, "left_leg", 0.5F, 0.5F);
	
	public MultiPartEntityPart right_leg = new MultiPartEntityPart(this, "right_leg", 0.5F, 0.5F);
	
	
	private Predicate<Entity> PRED = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return !(p_apply_1_ instanceof MultiPartEntityPart) && p_apply_1_ != EntityRagdoll.this;
        }
    };

	public EntityRagdoll(World worldIn) 
	{
		super(worldIn);
		partArray = new MultiPartEntityPart[] {head, left_arm, right_arm, left_leg, right_leg};
		this.setSize(0.7F, 0.7F);
		
		for(int i = 0; i < partArray.length; ++i)
		{
			MultiPartEntityPart entity = partArray[i];
			Vec3d pos = joints()[i].addVector(0, 0.5, 0);
			UtilsVoids.AttractEntity(this, entity, 1, -1);
		}
	}
	
	public Vec3d[] joints()
	{
		Vec3d head = UtilsVoids.laser(0.5, this.rotationYaw, 0).addVector(0, 0, 0);
		Vec3d rarm = UtilsVoids.rotateVec(new Vec3d(-0.3, 0, 0.2), 0, this.rotationYaw * 0.01745329251994329, 0);
		Vec3d larm = UtilsVoids.rotateVec(new Vec3d(0.3, 0, 0.2), 0, this.rotationYaw * 0.01745329251994329, 0);
		Vec3d rleg = UtilsVoids.rotateVec(new Vec3d(-0.3, 0, -0.4), 0, this.rotationYaw * 0.01745329251994329, 0);
		Vec3d lleg = UtilsVoids.rotateVec(new Vec3d(0.3, 0, -0.4), 0, this.rotationYaw * 0.01745329251994329, 0);
		return new Vec3d[] {head, larm, rarm, lleg, rleg};
	}
	
	@Nullable
    public Entity[] getParts()
    {
        return new MultiPartEntityPart[] {head, left_arm, right_arm, left_leg, right_leg};
    }
	
	public void onUpdate()
	{
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		for(int index = 0; index < this.partArray.length; ++index)
		{
			MultiPartEntityPart part = partArray[index];
			
			part.onUpdate();
			part.noClip = this.noClip;
			part.move(MoverType.SELF, 0, part.motionY, 0);
			if(world.rayTraceBlocks(part.getPositionVector(), part.getPositionVector().addVector(0, part.motionY, 0), false, true, false) != null || part.onGround)
			{
				part.motionY = 0;
			}
			else
			{
				part.motionY -= 0.015f;
			}
			part.motionY *= 0.998;
			
			if(this.ticksExisted % 5 == 0 && part.getPositionVector().distanceTo(this.getPositionVector().add(this.joints()[index])) != 0.5)
			{
				Vec3d dv = part.getPositionVector().add(this.joints()[index]).subtract(this.getPositionVector()).normalize().scale(0.5).add(this.getPositionVector().add(this.joints()[index]));
				part.setPosition(dv.x, dv.y, dv.z);
			}
		}
		
		if(this.ticksExisted > 1200)
		{
			this.setDead();
		}
		
		this.rotationYaw = UtilsVoids.getYawFromEntity(this.head.getPositionVector(), this);
		
		if(this.onGround)
		{
			this.motionX *= 0.4F;
			this.motionZ *= 0.4F;
		}
		
		else if(this.inWater || this.isInLava())
		{
			this.motionX *= 0.5F;
			this.motionZ *= 0.5F;
		}
		this.motionX *= 0.9998F;
		this.motionZ *= 0.9998F;
		
		this.motionY -= 0.02f;
		this.motionY *= 0.99998;
		
	}

	@Override
	public World getWorld() 
	{
		return world;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) 
	{
		return this.attackEntityFrom(source, damage);
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(source.getTrueSource() != null)
        {
        	Vec3d vec = this.getPositionVector().subtract(source.getTrueSource().getPositionVector()).normalize().addVector(0, 0.1, 0).scale(amount * 0.12F);
        	
        	if(source.isExplosion())
        	{
        		vec = vec.scale(4);
        	}
        	
        	this.motionX += vec.x;
        	this.motionZ += vec.z;
        	this.motionY += vec.y;
        }
        return true;
    }
	
	@Override
	protected void entityInit() 
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
	}
	

	
	public boolean canBePushed()
    {
        return true;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
		if(entityIn instanceof MultiPartEntityPart)
		{
			return null;
		}
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    

}
