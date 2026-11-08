package com.mod.mathsmod.add.items.guns;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.add.items.ItemAmmoBox;
import com.mod.mathsmod.add.items.guns.renderer.IAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenAnimatedGun;
import com.mod.mathsmod.add.items.guns.renderer.IItemSpecialRenderer;
import com.mod.mathsmod.add.items.guns.renderer.armsLoRo;
import com.mod.mathsmod.add.items.guns.renderer.models.modelBaseGun;
import com.mod.mathsmod.add.items.guns.renderer.models.modelMinigun;
import com.mod.mathsmod.add.messages.MessageInfiniteBullet;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMinigun extends ItemMachineGun implements IItemSpecialRenderer, ICustomColor
{
	protected static final UUID HEAVY = UUID.fromString("845DB27C-C624-495F-8C9F-6020A5A58B6F");
	
	public ItemMinigun() 
	{
		super("minigun");
	}
	
	public ItemMinigun(String name) 
	{
		super(name);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(this.bulletUsed(stack, null) == null ? "not found" : this.bulletUsed(stack, null).getRegistryName().toString());
		tooltip.add(I18n.translateToLocal("stats.shoot_power.name") + " : " + this.getDamagesCaused());
		tooltip.add("");
		tooltip.add(TextFormatting.DARK_RED + "base DPS:" + this.getDPS(stack));
    }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
        }
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
    	super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    	
    	if(this.getShootTicks(stack) != 0)
    	{
    		this.setShootTicks(stack, this.getShootTicks(stack) - 1);
    	}
    	
    }
	
	@Override
	protected int shootCadence() 
	{
		return 1;
	}

	@Override
	public float zoomOnUsed() 
	{
		return 1.2F;
	}

	@Override
	public int getMaxReloadTime() 
	{
		return 0;
	}

	@Override
	public int getChargerSize() 
	{
		return 0;
	}

	@Override
	protected float innacuracyOnShoot() 
	{
		return 0;
	}

	@Override
	protected int maxInnacuracy()
	{
		return 0;
	}

	@Override
	protected float removeInnacuracyNumber() 
	{
		return 0;
	}

	@Override
	public float getBulletDrop() 
	{
		return 0;
	}

	@Override
	public float getPower() 
	{
		return 2F;
	}

	@Override
	public float getDamagesCaused() 
	{
		return 1.0F * Mathsmod.MINIGUNS_MULTIPLIER;
	}

	@Override
	public float getInnacuracy(ItemStack stack, Entity entity) 
	{
		return this.getUsed(stack) != 0 ? 10 : 20;
	}

	@Override
	public ThreeFloat rightArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-90 * 0.017453292F, 0, 0);
	}

	@Override
	public ThreeFloat leftArmRotation(Entity entity, ItemStack stack, float partialTicks)
	{
		return new ThreeFloat(-90 * 0.017453292F, 50 * 0.017453292F, 0);
	}

	@Override
	public armsLoRo rightArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks)
	{
		return null;
	}

	@Override
	public armsLoRo leftArmFirstPersonRotation(ItemStack stack, EntityPlayer player, float partialTicks) 
	{
		return null;
	}

	@Override
	public boolean scopeToShoot()
	{
		return false;
	}

	@Override
	public int getReach() 
	{
		return 20;
	}
	
	public void onItemUsedByEntity(World worldIn, EntityLivingBase entityIn, EnumHand handIn)
    {
    	ItemStack stack = entityIn.getHeldItem(handIn);
    	
    	Vec3d vec = UtilsVoids.laser(0.75D, entityIn.rotationYaw, entityIn.rotationPitch);
    	
    	if (handIn == EnumHand.MAIN_HAND && this.getTakeTime(stack) >= this.timeToUse() && (entityIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != RegisterItems.KURAYUM_JETPACK || !RegisterItems.KURAYUM_JETPACK.getUsing(entityIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST))))
        {
    		if(!this.ChargerEmpty(stack) && stack.getItem() instanceof ItemGun && this.bulletUsed(stack, entityIn) != null && !this.getReloading(stack))
    		{
				entityIn.rotationPitch -= 1 - 2 * new Random().nextFloat();
				worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, this.getShotSound(), SoundCategory.AMBIENT, this.getSoundPower() * 0.5F, this.soundPitch(worldIn));
				
				this.bulletUsed(stack, entityIn).shootEvent(worldIn, entityIn, (ItemGun) stack.getItem(), false, null);
				
    			this.substractReload(stack, this.getReload(stack));
    			
    			
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() <= this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.getShootDefault(stack) + this.innacuracyOnShoot());
    			}
    			if(this.getShootDefault(stack) + this.innacuracyOnShoot() > this.maxInnacuracy())
    			{
    				this.setShootDefault(stack, this.maxInnacuracy());
    			}
    			if(entityIn instanceof EntityPlayer && !((EntityPlayer) entityIn).capabilities.isCreativeMode)
    			{
    				if(((EntityPlayer) entityIn).inventory.clearMatchingItems(this.bulletUsed(stack, entityIn), -1, 1, null) == 0)
    				{
    					for(ItemStack s : UtilsVoids.getAmmoBoxes((EntityPlayer) entityIn, this))
						{
							if(s.getItem() instanceof ItemAmmoBox && ((ItemAmmoBox) s.getItem()).getAmmo(s).getItem() instanceof ItemBaseBullet && this.goodBulletType((ItemBaseBullet) ((ItemAmmoBox) s.getItem()).getAmmo(s).getItem()))
							{
								ItemAmmoBox item = (ItemAmmoBox) s.getItem();
								
								if(item.getItemCount(s) > 0)
								{
    								item.setItemCount(item.getItemCount(s) - 1, s);
								}
							}
						}
    				}
    			}
				//worldIn.spawnParticle(EnumParticleTypes.CRIT, entityIn.posX + vec.x, entityIn.posY + entityIn.getEyeHeight() + vec.y, entityIn.posZ + vec.z, 0, -0.1, 0);
            }
        }
    }
	
	protected String getDPS(ItemStack stack)
	{
		float damagePerSeconde = this.getDamagesCaused() * 20;
		damagePerSeconde /= 2;
		String dps = Float.toString(damagePerSeconde);
		if(dps.length() > 4)
		{
			dps = dps.substring(0, dps.length() - 4);
		}
		
		return dps;
	}

	@Override
	public EnumBulletType getBullet() 
	{
		return EnumBulletType.LITTLE;
	}
	
	public void setReloading(ItemStack stack, boolean used) 
	{
    }
    
    public static boolean getReloading(ItemStack stack) 
    {
    	return false;
    }
    
    public boolean canBeUseByHunter()
    {
    	return false;
    }
    
    public float handMovementMultiplicator()
    {
    	return 0.3F;
    }
    
    @Override
	public int cooldown() 
	{
		return 0;
	}
    
    public void removeBulletInCharger(ItemStack stack)
    {
    }
    
    public void addReload(ItemStack stack, int reload) 
    {
    }
    
    public void substractReload(ItemStack stack, int reload) 
    {
    }
    
    public int getCharger(ItemStack stack) 
    {
    	return 0;
    }
    
    public boolean ChargerEmpty(ItemStack stack)
    {
    	return false;
    }
    
    @Override
    public ItemBaseBullet bulletUsed(ItemStack stack, @Nullable Entity player)
    {
    	
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) != 0 || this.getUseIB(stack))
    	{
    		return this.getFirstBullet();
    	}
    	else
    	{
    		if(player != null)
    		{
    			if(player instanceof EntityPlayer)
        		{
    				if(((EntityPlayer) player).capabilities.isCreativeMode)
    				{
    					if(this.goodBullet((EntityPlayer) player) != null)
    					{
    						return this.goodBullet((EntityPlayer) player);
    					}
    					else
    					{
    						return this.getFirstBullet();
    					}
    				}
    				else
    				{
    					for(ItemStack s : UtilsVoids.getAmmoBoxes((EntityPlayer) player, this))
						{
							if(s.getItem() instanceof ItemAmmoBox && ((ItemAmmoBox) s.getItem()).getAmmo(s).getItem() instanceof ItemBaseBullet && this.goodBulletType((ItemBaseBullet) ((ItemAmmoBox) s.getItem()).getAmmo(s).getItem()))
							{
								ItemAmmoBox item = (ItemAmmoBox) s.getItem();
								
								if(item.getItemCount(s) > 0)
								{
    								return (ItemBaseBullet) item.getAmmo(s).getItem();
								}
							}
						}
    					
    					return this.goodBullet((EntityPlayer) player);
    				}
        			
        		}
        		else
        		{
        			return this.getFirstBullet();
        		}
    		}
    		else
    		{
    			return this.getFirstBullet();
    		}
    	}
    }
    
    public SoundEvent getShotSound()
    {
    	return SoundEvents.ENTITY_FIREWORK_LARGE_BLAST;
    }
    
    public float soundPitch(World worldIn)
    {
    	return 1.2F;
    }
    
    @Override
    public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int tick)
	{
    	ItemStack stack = playerIn.getHeldItemMainhand();
    	int ticks = this.getShootTicks(stack);
    	
    	if(playerIn.getCooldownTracker().hasCooldown(this) || this.getTakeTime(stack) < this.timeToUse() || tick == 0)
    	{
    		this.setShootTicks(stack, 0);
    	}
    	else
    	{
    		playerIn.motionX *= 0.9F;
    		playerIn.motionZ *= 0.9F;
    		
    		if(ticks == 50 && !worldIn.isRemote && playerIn instanceof EntityPlayerMP)
    		{
    			this.setUseIB(stack, MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "infinite bullets", false).getBoolean());
    			Mathsmod.network.sendTo(new MessageInfiniteBullet(MConfig.getConfig().get(Configuration.CATEGORY_GENERAL, "infinite bullets", false).getBoolean()), (EntityPlayerMP) playerIn);
    		}
    		
    		if(ticks > 69)
    		{
    			this.onItemUsedByEntity(worldIn, playerIn, EnumHand.MAIN_HAND);
    			this.setShootTicks(playerIn.getHeldItemMainhand(), ticks + 2);
    		}
    		else
    		{
    			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.AMBIENT, 3, 0.7F);
    			this.setShootTicks(playerIn.getHeldItemMainhand(), ticks + 2);
    		}
    	}
	}
    
    @Override
	public boolean hasTravel()
	{
		return false;
	}
    
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
        	multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(HEAVY, "Heavy", -0.03F, 0));
        }

        return multimap;
    }
    
    //TODO shootTicks
    
    public void setShootTicks(ItemStack stack, int ticks) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("shootTicks", ticks);

    }
    
    public static int getShootTicks(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("shootTicks")) {

        	stack.getTagCompound().setInteger("shootTicks", 0);
            return 0;
        }

        return stack.getTagCompound().getInteger("shootTicks");
    }
    
    public static void setUseIB(ItemStack stack, boolean ib) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("cfgib", ib);

    }
    
    public static boolean getUseIB(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("cfgib")) {

        	stack.getTagCompound().setBoolean("cfgib", false);
            return false;
        }

        return stack.getTagCompound().getBoolean("cfgib");
    }
    
    @Override
	public int timeToUse()
	{
		return 1;
	}
	
	@Override
	public NonNullList<ItemStack> Craft() 
	{
		return null;
	}
	
	@Override
	public boolean displayRecipe()
	{
		return false;
	}
    
    public void setLastBulletBeforeShot(ItemStack stack, EntityLivingBase entity)
    {
    	if (!stack.hasTagCompound()) 
    	{
            stack.setTagCompound(new NBTTagCompound());
        }
    	else
    	{
    		stack.getTagCompound().setString("last_bullet", this.bulletUsed(stack, entity).getRegistryName().toString());
    	}
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void render(ItemStack stack, EntityPlayer entity, float partialTick)
	{
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		
		boolean flag = this.getReloading(stack);
		float progress = 0;
		
		GlStateManager.translate(0.5D, 0D, 0D);
		IBakedModel bakedBody = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/minigun/minigun.json"), "normal"));
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedBody, 0, partialTick);
		
		IBakedModel bakedClip = blockrendererdispatcher.getBlockModelShapes().getModelManager().getModel(new ModelResourceLocation(new ResourceLocation(Mathsmod.MODID, "models/item/minigun/minigun_canons.json"), "normal"));
		GlStateManager.rotate(this.getShootTicks(stack) * 8F, 0, 0, 1);
		RenderLaser.renderModelM(stack, this, this.getLayer(stack), bakedClip, 0, partialTick);
		GlStateManager.rotate(-this.getShootTicks(stack) * 8F, 0, 0, 1);
		GlStateManager.translate(-0.5D, 0D, 0D);
	}
	
	@Override
	public int getColorMultiplier(ItemStack stack, int tintIndex)
	{
		Color color = new Color(super.getColorMultiplier(stack, tintIndex));
		int blue = (int) (color.getBlue() * (1.0F - Math.min(((float) this.getShootTicks(stack)), 380.0F) / 380.0F));
		int green = (int) (color.getGreen() * (1.0F - Math.min(((float) this.getShootTicks(stack)), 380.0F) / 380.0F));
		return new Color(color.getRed(), green, blue).getRGB();
	}
}
