package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemGunLayer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityFrec extends EntityMob
{
	protected static final DataParameter<Float> WALK_PROGRESS = EntityDataManager.<Float>createKey(EntityFrec.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> HAS_TARGET = EntityDataManager.<Boolean>createKey(EntityFrec.class, DataSerializers.BOOLEAN);
	
	public EntityFrec(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(0.8F, 2.6F);
        this.isImmuneToFire = true;
        this.experienceValue = 60;
        this.isImmuneToExplosions();
    }

	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(WALK_PROGRESS, 0.0F);
        this.dataManager.register(HAS_TARGET, false);
    }
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false)
        		{
        	protected double getAttackReachSqr(EntityLivingBase attackTarget)
            {
                return (double)(this.attacker.width * 1.2F * this.attacker.width * 1.2F + attackTarget.width);
            }
        		});
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F)
        		{
        			public boolean shouldExecute()
        			{
        				return super.shouldExecute() && !EntityFrec.this.isDirectlyLookedAt();
        			}
        		});
        this.tasks.addTask(8, new EntityAILookIdle(this)
		{
			public boolean shouldExecute()
			{
				return super.shouldExecute() && !EntityFrec.this.isDirectlyLookedAt();
			}
		});
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 1, false, true, null));
    }

	public boolean canBeCollidedWith()
    {
    	return this.deathTime == 0;
    }
	
    public void onUpdate()
    {
    	super.onUpdate();
    	this.setHasTarget(this.getAttackTarget() != null);
    	if(!this.isDirectlyLookedAt())
    	{
    		setWalkProgress(MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) + this.getWalkProgress());
    	}
    }
    
    public void setWalkProgress(float p)
    {
    	this.dataManager.set(WALK_PROGRESS, p);
    }
    
    public float getWalkProgress()
    {
    	return this.dataManager.get(WALK_PROGRESS);
    }
    
    public void setHasTarget(boolean p)
    {
    	this.dataManager.set(HAS_TARGET, p);
    }
    
    public boolean hasTarget()
    {
    	return this.dataManager.get(HAS_TARGET);
    }
    
    public void move(MoverType type, double x, double y, double z)
    {
    	if(this.isDirectlyLookedAt())
    	{
    		super.move(type, 0, y, 0);
    	}
    	else
    	{
    		super.move(type, x, y, z);
    	}
    }
    
    public void addPotionEffect(PotionEffect potioneffectIn)
    {
    }

    protected boolean isMovementBlocked()
    {
        return this.getHealth() <= 0.0F;
    }
    
    public boolean isDirectlyLookedAt() 
    {
    	for(EntityPlayer player : UtilsVoids.getEntities(EntityPlayer.class, world, this, 40))
		{
			if(UtilsVoids.dotProduct(player.getLook(1.0F), this.getPositionEyes(1.0F).subtract(player.getPositionEyes(1.0F))) > 0)
			{
				return true;
			}
		}
		return false;
	}

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3600000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.OUT_OF_WORLD)
    	{
    		super.attackEntityFrom(source, amount * 2);
    	}
    	if(source instanceof EntityDamageSource && !source.isProjectile())
    	{
    		return super.attackEntityFrom(source, amount);
    	}
    	return false;
    }
	
}