package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityTurretAuto extends EntityTurret
{
	protected static final DataParameter<String> OWNERS = EntityDataManager.<String>createKey(EntityTurretAuto.class, DataSerializers.STRING);
	protected static final DataParameter<Boolean> ACTIVATED = EntityDataManager.<Boolean>createKey(EntityTurretAuto.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> TEMP = EntityDataManager.<Integer>createKey(EntityTurretAuto.class, DataSerializers.VARINT);
	
	protected static final DataParameter<Integer> BUILD_TIME = EntityDataManager.<Integer>createKey(EntityTurretAuto.class, DataSerializers.VARINT);
	protected static final DataParameter<Float> LIFE = EntityDataManager.<Float>createKey(EntityTurretAuto.class, DataSerializers.FLOAT);
	int tempCooldown;
	Random rand = new Random();

	public EntityTurretAuto(World worldIn) 
	{
		super(worldIn);
	}
	
	public float getMaxHealth()
	{
		return 60F;
	}
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.dataManager.register(ACTIVATED, true);
		this.dataManager.register(OWNERS, "");
		this.dataManager.register(TEMP, 0);
		this.dataManager.register(BUILD_TIME, 0);
		this.dataManager.register(LIFE, this.getMaxHealth());
	}
	
	protected float rotationSpeed()
	{
		return 5.0F;
	}
	
	@Override
	public void onUpdate() {
		
		super.onUpdate();
		
		if(this.getBuildTime() >= 100)
		{
			if(this.getActivated() && this.getHealth() > 0)
			{
				if(this.getControllingPassenger() == null)
				{
					if(!UtilsVoids.getEntitiesTurret(world, this, 15).isEmpty())
					{
						Entity entity = UtilsVoids.getEntitiesTurret(world, this, 15).get(0);
						if(entity != null && this.canShoot())
						{
							if(!this.getOwnersEntities().contains(entity))
							{
								if(entity instanceof IEntityOwnable)
								{
									if(((IEntityOwnable) entity).getOwnerId() == null || !this.getOwnersEntities().contains(((IEntityOwnable) entity).getOwner()))
									{
										if(world.rayTraceBlocks(new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), new Vec3d(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false, true, false) == null)
								    	{
											Vec3d rots = UtilsVoids.getRotationObjectives(entity, this);
											double pitch = rots.x;
											double yaw = rots.y;
											
											double dy = (this.rotationYaw - yaw) % 360;
											double dp = (this.rotationPitch - pitch);
											
											if(Math.abs(dy) < this.rotationSpeed())
											{
												this.rotationYaw = (float) yaw;
											}
											else
											{
												if(dy < 0)
												{
													this.rotationYaw += this.rotationSpeed();
												}
												else if(dy > 0)
												{
													this.rotationYaw -= this.rotationSpeed();
												}
											}
											
											if(Math.abs(dp) < this.rotationSpeed())
											{
												this.rotationPitch = (float) pitch;
											}
											else
											{
												if(dp < 0)
												{
													this.rotationPitch += this.rotationSpeed();
												}
												else if(dy > 0)
												{
													this.rotationPitch -= this.rotationSpeed();
												}
											}
											
											if(Math.abs(dy) < this.rotationSpeed() && Math.abs(dp) < this.rotationSpeed())
											{
												this.shoot();
												//this.setTemp(this.getTemp() + 1);
											}
											//UtilsVoids.setLookEntity(entity, this);
								    	}
									}
								}
								else
								{
									if(world.rayTraceBlocks(new Vec3d(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ), new Vec3d(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ), false, true, false) == null)
							    	{
										Vec3d rots = UtilsVoids.getRotationObjectives(entity, this);
										double pitch = rots.x;
										double yaw = rots.y;
										
										double dy = (this.rotationYaw - yaw) % 360;
										double dp = (this.rotationPitch - pitch);
										
										if(Math.abs(dy) < this.rotationSpeed())
										{
											this.rotationYaw = (float) yaw;
										}
										else
										{
											if(dy < 0)
											{
												this.rotationYaw += this.rotationSpeed();
											}
											else if(dy > 0)
											{
												this.rotationYaw -= this.rotationSpeed();
											}
										}
										
										if(Math.abs(dp) < this.rotationSpeed())
										{
											this.rotationPitch = (float) pitch;
										}
										else
										{
											if(dp < 0)
											{
												this.rotationPitch += this.rotationSpeed();
											}
											else if(dy > 0)
											{
												this.rotationPitch -= this.rotationSpeed();
											}
										}
										
										if(Math.abs(dy) < this.rotationSpeed() && Math.abs(dp) < this.rotationSpeed())
										{
											this.shoot();
											//this.setTemp(this.getTemp() + 1);
										}
										//UtilsVoids.setLookEntity(entity, this);
							    	}
								}
							}
						}
					}
				}
				
				if(this.getTemp() > 50)
				{
					this.tempCooldown = 160;
					this.setTemp(50);
				}
				
				if(this.tempCooldown > 0)
				{
					this.setTemp((int) this.progressTempChanges());
					world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 10, 1);
					world.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY + 0.5, this.posZ, rand.nextDouble() * 0.3 - 0.15, 0.3 , rand.nextDouble() * 0.3 - 0.15);
					--tempCooldown;
				}
			}
			else if(this.getHealth() <= 0)
			{
				world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 10, 1);
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, rand.nextDouble() * 0.3 - 0.15, 0.3 , rand.nextDouble() * 0.3 - 0.15);
			}
		}
		else
		{
			this.setBuildTime(this.getBuildTime() + 1);
		}
	}
	
	public float tempColor()
	{
		return ((float)this.getTemp()) / 50F;
	}
	
	protected float progressTempChanges()
	{
		return (((float)this.tempCooldown) / 160F) * 50F;
	}

	public void setActivated(boolean act)
	{
		this.dataManager.set(ACTIVATED, act);
	}
	
	public boolean getActivated()
	{
		return this.dataManager.get(ACTIVATED);
	}
	
	public void setFirstOwner(String owner)
	{
		this.dataManager.set(OWNERS, owner);
	}
	
	public String getOwners()
	{
		return this.dataManager.get(OWNERS);
	}
	
	public void addOwner(String owner)
	{
		this.dataManager.set(OWNERS, this.getOwners() + "-" + owner);
	}
	
	public int getTemp()
	{
		return this.dataManager.get(TEMP);
	}
	
	public void setTemp(int temp)
	{
		this.dataManager.set(TEMP, temp);
	}
	
	public int getBuildTime()
	{
		return this.dataManager.get(BUILD_TIME);
	}
	
	public void setBuildTime(int time)
	{
		this.dataManager.set(BUILD_TIME, time);
	}
	
	public float getHealth()
	{
		return this.dataManager.get(LIFE);
	}
	
	public void setHealth(float health)
	{
		this.dataManager.set(LIFE, health);
	}
	
	public List<Entity> getOwnersEntities()
	{
		List<Entity> list = Lists.newArrayList();
		String i = "" + this.getOwners();
		String[] names = i.split("-");
		for(String name : names)
		{
			if(world.getPlayerEntityByName(name) != null)
			{
				list.add(world.getPlayerEntityByName(name));
			}
		}
		
		return list;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.setFirstOwner(compound.getString("owners"));
		this.setActivated(compound.getBoolean("activated"));
		this.setBuildTime(compound.getInteger("build_time"));
		this.setHealth(compound.getFloat("health"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setString("owners", this.getOwners());
		compound.setBoolean("activated", this.getActivated());
		compound.setInteger("build_time", this.getBuildTime());
		compound.setFloat("health", this.getHealth());
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayer && !source.isProjectile() && !source.isMagicDamage())
    	{
    		if(this.getOwnersEntities().contains(source.getTrueSource()) && !world.isRemote)
    		{
    			if(this.getHealth() >= this.getMaxHealth())
    			{
    				this.setActivated(!this.getActivated());
    			}
    			else
    			{
    				this.setHealth(MathHelper.clamp(this.getHealth() + 20, 0, this.getMaxHealth()));
    			}
    		}
    	}
    	else
    	{
    		this.setHealth(MathHelper.clamp(this.getHealth() - amount, 0, this.getMaxHealth()));
    	}
    	
		return false;
    }
    
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote && this.getOwnersEntities().contains(player))
            {
                player.startRiding(this);
            }
            
            if (!this.world.isRemote && !this.getActivated() && !this.getOwnersEntities().contains(player))
            {
                this.addOwner(player.getName());
            }
            
            return true;
        }
    }
    
    public void shoot()
    {
    	if(this.tempCooldown == 0)
    	{
    		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase)
    		{
    			if(this.getHealth() > 0.0F)
    			{
    				RegisterItems.SNIPER_BULLET.preSpawn(world, (EntityLivingBase) this.getControllingPassenger(), new ItemStack(RegisterItems.MACHINE_PISTOL), (ItemGun) RegisterItems.MACHINE_PISTOL, false, this.rotationYaw, this.rotationPitch, this.posX, this.posY + 1, this.posZ, true);
    			}
    		}
    		else
    		{
    			RegisterItems.SNIPER_BULLET.preSpawn(world, this, new ItemStack(RegisterItems.MACHINE_PISTOL), (ItemGun) RegisterItems.MACHINE_PISTOL, false, this.rotationYaw, this.rotationPitch, this.posX, this.posY + 1, this.posZ, true);
    		}
    		
    		cooldown = 0;
    		world.playSound(null, this.posX, this.posY + 1, this.posZ, MathsSounds.AA12_SOUND, SoundCategory.AMBIENT, 10.5F, 1.5F + world.rand.nextFloat() * 0.2F);
    	}
    }
}
