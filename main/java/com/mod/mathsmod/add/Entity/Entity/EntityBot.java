package com.mod.mathsmod.add.Entity.Entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRemoteGrenade;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.EntityAi.BotFollowOwner;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemCommunistDisk;
import com.mod.mathsmod.add.items.ItemGoldenExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMedigun;
import com.mod.mathsmod.add.items.guns.ItemTacticalBow;
import com.mod.mathsmod.utils.MDamageSource;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityBot extends EntityMob implements IOnShot
{

	private int timer;
	private Vec3d base;
	public boolean isSentry;
	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityBot.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<String> PLAYER_SKIN = EntityDataManager.<String>createKey(EntityBot.class, DataSerializers.STRING);
	protected static final DataParameter<Boolean> IS_FRIENDLY = EntityDataManager.<Boolean>createKey(EntityBot.class, DataSerializers.BOOLEAN);
	
	public EntityBot(World worldIn)
	{
		super(worldIn);
        this.setSize(0.8F, 2F);
	}
	
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(PLAYER_SKIN, "-");
        this.dataManager.register(IS_FRIENDLY, false);
    }
	
	public boolean isLeftHanded()
    {
        return false;
    }
	
	public boolean isImmuneToExplosions()
    {
        return true;
    }
	
	protected boolean canDespawn()
    {
        return false;
    }
	
	public void setBasePos(Vec3d vec)
	{
		this.base = vec;
	}
	
	public Vec3d getBasePos()
	{
		return this.base;
	}
	
	protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false)
        		{
        			public boolean shouldExecute()
        			{
        				if(this.attacker.getHeldItemMainhand().getItem() instanceof ItemMedigun)
        				{
        					return false;
        				}
        				else
        				{
        					return super.shouldExecute();
        				}
        			}
        		});
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D)
        		{
		        	public boolean shouldExecute()
		            {
		        		if(EntityBot.this.isSentry)
		        		{
		        			return false;
		        		}
		        		return super.shouldExecute();
		            }
        		});
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, new BotFollowOwner(this, 1.0D, 7.0F, 2.0F));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityBot) && !(p_apply_1_ instanceof EntityHunter) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, true, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && EntityBot.this.getOwner() != p_apply_1_ && !EntityBot.this.isFriendly();
            }
        }));
    }
	
	public NonNullList<ItemStack> armorItems()
	{
		NonNullList<ItemStack> list = ObfuscationReflectionHelper.getPrivateValue(EntityLiving.class, this, 14);
		return list;
	}
	
	public NonNullList<ItemStack> handItems()
	{
		NonNullList<ItemStack> list = ObfuscationReflectionHelper.getPrivateValue(EntityLiving.class, this, 12);
		return list;
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
    
    @Override
    public void fall(float nope, float cantfell)
    {
    }

    public void setOwnerId(@Nullable UUID p_184754_1_)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
    }
    
    public void setOwner(EntityPlayer player)
    {
    	this.setOwnerId(player.getUniqueID());
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
       
        compound.setByte("is_sentry", (byte) (this.isSentry ? 0 : 1));
        compound.setByte("is_friendly", (byte) (this.isFriendly() ? 0 : 1));
        
        if(this.getOwnerId() != null)
        {
            compound.setString("OwnerID", this.getOwnerId().toString());
        }
        
        if(this.getSkin() != null && this.getSkin().length() > 0 && this.getSkin() != "-")
        {
            compound.setString("skin", this.getSkin());
        }
        
        if(this.base != null)
        {
        	compound.setDouble("base_x", base.x);
        	compound.setDouble("base_y", base.y);
        	compound.setDouble("base_z", base.z);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        this.isSentry = compound.getByte("is_sentry") == 0;
        this.setFriendly(compound.getByte("is_friendly") == 0);
        
        if(compound.getString("OwnerID").length() != 0 && UUID.fromString(compound.getString("OwnerID")) != null)
        {
        	this.setOwnerId(UUID.fromString(compound.getString("OwnerID")));
        }
        
        if(compound.getString("skin").length() > 0)
        {
        	this.setSkin(compound.getString("skin"));
        }
        
        if(compound.hasKey("base_x") && compound.hasKey("base_y") && compound.hasKey("base_z"))
        {
        	double x = compound.getDouble("base_x");
        	double y = compound.getDouble("base_y");
        	double z = compound.getDouble("base_z");
        	this.base = new Vec3d(x, y, z);
        }
    }
    
    public String getSkin() 
    {
		return this.dataManager.get(PLAYER_SKIN);
	}
    
    public void setSkin(String name) 
    {
    	if(name.isEmpty())
    	{
    		this.dataManager.set(PLAYER_SKIN, "-");
    	}
    	else
    	{
    		this.dataManager.set(PLAYER_SKIN, name);
    	}
	}
    
    public boolean isFriendly() 
    {
		return this.dataManager.get(IS_FRIENDLY);
	}
    
    public void setFriendly(boolean friendly) 
    {
		this.dataManager.set(IS_FRIENDLY, friendly);
	}

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
    }

    public void onUpdate()
    {
    	if(this.getHealth() > 0)
    	{
    		if(this.getHeldItemMainhand().getItem() instanceof ItemGun && !(this.getHeldItemMainhand().getItem() instanceof ItemMedigun))
    		{
    			if(((ItemGun) this.getHeldItemMainhand().getItem()).ChargerEmpty(this.getHeldItemMainhand()))
    			{
    				((ItemGun) this.getHeldItemMainhand().getItem()).setReloading(getHeldItemMainhand(), true);
    				((ItemGun) this.getHeldItemMainhand().getItem()).reloadGun(getHeldItemMainhand(), world, this);
    				((ItemGun) this.getHeldItemMainhand().getItem()).setCooldown(getHeldItemMainhand(), (float) this.timer / (float) ((ItemGun) this.getHeldItemMainhand().getItem()).cooldown());
    			}
    		}
    		
        	if(this.timer > 0)
        	{
        		--timer;
        	}
    		
    		if(isSentry && this.base != null && this.getAttackTarget() == null)
    		{
    			if(this.base.distanceTo(this.getPositionVector()) > 3 && !this.getMoveHelper().isUpdating())
    			{
    				this.getMoveHelper().setMoveTo(this.base.x, this.base.y, this.base.z, 0.5);
    				
    				if(this.base.distanceTo(this.getPositionVector()) > 100)
        			{
        				this.setPosition(this.base.x, this.base.y, this.base.z);
        			}
    			}
    		}
    		
    		if(!isSentry && this.getOwner() != null)
    		{
    			for(EntityBot entity : UtilsVoids.getEntities(EntityBot.class, world, this, Float.MAX_VALUE))
    			{
    				if(entity.getOwnerId() != null && entity.getOwnerId().toString().equals(this.getOwnerId().toString()) && entity != this)
    				{
        				entity.isSentry = true;
    				}
    			}
    		}
    		
    		if(this.collidedHorizontally)
    		{
    			this.jump();
    		}
    		
    		if(this.getHealth() < this.getMaxHealth())
    		{
    			this.heal(0.25F);
    		}
        	
        	if(this.getAttackTarget() != null && this.getAttackTarget() != this)
        	{
        		Entity entity = this.getAttackTarget();
        		Item item = this.getHeldItemMainhand().getItem();
        		
        		UtilsVoids.setLookEntity(entity, this);
        		
        		if(this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemExplosiveBelt)
        		{
        			ItemStack stack = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        			ItemExplosiveBelt i2 = (ItemExplosiveBelt) this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        			if(this.getDistance(entity) < 8)
        			{
        				i2.onExplUsed(stack, world, this);
        				if(i2.getUsed(stack) > 0)
        				{
        					i2.setUsed(stack, i2.getUsed(stack) - 1);
        				}
        			}
        		}
        		
        		if(this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemGoldenExplosiveBelt)
        		{
        			ItemStack stack = this.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        			ItemGoldenExplosiveBelt i2 = (ItemGoldenExplosiveBelt) this.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        			if(this.getDistance(entity) < 8 && timer == 0 && this.getRNG().nextInt(40) == 0)
        			{
        				timer = 600;
        				for(EntityLivingBase entityTarget : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 5))
        				{
        					if(entityTarget != null && entityTarget != this)
        					{
        						float damages = (45F - entityTarget.getDistance(this) * 9F) * 2;
        						entityTarget.attackEntityFrom(DamageSource.causeExplosionDamage(this), damages * 4);
        					}
        				}
        				
        				this.setHealth(1);
        			}
        		}
        		
        		if(item instanceof ItemBow)
        		{
        			if(timer == 0)
        			{
        				EntityArrow entityarrow = new EntityTippedArrow(world, this);
                        entityarrow.shoot(this, this.rotationPitch, this.rotationYaw, 0.0F, 1 * 3.0F, 1.0F);
                        entityarrow.setDamage(2);
                        this.activeItemStack = this.getHeldItemMainhand();
                        this.activeItemStackUseCount = timer;
                        if(item instanceof ItemTacticalBow)
                        {
                        	entityarrow.setDamage(4);
                        }
                        
                        if(!world.isRemote)
                        {
                        	world.spawnEntity(entityarrow);
                        }
                        
                        if(this.getAttackTarget().getDistance(this) < 8)
                        {
                        	this.travel(0, 0, -1);
                        }
            			this.timer = 30;
        			}
        		}
        		
        		else if(item instanceof ItemGun && !(item instanceof ItemMedigun))
        		{
            		item.onUpdate(getHeldItemMainhand(), world, this, 0, true);
            		//item.onUsingTick(getHeldItemMainhand(), this, this.activeItemStackUseCount);
            		
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
        			
        			if(timer <= 0 && this.getDistance(this.getAttackTarget()) <= ((ItemGun) item).getReach())
        			{
        				((ItemGun) item).onItemUsedByEntity(world, this, EnumHand.MAIN_HAND);
        				
            			this.timer = ((ItemGun) item).cooldown();
        			}
        		}
        	}
        	
        	if(this.getHeldItemMainhand().getItem() instanceof ItemMedigun)
    		{
    			if(this.getOwner() != null && this.getOwner().getDistance(this) < 10)
    			{
    				Entity entity = this.getOwner();
    				
    				if(((ItemMedigun) this.getHeldItemMainhand().getItem()).getTargetID(this.getHeldItemMainhand()) == -1 || ((ItemMedigun) this.getHeldItemMainhand().getItem()).getTargetID(this.getHeldItemMainhand()) != entity.getEntityId())
    				{
    					((ItemMedigun) this.getHeldItemMainhand().getItem()).setTargetID(this.getHeldItemMainhand(), entity.getEntityId());
    				}
    				
    				((ItemMedigun) this.getHeldItemMainhand().getItem()).setUsed(this.getHeldItemMainhand(), 20);
    				((ItemMedigun) this.getHeldItemMainhand().getItem()).onUpdate(getHeldItemMainhand(), world, this, 0, true);
    				
    				if(((ItemMedigun) this.getHeldItemMainhand().getItem()).getUber(this.getHeldItemMainhand()) >= ((ItemMedigun) this.getHeldItemMainhand().getItem()).uberTime())
    				{
    					((ItemMedigun) this.getHeldItemMainhand().getItem()).onItemUsedByEntity(world, this, EnumHand.MAIN_HAND);
    				}
    				
    				if(this.getAttackTarget() != null)
    				{
    					if(this.getAttackTarget().getDistance(this) < 10)
                        {
                        	this.travel(0, 0, -1);
                        }
    				}
    			}
    		}
    	}
    	
    	super.onUpdate();
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(this.getOwner() == player || player.capabilities.isCreativeMode)
    	{
    		if(player.isSneaking() && player.getHeldItemMainhand().getItem() != Items.NAME_TAG)
    		{
    			if(!world.isRemote && !this.isDead)
    			{
    				ItemStack stack = new ItemStack(RegisterItems.BOT);
    		        stack.setItemDamage((int) MathHelper.clamp(40 - this.getHealth(), 0, 40));
    		        
    				EntityItem entity = new EntityItem(this.world, this.posX, this.posY, this.posZ, stack);
    				entity.setOwner(player.getName());
    				world.spawnEntity(entity);
    				this.setDead();
    				
    				for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
    		        {
    		            ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);
    		            this.entityDropItem(itemstack, 0.0F);
    		        }
    			}
    			return false;
    		}
    		else if(!player.isSneaking())
    		{
    			player.openGui(Mathsmod.instance, 16, player.world, this.getEntityId(), 0, 0);
    			return true;
    		}
    		else
    		{
    			return true;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
    
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
        {
            ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);
            this.entityDropItem(itemstack, 0.0F);
        }
        
        ItemStack stack = new ItemStack(RegisterItems.BOT);
        stack.setItemDamage((int) MathHelper.clamp(40 - this.getHealth(), 0, 40));
        this.entityDropItem(stack, 0);
    }

	@Override
	public void onShot(EntityLivingBase shooter, ItemStack stack, float amount) 
	{
		this.attackEntityFrom(MDamageSource.causeThrownDamage(shooter), amount * 0.5F);
	}
}
