package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFrostBlal  extends EntityMob implements IOnShot, FrostResistance
{
	
	public EntityFrostBlal(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 3F);
        this.isImmuneToFire = true;
        this.experienceValue = 43;
    }
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false)
        		{
        			@Override
        			protected double getAttackReachSqr(EntityLivingBase attackTarget)
        			{
        				return (double)(this.attacker.width * 3.0F * this.attacker.width * 3.0F + attackTarget.width);
        			}
        		});
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 5, false, true, null));
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
    	if(potioneffectIn.getPotion() == MobEffects.SPEED || potioneffectIn.getPotion() == RegisterPotions.FALL_PROTECTION)
    	{
    		super.addPotionEffect(potioneffectIn);
    	}
    }

    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2700000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(28.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source == DamageSource.OUT_OF_WORLD)
    	{
    		super.attackEntityFrom(source, amount * 2);
    	}
    	if(!source.isProjectile())
    	{
    		return super.attackEntityFrom(source, amount);
    	}
    	return false;
    }
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	while(lootingModifier + 1 > 0)
    	{
    		if(this.getRNG().nextInt(10) == 0)
            {
            	EntityItem entityitem = this.dropItem(RegisterItems.EYE_OF_BLAL, 1);
            }
            --lootingModifier;
    	}
    }

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
		UtilsVoids.EasyShootEntity(this, shooter.rotationPitch, shooter.rotationYaw, 0.8F, 0);
	}
    
}