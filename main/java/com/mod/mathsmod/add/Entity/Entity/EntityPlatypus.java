package com.mod.mathsmod.add.Entity.Entity;

import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPlatypus extends EntityLiving
{

	public EntityPlatypus(World worldIn) 
	{
		super(worldIn);
		this.setSize(0.6F, 0.2F);
	}

	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }
	
	@Override
	protected float getWaterSlowDown()
    {
        return 1F;
    }
	
	@Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(!world.isRemote && this.getRNG().nextInt(3) == 0)
		{
			EntityItem entityitem = this.dropItem(RegisterItems.PLATYPUS_PAW, 1);
		}
    }
	
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && !source.isProjectile() && !source.isMagicDamage() && !source.isExplosion() 
				&& source.getTrueSource().getDistance(this) < 2)
		{
			EntityLivingBase entity = (EntityLivingBase) source.getTrueSource();
			entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 40, 2));
		}
		
		return super.attackEntityFrom(source, amount);
    }
}
