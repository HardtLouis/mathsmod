package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.UUID;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.PerfectExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityNuclearRocket extends EntityMissile
{

	protected EntityLivingBase thrower;
	protected float damages;
	protected float strenght;
	private String throwerName;
	
	public EntityNuclearRocket(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source instanceof EntityDamageSource && (source instanceof EntityDamageSourceIndirect))
    	{
    		if(!world.isRemote)
    		{
    			PerfectExplosion.EntityBoom(null, this, posX, posY, posZ, this.strenght, true, true, true);
    		}
    		this.setDead();
    		return true;
    	}
    	if(source.isExplosion())
    	{
    		this.setDead();
    		return true;
    	}
    	else
    	{
    		return false;
    		
    	}
    }
	
    public EntityNuclearRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	this(worldIn);
    	this.setRotation(throwerIn.rotationYaw, throwerIn.rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = 4F;
    	damages = damagesIn;
    	thrower = throwerIn;
    }
    
    public EntityNuclearRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	this(worldIn);
    	this.setRotation(throwerIn.rotationYaw, throwerIn.rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = strenghtIn;
    	damages = damagesIn;
    	thrower = throwerIn;
    }
    
    public EntityNuclearRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
    {
    	this(worldIn);
    	this.setRotation(rotationYaw, rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = 4F;
    	damages = damagesIn;
    	thrower = throwerIn;
    }
	
	@Override
    public void onUpdate()
    {
    	super.onUpdate();
    	this.moveRelative(0, 0, 1, this.getSpeed());
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		if(this.posY > 1000)
		{
			this.setDead();
		}
    	
    	for(int i = 0; i < 3; ++i)
    	{
    		world.spawnParticle(EnumParticleTypes.CLOUD, posX, posY + 0.5, posZ, 0, 0, 0);
    	}
    	if(this.collided && this.getThrower() != null)
    	{
    		PerfectExplosion.EntityBoom(null, this, posX, posY, posZ, this.strenght, true, true, true);
    		this.setDead();
    	}
    	for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(1)))
    	{
    		if(entity instanceof EntityTank)
			{
    			if(this.getThrower() != null && this.getThrower() != entity && this.getThrower().getRidingEntity() != entity)
    			{
    				this.onTankCollided((EntityTank) entity);
    			}
			}
    	}
    }
	
	
	protected void onTankCollided(EntityTank entity)
	{
		PerfectExplosion.EntityBoom(null, this, posX, posY, posZ, this.strenght, true, true, true);
		entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()),entity.health());
		this.setDead();
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn)
    {
		super.applyEntityCollision(entityIn);
			
		if(!world.isRemote)
    	{
			if(this.getThrower() != null && this.getThrower() != entityIn && this.getThrower().getRidingEntity() != entityIn)
			{
				if(entityIn instanceof EntityLivingBase)
				{
					PerfectExplosion.EntityBoom(Blocks.FIRE, this, posX, posY, posZ, this.strenght, true, true, true);
				}
			}
    	}
    }
	
	public void onCollideWithPlayer(EntityPlayer entityIn)
    {
		if(entityIn.posY > this.posY + this.getEyeHeight() && this.ticksExisted > 20)
		{
			entityIn.motionX = this.motionX;
			entityIn.motionY = this.motionY;
			entityIn.motionZ = this.motionZ;
			entityIn.onGround = true;
		}
    }
	
	
	
	public boolean hasNoGravity()
    {
        return true;
    }

	@Override
	protected float Explosion() 
	{
		return this.strenght;
	}

	@Override
	protected float getSpeed() {
		return 2F;
	}

	@Override
	protected double gravity() {
		return 0;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.3F;
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
		compound.setFloat("damages", this.damages);
		compound.setFloat("strenght", this.strenght);
        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer)
        {
            this.throwerName = this.thrower.getName();
        }

        compound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }
	
	public void readEntityFromNBT(NBTTagCompound compound)
    {
		this.damages = compound.getFloat("damages");
		this.strenght = compound.getFloat("strenght");
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
	
	public void moveRelative(float strafe, float up, float forward, float friction)
    {
        float f = strafe * strafe + up * up + forward * forward;

        if (f >= 1.0E-4F)
        {
            f = MathHelper.sqrt(f);

            if (f < 1.0F)
            {
                f = 1.0F;
            }

            f = friction / f;
            strafe = strafe * f;
            up = up * f;
            forward = forward * f;
            
            float x = -MathHelper.sin(this.rotationYaw * 0.017453292F) * MathHelper.cos(this.rotationPitch * 0.017453292F);
            float y = -MathHelper.sin((this.rotationPitch) * 0.017453292F);
            float z = MathHelper.cos(this.rotationYaw * 0.017453292F) * MathHelper.cos(this.rotationPitch * 0.017453292F);
            float f23 = MathHelper.sqrt(x * x + y * y + z * z);
            x = x / (float)f23;
            y = y / (float)f23;
            z = z / (float)f23;
            
            this.motionX = x * f;
            this.motionY = y * f;
            this.motionY -= this.gravity();
            this.motionZ = z * f;
        }
    }
	
	protected void setSize(float width, float height)
    {
        if (width != this.width || height != this.height)
        {
            float f = this.width;
            this.width = width;
            this.height = height;

            if (this.width < f)
            {
                double d0 = (double)width / 2.0D;
                double d1 = (double)height / 2.0D;
                this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY - d1, this.posZ - d0, this.posX + d0, this.posY + d1, this.posZ + d0));
                return;
            }

            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
            this.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)this.width, axisalignedbb.minY + (double)this.height, axisalignedbb.minZ + (double)this.width));

            if (this.width > f && !this.firstUpdate && !this.world.isRemote)
            {
                this.move(MoverType.SELF, (double)(f - this.width), 0.0D, (double)(f - this.width));
            }
        }
    }
	
	public void explode()
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
                this.setDead();
                PerfectExplosion.EntityBoom(Blocks.FIRE, this, posX, posY, posZ, this.strenght, true, true, true);
            }
        }
        
    }
	
	public void fall(float distance, float damageMultiplier)
    {
		if(distance > 5)
		{
			this.setDead();
			PerfectExplosion.EntityBoom(Blocks.FIRE, this, posX, posY, posZ, this.strenght, true, true, true);
		}
    }
}
