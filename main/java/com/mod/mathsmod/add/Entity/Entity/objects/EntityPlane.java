package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.container.easyInv.EasyInventoryEntity;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemMissile;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.DamageSourceGun;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityPlane extends Entity implements IVehicle, IReloadVehicle
{
	
	public NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	protected static final DataParameter<Float> DAMAGES = EntityDataManager.<Float>createKey(EntityPlane.class, DataSerializers.FLOAT);
	protected static final DataParameter<String> TEXTURE = EntityDataManager.<String>createKey(EntityPlane.class, DataSerializers.STRING);
	
	protected static final DataParameter<Integer> FUEL = EntityDataManager.<Integer>createKey(EntityPlane.class, DataSerializers.VARINT);
	protected float boost;
	protected boolean noControl;
	public float prevRotation;
	public float rotation;
	public float strafing;
	protected int cooldown;
	protected float forward;
	protected boolean side;

	public EntityPlane(World worldIn) {
		super(worldIn);
		this.setSize(3, 2);
		this.isImmuneToFire = true;
	}

	public int getSizeInventory() {
		return 47;
	}
	
	protected int getMaxFuel()
	{
		return 50000;
	}

	@Override
	protected void entityInit() 
	{
		this.dataManager.register(DAMAGES, 0.0F);
		this.dataManager.register(TEXTURE, "");
		this.dataManager.register(FUEL, this.getMaxFuel());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.setDamages(compound.getFloat("damages"));
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.boost = compound.getFloat("boost");
		this.setFuel(compound.getInteger("fuel"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setFloat("damages", this.getDamages());
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setFloat("boost", this.boost);
		compound.setInteger("fuel", this.getFuel());
	}
	
	public float getSpeed()
	{
		return 0.6F * this.boost;
	}
	
	//TODO Fuel
	public int getFuel()
	{
		return this.dataManager.get(FUEL);
	}
	
	public void setFuel(int fuel)
	{
		this.dataManager.set(FUEL, fuel);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.crashHelper();
		float i = 0;
		this.prevRotation = this.rotation;
		
		if(this.getDamages() > this.maxDamages() / 2)
		{
			float d = (1 - this.getHealthBar()) * 6;
			for(int u = 0; u < d; ++u)
	    	{ 
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + new Random().nextDouble() * 2 - 1, posY + 1, posZ + new Random().nextDouble() * 2 - 1, 0, 0, 0);
	    	}
		}
		
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
		{
			if(!world.isRemote && !this.onGround)
			{
				this.setFuel(MathHelper.clamp(this.getFuel() - 1, 0, this.getMaxFuel()));
			}
			EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
			this.MoveLikeEntity(entity.moveStrafing, 0, entity.moveForward);
		}
		else
		{
			this.MoveLikeEntity(0, 0, this.noControl ? 1 : 0);
			if(this.noControl && !this.onGround)
			{
				this.setFuel(MathHelper.clamp(this.getFuel() - 1, 0, this.getMaxFuel()));
			}
			else if(!this.noControl)
			{
				this.setFuel(MathHelper.clamp(this.getFuel() + 10, 0, this.getMaxFuel()));
			}
		}
		
		i = this.boost * 5;
		
		if(i > 0)
		{
			rotation += i;
		}
		
		if(this.rotation > 360)
		{
			this.rotation -= 360;
		}
		
		if(cooldown < this.getCooldown())
		{
			cooldown += 1;
		}
		
		if(this.getDamages() >= this.maxDamages())
    	{
    		if(!world.isRemote)
    		{
    			this.explode();
    		}
    	}
		
	}

	public float maxDamages()
	{
		return 300F;
	}
	
	public double getMountedYOffset()
    {
        return 1.2D;
    }
	
	protected boolean canTriggerWalking()
    {
        return false;
    }
	
	@Override
	protected void removePassenger(Entity passenger)
    {
		if(!this.onGround)
		{
			this.noControl = true;
		}
		
		EntityGlider glider = new EntityGlider(this.world);
		glider.setUser(passenger);
		glider.setPosition(posX, posY, posZ);
		if(!world.isRemote)
		{
			world.spawnEntity(glider);
		}
		
        super.removePassenger(passenger);
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
    	if(source.getTrueSource() instanceof EntityPlayer && this.getPassengers().size() == 0 && !source.isProjectile() && !source.isMagicDamage() && this.boost < 0.1)
    	{
    		if(!world.isRemote && !this.isDead)
    		{
    			world.spawnEntity(new EntityItem(world, posX, posY, posZ, new ItemStack(this.getEntityItem())));
    		}
    		this.setDead();
    	}
    	else if(source.isExplosion() && amount >= 10)
    	{
    		this.setDead();
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
    	return RegisterItems.PLANE;
    }
    
    public float getDamages()
    {
    	return this.dataManager.get(DAMAGES);
    }
    
    public void setDamages(float damages)
    {
        this.dataManager.set(DAMAGES, damages);
    }
    
    public void addDamages(float damages)
    {
        this.dataManager.set(DAMAGES, this.getDamages() + damages);
    }
    
    public String getTexture()
    {
    	return this.dataManager.get(TEXTURE);
    }
    
    public void setTexture(String texture)
    {
        this.dataManager.set(TEXTURE, texture);
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
            player.openGui(Mathsmod.instance, 4, world, this.getEntityId(), 0, 0);
            return true;
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
    
    
    //TODO modify
    public void MoveLikeEntity(float strafe, float up, float forward)
    {
    	this.motionX *= 0;
        this.motionZ *= 0;
        
    	if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer)
    	{
    		Entity entity = this.getControllingPassenger();
    		
    		this.rotationPitch = MathHelper.clamp(entity.rotationPitch, this.rotationPitch - 5, this.rotationPitch + 5);
    		this.rotationYaw = entity.rotationYaw;
    		this.strafing = strafe / 4;
    		
    		if(this.onGround)
    		{
    	        if(forward > 0)
    	    	{
    	    		this.boost = MathHelper.clamp(this.boost + 0.005F, 0, 1);
    	    	}
    	        if(forward == 0)
    	    	{
    	    		this.boost = MathHelper.clamp(this.boost * 0.99F , 0, 1);
    	    	}
    	        if(forward < 0)
    	    	{
    	        	this.boost = MathHelper.clamp(this.boost - 0.01F , -0.1F, 1);
    	    	}
    	        
    	        if(this.isInWater() || this.isInLava())
    			{
    	        	if(this.boost > 0.5F)
        			{
    	        		this.boost = MathHelper.clamp(this.boost * 0.95F, 0, 1);
        			}
    	        	else
    	        	{
        	        	this.boost = 0;
    	        	}
    			}
    	        
    	        if(this.boost == 1.0)
    	        {
    	        	this.motionY = this.rotationPitch / -90;
    	        	
    	        	if(this.getFuel() == 0 && motionY >= 0)
        			{
        				motionY = this.rotationPitch / 90 - 0.2F;
        			}
    	        }
    	        
    	        this.moveRelative(0, up, 1, this.getSpeed());
    		}
    		else
    		{
    			
    			if(this.boost >= 0.9F)
    			{
    				this.motionY = this.rotationPitch / -90;
    				
    				if(this.getFuel() == 0 && motionY >= 0)
        			{
        				motionY = this.rotationPitch / 90 - 0.2F;
        			}
    			}
    			
    			if(this.isInWater() || this.isInLava())
    			{
    				this.moveRelative(strafe / 4, up, 1, this.getSpeed());
    				this.motionX *= this.getWaterSlowDown();
        	        this.motionZ *= this.getWaterSlowDown();
        	        
        	        this.boost = MathHelper.clamp(this.boost * this.getWaterSlowDown(), 0, 1);
    			}
    			else
    			{
    				this.moveRelative(strafe / 4, up, 1, this.getSpeed());
    				this.boost = MathHelper.clamp(this.boost + 0.01F, 0, 1);
    			}
    		}
    		
    		if(this.boost < 0.5F)
    		{
    			this.motionY -= 0.02;
    		}
    		else
    		{
    			this.moveRelative(strafe / 4, up, 1, this.getSpeed());
    			this.motionY = this.rotationPitch / -90;
    			
    			if(this.getFuel() == 0 && motionY >= 0)
    			{
    				motionY = this.rotationPitch / 90 - 0.2F;
    			}
    			
    		}
    	}
    	else if(this.boost < 0.5F)
    	{
    		this.motionY -= 0.02;
    	}
    	else
    	{
    		this.moveRelative(strafe / 4, up, 1, this.getSpeed());
    		this.motionY = this.rotationPitch / -90;
    		
    		if(this.getFuel() == 0 && motionY >= 0)
			{
				motionY = this.rotationPitch / 90 - 0.2F;
			}
    	}
    	
    	this.motionY *= 0.9800000190734863D;
    	this.move(MoverType.SELF, motionX, motionY, motionZ);
    }

	protected float getWaterSlowDown() {
		return 0.96F;
	}

	protected void crashHelper()
	{
		if (this.motionY > -0.5D)
        {
            this.fallDistance = 1.0F;
        }
		
		if(this.getPassengers().isEmpty())
		{
	        if (this.collidedHorizontally && !this.world.isRemote && this.boost > 0.5F && !this.onGround)
	        {
	            double d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

	            if (d11 < 0.4F)
	            {
	                this.explode();
	            }
	        }
		}
    }
	
	protected void explode()
	{
		this.setDead();
        world.newExplosion(null, posX, posY, posZ, 8, true, true);
	}
	
	public void fall(float distance, float damageMultiplier)
    {
		if(distance > 5)
		{
			this.attackEntityFrom(DamageSource.FALL, distance * 10.5F);
		}
        if (this.isBeingRidden())
        {
            for (Entity entity : this.getPassengers())
            {
                entity.fall(distance, damageMultiplier);
            }
        }
    }
	
	public void setDead()
    {
		if(!world.isRemote)
		{
			for(ItemStack stack : this.inventory)
			{
				world.spawnEntity(new EntityItem(world, posX, posY, posZ, stack));
			}
		}
		/*if(this.getControllingPassenger() != null)
		{
			this.world.updateEntityWithOptionalForce(getControllingPassenger(), true);
		}*/
        super.setDead();
        
    }
	
	public void LaunchMissile(EntityPlayer player)
	{
		if(this.inventory.get(45).getItem() instanceof ItemMissile)
		{
			player.getCooldownTracker().setCooldown(this.inventory.get(45).getItem(), 50);
			world.spawnEntity(((ItemMissile) this.inventory.get(45).getItem()).PlaneMissile(this));
			if(!player.capabilities.isCreativeMode)
			{
				this.inventory.get(45).shrink(1);
			}
			return;
		}
		if(this.inventory.get(46).getItem() instanceof ItemMissile)
		{
			player.getCooldownTracker().setCooldown(this.inventory.get(46).getItem(), 50);
			world.spawnEntity(((ItemMissile) this.inventory.get(46).getItem()).PlaneMissile(this));
			if(!player.capabilities.isCreativeMode)
			{
				this.inventory.get(46).shrink(1);
			}
			return;
		}
	}
	
	public boolean canLaunchMissile(EntityPlayer player)
	{
		if(this.inventory.get(45).getItem() instanceof ItemMissile)
		{
			if(!player.getCooldownTracker().hasCooldown(this.inventory.get(45).getItem()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if(this.inventory.get(46).getItem() instanceof ItemMissile)
		{
			if(!player.getCooldownTracker().hasCooldown(this.inventory.get(46).getItem()))
			{
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
	
	protected int getCooldown()
	{
		return 2;
	}
	
	protected void planeGun(float rotationPitch, float rotationYaw)
	{
		int e = this.side ? 1 : -1;
		Vec3d vec = UtilsVoids.laser(1.7F * e, rotationYaw + 90, rotationPitch);
		
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer)
		{
			/*for(double i = 0; i <= 100 * 2; ++i)
			{
				double t = i / 2;
				Vec3d vec3d = UtilsVoids.laser(new Vec3d(t, 0, 0), rotationYaw, rotationPitch);
				
					//world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + vec.x, this.posY + 1.5F + vec.y, this.posZ + vec.z, 0, 0, 0, Block.getIdFromBlock(Blocks.FIRE));
				
				
			}*/
			
			RegisterItems.SNIPER_BULLET.preSpawn(world, (EntityLivingBase) this.getControllingPassenger(), new ItemStack(RegisterItems.AK47), (ItemGun) RegisterItems.AK47, false, rotationYaw, rotationPitch, this.posX + vec.x, this.posY + 1.5F + vec.y, this.posZ + vec.z, true);
		}
		
	}
	
	public boolean canShoot()
	{
		return cooldown >= this.getCooldown();
	}
	
	public void Shoot()
	{
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer)
		{
			EntityLivingBase target = UtilsVoids.getNearestEntity(this, 60);
			
			float rotationPitch = this.rotationPitch;
			float rotationYaw = this.rotationYaw;
	        
			side = !side;
			planeGun(rotationPitch, rotationYaw);
			this.world.playSound((EntityPlayer) null, posX, posY, posZ, SoundEvents.ENTITY_FIREWORK_LARGE_BLAST, SoundCategory.AMBIENT, 10, 1.5F);
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
		return (float)this.getFuel() / (float)this.getMaxFuel();
	}
	
}
