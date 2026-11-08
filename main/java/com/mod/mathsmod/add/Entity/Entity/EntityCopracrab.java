package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAbsorbedBlock;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemGunLayer;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityCopracrab extends EntityMob
{
	
	public EntityCopracrab(World worldIn)
    {
        super(worldIn);
        this.setHealth(this.getMaxHealth());
        this.setSize(3F, 1.5F);
        this.isImmuneToFire = true;
        this.experienceValue = 60;
        this.isImmuneToExplosions();
        this.setLeftHanded(false);
    }

	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false)
        		{
        	protected double getAttackReachSqr(EntityLivingBase attackTarget)
            {
                return (double)(this.attacker.width * 1.7F * this.attacker.width * 1.7F + attackTarget.width);
            }
        		});
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

	public boolean canBeCollidedWith()
    {
    	return this.deathTime == 0;
    }
	
    public void onUpdate()
    {
    	super.onUpdate();
    }
    

	@Override
    protected void entityInit()
    {
        super.entityInit();
    }
    
    public void addPotionEffect(PotionEffect potioneffectIn)
    {
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3500000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    protected void collideWithEntity(Entity entityIn)
    {
    	entityIn.setFire(15);
        super.collideWithEntity(entityIn);
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
    
    public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);
		if(!world.isRemote && this.getRNG().nextInt(3) == 0)
		{
    		world.spawnEntity(this.dropItem(RegisterItems.CRAB_CLAW, 1));
    	}
	}
	
}