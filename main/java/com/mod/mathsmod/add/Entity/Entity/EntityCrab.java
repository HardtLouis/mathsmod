package com.mod.mathsmod.add.Entity.Entity;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityCrab extends EntityTameable
{
	
	private static final DataParameter<Boolean> IS_GIANT = EntityDataManager.<Boolean>createKey(EntityCrab.class, DataSerializers.BOOLEAN);
	
    public EntityCrab(World worldIn)
    {
        super(worldIn);
        if(this.isGiant())
        {
            this.setSize(2F, 1F);
        }
        else
        {
            this.setSize(0.5F, 0.5F);
        }
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(IS_GIANT, Boolean.valueOf(false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    }
    
    public boolean canBreatheUnderwater()
    {
        return true;
    }
    
    public boolean isInWater()
    {
        return false;
    }
    
    public boolean isGiant()
    {
        return ((Boolean)this.dataManager.get(IS_GIANT)).booleanValue();
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	if(this.isGiant() && !this.isChild())
    	{
    		this.setSize(2F, 1F);
    	}
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setGiant(boolean giant)
    {
        this.dataManager.set(IS_GIANT, Boolean.valueOf(giant));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("giant", (byte) (this.isGiant() ? 0 : 1));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setGiant(compound.getByte("giant") == 0);
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.BLOCK_SAND_STEP, 0.15F, 1.4F);
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        if (!itemstack.isEmpty())
        {
        	if (itemstack.getItem() == Items.NAME_TAG)
            {
                itemstack.interactWithEntity(player, this, hand);
                return true;
            }
            else if(itemstack.getItem() == Items.IRON_INGOT && !world.isRemote && this.isGiant())
            {
            	itemstack.shrink(1);
            	/*if(this.getRNG().nextInt(3) == 0)
            	{
            		EntitySquid entity = new EntitySquid(world);
            		entity.setPosition(this.posX, this.posY, this.posZ);
            		world.spawnEntity(entity);
            	}
            	else */if(this.getRNG().nextInt(6) == 0)
            	{
            		this.dropItem(Items.DIAMOND, 1);
            	}
            	else if(this.getRNG().nextInt(3) == 0)
            	{
            		this.dropItem(Items.GOLD_INGOT, 1);
            	}
            	return true;
            }
            else if (itemstack.getItem() == Items.DYE && itemstack.getMetadata() == 0)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                if (!this.world.isRemote && !this.isTamed())
                {
                    if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                    {
                        this.setTamedBy(player);
                        this.navigator.clearPath();
                        this.setAttackTarget((EntityLivingBase)null);
                        this.setHealth(20.0F);
                        this.playTameEffect(true);
                        this.world.setEntityState(this, (byte)7);
                    }
                    else
                    {
                        this.playTameEffect(false);
                        this.world.setEntityState(this, (byte)6);
                    }
                }
                else if(!world.isRemote)
                {
                	return super.processInteract(player, hand);
                }
            }
        	
        }

        return super.processInteract(player, hand);
    }

    /*@Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_PIG;
    }*/

    /*public void travel(float strafe, float vertical, float forward)
    {
        super.travel(forward, vertical, strafe);
    }*/

    public EntityCrab createChild(EntityAgeable ageable)
    {
        return new EntityCrab(this.world);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.DYE && stack.getMetadata() == 0;
    }
    
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        if(this.getRNG().nextInt(10) == 0)
        {
        	this.setGiant(true);
        	this.setSize(2F, 1F);
        }
        return super.onInitialSpawn(difficulty, livingdata);
    }
}