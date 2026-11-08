package com.mod.mathsmod.add.Entity.Entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntitySuperHunterDeathOrb;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkeledon extends EntityFlying implements IMob, IOnShot
{
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
    /** The explosion radius of spawned fireballs. */
	protected static final DataParameter<Integer> ATTACK_TYPE = EntityDataManager.<Integer>createKey(EntitySkeledon.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> COOLDOWN = EntityDataManager.<Integer>createKey(EntitySkeledon.class, DataSerializers.VARINT);
	int cooldown;
	protected static final DataParameter<Float> RED = EntityDataManager.<Float>createKey(EntitySkeledon.class, DataSerializers.FLOAT);
	int strafe = 1;

    public EntitySkeledon(World worldIn)
    {
        super(worldIn);
        this.setSize(4.0F, 2.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 20304;
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(RED, 0.0F);
        this.dataManager.register(ATTACK_TYPE, 0);
        this.dataManager.register(COOLDOWN, 0);
    }
    
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }
    
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected void initEntityAI()
    {
    	this.tasks.addTask(1, new EntityAISwimming(this));
    	this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    	this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }
    
    public float getRed()
    {
    	return this.dataManager.get(RED);
    }
    
    public int getAttackType()
    {
    	return this.dataManager.get(ATTACK_TYPE);
    }
    
    public void setAttackType(int type)
    {
    	this.dataManager.set(ATTACK_TYPE, type);
    }
    
    public int getCooldown()
    {
    	return this.dataManager.get(COOLDOWN);
    }
    
    public void setCooldown(int cool)
    {
    	this.dataManager.set(COOLDOWN, cool);
    }
    
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }
        
        if(this.getHealth() > 0)
        {
        	if(this.getAttackTarget() != null)
            {
            	EntityLivingBase target = this.getAttackTarget();
            	UtilsVoids.setLookEntity(target, this);
            	this.travel(strafe, 0, this.getDistance(target) < 6 ? -1 : this.getDistance(target) > 20 ? 1 : 0);
            	
            	if(target.posY + 5 > this.posY && this.getAttackType() != 2)
            	{
            		this.motionY = 0.5D;
            	}
            	else
            	{
            		this.motionY = -0.2F;
            	}
            	
            	if(this.getRNG().nextInt(200) == 0 && this.getAttackType() == 0)
            	{
            		this.setAttackType(1);
            	}
            	else if(this.getRNG().nextInt(300) == 0 && this.getAttackType() == 0)
            	{
            		this.setAttackType(2);
            	}
            	else if(this.getRNG().nextInt(170) == 0 && this.getAttackType() == 0)
            	{
            		this.setAttackType(3);
            	}
            	
            	if(this.getRNG().nextInt(80) == 0 && this.getAttackType() == 0)
            	{
            		strafe *= -1;
            	}
            	
            	if(this.getAttackType() == 0)
            	{
            		if(this.cooldown == 4)
            		{
            			EntityLargeFireball projec = new EntityLargeFireball(world, this, 0, 0, 0);
            			projec.explosionPower = 2;
            			UtilsVoids.EasyShootEntity(projec, this.rotationPitch, this.rotationYaw, 2, 0);
            			if(!world.isRemote)
            			{
            				world.spawnEntity(projec);
            			}
            			cooldown = 0;
            		}
            		else
            		{
            			cooldown += 1;
            		}
            	}
            	else if(this.getAttackType() == 1)
            	{
            		if(cooldown == 60)
            		{
            			this.dataManager.set(RED, 0.0F);
            			this.setAttackType(0);
            			cooldown = 0;
            			for(int i = 0; i < 3; ++i)
            			{
            				EntityBlal entity = new EntityBlal(world);
            				entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 3));
            				entity.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 200, 1));
            				entity.setPosition(this.posX, this.posY, this.posZ);
            				UtilsVoids.EasyShootEntity(entity, this.rotationPitch - 10, this.rotationYaw, 2, 0);
            				if(!world.isRemote)
                			{
                				world.spawnEntity(entity);
                				world.addWeatherEffect(new EntityLightningBolt(world, this.posX, this.posY, this.posZ, true));
                			}
            			}
            		}
            		else
            		{
            			this.dataManager.set(RED, ((float)cooldown) / 60.0F);
            			cooldown += 1;
            		}
            	}
            	else if(this.getAttackType() == 2)
            	{
            		if(cooldown >= 30)
            		{
            			cooldown += 1;
            			
            			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, this.world, this,15))
            			{
            				UtilsVoids.AttractEntity(this, entity, 15, 4);
            				
            				if(entity instanceof EntityPlayerMP)
            				{
            					Mathsmod.network.sendTo(new MessageModifyVelocity(new Vec3d(entity.motionX, entity.motionY, entity.motionZ)), (EntityPlayerMP) entity);
            				}
            				
            				if(entity.getDistance(this) < 3 && entity != this)
            				{
            					entity.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), 5);
            				}
            			}
            			
            			if(cooldown == 90)
                		{
            				this.setAttackType(0);
            				this.cooldown = 0;
            				this.dataManager.set(RED, 0.0F);
                		}
            		}
            		else
            		{
            			this.dataManager.set(RED, ((float)cooldown) / 30.0F);
            			cooldown += 1;
            		}
            	}
            	else if(this.getAttackType() == 3)
            	{
            		cooldown = 0;
            		this.setAttackType(0);
        			for(int i = 0; i < 7; ++i)
        			{
        				if(!world.isRemote)
            			{
            				world.addWeatherEffect(new EntityLightningBolt(world, target.posX, target.posY, target.posZ, false));
            			}
        			}
            	}
            }
            else
            {
            	if(world.getHeight(new BlockPos(this)).getY() + 2 > this.posY)
            	{
            		this.motionY = 0.4D;
            	}
            	else
            	{
            		this.motionY = -0.2F;
            	}
            }
        	
        	this.setCooldown(cooldown);
        }
        else
        {
        	motionY -= 0.02D;
        }
        
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
    
    @Override
    protected void onDeathUpdate()
    {
    	this.deathTime += 1;
    	
    	this.setGlowing(true);
    	
        if(this.deathTime >= 100)
        {
        	for(EntityBlal entity : UtilsVoids.getEntities(EntityBlal.class, world, this, 100))
        	{
        		entity.setDead();
        	}
        	
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")))
            {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }
            
            EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, this.getHeldItemMainhand().copy());

            if (entityitem != null)
            {
                entityitem.setNoDespawn();
            }
            
            if(!world.isRemote)
            {
            	this.dropItem(RegisterItems.BOT, 1);
            }
            
            this.setDead();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source.getImmediateSource() instanceof EntityLargeFireball && source.getTrueSource() instanceof EntityPlayer)
        {
            super.attackEntityFrom(source, amount * 2.0F);
            return true;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return false;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
    }

    public float getEyeHeight()
    {
        return 2.6F;
    }

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
		UtilsVoids.attackEntityFrom(this, DamageSource.GENERIC, 3);
	}
}