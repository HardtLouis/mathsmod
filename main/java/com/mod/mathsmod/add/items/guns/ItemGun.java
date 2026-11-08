package com.mod.mathsmod.add.items.guns;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMythicalItem;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.add.items.ILeftClickItem;
import com.mod.mathsmod.add.items.ISpecialRightClickItem;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.messages.MessageSuperHit;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.IGunCraft;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemGun extends Item implements IGunCraft, ILeftClickItem, ISpecialRightClickItem, ICustomColor
{
	
	public ItemGun(String name) {
		this.setMaxStackSize(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_GUNS_TAB).setRegistryName(name).setUnlocalizedName(name);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(this.getCharger(stack) + "/" + this.getChargerSize());
		tooltip.add(I18n.translateToLocal("stats.shoot_power.name") + " : " + this.getDamagesCaused() * this.damageMultiplier(stack, null, null));
		tooltip.add("");
		tooltip.add(TextFormatting.DARK_RED + "base DPS:" + this.getDPS(stack));
		tooltip.add("");
		tooltip.add(this.bulletUsed(stack, null) == null ? "not found" : this.bulletUsed(stack, null).getRegistryName().toString());
		if(this instanceof ItemShotGun)
		{
			tooltip.add("stop power : " + ((ItemShotGun)this).stopPower());
		}
    }
	
	public void onRightClickPressed(World worldIn, EntityPlayer playerIn, int ticks)
	{
    	ItemStack stack = playerIn.getHeldItemMainhand();
    	if(!worldIn.isRemote)
    	{
    		if(this.ChargerEmpty(stack))
            {
    			this.setReloading(stack, true);
            }
            else
            {
            	this.setUsed(stack, ticks);
            	return;
            }
    	}
	}
	
	public boolean isEnchantable(ItemStack stack)
    {
        return this.getItemStackLimit(stack) == 1;
    }
	
	public float backMultiplier()
	{
		return 1.0F;
	}
	
	
	public ResourceLocation getLayer(ItemStack stack)
	{
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("layer"))
		{
			return new ResourceLocation(stack.getTagCompound().getString("layer"));
		}
		
		return null;
	}
	
	public void setLayer(ItemStack stack, ResourceLocation loc)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if(loc == null)
		{
			stack.getTagCompound().removeTag("layer");
		}
		else
		{
			stack.getTagCompound().setString("layer", loc.toString());
		}
		
	}
	
	
	public float HeadMultiplier()
	{
		if(this instanceof ItemMachineGun || this instanceof ItemShotGun)
		{
			return 1F;
		}
		else
		{
			return 1.3F;
		}
	}
	
	public static int getReload(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("reload")) 
        {
            return 0;
        }

        return stack.getTagCompound().getInteger("reload");
    }
	
	public float multiplierPerDistances(ItemStack stack, EntityPlayer player, float distance)
	{
		if(this.getBullet() == EnumBulletType.LONG)
		{
			return 1.0F;
		}
		float mult = (float) ((1.0 - Math.tanh(- 3.0 + ((distance / this.getReach()) * 6.0))) / 2);
		return mult;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
		if(this instanceof ItemEnderSniper)
		{
			return super.getDurabilityForDisplay(stack);
		}
		else
		{
	        return (double)(this.getChargerSize() - this.getCharger(stack)) / (double)this.getChargerSize();
		}
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
		if(this instanceof ItemEnderSniper)
		{
			return super.showDurabilityBar(stack);
		}
		else
		{
	        return this.getCharger(stack) < this.getChargerSize();
		}
    }
	
	public int getRGBDurabilityForDisplay(ItemStack stack)
    {
		double i = (double)this.getCharger(stack) / (double)this.getChargerSize();
        return new Color((int)(i * 200D), (int)(i * 200D), 255).getRGB();
    }
	
	public abstract int cooldown();
	
	public int cooldown(ItemStack stack, EntityPlayer player)
	{
		return cooldown();
	}

	public abstract float zoomOnUsed();
	
	public abstract int getMaxReloadTime();
	
	public int getMaxReloadTime(ItemStack stack, EntityPlayer player)
	{
		return getMaxReloadTime();
	}
	
	public abstract int getChargerSize();
	
	protected abstract float innacuracyOnShoot();
	protected abstract int maxInnacuracy();
	protected abstract float removeInnacuracyNumber();

	public abstract float getBulletDrop();
	
	public float getPower(ItemStack stack, Entity entity)
	{
		return this.getPower();
	}
	
	public abstract float getPower();
	public abstract float getDamagesCaused();
	public abstract float getInnacuracy(ItemStack stack, Entity entity);
	
	public abstract ThreeFloat rightArmRotation(Entity player, ItemStack stack, float partialTicks);
	public abstract ThreeFloat leftArmRotation(Entity player, ItemStack stack, float partialTicks);

	public abstract armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks);
	public abstract armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks);
	
	public abstract int timeToUse();
	
	public boolean hasTravel()
	{
		return true;
	}
	
	public abstract boolean autoMod();
	
	public abstract boolean scopeToShoot();
	
	public abstract boolean lowBulletFar();
	
	public abstract int getReach();
	
	public boolean rechargeOneByOne()
	{
		return false;
	}
	
	public boolean smoothScope()
	{
		return false;
	}
	
	public float smoothMultiplier()
	{
		return 1.0F;
	}
    
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    
    protected void onShoot(EntityLivingBase entity)
    {
    	
    }
    
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	
    	if(this.bulletUsed(stack, null) == null && !this.ChargerEmpty(stack))
    	{
    		this.removeBulletInCharger(stack);
    	}
    	
    	if(this.getUsed(stack) != 0 && entityIn.onGround)
    	{
    		entityIn.motionX *= 0.7F;
    		entityIn.motionZ *= 0.7F;
    	}
    	
    	if(!isSelected)
    	{
    		this.setUsed(stack, 0);
    		this.setTakeTime(stack, 0);
    		this.setShootDefault(stack, 0);
    	}
    	
    	if(this.ChargerEmpty(stack))
    	{
    		this.setUsed(stack, 0);
    	}
    	
    	if(isSelected)
    	{
    		if(entityIn instanceof EntityLivingBase)
    		{
    			if(!((EntityLivingBase) entityIn).isPotionActive(MobEffects.NAUSEA))
    			{
    				if(this.getShootDefault(stack) > 0)
    	        	{
    	        		this.setShootDefault(stack, this.getShootDefault(stack) - removeInnacuracyNumber());
    	        	}
    	        	
    	        	if(this.getShootDefault(stack) < 0)
    	        	{
    	        		this.setShootDefault(stack, 0);
    	        	}
    	        	
    	        	if(this.getTakeTime(stack) < this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
    	        	{
    	        		this.addTakeTime(stack, 1);
    	        	}
    			}
    			else
    			{
    				this.setShootDefault(stack, 30);
    			}
    		}
    	}
    	
    	if(entityIn instanceof EntityPlayer)
    	{
        	if(isSelected)
        	{
        		this.reloadGun(stack, worldIn, (EntityLivingBase) entityIn);
        		this.setCooldown(stack, ((EntityPlayer) entityIn).getCooldownTracker().getCooldown(this, 0.0F));
        		
        		if(this.getReloading(stack) && this.getCharger(stack) >= this.getChargerSize())
        		{
        			this.setReloading(stack, false);
        		}
        	}
    	}
    }
    
    public void reloadGun(ItemStack stack, World worldIn, EntityLivingBase entityIn)
    {
    	if(this.getReloading(stack))
		{
    		if(entityIn instanceof EntityPlayer)
    		{
    			if(this.getMaxReloadTime(stack, (EntityPlayer) entityIn) <= this.getReload(stack))
    			{
    				com.mod.mathsmod.add.event.MEvents.OnGunReload event = new com.mod.mathsmod.add.event.MEvents.OnGunReload(worldIn, entityIn, stack, this);
    				if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
    				
    				if(this.goodBullet((EntityPlayer) entityIn) == null)
    				{
    					if(!this.ChargerEmpty(stack) && !event.isBlocked())
    					{
    						this.setReloading(stack, false);
    					}
    					return;
    				}
    				
    				if(this.goodBullet((EntityPlayer) entityIn) != null && (this.goodBullet((EntityPlayer) entityIn)) == this.bulletUsed(stack, entityIn) || this.getCharger(stack) == 0)
					{
						if(!((EntityPlayer) entityIn).capabilities.isCreativeMode)
                    	{
							if(!this.rechargeOneByOne())
							{
								int val = this.getCharger(stack);
								if(!MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "infinite bullets", false).getBoolean())
	                    		{
	                        		val += ((EntityPlayer) entityIn).inventory.clearMatchingItems(this.goodBullet((EntityPlayer) entityIn), -1, this.getChargerSize() - this.getCharger(stack), null);
	                    		}
								else
								{
									val = this.getChargerSize();
								}
	                    		this.chargeCharger(stack, this.goodBullet((EntityPlayer) entityIn), val);
	                    		this.setReloading(stack, false);
	                    		this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
							}
							else
							{
								int val = this.getCharger(stack);
								if(!MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "infinite bullets", false).getBoolean())
	                    		{
	                        		val += ((EntityPlayer) entityIn).inventory.clearMatchingItems(this.goodBullet((EntityPlayer) entityIn), -1, 1, null);
	                    		}
								else
								{
									val += 1;
								}
								
	                    		this.chargeCharger(stack, this.goodBullet((EntityPlayer) entityIn), val);
	                    		if(this.getCharger(stack) == this.getChargerSize())
                    			{
                    				this.setReloading(stack, false);
                    			}
	                    		this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
							}
                    	}
                    	else
                    	{
                    		if(!this.rechargeOneByOne())
							{
                        		this.chargeCharger(stack, this.goodBullet((EntityPlayer) entityIn), this.getChargerSize());
                        		this.setReloading(stack, false);
                        		this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
							}
                    		else
                    		{
                    			this.chargeCharger(stack, this.goodBullet((EntityPlayer) entityIn), this.getCharger(stack) + 1);
                    			if(this.getCharger(stack) == this.getChargerSize())
                    			{
                    				this.setReloading(stack, false);
                    			}
                        		this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
                    		}
                    	}
					}
    				return;
    			}
    			else
    			{
    				this.addReload(stack, (EntityPlayer) entityIn, 1);
    			}
    		}
    		else
    		{
    			if(this.getMaxReloadTime() <= this.getReload(stack))
    			{
    				if(this.getFirstBullet() != null)
					{
    					if(!this.rechargeOneByOne())
						{
                    		this.chargeCharger(stack, this.getFirstBullet(), this.getChargerSize());
                    		this.setReloading(stack, false);
                    		this.substractReload(stack, null, this.getReload(stack));
						}
                		else
                		{
                			this.chargeCharger(stack, this.getFirstBullet(), this.getCharger(stack) + 1);
                			if(this.getCharger(stack) == this.getChargerSize())
                			{
                				this.setReloading(stack, false);
                			}
                    		this.substractReload(stack, null, this.getReload(stack));
                		}
					}
    			}
    			else
    			{
    				this.addReload(stack, null, 1);
    			}
    		}
		}
	}

	@Override
    public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
    {
		if(playerIn.getRidingEntity() != null && playerIn.getRidingEntity() instanceof IVehicle)
		{
			return;
		}
		if(playerIn.getCooldownTracker().getCooldown(this, 0.0F) <= 0)
		{
			this.onItemUsedByEntity(worldIn, playerIn, EnumHand.MAIN_HAND);
		}
    }
    
    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack stack = playerIn.getHeldItem(handIn);
    	if(playerIn.getRidingEntity() != null && playerIn.getRidingEntity() instanceof IVehicle)
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
		}
    	
    	if(this.autoMod() || this.getReset(stack))
		{
        	this.onItemUsedByEntity(worldIn, playerIn, handIn);
		}
    	
    	return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }*/
    
    public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	if ((handIn == EnumHand.MAIN_HAND || entityIn instanceof EntityPub) && this.getTakeTime(stack) >= this.timeToUse() + this.switchCooldownBonus(stack, entityIn))
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null)
    		{
				this.bulletUsed(stack, entityIn).shootEvent(worldIn, entityIn, (ItemGun) stack.getItem(), false, null);
				
				if(this.getReloading(stack))
	    		{
	    			this.setReloading(stack, false);;
	    		}
				
				this.onShoot(entityIn);
				
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F * this.soundMultiplier(stack, entityIn), this.soundPitch(worldIn));
				if(!(entityIn instanceof EntityPlayer) || !((EntityPlayer) entityIn).capabilities.isCreativeMode)
				{
					this.removeBulletInCharger(stack);
				}
				
				if(entityIn instanceof EntityPlayer)
				{
	    			this.substractReload(stack, (EntityPlayer) entityIn, this.getReload(stack));
				}
				else
				{
	    			this.substractReload(stack, null, this.getReload(stack));
				}
				
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() <= this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.getShootDefault(stack) + this.innacuracyOnShoot());
    			}
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() > this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.maxInnacuracy());
    			}
    			if(entityIn instanceof EntityPlayer)
    			{
    				if(this instanceof ItemShotGun)
    				{
    					for(ItemStack stack2 : ((EntityPlayer) entityIn).inventory.mainInventory)
    					{
    						if(stack2.getItem() instanceof ItemShotGun && stack2.getItem() != this)
    						{
    							((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack2.getItem(), 40);
    						}
    					}
    				}
    				
    				if(worldIn.isRemote && this.cooldown(stack, (EntityPlayer) entityIn) <= 4 && !(this instanceof ItemMachineGun))
    				{
    					((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), 3);
    				}
    				else
    				{
    					((EntityPlayer) entityIn).getCooldownTracker().setCooldown(stack.getItem(), this.cooldown(stack, (EntityPlayer) entityIn));
    				}
    			}
            }
        }
    }
    
    public boolean ChargerEmpty(ItemStack stack)
    {
    	return this.getCharger(stack) == 0;
    }
    
    public ItemBaseBullet goodBullet(EntityPlayer playerIn)
    {
    	for(ItemStack stack : UtilsVoids.getBullets(playerIn))
    	{
    		if(stack.getItem() instanceof ItemBaseBullet && this.goodBulletType((ItemBaseBullet) stack.getItem()))
    		{
    			return (ItemBaseBullet) stack.getItem();
    		}
    	}
    	return null;
    }
    
    public abstract EnumBulletType getBullet();
    
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            if(this.getChargerSize() > 0)
            {
                ItemStack loaded = new ItemStack(this);
                if(this.getFirstBullet() != null)
                {
                	for(int i = 0; i < this.getChargerSize(); ++i)
                	{
                		this.chargeCharger(loaded, getFirstBullet(), this.getChargerSize());
                	}
                	items.add(loaded);
                }
            }
        }
    }
    
    public boolean goodBulletType(ItemBaseBullet item)
    {
    	if(item.type() == this.getBullet())
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public SoundEvent getShotSound()
    {
    	return SoundEvents.ENTITY_GENERIC_EXPLODE;
    }
    
    public float getSoundPower()
    {
    	return 1F;
    }
    
    public ItemBaseBullet getFirstBullet()
    {
    	switch(this.getBullet())
    	{
    		case GRENADE:
    			return RegisterItems.GRENADE;
    		case LONG:
    			return RegisterItems.SNIPER_BULLET;
    		case NORMAL:
    			return RegisterItems.LONG_AMMO;
    		case LITTLE:
    			return RegisterItems.LITTLE_AMMO;
    		case ROCKET:
    			return RegisterItems.ROCKET;
    		case SHOTGUN:
    			return RegisterItems.BUCKSHOT;
    		case NONE:
    			return null;
    	}
		return RegisterItems.LITTLE_AMMO;
    }
    
    //TODO data
    public void addReload(ItemStack stack, @Nullable EntityPlayer player, int reload)
    {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());

        }
        
        int i = stack.getTagCompound().getInteger("reload") + reload;
        if(player == null)
        {
        	if(i > this.getMaxReloadTime())
        	{
        		stack.getTagCompound().setInteger("reload", this.getMaxReloadTime());
        	}
        	else
        	{
        		stack.getTagCompound().setInteger("reload", i);
        	}
        }
        else
        {
        	if(i > this.getMaxReloadTime(stack, player))
        	{
        		stack.getTagCompound().setInteger("reload", this.getMaxReloadTime(stack, player));
        	}
        	else
        	{
        		stack.getTagCompound().setInteger("reload", i);
        	}
        }
        
        
    }
    
    public void substractReload(ItemStack stack, @Nullable EntityPlayer player, int reload)
    {
        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        int i = stack.getTagCompound().getInteger("reload") - reload;
        if(player == null)
        {
        	if(i < 0)
            {
            	stack.getTagCompound().setInteger("reload", getMaxReloadTime());
            }
            else
            {
            	stack.getTagCompound().setInteger("reload", i);
            }
        }
        else
        {
        	if(i < 0)
            {
            	stack.getTagCompound().setInteger("reload", getMaxReloadTime(stack, player));
            }
            else
            {
            	stack.getTagCompound().setInteger("reload", i);
            }
        }
        
    }
    
    protected String getDPS(ItemStack stack)
	{
    	float ticksTot = this.getChargerSize() * this.cooldown() + this.getMaxReloadTime();
    	return (this.getDamagesCaused() * this.damageMultiplier(stack, null, null) * this.getChargerSize() / ticksTot * 20.0F) + "";
	}
    
    /***************************************************************************************************************/
    
    public void chargeCharger(ItemStack stack, Item bullet, int num) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        if(bullet != null)
        {
        	stack.getTagCompound().setString("bullet_used", bullet.getRegistryName().toString());
        	stack.getTagCompound().setInteger("clip", num);
        }
    }
    
    public ItemBaseBullet bulletUsed(ItemStack stack, @Nullable Entity entityIn)
    {
    	if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
    	else
    	{
    		Item item = Item.REGISTRY.getObject(new ResourceLocation(stack.getTagCompound().getString("bullet_used")));
        	if(item != null && item instanceof ItemBaseBullet)
        	{
        		return (ItemBaseBullet) item;
        	}
    	}
    	
    	return null;
    }
    
    public void setLastBulletBeforeShot(ItemStack stack, EntityLivingBase entityIn)
    {
    	if (!stack.hasTagCompound()) 
    	{
            stack.setTagCompound(new NBTTagCompound());
        }
    	else
    	{
    		stack.getTagCompound().setString("last_bullet", stack.getTagCompound().getString("bullet_used"));
    	}
    }
    
    public ItemBaseBullet lastBullet(ItemStack stack, @Nullable Entity entityIn)
    {
    	if (!stack.hasTagCompound()) 
    	{
            stack.setTagCompound(new NBTTagCompound());
        }
    	else
    	{
    		Item item = Item.REGISTRY.getObject(new ResourceLocation(stack.getTagCompound().getString("last_bullet")));
        	if(item != null && item instanceof ItemBaseBullet)
        	{
        		return (ItemBaseBullet) item;
        	}
    	}
    	
    	return null;
    }
    
    public void removeBulletInCharger(ItemStack stack)
    {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("clip", MathHelper.clamp(this.getCharger(stack) - 1, 0, this.getChargerSize()));
    }
    
    public int getCharger(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }
        
    	return stack.getTagCompound().getInteger("clip");
    }
    
    public void setUsed(ItemStack stack, int used) 
    {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("usingTicks", used);

    }
    
    public static int getUsed(ItemStack stack) 
    {
        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("usingTicks")) {

        	stack.getTagCompound().setInteger("usingTicks", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("usingTicks");
    }
    
    public void setShootDefault(ItemStack stack, float d) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setFloat("gsd", d);

    }
    
    public static float getShootDefault(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("gsd")) {

        	stack.getTagCompound().setFloat("gsd", 0);
            return 0;
        }

        return stack.getTagCompound().getFloat("gsd");
    }
    
    public void setReloading(ItemStack stack, boolean used) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("reloading", used);

    }
    
    public static boolean getReloading(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("reloading")) {

        	stack.getTagCompound().setBoolean("reloading", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("reloading");
    }
    
    public void setReset(ItemStack stack, boolean reset) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("rst", reset);

    }
    
    public static boolean getReset(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("rst")) {

        	stack.getTagCompound().setBoolean("rst", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("rst");
    }
    
    public void setEhanced(ItemStack stack, boolean ehanced) 
    {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("ehanced", ehanced);

    }
    
    public boolean getEhanced(ItemStack stack)
    {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("ehanced")) {

        	stack.getTagCompound().setBoolean("ehanced", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("ehanced");
    }
    
    public void setCooldown(ItemStack stack, float ticks) 
    {
        if (!stack.hasTagCompound()) 
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setFloat("cooldown", ticks);
    }
    
    public float getCooldownTime(ItemStack stack, float partialTicks, Entity entity) 
    {
        if (!stack.hasTagCompound())
        {
            return 0;
        }

        if (!stack.getTagCompound().hasKey("cooldown")) {

        	stack.getTagCompound().setFloat("cooldown", 0);
            return 0;
        }
        
        if(entity instanceof EntityPlayer)
        {
        	return ((EntityPlayer) entity).getCooldownTracker().getCooldown(this, 0.0F);
        }
        else
        {
        	return stack.getTagCompound().getFloat("cooldown");
        }
    }
    
    public void setTakeTime(ItemStack stack, int ticks) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("changeCooldown", ticks);

    }
    
    public void addTakeTime(ItemStack stack, int ticks) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("changeCooldown", this.getTakeTime(stack) + ticks);
    }
    
    public static int getTakeTime(ItemStack stack) 
    {
        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("changeCooldown")) {

        	stack.getTagCompound().setInteger("changeCooldown", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("changeCooldown");
    }
    
    @SideOnly(Side.CLIENT)
    public boolean Animated(ItemStack stack, EntityPlayer player)
    {
    	if(!this.scopeToShoot() || ItemGun.getUsed(stack) != 0 || this.getReloading(stack) || (player.getCooldownTracker().hasCooldown(this) && player.getHeldItemMainhand() == stack))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public boolean canBeUseByHunter()
    {
    	return true;
    }
    
    @Override
    public int size()
    {
    	return 1;
    }
    
    @Override
	public boolean displayRecipe()
	{
		return true;
	}
    
    public int getItemEnchantability()
    {
        return 5;
    }
    
    public float soundPitch(World worldIn)
    {
    	return 1; //(1.0F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F) * 0.7F;
    }
    
    protected boolean userNotMoving(Entity entity)
    {
		return entity.motionX == 0 && entity.motionY <= 0 && entity.motionZ == 0;
    }
    
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player)
    {
        return false;
    }
    
    public float handMovementMultiplicator()
    {
    	return 1F;
    }
    
    public boolean isPistol()
    {
    	return false;
    }
    
    
    
    public float innacuracyMultiplier(ItemStack stack, Entity entity)
    {
		return 1;
    }
    
    public int switchCooldownBonus(ItemStack stack, Entity entity)
    {
		return 0;
    }
    
    public float zoomMultiplier(ItemStack stack, Entity entity)
    {
    	return 1;
    }
    
    protected float soundMultiplier(ItemStack stack, Entity entity)
    {
    	return 1;
    }
    
    public float smoothMultiplier(ItemStack stack, Entity entity)
    {
    	return 1;
    }
    
    public float damageMultiplier(ItemStack stack, Entity entity, @Nullable Entity target)
    {
    	float mult = 1.0F;
    	if(target != null && entity instanceof EntityPlayer)
    	{
    		mult = this.multiplierPerDistances(stack, (EntityPlayer) entity, (float) entity.getPositionEyes(1.0F).distanceTo(target.getPositionVector().addVector(0, target.height / 2, 0)));
    	}
    	
    	if(this.getEhanced(stack))
    	{
    		mult *= 1.5F;
    	}
    	float bonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack) * 0.04F;
    	return (1 + bonus) * mult;
    }
    
    public boolean hasCustomEntity(ItemStack stack)
    {
        return this instanceof IGoldenWeapon || this instanceof IGoldenAnimatedGun || this.getEhanced(stack);
    }
    
    public static void makeSuperHit(Entity shot, int type, Entity entityIn)
    {
    	if(entityIn instanceof EntityPlayerMP)
    	{
    		Mathsmod.network.sendTo(new MessageSuperHit(shot, type), (EntityPlayerMP) entityIn);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public void setTileEntityItemStackRenderer(@Nullable net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer teisr)
    {
    	RegisterItems.registerColor(this);
    	super.setTileEntityItemStackRenderer(teisr);
    }
	
    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemstack)
    {
    	if(this.getEhanced(itemstack))
    	{
    		EntityMythicalItem entity = new EntityMythicalItem(world, location.posX, location.posY, location.posZ, itemstack);
        	entity.motionX = location.motionX;
        	entity.motionY = location.motionY;
        	entity.motionZ = location.motionZ;
        	entity.setPickupDelay(20);
            return entity;
    	}
    	else
    	{
    		return null;
    	}
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex) 
	{
		if(this.getEhanced(stack))
		{
			float t = ((float)(Minecraft.getMinecraft().getSystemTime()) / 1000.0F) * 20.0F;
			return RenderLaser.getColorFromAngle(t, 150, 150, 150);
		}
		else
		{
			return -1;
		}
	}
}
