package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntitySuperHunterDeathOrb;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTankRocket;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTrapHarpoon;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemCommunistDisk;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMedigun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySuperHunter extends EntityMob
{
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	
	
	public boolean attackingFinal;
	protected static final DataParameter<Integer> CHARGE = EntityDataManager.<Integer>createKey(EntitySuperHunter.class, DataSerializers.VARINT);
	
	private int timer;
 	private int reload;
 	public int watchTick;
	
	public EntitySuperHunter(World worldIn) 
	{
		super(worldIn);
		this.setGlowing(false);
		this.setSize(0.9F, 2.4F);
		this.setDropChance(EntityEquipmentSlot.MAINHAND, 0F);
		this.isImmuneToFire = true;
		this.experienceValue = 20000;
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(800.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
    }
	
	public boolean moveBackward()
    {
    	return false;
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
            
            EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(RegisterItems.SUPER_HUNTER_CORE));
            if(this.getHeldItemMainhand().getItem() == RegisterItems.GOLDEN_BAT)
            {
            	EntityItem entityitem2 = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(RegisterItems.GOLDEN_BAT));
            	
            	if (entityitem2 != null)
                {
                    entityitem2.setNoDespawn();
                }
                
                if(!world.isRemote)
                {
                	world.spawnEntity(entityitem2);
                }
            }
            if (entityitem != null)
            {
                entityitem.setNoDespawn();
            }
            
            if(!world.isRemote)
            {
            	world.spawnEntity(entityitem);
            }
            
            this.setDead();
        }
        
        /*if(this.deathTime == 99)
        {
        	this.motionY = 0.05;
        	for(int i = 0; i < 360; i += 15)
			{
				for(int j = 0; j < 180; j += 15)
				{
					world.spawnEntity(new EntitySuperHunterDeathOrb(world, this, i, j, 0.25F));
				}
			}
        }*/
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
     	
     	if(this.hurtTime > 0)
     	{
     		if(this.posY < world.getHeight((int)this.posX, (int)this.posZ));
     		{
     			this.posY = world.getHeight((int)this.posX, (int)this.posZ);
     		}
     	}
     	
     	if(!this.attackingFinal)
 		{
 			this.setCharge(0);
 		}
     	
     	for(EntityLivingBase entity : UtilsVoids.getEntities(EntityBlal.class, world, this, 60))
 		{
 			entity.setDead();
 		}
 		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityDeadHunter.class, world, this, 60))
 		{
 			entity.setDead();
 		}
     	
     	if(this.getHealth() > 0)
     	{
     		if(this.getHeldItemMainhand().getItem() instanceof ItemGun)
    		{
    			if(((ItemGun) this.getHeldItemMainhand().getItem()).ChargerEmpty(this.getHeldItemMainhand()))
    			{
    				((ItemGun) this.getHeldItemMainhand().getItem()).setReloading(getHeldItemMainhand(), true);
    				((ItemGun) this.getHeldItemMainhand().getItem()).reloadGun(getHeldItemMainhand(), world, this);
    				((ItemGun) this.getHeldItemMainhand().getItem()).setCooldown(getHeldItemMainhand(), (float) this.timer / (float) ((ItemGun) this.getHeldItemMainhand().getItem()).cooldown());
    			}
    		}
     		
     		if(this.timer > 0)
         	{
         		--timer;
         	}
         	
         	if(this.getAttackTarget() != null && this.getAttackTarget() != this)
         	{
         		if(UtilsVoids.distanceIgnoreY(this.posX, this.posZ, this.getAttackTarget()) > 50)
         		{
         			this.getAttackTarget().attackEntityFrom(DamageSource.OUT_OF_WORLD, 1);
         		}
         		
         		if(this.getRNG().nextInt(300) == 0)
         		{
         			EntityBMissile entity = new EntityBMissile(world, new BlockPos(this.getAttackTarget()));
     				entity.setPosition(this.posX, this.posY + 2, this.posZ);
     				if(!world.isRemote)
     				{
     					world.spawnEntity(entity);
     				}
         		}
         		
         		if(this.getRNG().nextInt(300) == 0)
         		{
         			EntityTrapHarpoon entity = new EntityTrapHarpoon(world, this);
         			entity.setPosition(this.posX, this.posY + 2, this.posZ);
         			
     				if(!world.isRemote)
     				{
     					world.spawnEntity(entity);
     				}
         		}
         		
         		if(this.getRNG().nextInt(250) == 0 && !this.attackingFinal)
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
         				EntityTankRocket entity = new EntityTankRocket(world, this, 4F, 2F, this.rotationYaw, this.rotationPitch);
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
         		if(this.getDistance(this.getAttackTarget()) < 5 && this.getRNG().nextInt(10) == 0)
     			{
     				this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), 5);
     			}
         		else
         		{
         			this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 3, 3);
             		Entity entity = this.getAttackTarget();
             		Item item = this.getHeldItemMainhand().getItem();
             		item.onUpdate(getHeldItemMainhand(), world, this, 0, true);
             		item.onUsingTick(getHeldItemMainhand(), this, this.activeItemStackUseCount);
             		
             		if(item instanceof ItemGun && !(item instanceof ItemMedigun))
             		{
             			((ItemGun) item).setUsed(getHeldItemMainhand(), 20);
             			
             			if(((ItemGun) item).zoomOnUsed() > 1.3F)
             			{
             				this.motionX *= 0.05;
             				this.motionZ *= 0.05;
             			}
             			else if(!((ItemGun) item).lowBulletFar())
             			{
             				this.motionX *= 0.4;
             				this.motionZ *= 0.4;
             			}
             			if(((ItemGun) item).ChargerEmpty(this.getHeldItemMainhand()))
             			{
             				((ItemGun) item).setReloading(getHeldItemMainhand(), true);
             				((ItemGun) item).reloadGun(getHeldItemMainhand(), world, this);
             			}
             			
             			Vec3d look = this.getPositionVector().addVector(0, this.getEyeHeight(), 0);
                		Vec3d targetHead = UtilsVoids.laser(entity.getDistance(this), this.rotationYaw, this.rotationPitch).add(look);
                		
             			if(timer == 0 && this.getDistance(this.getAttackTarget()) <= ((ItemGun) item).getReach() && world.rayTraceBlocks(look, targetHead) == null)
             			{
             				((ItemGun) item).onItemUsedByEntity(world, this, EnumHand.MAIN_HAND);
             				
                 			this.timer = ((ItemGun) item).cooldown();
             			}
             		}
         		}
         	}
    		
    		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
     	}
     	
     	if(this.getRidingEntity() instanceof EntityTank)
     	{
     		EntityTank entity = (EntityTank) this.getRidingEntity();
     		if(this.getAttackTarget() != null && !this.getAttackTarget().isDead && this.getDistance(this.getAttackTarget()) < 20)
     		{
     			entity.shoot(this);
     			UtilsVoids.setYawEntity(this.getAttackTarget(), entity);
     		}
     	}
     	
     	if(this.getRevengeTarget() != null)
     	{
     		if(this.getRevengeTarget().getHealth() <= 0)
     		{
     			this.setRevengeTarget(null);
     		}
     		
     		if(this.getRevengeTarget() instanceof EntityHunter)
     		{
     			this.setRevengeTarget(null);
     		}
     		
     	}
     	
     	if(this.isLeftHanded())
     	{
     		this.setLeftHanded(false);
     	}
     	
     	super.onUpdate();
     }

	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
     {
         super.setEquipmentBasedOnDifficulty(difficulty);
         Item item = this.getRandomGun();
         ItemStack gun = new ItemStack(item);
         if(item instanceof ItemGun)
         {
        	 if(((ItemGun) item).getFirstBullet() != null)
          	{
          		((ItemGun) item).chargeCharger(gun, ((ItemGun) item).getFirstBullet(), ((ItemGun) item).getChargerSize());
          	}
         }
         this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, gun);
     }
     
     protected Item getRandomGun()
     {
     	List<Item> list = Lists.newArrayList();
     	
     	for(Item item : Item.REGISTRY)
     	{
     		if(item instanceof ItemGun)
     		{
     			if(item != RegisterItems.MEDIGUN && item != RegisterItems.NUCLEAR_ROCKET_LAUNCHER && item != RegisterItems.BLACK_HOLE_LAUNCHER)
     			{
         			list.add(item);
     			}
     		}
     	}
     	
     	list.add(RegisterItems.GOLDEN_BAT);
     	
 		return list.get(new Random().nextInt(list.size()));
     }
     
     public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
     {
         this.setEquipmentBasedOnDifficulty(difficulty);
         return super.onInitialSpawn(difficulty, livingdata);
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

}
