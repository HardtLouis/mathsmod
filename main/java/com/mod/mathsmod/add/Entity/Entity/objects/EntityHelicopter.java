package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.DamageSourceGun;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityHelicopter extends Entity implements IVehicle, IReloadVehicle
{
	
	protected static final DataParameter<Integer> FUEL = EntityDataManager.<Integer>createKey(EntityHelicopter.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> DAMAGES = EntityDataManager.<Float>createKey(EntityHelicopter.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> ON_GROUND = EntityDataManager.<Boolean>createKey(EntityHelicopter.class, DataSerializers.BOOLEAN);
	public int cooldown;
	public float rotSpeed;
	public float boostToStart;
	public int timeOnGround;

	public EntityHelicopter(World worldIn) 
	{
		super(worldIn);
		this.setSize(3, 2);
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(DAMAGES, 0.0F);
		this.dataManager.register(FUEL, this.getMaxFuel());
		this.dataManager.register(ON_GROUND, true);
	}
	
	protected int getMaxFuel()
	{
		return 10000;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.setDamages(compound.getFloat("damages"));
		this.setFuel(compound.getInteger("fuel"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setFloat("damages", this.getDamages());
		compound.setInteger("fuel", this.getFuel());
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.motionX *= 0.9;
		this.motionZ *= 0.9;		
		float i = 0;
		
		if(!world.isRemote)
		{
			this.setOnGroundSync(this.onGround);
		}
		
		if(this.getOnGroundSync())
		{
			++this.timeOnGround;
		}
		else
		{
			this.timeOnGround = 0;
		}
		
		if(this.getDamages() > this.maxDamages() / 2)
		{
			float d = (1 - this.getHealthBar()) * 6;
			for(int u = 0; u < d; ++u)
	    	{ 
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + new Random().nextDouble() * 2 - 1, posY + 2, posZ + new Random().nextDouble() * 2 - 1, 0, 0, 0);
	    	}
		}
		
		if(this.getDamages() > 3 * this.maxDamages() / 4)
		{
			for(int u = 0; u < 2; ++u)
	    	{ 
				world.spawnParticle(EnumParticleTypes.FLAME, posX + new Random().nextDouble() * 2 - 1, posY + 2, posZ + new Random().nextDouble() * 2 - 1, 0, 0.2, 0);
	    	}
		}
		
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
			
			if(this.getFuel() > 0)
			{
				this.rotationPitch = entity.rotationPitch;
				this.rotationYaw = entity.rotationYaw;
				
				if(!this.getOnGroundSync())
				{
					if(!world.isRemote)
					{
						this.setFuel(this.getFuel() - 1);
					}
					
					boostToStart = 1.0F;
					this.moveRelative(entity.moveStrafing / 2, 0, entity.moveForward < 0 ? entity.moveForward / 2 : entity.moveForward, 0.075F);
				}
				
				this.motionY *= 0.97F;
				
				if(!this.getOnGroundSync())
				{
					if(this.motionY > 0)
					{
						this.rotSpeed = 1.4F;
					}
					else if(this.motionY < 0)
					{
						this.rotSpeed = 0.8F;
					}
					else
					{
						this.rotSpeed = 1.0F;
					}
					
				}
				else if(this.timeOnGround >= 30)
				{
					this.rotSpeed = MathHelper.clamp(this.rotSpeed + 0.05F, 0, 1);
					this.boostToStart *= 0.97;
				}
				else
				{
					this.rotSpeed = MathHelper.clamp(this.rotSpeed + 0.05F, 0, 1);
				}
				
				this.rotSpeed *= this.boostToStart;
				
			}
			else
			{
				this.motionY -= 0.01;
				this.motionY *= 0.9900000190734863D;
				this.rotSpeed = 0.5F;
				if(!this.getOnGroundSync())
				{
					this.rotationYaw += 15;
					this.moveRelative(0, 0, 1, 0.05F);
				}
				
				boostToStart = 0;
			}
		}
		else
		{
			if(this.getFuel() <= 0 && !this.getOnGroundSync())
			{
				this.motionY -= 0.01;
				this.motionY *= 0.9900000190734863D;
				this.rotSpeed = 0.5F;
				if(!this.getOnGroundSync())
				{
					this.rotationYaw += 15;
					this.moveRelative(0, 0, 1, 0.01F);
				}
			}
			else
			{
				this.motionY -= 0.02;
			}
			
			if(this.getFuel() < this.getMaxFuel() && this.getOnGroundSync())
			{
				this.setFuel(MathHelper.clamp(this.getFuel() + 20, 0, this.getMaxFuel()));
			}
			
			this.rotSpeed *= 0.97F;
			boostToStart = 0;
		}
		
		if(this.isInWater() && this.motionY < 0)
		{
			this.motionY = 0;
		}
		
		
    	this.motionY *= 0.9800000190734863D;
    	if(this.getControllingPassenger() != null)
    	{
        	this.move(MoverType.PLAYER, motionX, motionY, motionZ);
    	}
    	else
    	{
        	this.move(MoverType.SELF, motionX, motionY, motionZ);
    	}
		
		if(cooldown < this.getCooldown())
		{
			cooldown += 1;
		}
		
		if(this.getDamages() >= this.maxDamages())
		{
			this.setFuel(0);
			if(this.onGround && !world.isRemote)
			{
				this.explode();
			}
		}
		
		
	}

	public float maxDamages()
	{
		return 100F;
	}
	
	public double getMountedYOffset()
    {
        return 0.4D;
    }
	
	protected boolean canTriggerWalking()
    {
        return false;
    }
	
	public boolean canBePushed()
    {
        return false;
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
    
    public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            float f = 0.0F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

            float y = 0;

            if(passenger instanceof EntityLivingBase && !this.getOnGroundSync())
            {
            	y = ((EntityLivingBase) passenger).moveForward * 0.3F;
            	if(((EntityLivingBase) passenger).moveForward < 0)
            	{
            		y *= 2;
            	}
            }
            
            if (this.getPassengers().size() > 0)
            {
                int i = this.getPassengers().indexOf(passenger);

                if (i == 0)
                {
                    f = 2.6F;
                    Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
                    passenger.setPosition(this.posX + vec3d.x, this.posY - y + (double)f1, this.posZ + vec3d.z);
                }
                else
                {
                    int a = this.getPassengers().indexOf(passenger) / 2;
                    f = a;
                    
                    float s = this.getPassengers().indexOf(passenger) % 2 == 0 ? 1 : -1;
                    
                    Vec3d vec3d = (new Vec3d((double)f, 0.0D, s * 1.4D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
                    passenger.setPosition(this.posX + vec3d.x, this.posY - 0.3 - y / 3 + (double)f1, this.posZ + vec3d.z);
                }

                if (passenger instanceof EntityAnimal)
                {
                    f = (float)((double)f + 0.2D);
                }
            }
            
        }
    }
	
	@Override
	protected boolean canFitPassenger(Entity passenger)
    {
        return this.getPassengers().size() < 7;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayer && this.rideCooldown == 0 && !source.isProjectile() && this.getPassengers().size() == 0 && !source.isMagicDamage())
    	{
    		if(!world.isRemote && !this.isDead)
    		{
    			world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(this.getEntityItem())));
    			this.setDead();
    		}
    	}
    	else if(source.isExplosion() && amount >= 20)
    	{
    		this.setDead();
    	}
    	else if(source.getTrueSource() != null && this.isPassenger(source.getTrueSource()))
    	{
    		return false;
    	}
    	else if(source instanceof DamageSourceGun)
    	{
    		if(((EntityThrowable) source.getTrueSource()).getThrower() != null && ((EntityThrowable) source.getTrueSource()).getThrower() != this.getControllingPassenger())
    		{
    			this.addDamages(amount);
    		}
    		if(((EntityThrowable) source.getTrueSource()).getThrower() == null)
    		{
    			this.addDamages(amount);
    		}
    	}
    	else
    	{
    		this.addDamages(amount);
    	}
    	
		return true;
    }
    
    protected Item getEntityItem()
    {
    	return RegisterItems.HELICOPTER;
    }
    
    public float getDamages()
    {
    	return this.dataManager.get(DAMAGES);
    }
    
    public void setDamages(float damages)
    {
        this.dataManager.set(DAMAGES, damages);
    }
    
    public int getFuel()
    {
    	return this.dataManager.get(FUEL);
    }
    
    public void setFuel(int fuel)
    {
        this.dataManager.set(FUEL, fuel);
    }
    
    public void addDamages(float damages)
    {
        this.dataManager.set(DAMAGES, this.getDamages() + damages);
    }
    
    public boolean getOnGroundSync()
    {
    	return this.dataManager.get(ON_GROUND);
    }
    
    public void setOnGroundSync(boolean onGround)
    {
        this.dataManager.set(ON_GROUND, onGround);
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
    	if (!this.world.isRemote)
        {
    		player.startRiding(this);
        }
        return false;
    }
    
    @Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }

	protected float getWaterSlowDown() {
		return 0.96F;
	}
	
	protected void explode()
	{
		this.setDead();
        world.newExplosion(null, posX, posY, posZ, 8, true, true);
	}
	
	@Override
	protected void removePassenger(Entity passenger)
    {
		EntityGlider glider = new EntityGlider(this.world);
		glider.setUser(passenger);
		glider.setPosition(posX, posY, posZ);
		if(!world.isRemote)
		{
			world.spawnEntity(glider);
		}
		
        super.removePassenger(passenger);
    }
	
	public void fall(float distance, float damageMultiplier) {}
	
	public void setDead()
    {
        super.setDead();
    }
	
	protected int getCooldown()
	{
		return 2;
	}
	
	public boolean canShoot()
	{
		return cooldown >= this.getCooldown();
	}
	
	public void Shoot(EntityPlayer player)
	{
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer && this.getControllingPassenger() == player)
		{
			Vec3d vec = new Vec3d(2.4, 0, 0.9).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
			RegisterItems.SNIPER_BULLET.preSpawn(world, (EntityLivingBase) this.getControllingPassenger(), new ItemStack(RegisterItems.SCAR_H), (ItemGun) RegisterItems.SCAR_H, false, rotationYaw, rotationPitch, this.posX + vec.x, this.posY + 1.3F + vec.y, this.posZ + vec.z, false);
			
			this.world.playSound((EntityPlayer) null, posX, posY, posZ, MathsSounds.AR15, SoundCategory.AMBIENT, 10, 0.8F);
			this.cooldown = 0;
		}
	}
	
	public boolean getAlwaysRenderNameTagForRender()
    {
		return false;
    }

	@Override
	public float getHealthBar() 
	{
		return  (this.maxDamages() - this.getDamages()) / this.maxDamages();
	}

	@Override
	public float getReloadBar()
	{
		float i = ((float)this.getFuel()) / ((float)this.getMaxFuel());
		return i;
	}
	
}