package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelThoriumArmor;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemUberArmor extends ItemStatArmor implements IExplosionProtection
{

	public ItemUberArmor(EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(RegisterItems.UBER, 0, equipmentSlotIn, 100, 10, 2F, 20, 0F, 1, 6, name);
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		int i = 0;
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
		{
			player.capabilities.allowFlying = true;
			
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3, 1));
			if(player.isInWater())
			{
				player.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 0)));
			}
			
			if(player.getFoodStats().getFoodLevel() <= 6)
			{
				player.getFoodStats().setFoodLevel(40);
			}
		}
		else
		{
			if(player.capabilities.isFlying && !player.capabilities.allowFlying)
			{
				player.capabilities.isFlying = false;
			}
			player.capabilities.allowFlying = false;
		}
		
		player.fallDistance = 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	@Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		if(entity instanceof EntityLivingBase)
		{
			EntityLivingBase player = (EntityLivingBase) entity;
			if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
					player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
			{
				boolean leg = slot == EntityEquipmentSlot.LEGS;
				int sl = leg ? 2 : 1;
				int i = (int) ((Minecraft.getSystemTime()) / 50) % 15;
		        return Mathsmod.MODID + ":textures/models/armor/uber/uber_layer_" + sl + "_" + i + ".png";
			}
			else
			{
				boolean leg = slot == EntityEquipmentSlot.LEGS;
				int sl = leg ? 2 : 1;
				return Mathsmod.MODID + ":textures/models/armor/uber/uber_layer_" + sl + "_0.png";
			}
		}
		else
		{
			boolean leg = slot == EntityEquipmentSlot.LEGS;
			int sl = leg ? 2 : 1;
			return Mathsmod.MODID + ":textures/models/armor/uber/uber_layer_" + sl + "_0.png";
		}
		
		
    }
	
	@SuppressWarnings("incomplete-switch")
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		ModelThoriumArmor chest = new ModelThoriumArmor(0.5F, _default);
		ModelThoriumArmor legs = new ModelThoriumArmor(0.25F, _default);
		
		switch(armorSlot)
		{
			case CHEST:
				chest.slot = armorSlot;
				return chest;
			case FEET:
				chest.slot = armorSlot;
				return chest;
			case HEAD:
				chest.slot = armorSlot;
				return chest;
			case LEGS:
				legs.slot = armorSlot;
				return legs;
		}
		
		return chest;
    }
	
	public int getLaser(ItemStack stack)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return stack.getTagCompound().getInteger("laser");
	}
	
	public void setLaser(ItemStack stack, int laser)
	{
		if(!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setInteger("laser", laser);
	}
	
	public void onLeftClickEmptyHand(EntityLivingBase entity, Item item, ItemStack stack, boolean stop)
	{
		if(stop)
		{
			this.setLaser(stack, 0);
		}
		
		if(entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
			entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
		{
			this.setLaser(stack, this.getLaser(stack) + 1);
			
			if(this.getLaser(stack) >= 100)
			{
				entity.motionX *= 0.01F;
				entity.motionY *= 0.01F;
				entity.motionZ *= 0.01F;
				
				entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 10, 1);
				
				for(float i = 0; i <= 20; i += 0.25F)
				{
					for(Entity entityIn : UtilsVoids.getHitEntities(entity.world, entity, UtilsVoids.laser(i, entity.rotationYaw, entity.rotationPitch).addVector(entity.posX, entity.posY + entity.height * 0.65, entity.posZ), 20))
					{
						if(entityIn instanceof EntityLivingBase && entityIn != entity)
						{
							UtilsVoids.attackEntityFrom((EntityLivingBase) entityIn, DamageSource.causeMobDamage(entity), 2);
							i += entityIn.width + 0.25F;
						}
					}
				}
			}
			else
			{
				entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.AMBIENT, 10, 1);
			}
		}
	}

	@Override
	public float explosionMultiplier(EntityLivingBase entity, Item item, ItemStack stack)
	{
		if(entity != null && entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
				entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
		{
			return 0;
		}
		else
		{
			return 0.85F;
		}
	}
}
