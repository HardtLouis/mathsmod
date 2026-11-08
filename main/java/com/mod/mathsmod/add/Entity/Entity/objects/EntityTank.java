package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTank extends Entity implements IVehicle, IReloadVehicle
{
	
	public float canonRotationPitch;
	public float canonRotationYaw;
	
	public float dis;
	
	protected boolean move;
	
	protected static final DataParameter<Float> DAMAGES = EntityDataManager.<Float>createKey(EntityTank.class, DataSerializers.FLOAT);
	protected static final DataParameter<Integer> EXPLODE = EntityDataManager.<Integer>createKey(EntityTank.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> RELOAD = EntityDataManager.<Float>createKey(EntityTank.class, DataSerializers.FLOAT);

	public EntityTank(World worldIn) 
	{
		super(worldIn);
		this.stepHeight = 3F;
		this.isImmuneToFire = true;
		this.setSize(5.5F, 3F);
	}
	
	public double getCameraChanges(boolean scope)
	{
		return scope ? -5 : 8;
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(DAMAGES, 0.0F);
		this.dataManager.register(EXPLODE, 0);
		this.dataManager.register(RELOAD, 0.0F);
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if(source.isFireDamage())
		{
			return false;
		}
		if(this.getControllingPassenger() != null && source.getTrueSource() != null)
		{
			if(source.getTrueSource() == this.getControllingPassenger() && !source.isExplosion() && !source.isProjectile() && !source.isMagicDamage())
			{
				return false;
			}
			else
			{
				this.setDamages(this.getDamages() + amount);
				return true;
			}
		}
		else
		{
			this.setDamages(this.getDamages() + amount);
			return true;
		}
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setDamages(compound.getFloat("damages"));
		this.setExplode(compound.getInteger("explode"));
		this.setReload(compound.getFloat("reload"));
	}
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setFloat("damages", this.getDamages());
		compound.setInteger("explode", this.getExplode());
		compound.setFloat("reload", this.getReload());
	}
	
	protected float health()
	{
		return 2000;
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
    
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    @Nullable
    public Entity getControllingPassenger()
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : (Entity)list.get(0);
    }
    
    @Nullable
    public Entity getCanonPassenger()
    {
    	List<Entity> list = this.getPassengers();
        return list.size() < 2 ? null : list.get(1);
    }
    
	public void onUpdate()
	{
		super.onUpdate();
		this.damageCollide();
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
		{
			this.addReload(1);
			this.moveLikeEntity(((EntityLivingBase)this.getControllingPassenger()).moveStrafing, 0, ((EntityLivingBase)this.getControllingPassenger()).moveForward);
		}
		else
		{
			this.moveLikeEntity(0, 0, 0);
		}
		
		if(this.getCanonPassenger() != null)
		{
    		this.setCanonRotation(MathHelper.clamp(this.getCanonPassenger().rotationYaw, this.rotationYaw - 90, this.rotationYaw + 90), this.getCanonPassenger().rotationPitch);
		}
		
		if(this.getCanonPassenger() == null && this.getControllingPassenger() != null)
		{
			this.getControllingPassenger().rotationYaw = MathHelper.clamp(this.getControllingPassenger().rotationYaw, this.rotationYaw - 90, this.rotationYaw + 90);
    		this.setCanonRotation(this.getControllingPassenger().rotationYaw, this.getControllingPassenger().rotationPitch);
		}
		
		if(this.getDamages() >= this.health() && !this.inWater)
		{
			this.addExplode(1);
			if(this.getExplode() >= this.explodeTime())
			{
				this.setDead();
				world.createExplosion(this, posX, posY, posZ, 6, true);
			}
		}
		
		if(this.inWater && this.getExplode() > 0)
		{
			this.addExplode(-1);
			
			if(this.getExplode() <= 0)
			{
				this.setDamages(1999.0F);
			}
		}
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
    		
    		this.rotationYaw += strafe * -4;
    		this.getControllingPassenger().rotationYaw += strafe * -4;
    		
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

	private void setCanonRotation(float canonRotationYaw, float canonRotationPitch) 
	{
		this.canonRotationPitch = MathHelper.clamp(canonRotationPitch, -90.0F, 45.0F);
		this.canonRotationYaw = canonRotationYaw;
	}

	protected float getSpeed() 
	{
		return 0.5F;
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if(this.getPassengers().size() >= 2 && !this.getPassengers().contains(player))
        {
        	return false;
        }
        else
        {
        	if(player.isSneaking() && this.getPassengers().isEmpty())
        	{
        		this.setDead();
        		if(!world.isRemote)
        		{
        			Vec3d vec = UtilsVoids.laser(this.width / 2 - 1, player.rotationYaw, player.rotationPitch);
        			world.spawnEntity(new EntityItem(world, posX + vec.x, posY + vec.y, posZ + vec.z, new ItemStack(RegisterItems.TANK_ITEM)));
        		}
        	}
        	if(this.getControllingPassenger() == null || (this.getCanonPassenger() == null && this.getControllingPassenger() != player))
        	{
        		if (!this.world.isRemote)
        		{
        			if (player.isSneaking())
        			{
        				return false;
        			}
        			player.startRiding(this);
        		}
        	}
        	else
        	{
        		this.shoot(player);
        	}
            return true;
        }
    }
	
	public boolean shoot(EntityLivingBase entity)
	{
		if(this.getReload() == this.reloadTime())
		{
			if(!world.isRemote && this.getCanonPassenger() == null && entity == this.getControllingPassenger() && this.getControllingPassenger() instanceof EntityLivingBase)
			{
				Entity rocket = new EntityTankRocket(world, (EntityLivingBase) this.getControllingPassenger(), 10, this.canonRotationYaw, this.canonRotationPitch);
				rocket.setPosition(this.getControllingPassenger().posX, this.getControllingPassenger().posY + this.getControllingPassenger().getEyeHeight(), this.getControllingPassenger().posZ);
				world.spawnEntity(rocket);
				this.setReload(0);
			}
    		if(!world.isRemote && entity == this.getCanonPassenger())
			{	Entity rocket = new EntityTankRocket(world, (EntityLivingBase) this.getCanonPassenger(), 10, this.canonRotationYaw, this.canonRotationPitch);
				rocket.setPosition(this.getControllingPassenger().posX, this.getControllingPassenger().posY + this.getControllingPassenger().getEyeHeight(), this.getControllingPassenger().posZ);
				world.spawnEntity(rocket);
				this.setReload(0);
			}
		}
		
		return false;
	}
	
	public void updatePassenger(Entity passenger)
    {
        if (this.isPassenger(passenger))
        {
            float f = 0.0F;
            int i = this.getPassengers().indexOf(passenger);

            if (this.getPassengers().size() > 1)
            {
            	

                if (i == 0)
                {
                    f = 0.5F;
                }
                if(i == 1)
                {
                	f = -1F;
                }
                else
                {
                    f = 0F;
                }
            }

            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
            if(i < 2)
            {
            	passenger.setPosition(this.posX + vec3d.x, this.posY + 0.2F, this.posZ + vec3d.z);
            }
            else
            {
            	passenger.setPosition(this.posX, this.posY + this.getEntityBoundingBox().maxY - 1F, this.posZ);
            }
            
        }
    }
	
	protected void removePassenger(Entity passenger)
    {
		passenger.posY = this.posY + 0.2F + this.getEntityBoundingBox().maxY;
        super.removePassenger(passenger);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public float getHealthBar() 
	{
		return (this.health() - this.getDamages()) / this.health();
	}
	
	@Override
	public float getReloadBar() 
	{
		return (this.getReload() * 1.0F) / (this.reloadTime() * 1.0F);
	}
	
	public float getDamages()
    {
    	return this.dataManager.get(DAMAGES);
    }
    
    public void setDamages(float damages)
    {
        this.dataManager.set(DAMAGES, damages);
    }
    
    public int getExplode()
    {
    	return this.dataManager.get(EXPLODE);
    }
    
    public void setExplode(int explode)
    {
        this.dataManager.set(EXPLODE, MathHelper.clamp(explode, 0, this.explodeTime()));
    }
    
    public void addExplode(int explode)
    {
        this.dataManager.set(EXPLODE, MathHelper.clamp(this.getExplode() + explode, 0, this.explodeTime()));
    }
    
    public float getReload()
    {
    	return this.dataManager.get(RELOAD);
    }
    
    public void addReload(float reload)
    {
        this.dataManager.set(RELOAD, MathHelper.clamp(this.getReload() + reload, 0, this.reloadTime()));
    }
    
    public void setReload(float reload)
    {
        this.dataManager.set(RELOAD, MathHelper.clamp(reload, 0, this.reloadTime()));
    }
    
    protected int explodeTime()
    {
    	return 40;
    }
    
    protected int reloadTime()
    {
    	return 30;
    }
    
    public boolean isBurning()
    {
    	return this.getExplode() > 0;
    }
    
    protected boolean canFitPassenger(Entity passenger)
    {
        return this.getPassengers().size() < 3;
    }
    
    private void damageCollide()
    {
    	List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.2000776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

        if (!list.isEmpty())
        {
            boolean flag = !this.world.isRemote;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity = list.get(j);

                if (!entity.isPassenger(this) && entity.posY <= this.posY && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHealth() < 200)
                {
                	if(this.fallDistance != 0)
                	{
                		entity.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), 200);
                	}
                	else if(this.move)
                	{
                		entity.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), 200);
                	}
                }
            }
        }
    }
    
}
