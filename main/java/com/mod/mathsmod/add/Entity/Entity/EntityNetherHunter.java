package com.mod.mathsmod.add.Entity.Entity;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMedigun;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityNetherHunter extends EntityHunter
{
	public EntityNetherHunter(World worldIn)
    {
        super(worldIn);
        this.experienceValue = 20;
        this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.1F);
    }
	
	protected ItemGun getRandomGun()
    {
		return (ItemGun) RegisterItems.CANARDIER;
    }
	
	public int getSkin()
    {
    	return MathHelper.clamp(this.dataManager.get(SKIN), 0, 3);
    }
	
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
		IEntityLivingData data = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setSkin(0);
        return data;
    }
	
	protected boolean hasPerfectAim() 
    {
		return false;
	}
	
	@Override
    public boolean getCanSpawnHere()
    {
    	return this.getCanSpawnHere(false);
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

	@Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(this.getHeldItemMainhand().getItem() instanceof ItemGun)
    	{
    		ItemGun item = (ItemGun) this.getHeldItemMainhand().getItem();
    		if(!world.isRemote)
    		{
    			EntityItem entityitem = this.dropItem(item.getFirstBullet(), (2 + this.getRNG().nextInt(6)) * (lootingModifier / 2 + 1));
    			if(this.getRNG().nextInt(10) == 0)
    			{
    				if(this.getRNG().nextInt(2) == 0)
    				{
    					this.dropItem(RegisterItems.BASIC_SOUND_MUFFLER, 1);
    				}
    				else
    				{
    					this.dropItem(RegisterItems.HALF_MUFFLER, 1);
    				}
    			}
    			
    			if(this.getRNG().nextInt(10) == 0)
    			{
    				this.dropItem(RegisterItems.GAS_MASK, 1);
    			}
    			
    			if(this.getRNG().nextInt(40) == 0)
    			{
    				this.dropItem(RegisterItems.GUN_GRENADE_LAUNCHER, 1);
    			}
    			
    			if(this.getRNG().nextInt(5) == 0)
    			{
    				if(this.getRNG().nextInt(2) == 0)
    				{
    					this.dropItem(RegisterItems.BASIC_SCOPE, 1);
    				}
    				else
    				{
    					this.dropItem(RegisterItems.BASIC_SIGHT, 1);
    				}
    			}
    			
    			if(item instanceof ItemMedigun)
    			{
    				this.dropItem(RegisterItems.MEDIGUN, 1);
    			}
    		}
    	}
    }
}
