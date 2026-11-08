package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemGunLayer;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;

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
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDeadHunter extends EntityHunter
{
	
	ItemStack gun;

	public EntityDeadHunter(World worldIn) 
	{
		super(worldIn);
		this.setSize(1.2F, 3);
	}
	
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setSkin(new Random().nextInt(2));
        return super.onInitialSpawn(difficulty, livingdata);
    }

	protected void initEntityAI()
    {
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 1, false, true, null));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityHunter.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityDeadHunter) && p_apply_1_ instanceof EntityHunter && !((EntityHunter) p_apply_1_).getCommunist();
            }
        }));
    }
	
	public void onUpdate()
	{
		super.onUpdate();
		
		if(this.gun == null || this.gun.isEmpty())
		{
			this.gun = this.getHeldItemMainhand();
		}
		
		if(this.getAttackTarget() != null)
		{
			if(this.getAttackTarget().getDistance(this) < 5)
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegisterItems.KURAYUM_AXE));
			}
			else
			{
				this.setHeldItem(EnumHand.MAIN_HAND, gun);
			}
		}
	}
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
        ItemStackHelper.saveAllItems(compound, list);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, list);
        this.gun = list.get(0);
    }
	
	public boolean isPreventingPlayerRest(EntityPlayer playerIn)
    {
        return true;
    }
	
	public int getSkin()
    {
    	return MathHelper.clamp(this.dataManager.get(SKIN), 0, 1);
    }
	
	@Override
	protected void applyEntityAttributes()
	{
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(70.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }
	 
	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);
		if(!world.isRemote)
		{
    		if(cause.getTrueSource() != null && cause.getTrueSource() instanceof EntityHunter && this.getCommunist() && !((EntityHunter) cause.getTrueSource()).getCommunist())
    		{
    			for(int i = 0; i < 4; ++i)
    			{
    				EntityHunter hunter = new EntityDeadHunter(world);
            		hunter.setAttackTarget((EntityLivingBase) cause.getTrueSource());
            		hunter.setPosition(posX, posY, posZ);
            		hunter.setCommunist(true);
            		hunter.setCustomNameTag("communist");
            		hunter.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(this)), null);
            		world.spawnEntity(hunter);
    			}
    		}
    		for(int i = 0; i < this.getRNG().nextInt(4); ++i)
    		{
    			world.spawnEntity(this.dropItem(getRandomLayer(), 1));
    		}
    	}
	}
	
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
	
	public boolean moveBackward()
    {
    	return false;
    }
	
	protected ItemGunLayer getRandomLayer()
    {
    	List<ItemGunLayer> list = Lists.newArrayList();
    	
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemGunLayer)
    		{
    			list.add((ItemGunLayer) item);
    		}
    	}
		return list.get(new Random().nextInt(list.size()));
    }
	
	protected ItemGun getRandomGun()
    {
    	List<ItemGun> list = Lists.newArrayList();
    	
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemGun && ((ItemGun) item).canBeUseByHunter() && !(item instanceof IGoldenWeapon) && !(item instanceof IGoldenAnimatedGun))
    		{
    			list.add((ItemGun) item);
    		}
    	}
		return list.get(new Random().nextInt(list.size()));
    }
}
