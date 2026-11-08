package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTankRocket;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDevHand  extends EntityMob implements IOnShot
{
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	
	
	public boolean attackingFinal;
	protected static final DataParameter<Integer> CHARGE = EntityDataManager.<Integer>createKey(EntitySuperHunter.class, DataSerializers.VARINT);
	
	private int timer;
 	private int reload;
 	public int watchTick;
	
	public EntityDevHand(World worldIn) 
	{
		super(worldIn);
		this.setGlowing(false);
		this.setSize(2F, 4F);
		this.setDropChance(EntityEquipmentSlot.MAINHAND, 0F);
		this.isImmuneToFire = true;
		this.experienceValue = 200000;
	}
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CHARGE, 0);
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
    
    public boolean isImmuneToExplosions()
    {
        return true;
    }
    
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4300000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
    }
	
	public boolean moveBackward()
    {
    	return false;
    }
	
	protected void initEntityAI()
    {
         this.tasks.addTask(1, new EntityAISwimming(this));
         this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
         this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
         this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
         this.tasks.addTask(8, new EntityAILookIdle(this));
         this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
         this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, null));
     }
	
     public void onUpdate()
     {
     	this.watchTick = MathHelper.clamp(--this.watchTick, 0, 40);
     	
     	if(UtilsVoids.getEntities(EntityPlayer.class, world, this, 200).isEmpty())
     	{
     		this.setDead();
     	}
     	
     	if(this.hurtTime > 0)
     	{
     		if(this.posY < world.getHeight((int)this.posX, (int)this.posZ));
     		{
     			this.setPositionAndUpdate(this.posX, world.getHeight((int)this.posX, (int)this.posZ), this.posZ);
     		}
     	}
     	
     	if(!this.attackingFinal)
 		{
 			this.setCharge(0);
 		}
     	
     	if(this.getHealth() > 0)
     	{
     		if(this.timer > 0)
         	{
         		--timer;
         	}
         	
         	if(this.getAttackTarget() != null && this.getAttackTarget() != this)
         	{
         		if(UtilsVoids.distanceIgnoreY(this.posX, this.posZ, this.getAttackTarget()) > 30)
         		{
         			this.getAttackTarget().attackEntityFrom(DamageSource.OUT_OF_WORLD, 4);
         		}
         		
         		if(this.getRNG().nextInt(200) == 0)
         		{
         			for(int i = -2; i <=2; ++i)
         			{
         				EntityBMissile entity = new EntityBMissile(world, new BlockPos(this.getAttackTarget()));
         				entity.setPosition(this.posX + this.getRNG().nextInt(7) - 3, this.posY + 2, this.posZ + this.getRNG().nextInt(7) - 3);
         				if(!world.isRemote)
         				{
         					world.spawnEntity(entity);
         				}
         			}
         		}
         		
         		if(this.getRNG().nextInt(200) == 0)
            	{
        			for(int i = 0; i < 14; ++i)
        			{
        				EntityLivingBase entity = new EntityFrostBlal(world);
        				if(this.getRNG().nextInt(3) == 0)
        				{
        					entity = new EntityFrostSkeleton(world);
        				}
        				if(this.getRNG().nextInt(3) == 1)
        				{
        					entity = new EntitySBlaze(world);
        				}
        				entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 2));
        				entity.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 200, 1));
        				entity.setPosition(this.posX + this.getRNG().nextInt(5) - 2, this.posY, this.posZ + this.getRNG().nextInt(5) - 2);
        				
        				if(!world.isRemote)
            			{
            				world.spawnEntity(entity);
            				world.addWeatherEffect(new EntityLightningBolt(world, this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, true));
            			}
        			}
            	}
         		
         		if(this.getRNG().nextInt(150) == 0 && !this.attackingFinal)
         		{
         			this.attackingFinal = true;
         		}
         		
         		if(this.attackingFinal)
         		{
     				this.motionX *= 0.0000001;
     				this.motionZ *= 0.0000001;
     				this.motionY = 0.3F;
     				this.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 10000));
         			if(this.getCharge() < 40)
         			{
         				this.addCharge(1);
         			}
         			if(this.getCharge() >= 30 && this.getCharge() % 5 == 0)
         			{
         				UtilsVoids.setLookEntity(this.getAttackTarget(), this);
         				EntityTankRocket entity = new EntityTankRocket(world, this, 9F, 2F, this.rotationYaw, this.rotationPitch);
         				if(!world.isRemote)
         				{
         					world.spawnEntity(entity);
         				}
         			}
         			if(this.getCharge() >= 40)
         			{
         				this.attackingFinal = false;
             			this.setCharge(0);
         			}
         		}
         		if(this.getDistance(this.getAttackTarget()) < 3 && this.getRNG().nextInt(6) == 0)
     			{
     				this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), 10);
     			}
         	}
    		
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
     	}
     	
     	super.onUpdate();
     }
     
     protected boolean canDespawn()
     {
         return false;
     }
     
     public void writeEntityToNBT(NBTTagCompound compound)
     {
         super.writeEntityToNBT(compound);
     }

     /**
      * (abstract) Protected helper method to read subclass entity data from NBT.
      */
     public void readEntityFromNBT(NBTTagCompound compound)
     {
         super.readEntityFromNBT(compound);
     }
     
     public void setTimer(int timerIn)
     {
     	timer = timerIn;
     }
     
     public int getCharge()
     {
    	 return this.getDataManager().get(CHARGE);
     }
     
     public void setCharge(int charge)
     {
    	 this.dataManager.set(CHARGE, charge);
     }
     
     public void addCharge(int charge)
     {
    	 this.dataManager.set(CHARGE, this.getCharge() + charge);
     }

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
	}

	
	@Override
    protected void onDeathUpdate()
    {
    	this.deathTime += 1;
    	
    	this.setGlowing(true);
    	
        if(this.deathTime >= 100)
        {
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
            	this.dropItem(RegisterItems.POLARITE_FRYING_PAN, 1);
            	this.dropItem(RegisterItems.SMASHER, 1);
            }
            
            this.setDead();
        }
    }
}