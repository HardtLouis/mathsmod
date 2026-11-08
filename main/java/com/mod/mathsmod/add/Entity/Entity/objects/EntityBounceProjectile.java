package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.ibm.icu.util.DangiCalendar;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.IReturnToSender;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityBounceProjectile extends Entity implements IProjectile
{
    double bounceMotionX;
    double bounceMotionZ;
    double bounceMotionY;
    
    public int bounce;
    
    /** The entity that threw this throwable item. */
    protected EntityLivingBase thrower;
    private String throwerName;
	private Entity ignoreEntity;
	private int ignoreTime;

    public EntityBounceProjectile(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    public EntityBounceProjectile(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
    }

    public EntityBounceProjectile(World worldIn, EntityLivingBase throwerIn)
    {
        this(worldIn, throwerIn.posX, throwerIn.posY + (double)throwerIn.getEyeHeight() - 0.10000000149011612D, throwerIn.posZ);
        this.thrower = throwerIn;
    }

    protected void entityInit()
    {
    }
    
    public boolean bounce()
    {
    	return true;
    }
    
    public boolean canBePushed()
    {
        return false;
    }
    
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source instanceof EntityDamageSource)
    	{
    		if(this.bounce() && source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase)source.getTrueSource()).getHeldItemMainhand().getItem() instanceof IReturnToSender)
    		{
    			float velocity = ((IReturnToSender)((EntityLivingBase)source.getTrueSource()).getHeldItemMainhand().getItem()).sendStrenght();
    			this.motionX = 0;
    			this.motionY = 0;
    			this.motionZ = 0;
    			this.bounce = 3;
    			
    			this.shoot(source.getTrueSource(), source.getTrueSource().rotationPitch, source.getTrueSource().rotationYaw, 0, velocity * 2, 0);
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Checks if the entity is in range to render.
     */
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    /**
     * Sets throwable heading based on an entity that's throwing it
     */
    public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.motionX += entityThrower.motionX;
        this.motionZ += entityThrower.motionZ;

        if (!entityThrower.onGround)
        {
            this.motionY += entityThrower.motionY;
        }
        this.bounceMotionX = this.motionX;
        this.bounceMotionZ = this.motionZ;
        
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(x * x + z * z);
            this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        
        super.onUpdate();
        
        if(this.bounce())
        {
        	this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            
            if(UtilsVoids.getCollidedBlockpos(world, getEntityBoundingBox()) != null)
            {
            	Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
                Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
            	if(raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
            	{
            		this.onImpact(raytraceresult);
            		
            		if(this.bounce())
            		{
            			if(this.bounce < 5)
                        {
                        	this.posY += 0.1F;
                        	this.motionY = (5F - this.bounce) * 0.1F;
                        	this.motionX = bounceMotionX * 0.5;
                        	this.motionZ = bounceMotionZ * 0.5;
                        	bounceMotionX = motionX;
                        	bounceMotionZ = motionZ;
                        	++this.bounce;
                        }
                		
                		if(this.motionX == 0)
                		{
                			this.motionX = bounceMotionX * -1;
                			bounceMotionX = motionX;
                		}
                		
                		if(this.motionZ == 0)
                		{
                			this.motionZ = bounceMotionZ * -1;
                			bounceMotionZ = motionZ;
                		}
            		}
            	}
            }
            
            this.motionX *= 0.99;
            this.motionY *= 0.99;
            this.motionZ *= 0.99;
            
            if(!this.hasNoGravity())
            {
            	this.motionY -= this.getGravityVelocity();
            }
            
            if(world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()).size() > 0 && world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()).get(0) != this.getThrower())
            {
            	this.onImpact(new RayTraceResult(world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()).get(0)));
            }
        }
        else
        {


            Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1);
            vec3d = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null)
            {
                vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            Entity entity = null;
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
            double d0 = 0.0D;
            boolean flag = false;

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = list.get(i);

                if (entity1.canBeCollidedWith())
                {
                    if (entity1 == this.ignoreEntity)
                    {
                        flag = true;
                    }
                    else if (this.thrower != null && this.ticksExisted < 2 && this.ignoreEntity == null)
                    {
                        this.ignoreEntity = entity1;
                        flag = true;
                    }
                    else
                    {
                        flag = false;
                        AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                        RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                        if (raytraceresult1 != null)
                        {
                            double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

                            if (d1 < d0 || d0 == 0.0D)
                            {
                                entity = entity1;
                                d0 = d1;
                            }
                        }
                    }
                }
            }

            if (this.ignoreEntity != null)
            {
                if (flag)
                {
                    this.ignoreTime = 2;
                }
                else if (this.ignoreTime-- <= 0)
                {
                    this.ignoreEntity = null;
                }
            }

            if (entity != null)
            {
                raytraceresult = new RayTraceResult(entity);
            }

            if (raytraceresult != null)
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && this.world.getBlockState(raytraceresult.getBlockPos()).getBlock() == Blocks.PORTAL)
                {
                    this.setPortal(raytraceresult.getBlockPos());
                }
                else if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
                {
                    this.onImpact(raytraceresult);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

            for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f1 = 0.99F;
            float f2 = this.getGravityVelocity();

            if (this.isInWater())
            {
                for (int j = 0; j < 4; ++j)
                {
                    float f3 = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }

                f1 = 0.8F;
            }

            this.motionX *= (double)f1;
            this.motionY *= (double)f1;
            this.motionZ *= (double)f1;

            if (!this.hasNoGravity())
            {
                this.motionY -= (double)f2;
            }

            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.03F;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected abstract void onImpact(RayTraceResult result);

    public static void registerFixesThrowable(DataFixer fixer, String name)
    {
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setInteger("bounce", this.bounce);
        
        compound.setDouble("originMotionX", this.bounceMotionX);
        compound.setDouble("originMotionZ", this.bounceMotionZ);

        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer)
        {
            this.throwerName = this.thrower.getName();
        }

        compound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        this.bounce = compound.getInteger("bounce");
        
        this.bounceMotionX = compound.getDouble("originMotionX");
        this.bounceMotionZ = compound.getDouble("originMotionZ");
        
        this.thrower = null;
        this.throwerName = compound.getString("ownerName");

        if (this.throwerName != null && this.throwerName.isEmpty())
        {
            this.throwerName = null;
        }
        
        this.thrower = this.getThrower();
    }

    @Nullable
    public EntityLivingBase getThrower()
    {
        if (this.thrower == null && this.throwerName != null && !this.throwerName.isEmpty())
        {
            this.thrower = this.world.getPlayerEntityByName(this.throwerName);

            if (this.thrower == null && this.world instanceof WorldServer)
            {
                try
                {
                    Entity entity = ((WorldServer)this.world).getEntityFromUuid(UUID.fromString(this.throwerName));

                    if (entity instanceof EntityLivingBase)
                    {
                        this.thrower = (EntityLivingBase)entity;
                    }
                }
                catch (Throwable var2)
                {
                    this.thrower = null;
                }
            }
        }

        return this.thrower;
    }
    
}