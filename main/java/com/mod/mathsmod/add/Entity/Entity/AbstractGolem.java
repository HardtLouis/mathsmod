package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.container.ContainerGolem;
import com.mod.mathsmod.add.Entity.Entity.gui.guiGolem;
import com.mod.mathsmod.add.Entity.EntityAi.GolemFollowOwner;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.Village;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractGolem extends EntityGolem implements IEntityOwnable
{

	public AbstractGolem(World worldIn) {
		super(worldIn);
		this.setSize(1.4F * this.getSizeMultiplier(), 2.7F * this.getSizeMultiplier());
	}
	
	protected NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	protected static final DataParameter<Byte> TAMED = EntityDataManager.<Byte>createKey(EntityTameable.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityTameable.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityMateGolem.class, DataSerializers.BOOLEAN);
    protected int attackTimer;
    private int holdRoseTick;

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new GolemFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TAMED, Byte.valueOf((byte)0));
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(SITTING, false);
    }

    /**
     * Decrements the entity's air supply when underwater
     */
    protected int decreaseAirSupply(int air)
    {
        return air;
    }

    protected void collideWithEntity(Entity entityIn)
    {
        if (entityIn instanceof IMob && !(entityIn instanceof EntityCreeper) && this.getRNG().nextInt(20) == 0)
        {
            this.setAttackTarget((EntityLivingBase)entityIn);
        }

        super.collideWithEntity(entityIn);
    }
    
    protected int attackCooldown()
    {
    	return 10;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.attackTimer > 0)
        {
            --this.attackTimer;
        }

        if (this.holdRoseTick > 0)
        {
            --this.holdRoseTick;
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
        {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY - 0.20000000298023224D);
            int k = MathHelper.floor(this.posZ);
            IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));

            if (iblockstate.getMaterial() != Material.AIR)
            {
                this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.getEntityBoundingBox().minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 4.0D * ((double)this.rand.nextFloat() - 0.5D), 0.5D, ((double)this.rand.nextFloat() - 0.5D) * 4.0D, Block.getStateId(iblockstate));
            }
        }
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    public boolean canAttackClass(Class <? extends EntityLivingBase > cls)
    {
    	return cls == EntityCreeper.class ? false : super.canAttackClass(cls);
    }
    
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.chestContents)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }
    
    public abstract float getSizeMultiplier();

    public static void registerFixesIronGolem(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, AbstractGolem.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("tamed", this.isTamed());
        compound.setString("OwnerID", this.getOwnerId().toString());
        compound.setBoolean("isSitting", this.isSitting());
        ItemStackHelper.saveAllItems(compound, this.chestContents);
    }
    
    public abstract int getSizeInventory();

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setTamed(compound.getBoolean("tamed"));
        this.setOwnerId(UUID.fromString(compound.getString("OwnerID")));
        this.setSitting(compound.getBoolean("isSitting"));
        ItemStackHelper.loadAllItems(compound, this.chestContents);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        this.attackTimer = this.attackCooldown();
        this.world.setEntityState(this, (byte)4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag)
        {
            entityIn.motionY += 0.4000000059604645D;
            this.applyEnchantments(this, entityIn);
        }

        this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 4)
        {
            this.attackTimer = this.attackCooldown();
            this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.0F);
        }
        else if (id == 11)
        {
            this.holdRoseTick = 400;
        }
        else if (id == 34)
        {
            this.holdRoseTick = 0;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer()
    {
        return this.attackTimer;
    }

    public void setHoldingRose(boolean p_70851_1_)
    {
        if (p_70851_1_)
        {
            this.holdRoseTick = 400;
            this.world.setEntityState(this, (byte)11);
        }
        else
        {
            this.holdRoseTick = 0;
            this.world.setEntityState(this, (byte)34);
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
    }

    public int getHoldRoseTick()
    {
        return this.holdRoseTick;
    }

    public IInventory getInv()
    {
        return new InventoryGolem(this);
    }

    public void setInventory(NonNullList<ItemStack> newInv)
    {
    	this.chestContents = newInv;
    }
    
    protected NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }
    
    @Nullable
    public UUID getOwnerId()
    {
        return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }
    
    @Nullable
    public Entity getOwner()
    {
    	if(this.getOwnerId() == null)
    	{
    		return null;
    	}
    	else
    	{
    		return world.getPlayerEntityByUUID(getOwnerId());
    	}
        
    }

    public void setOwnerId(@Nullable UUID p_184754_1_)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
    }
    
    public boolean isTamed()
    {
        return (((Byte)this.dataManager.get(TAMED)).byteValue() & 4) != 0;
    }

    public void setTamed(boolean tamed)
    {
        byte b0 = ((Byte)this.dataManager.get(TAMED)).byteValue();

        if (tamed)
        {
            this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 | 4)));
        }
        else
        {
            this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 & -5)));
        }
        
    }
    
    public void setOwner(EntityPlayer player)
    {
    	this.setTamed(true);
    	this.setOwnerId(player.getUniqueID());
    }

    public boolean isSitting()
    {
        return this.dataManager.get(SITTING).booleanValue();
    }
    
    public void setSitting(boolean sit)
    {
        this.dataManager.set(SITTING, sit);
    }
    
    public static class InventoryGolem implements IInventory
    {
        private String inventoryTitle;
        private final int slotsCount;
        private final NonNullList<ItemStack> inventoryContents;
        private List<IInventoryChangedListener> changeListeners;
        private boolean hasCustomName;

        public InventoryGolem(AbstractGolem entityIn)
        {
            this.inventoryTitle = entityIn.getName();
            this.hasCustomName = true;
            this.slotsCount = entityIn.getSizeInventory();
            this.inventoryContents = entityIn.chestContents;
        }

        /**
         * Add a listener that will be notified when any item in this inventory is modified.
         */
        public void addInventoryChangeListener(IInventoryChangedListener listener)
        {
            if (this.changeListeners == null)
            {
                this.changeListeners = Lists.<IInventoryChangedListener>newArrayList();
            }

            this.changeListeners.add(listener);
        }

        /**
         * removes the specified IInvBasic from receiving further change notices
         */
        public void removeInventoryChangeListener(IInventoryChangedListener listener)
        {
            this.changeListeners.remove(listener);
        }

        /**
         * Returns the stack in the given slot.
         */
        public ItemStack getStackInSlot(int index)
        {
            return index >= 0 && index < this.inventoryContents.size() ? (ItemStack)this.inventoryContents.get(index) : ItemStack.EMPTY;
        }

        /**
         * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
         */
        public ItemStack decrStackSize(int index, int count)
        {
            ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventoryContents, index, count);

            if (!itemstack.isEmpty())
            {
                this.markDirty();
            }

            return itemstack;
        }

        public ItemStack addItem(ItemStack stack)
        {
            ItemStack itemstack = stack.copy();

            for (int i = 0; i < this.slotsCount; ++i)
            {
                ItemStack itemstack1 = this.getStackInSlot(i);

                if (itemstack1.isEmpty())
                {
                    this.setInventorySlotContents(i, itemstack);
                    this.markDirty();
                    return ItemStack.EMPTY;
                }

                if (ItemStack.areItemsEqual(itemstack1, itemstack))
                {
                    int j = Math.min(this.getInventoryStackLimit(), itemstack1.getMaxStackSize());
                    int k = Math.min(itemstack.getCount(), j - itemstack1.getCount());

                    if (k > 0)
                    {
                        itemstack1.grow(k);
                        itemstack.shrink(k);

                        if (itemstack.isEmpty())
                        {
                            this.markDirty();
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            if (itemstack.getCount() != stack.getCount())
            {
                this.markDirty();
            }

            return itemstack;
        }

        /**
         * Removes a stack from the given slot and returns it.
         */
        public ItemStack removeStackFromSlot(int index)
        {
            ItemStack itemstack = this.inventoryContents.get(index);

            if (itemstack.isEmpty())
            {
                return ItemStack.EMPTY;
            }
            else
            {
                this.inventoryContents.set(index, ItemStack.EMPTY);
                return itemstack;
            }
        }

        /**
         * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
         */
        public void setInventorySlotContents(int index, ItemStack stack)
        {
            this.inventoryContents.set(index, stack);

            if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
            {
                stack.setCount(this.getInventoryStackLimit());
            }

            this.markDirty();
        }
        
        

        /**
         * Returns the number of slots in the inventory.
         */
        public int getSizeInventory()
        {
            return this.slotsCount;
        }

        public boolean isEmpty()
        {
            for (ItemStack itemstack : this.inventoryContents)
            {
                if (!itemstack.isEmpty())
                {
                    return false;
                }
            }

            return true;
        }

        /**
         * Get the name of this object. For players this returns their username
         */
        public String getName()
        {
            return this.inventoryTitle;
        }

        /**
         * Returns true if this thing is named
         */
        public boolean hasCustomName()
        {
            return this.hasCustomName;
        }

        /**
         * Sets the name of this inventory. This is displayed to the client on opening.
         */
        public void setCustomName(String inventoryTitleIn)
        {
            this.hasCustomName = true;
            this.inventoryTitle = inventoryTitleIn;
        }

        /**
         * Get the formatted ChatComponent that will be used for the sender's username in chat
         */
        public ITextComponent getDisplayName()
        {
            return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
        }

        /**
         * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
         */
        public int getInventoryStackLimit()
        {
            return 64;
        }

        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
         * hasn't changed and skip it.
         */
        public void markDirty()
        {
            if (this.changeListeners != null)
            {
                for (int i = 0; i < this.changeListeners.size(); ++i)
                {
                    ((IInventoryChangedListener)this.changeListeners.get(i)).onInventoryChanged(this);
                }
            }
        }

        /**
         * Don't rename this method to canInteractWith due to conflicts with Container
         */
        public boolean isUsableByPlayer(EntityPlayer player)
        {
            return true;
        }

        public void openInventory(EntityPlayer player)
        {
        }

        public void closeInventory(EntityPlayer player)
        {
        }

        /**
         * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
         * guis use Slot.isItemValid
         */
        public boolean isItemValidForSlot(int index, ItemStack stack)
        {
            return true;
        }

        public int getField(int id)
        {
            return 0;
        }

        public void setField(int id, int value)
        {
        }

        public int getFieldCount()
        {
            return 0;
        }

        public void clear()
        {
            this.inventoryContents.clear();
        }
        
    }
    
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if(UtilsVoids.getEntities(EntityLivingBase.class, world, this, 100).size() > 0)
    	{
    		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 100))
    		{
    			if(entity != this.getOwner() && entity != this && this.getOwner() != null)
    			{
    				EntityLivingBase target = entity.getLastAttackedEntity();
    				
    				if(target != null)
    				{
    					if(target instanceof IEntityOwnable)
    					{
    						if(((IEntityOwnable) target).getOwner() != this.getOwner())
    						{
        						this.setAttackTarget(entity.getLastAttackedEntity());
    						}
    						else if(((IEntityOwnable) target).getOwner() == null)
    						{
    							this.setAttackTarget(entity.getLastAttackedEntity());
    						}
    					}
    					else
    					{
    						this.setAttackTarget(entity.getLastAttackedEntity());
    					}
    				}
    			}
    		}
    	}
    }
}
