package com.mod.mathsmod.add.Entity.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.hunterTrade.HunterTradeRegistry;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemCommunistDisk;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.ItemMedigun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;
import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityHunter extends EntityMob
{
	private int timer;
	private int reload;
	public int watchTick;
	public NonNullList<ItemStack> trades = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
	protected static final DataParameter<Integer> MATE = EntityDataManager.<Integer>createKey(EntityHunter.class, DataSerializers.VARINT);
	
	protected static final DataParameter<Integer> SKIN = EntityDataManager.<Integer>createKey(EntityHunter.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> COMMUNIST = EntityDataManager.<Boolean>createKey(EntityHunter.class, DataSerializers.BOOLEAN);
	
	public EntityHunter(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 2F);
        this.experienceValue = 10;
        this.setMateID(-1);
        this.setDropChance(EntityEquipmentSlot.MAINHAND, 0F);
    }
	
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
		if(!world.isRemote && this.canTrade())
		{
	         player.openGui(Mathsmod.instance, 19, world, this.getEntityId(), 0, 0);
	         return true;
		}
        return false;
    }
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(MATE, -1);
        this.dataManager.register(SKIN, 0);
        this.dataManager.register(COMMUNIST, false);
    }
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityHunter) && !(p_apply_1_ instanceof EntityCreeper) && !(p_apply_1_ instanceof EntityBot);
            }
        }));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityHunter.class, 1, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && EntityHunter.this.getCommunist() != ((EntityHunter) p_apply_1_).getCommunist();
            }
        }));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityDeadHunter.class, 1, false, true, null));
    }

	public int getMateID()
	{
		return this.dataManager.get(MATE);
	}
	
	public void setMateID(int mate)
	{
		this.dataManager.set(MATE, mate);
	}
	
	public int tradesNum()
	{
		return 9;
	}
    
    public void onUpdate()
    {
    	this.watchTick = MathHelper.clamp(--this.watchTick, 0, 40);
    	if(this.getHealth() > 0)
    	{
    		if(this.timer > 0)
        	{
        		--timer;
        	}
    		
    		if(this.getHeldItemMainhand().getItem() instanceof ItemGun && !(this.getHeldItemMainhand().getItem() instanceof ItemMedigun))
    		{
    			if(((ItemGun) this.getHeldItemMainhand().getItem()).ChargerEmpty(this.getHeldItemMainhand()))
    			{
    				((ItemGun) this.getHeldItemMainhand().getItem()).setReloading(getHeldItemMainhand(), true);
    				((ItemGun) this.getHeldItemMainhand().getItem()).reloadGun(getHeldItemMainhand(), world, this);
    				((ItemGun) this.getHeldItemMainhand().getItem()).setCooldown(getHeldItemMainhand(), (float) this.timer / (float) ((ItemGun) this.getHeldItemMainhand().getItem()).cooldown());
    			}
    		}
        	
        	if(this.getAttackTarget() != null && this.getAttackTarget() != this)
        	{
        		Entity entity = this.getAttackTarget();
        		Item item = this.getHeldItemMainhand().getItem();
        		if(this.hasPerfectAim())
        		{
        			UtilsVoids.setLookEntity(entity, this);
        		}
        		else
        		{
        			this.getLookHelper().setLookPosition(entity.posX, entity.posY + entity.getEyeHeight() + 0.2F, entity.posZ, 3, 3);
        		}
        		
        		Vec3d look = this.getPositionVector().addVector(0, this.getEyeHeight(), 0);
        		Vec3d targetHead = UtilsVoids.laser(entity.getDistance(this), this.rotationYaw, this.rotationPitch).add(look);
        		
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
        			if(timer == 0 && this.getDistance(this.getAttackTarget()) <= ((ItemGun) item).getReach() && world.rayTraceBlocks(look, targetHead) == null)
        			{
        				((ItemGun) item).onItemUsedByEntity(world, this, EnumHand.MAIN_HAND);
        				
            			this.timer = ((ItemGun) item).cooldown();
        			}
        		}
        		
        		if(this.getDistance(this.getAttackTarget()) <= 6 && this.moveBackward())
        		{
        			this.travel(0, 0, -1);
        		}
        		
        		if(this.getAttackTarget().getHealth() <= 0)
        		{
        			if(this.getAttackTarget().getLastDamageSource() != null && this.getAttackTarget().getLastDamageSource().getTrueSource() != null && this.getAttackTarget().getLastDamageSource().getTrueSource() instanceof EntityPlayer)
        			{
        				((EntityPlayer) this.getAttackTarget().getLastDamageSource().getTrueSource()).addPotionEffect(new PotionEffect(RegisterPotions.FRIENDS_OF_HUNTERS, 100000));
        			}
        			this.setAttackTarget(null);
        		}
        	}
        	
        	if(this.getHeldItemMainhand().getItem() instanceof ItemMedigun)
    		{
    			if(this.getMateID() == -1 || world.getEntityByID(this.getMateID()) == null)
    			{
    				for(EntityHunter friend : UtilsVoids.getEntities(EntityHunter.class,world, this, 10))
            		{
        				if(friend != this)
        				{
        					this.setMateID(friend.getEntityId());
        				}
            		}
    			}
    			
    			if(world.getEntityByID(this.getMateID()) != null)
    			{
    				Entity entity = world.getEntityByID(this.getMateID());
    				
    				if(((ItemMedigun) this.getHeldItemMainhand().getItem()).getTargetID(this.getHeldItemMainhand()) == -1)
    				{
    					((ItemMedigun) this.getHeldItemMainhand().getItem()).setTargetID(this.getHeldItemMainhand(), this.getMateID());
    				}
    				
    				((ItemMedigun) this.getHeldItemMainhand().getItem()).setUsed(this.getHeldItemMainhand(), 20);
    				//((ItemMedigun) this.getHeldItemMainhand().getItem()).onItemUsedByEntity(world, this, EnumHand.MAIN_HAND);
    				((ItemMedigun) this.getHeldItemMainhand().getItem()).onUpdate(getHeldItemMainhand(), world, this, 0, true);
    				
    				if(entity.getDistance(this) > 10)
    				{
    					this.setMateID(-1);
    				}
    			}
    		}
        	
        	if(this.getAttackTarget() == null)
        	{
        		if(UtilsVoids.getEntities(EntityPlayer.class,world, this, 10).size() > 0)
        		{
        			for(EntityPlayer entity : UtilsVoids.getEntities(EntityPlayer.class,world, this, 10))
            		{
            			Item item = entity.getHeldItemMainhand().getItem();
            			if(entity != null && item instanceof ItemGun && ((ItemGun) item).getUsed(entity.getHeldItemMainhand()) != 0 && !entity.isPotionActive(RegisterPotions.FRIENDS_OF_HUNTERS))
            			{
            				Vec3d vec3d = entity.getLook(1.0F).normalize();
        		            Vec3d vec3d1 = new Vec3d(this.posX - entity.posX, this.getEntityBoundingBox().minY + this.height / 2 - (entity.posY + (double)entity.getEyeHeight()), this.posZ - entity.posZ);
        		            double d0 = vec3d1.lengthVector();
        		            vec3d1 = vec3d1.normalize();
        		            double d1 = vec3d.dotProduct(vec3d1);
        		            if(!entity.capabilities.isCreativeMode && d1 > 1.0D - 0.18D / d0 && this.watchTick < 20)
        		            {
        		            	this.getLookHelper().setLookPositionWithEntity(entity, 0, 0);
        		            	this.watchTick += 2;
        		            }
            					
            				if(!entity.capabilities.isCreativeMode && this.watchTick > 20)
            				{
            					this.setAttackTarget(entity);
            				}
            			}
            		}
        		}
        	}
        	
        	for(int x = -10; x <= 10; ++x)
        	{
        		for(int y = -10; y <= 10; ++y)
            	{
        			for(int z = -10; z <= 10; ++z)
                	{
                		if(world.getTileEntity(new BlockPos(this).north(x).east(y).up(z)) instanceof BlockJukebox.TileEntityJukebox)
                		{
                			BlockPos pos = new BlockPos(this).north(x).east(y).up(z);
                			BlockJukebox.TileEntityJukebox te = (TileEntityJukebox) world.getTileEntity(pos);
                			if(te.getRecord().getItem() instanceof ItemCommunistDisk)
                			{
                				if(this.getCommunist())
                				{
                					this.moveHelper.setMoveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 1.5);
                				}
                			}
                		}
                	}
            	}
        	}
    	}
    	
    	if(this.getCommunist())
    	{
    		for(Entity entity : UtilsVoids.getEntities(EntityTank.class, world, this, 20))
    		{
    			this.moveHelper.setMoveTo(entity.posX, entity.posY, entity.posZ, 1);
    			if(this.getDistance(entity) < entity.width / 2 + 1)
    			{
    				this.startRiding(entity);
    			}
    		}
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
    
    public void createTrades()
    {
    	List list = Lists.newArrayList();
    	for(int i = 0; i < this.tradesNum(); ++i)
    	{
    		ItemStack result = (ItemStack) HunterTradeRegistry.HUNTER_TRADES_PRICES.keySet().toArray()[this.getRNG().nextInt(HunterTradeRegistry.HUNTER_TRADES_PRICES.size())];
    		list.add(result);
    		this.trades.set(i * 3 + 2, result);
    		
    		ItemStack price1 = HunterTradeRegistry.HUNTER_TRADES_PRICES.get(result).copy();
    		int num = price1.getCount();
    		if(price1.getCount() / 3 > 0)
    		{
    			num = price1.getCount() + this.rand.nextInt(price1.getCount() / 3) * 2 - (price1.getCount() / 3);
    		}
    		price1.setCount(num);
    		this.trades.set(i * 3, price1);
    		
    		if(result.getItem() instanceof ItemGun)
    		{
        		ItemStack price2 = new ItemStack(HunterTradeRegistry.randomLootsItem().get(this.rand.nextInt(HunterTradeRegistry.randomLootsItem().size())), 10 + this.rand.nextInt(55));
        		price1.setCount(num);
        		this.trades.set(i * 3 + 1, price2);
    		}
    	}
    }
    
    public void performTrade(int i, EntityPlayer player)
    {
    	if(i < this.tradesNum() && i > -1)
    	{
    		Item item1 = this.trades.get(i * 3).getItem();
    		Item item2 = this.trades.get(i * 3 + 1).getItem();
    		int count1 = this.trades.get(i * 3).getCount();
    		int count2 = this.trades.get(i * 3 + 1).getCount();
    		
    		int try1 = player.inventory.clearMatchingItems(item1, -1, count1, null);
    		int try2 = player.inventory.clearMatchingItems(item2, -1, count2, null);
    		
    		if(count1 == try1 && (count2 == try2 || this.trades.get(i * 3 + 1).isEmpty()) && !world.isRemote)
    		{
    			player.entityDropItem(this.trades.get(i * 3 + 2), 0.2F);
    		}
    		else if(!world.isRemote)
    		{
    			player.dropItem(item1, try1);
    			player.dropItem(item2, try2);
    		}
    	}
    }
    
    protected boolean hasPerfectAim() 
    {
		return !(this instanceof EntityDeadHunter) && !(this instanceof EntityNetherHunter);
	}
    
    protected boolean canTrade() 
    {
		return !(this instanceof EntityDeadHunter) && !(this instanceof EntityNetherHunter);
	}

	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        ItemGun item = this.getRandomGun();
        ItemStack gun = new ItemStack(item);
        if(item instanceof ItemMachineGun && ((ItemMachineGun) item).canEquipModifier())
        {
        	ItemMachineGun itemU = (ItemMachineGun) item;
        	NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
        	list.set(0, new ItemStack(RegisterItems.BASIC_SOUND_MUFFLER));
        	itemU.updateChanges(gun, list);
        }
        if(item.getFirstBullet() != null)
    	{
    		item.chargeCharger(gun, item.getFirstBullet(), item.getChargerSize());
    	}
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, gun);
        if(item.getReach() <= 20)
        {
        	if(this.getRNG().nextInt(3) == 0)
        	{
        		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(RegisterItems.BULLETPROOF_VEST));
        	}
        }
    }
    
    protected ItemGun getRandomGun()
    {
    	List<ItemGun> list = Lists.newArrayList();
    	
    	for(Item item : Item.REGISTRY)
    	{
    		if(item instanceof ItemMachineGun)
    		{
    			list.add((ItemGun) item);
    		}
    	}
    	
    	list.add((ItemGun) RegisterItems.CARBINE);
    	list.add((ItemGun) RegisterItems.HUNTER_SHOTGUN);
    	list.add((ItemGun) RegisterItems.BASIC_SHOTGUN);
    	list.add((ItemGun) RegisterItems.MEDIGUN);
		return list.get(new Random().nextInt(list.size()));
    }
    
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setSkin(new Random().nextInt(10));

        if(this.trades.get(0).isEmpty())
        {
        	this.createTrades();
        }
        
        return super.onInitialSpawn(difficulty, livingdata);
    }
    
    public void onDeath(DamageSource cause)
    {
    	super.onDeath(cause);
    	if(!world.isRemote)
    	{
    		if(cause.getTrueSource() != null && cause.getTrueSource() instanceof EntityHunter && this.getCommunist() && !((EntityHunter) cause.getTrueSource()).getCommunist())
    		{
    			for(int i = 0; i < 2; ++i)
    			{
    				EntityHunter hunter = new EntityHunter(world);
            		hunter.setAttackTarget((EntityLivingBase) cause.getTrueSource());
            		hunter.setPosition(posX, posY, posZ);
            		hunter.setCommunist(true);
            		hunter.setCustomNameTag("communist");
            		hunter.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(this)), null);
            		world.spawnEntity(hunter);
    			}
    		}
    	}
    }
    
    protected boolean isValidLightLevel()
    {
        return true;
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("skin", this.getSkin());
        compound.setBoolean("communist", this.getCommunist());
        ItemStackUtils.saveAllItems(compound, trades, "trades", true);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setSkin(compound.getInteger("skin"));
        this.setCommunist(compound.getBoolean("communist"));
        ItemStackUtils.loadAllItems(compound, trades, "trades");
    }
    
    public int getSkin()
    {
    	return MathHelper.clamp(this.dataManager.get(SKIN), 0, 9);
    }
    
    public void setSkin(int level)
    {
        this.dataManager.set(SKIN, level);
    }
    
    public boolean getCommunist()
    {
    	return this.dataManager.get(COMMUNIST);
    }
    
    public void setCommunist(boolean communist)
    {
        this.dataManager.set(COMMUNIST, communist);
    }
    
    public void setTimer(int timerIn)
    {
    	timer = timerIn;
    }
    
    public boolean getCanSpawnHere(boolean onlySurface)
    {
    	if(onlySurface)
    	{
    		IBlockState iblockstate = this.world.getBlockState((new BlockPos(this)).down());
            return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && new BlockPos(this).getY() == world.getHeight((int)this.posX, (int)this.posZ) && this.isValidLightLevel() && iblockstate.canEntitySpawn(this);
    	}
    	else
    	{
            return super.getCanSpawnHere();
    	}
    }

    @Override
    public boolean getCanSpawnHere()
    {
    	return this.getCanSpawnHere(true);
    }
    
    public boolean isPreventingPlayerRest(EntityPlayer playerIn)
    {
        return false;
    }
    
    public boolean moveBackward()
    {
    	return true;
    }
    
    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
    	if(this.getHeldItemMainhand().getItem() instanceof ItemGun)
    	{
    		ItemGun item = (ItemGun) this.getHeldItemMainhand().getItem();
    		if(!world.isRemote)
    		{
    			if(this.getRNG().nextInt(40) == 0)
    			{
    				this.dropItem(RegisterItems.GUN_GRENADE_LAUNCHER, 1);
    			}
    			
    			if(item instanceof ItemMedigun)
    			{
    				this.dropItem(RegisterItems.MEDIGUN, 1);
    			}
    		}
    	}
    }
    
    public static class InventoryHunter implements IInventory
    {
        private String inventoryTitle;
        private final int slotsCount;
        private final NonNullList<ItemStack> inventoryContents;
        private List<IInventoryChangedListener> changeListeners;
        private boolean hasCustomName;

        public InventoryHunter(EntityHunter entityIn)
        {
            this.inventoryTitle = entityIn.getName();
            this.hasCustomName = true;
            this.slotsCount = entityIn.tradesNum() * 3;
            this.inventoryContents = entityIn.trades;
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
}