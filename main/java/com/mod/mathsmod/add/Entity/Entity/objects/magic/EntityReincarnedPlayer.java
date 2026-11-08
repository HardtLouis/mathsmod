package com.mod.mathsmod.add.Entity.Entity.objects.magic;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityReincarnedPlayer extends EntityMob
{
	
	protected static final DataParameter<String> PLAYER = EntityDataManager.<String>createKey(EntityReincarnedPlayer.class, DataSerializers.STRING);
	protected static final DataParameter<Integer> OWNER_ID = EntityDataManager.<Integer>createKey(EntityReincarnedPlayer.class, DataSerializers.VARINT);
	boolean strafe;
	
	public EntityReincarnedPlayer(World worldIn)
	{
		super(worldIn);
		for(EntityEquipmentSlot equip : EntityEquipmentSlot.values())
		{
			this.setDropChance(equip, 0);
		}
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.EARTH_SWORD));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(PLAYER, "");
		this.dataManager.register(OWNER_ID, -1);
	}
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityReincarnedPlayer.EntityAIHurt(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_ != EntityReincarnedPlayer.this.getOwner();
            }
        }));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 2, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityReincarnedPlayer) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000149011612D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
    }
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(source.getTrueSource() != null && source.getTrueSource() == this.getOwner())
        {
        	return false;
        }
        else
        {
        	return super.attackEntityFrom(source, amount);
        }
    }
	
	public void onUpdate()
	{
		super.onUpdate();

		if(this.getOwner() != null)
		{
			if(this.getDistance(this.getOwner()) > 50)
			{
				this.setPositionAndUpdate(this.getOwner().posX, this.getOwner().posY, this.getOwner().posZ);
			}
		}
		
		
		if(this.getAttackTarget() != null)
		{
			this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 0, 0);
			this.travel(0, 0, 1F);
			
			if(this.isInWater() && this.getAttackTarget().posY > this.posY)
			{
				this.motionY = 0.4F;
			}
			
			if(this.getAttackTarget().getHealth() <= 0.0F && this.getAttackTarget() instanceof EntityPlayer)
			{
				this.setDead();
			}
			
			if(this.getAttackTarget().getDistance(this) < 2.6)
			{
				this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 0, 0);
				this.travel(strafe ? 1 : -1, 0, 0F);
				
				if(this.getRNG().nextInt(20) == 0)
				{
					strafe = !strafe;
				}
			}
		}
		
		if(this.getPlayer().length() > 0)
		{
			this.setAlwaysRenderNameTag(true);
			this.setCustomNameTag(this.getPlayer());
		}
	}
	
	protected float getWaterSlowDown()
    {
        return 0.9F;
    }
	
	public String getPlayer()
	{
		return this.dataManager.get(PLAYER);
	}
	
	public void setPlayer(String name)
	{
		this.dataManager.set(PLAYER, name);
	}
	
	public void setOwner(EntityPlayer entity)
	{
		this.dataManager.set(OWNER_ID, entity.getEntityId());
	}
	
	public void setOwnerID(int id)
	{
		this.dataManager.set(OWNER_ID, id);
	}
	
	public int getOwnerID()
	{
		return this.dataManager.get(OWNER_ID);
	}
	
	public EntityPlayer getOwner()
	{
		if(this.getOwnerID() >= 0)
		{
			if(world.getEntityByID(this.getOwnerID()) instanceof EntityPlayer)
			{
				return (EntityPlayer) world.getEntityByID(this.getOwnerID());
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setString("skin", this.getPlayer());
        compound.setInteger("owner_id", this.getOwnerID());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setPlayer(compound.getString("skin"));
        this.setOwnerID(compound.getInteger("owner_id"));
    }
	
	public static class EntityAIHurt extends EntityAITarget
	{
	    private final boolean entityCallsForHelp;
	    /** Store the previous revengeTimer value */
	    private int revengeTimerOld;
	    private final Class<?>[] excludedReinforcementTypes;

	    public EntityAIHurt(EntityCreature creatureIn, boolean entityCallsForHelpIn, Class<?>... excludedReinforcementTypes)
	    {
	        super(creatureIn, true);
	        this.entityCallsForHelp = entityCallsForHelpIn;
	        this.excludedReinforcementTypes = excludedReinforcementTypes;
	        this.setMutexBits(1);
	    }

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute()
	    {
	        int i = this.taskOwner.getRevengeTimer();
	        EntityLivingBase entitylivingbase = this.taskOwner.getRevengeTarget();
	        return i != this.revengeTimerOld && entitylivingbase != null && this.isSuitableTarget(entitylivingbase, false);
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	    	if(this.taskOwner.getRevengeTarget() != null)
	    	{
	    		if(this.taskOwner instanceof EntityReincarnedPlayer)
	    		{
	    			EntityReincarnedPlayer entity = (EntityReincarnedPlayer) this.taskOwner;
	    			if(entity.getOwner() != null && entity.getRevengeTarget() != entity.getOwner())
	    			{
	    				this.taskOwner.setAttackTarget(this.taskOwner.getRevengeTarget());
	    			}
	    		}
	    	}
	    	else
	    	{
	    		this.taskOwner.setAttackTarget(this.taskOwner.getRevengeTarget());
	    	}
	        
	        this.target = this.taskOwner.getAttackTarget();
	        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
	        this.unseenMemoryTicks = 300;

	        if (this.entityCallsForHelp)
	        {
	        }

	        super.startExecuting();
	    }

	    protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
	    {
	        creatureIn.setAttackTarget(entityLivingBaseIn);
	    }
	}
}
