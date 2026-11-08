package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.UUID;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.IRocketLauncher;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.add.messages.MessageParticleEffects;
import com.mod.mathsmod.utils.BadExplosionS;
import com.mod.mathsmod.utils.DamageSourceGun;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRocket extends EntityMissile
{

	protected EntityLivingBase thrower;
	protected float damages;
	protected float strenght;
	private String throwerName;
	protected float speedMultiplier;
	ItemStack stack;
	
	public EntityRocket(World worldIn)
    {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.isExplosion())
    	{
    		this.setDead();
    		return true;
    	}
    	if(source instanceof DamageSourceGun && !((DamageSourceGun) source).isFlameThrower())
    	{
    		this.explode();

    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
	
    public EntityRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn)
    {
    	this(worldIn);
    	this.setRotation(throwerIn.rotationYaw, throwerIn.rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = 4F;
    	damages = damagesIn;
    	thrower = throwerIn;
    	speedMultiplier = 1;
    }
    
    public EntityRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float strenghtIn)
    {
    	this(worldIn);
    	this.setRotation(throwerIn.rotationYaw, throwerIn.rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = strenghtIn;
    	damages = damagesIn;
    	thrower = throwerIn;
    	speedMultiplier = 1;
    }
    
    public EntityRocket(World worldIn, EntityLivingBase throwerIn, float damagesIn, float rotationYaw, float rotationPitch)
    {
    	this(worldIn);
    	this.setRotation(rotationYaw, rotationPitch);
    	this.setPosition(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight() - this.height / 2, throwerIn.posZ);
    	strenght = 4F;
    	damages = damagesIn;
    	thrower = throwerIn;
    	speedMultiplier = 1;
    }
	
    public void multiplyDamagesAndSpeed(float multiplier, float speed)
    {
    	damages *= multiplier;
    	speedMultiplier *= speed;
    }
    
    public void setStack(ItemStack stackIn)
    {
    	this.stack = stackIn;
    }
    
	@Override
    public void onUpdate()
    {
    	super.onUpdate();
    	this.moveRelative(0, 0, 1, this.getSpeed() * speedMultiplier);
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
    		this.explode();
    	}
    	for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, this.hitBox()))
    	{
    		if(entity instanceof EntityTank)
			{
    			if(this.getThrower() != null && this.getThrower() != entity && this.getThrower().getRidingEntity() != entity)
    			{
    				this.onTankCollided((EntityTank) entity);
    			}
			}
    		else
    		{
    			this.onEntityHit(entity);
    		}
    	}
    }
	
	protected AxisAlignedBB hitBox()
	{
		double d0 = (double)width / 2.0D;
        double d1 = (double)height / 2.0D;
		return new AxisAlignedBB(this.posX - d0 + this.motionX, this.posY - d1 + this.motionY, this.posZ - d0 + this.motionZ, this.posX + d0 + this.motionX, this.posY + d1 + this.motionY, this.posZ + d0 + this.motionZ);
	}
	
	protected void onTankCollided(EntityTank entity)
	{
		this.explode();
		this.collideTank(entity);
	}
	
	public void onEntityHit(Entity entityIn)
	{
		if(this.getThrower() != null && this.getThrower() != entityIn && this.getThrower().getRidingEntity() != entityIn)
		{
			if(entityIn instanceof EntityLivingBase)
			{
				UtilsVoids.attackEntityFrom((EntityLivingBase) entityIn, DamageSource.causeExplosionDamage(this.getThrower()),this.damages);
				
				if(this.stack != null && this.stack.getItem() instanceof IRocketLauncher)
				{
					IRocketLauncher item = (IRocketLauncher) this.stack.getItem();
					this.damages *= item.onRocketHit(this.getThrower(), entityIn, stack, (ItemGun) item) * 0.7F;
				}
				
				this.explode();
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
	
	public void collideEntity(Entity entityIn)
	{
		UtilsVoids.attackEntityFrom((EntityLivingBase) entityIn, DamageSource.causeExplosionDamage(this.getThrower()),this.damages * 0.5F);
	}
	
	public void collideTank(EntityTank entity)
	{
		entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()),entity.health() / 4 );
	}
	
	@Override
	public void explode()
	{
		if(!world.isRemote)
		{
			Mathsmod.network.sendToAllAround(new MessageParticleEffects(0, this.getPositionVector(), 3), new TargetPoint(this.dimension, this.posX, this.posY, this.posZ, 40));
		}
		world.playSound(posX, posY, posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 2, 1, false);
		
		for(Entity entity : UtilsVoids.getEntities(world, this, this.strenght))
		{
			UtilsVoids.AttractEntity(this, entity, 10, -3);
			entity.motionY += 0.5F;
			
			if(entity instanceof EntityPlayerMP)
			{
				Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
			}
			
			float mult = 1.0F;
			if(entity == this.getThrower())
			{
				if(this.getThrower().getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == RegisterItems.HEAVY_BOOTS)
				{
					mult *= 0.5F;
				}
				else
				{
					mult *= 2.0F;
				}
			}
			entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()), this.damages * 1F * mult);
		}
		
		this.setDead();
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
		return 0.6F;
	}

	@Override
	protected double gravity() {
		return 0;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.3F;
	}
	
	@SideOnly(Side.CLIENT)
	public Vec3d colorChanges()
	{
		return new Vec3d(0.2, 0.2, 0.2);
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
        
        compound.setFloat("speed_multi", this.speedMultiplier);
        
       
        compound.setTag("rl_stack", stack.writeToNBT(new NBTTagCompound()));
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
        
        this.speedMultiplier = compound.getFloat("speed_multi");

        this.thrower = this.getThrower();
        
        this.stack = new ItemStack(compound.getCompoundTag("rl_stack"));
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
	
}
