package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelDirectTit;
import com.mod.mathsmod.add.items.ItemArmor.models.model_jetpack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKurayumJetpack extends ItemExplosiveBelt implements IBulletProtection
{
	public ItemKurayumJetpack() 
	{
		super();
		this.setUnlocalizedName("kurayum_jetpack").setRegistryName("kurayum_jetpack");
		this.setMaxDamage(300);
	}
	
	public ItemKurayumJetpack(byte e) 
	{
		super();
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/kurayum_jetpack.png";
    }
	
	public void onExplUsed(ItemStack stack, World world, EntityLivingBase entity)
	{
		if(entity instanceof EntityPlayer)
		{
			if(this.getUsing(stack))
			{
				if(this.getUsing(stack) && entity instanceof EntityPlayer)
				{
					for(Item item : Mathsmod.GUNS_LIST)
					{
						((EntityPlayer) entity).getCooldownTracker().setCooldown(item, 40);
					}
				}
			}
			
			EntityPlayer player = (EntityPlayer) entity;
			if(!player.getCooldownTracker().hasCooldown(this))
			{
				player.getCooldownTracker().setCooldown(this, 3);
				this.setUsing(stack, !this.getUsing(stack));
			}
		}
	}
	
	@Override
	public int getDamage(ItemStack stack)
    {
        return Math.min(super.getDamage(stack) , this.getMaxDamage() - 1);
    }
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		model_jetpack model = new model_jetpack(_default, 0.5F);
		
		model.Shape1.showModel = true;
		model.Shape2.showModel = true;
		model.Shape3.showModel = true;
		model.Shape4.showModel = true;
		model.bipedBody.showModel = true;
		
        return model;
    }
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(this.isDamaged(stack))
		{
			if(entityIn.onGround)
			{

				if(this.getUsing(stack) && entityIn instanceof EntityPlayer)
				{
					for(Item item : Mathsmod.GUNS_LIST)
					{
						((EntityPlayer) entityIn).getCooldownTracker().setCooldown(item, 40);
					}
				}
				
				this.setUsing(stack, false);
				
				if(this.isDamaged(stack))
				{
					if(this.getTimer(stack) >= 20)
					{
						stack.setItemDamage(stack.getItemDamage() - 4);
					}
					else
					{
						this.setTimer(stack, this.getTimer(stack) + 1);
					}
				}
			}
			else
			{
				this.setTimer(stack, 0);
			}
		}
    }
	
	public void onUse(EntityLivingBase entity, ItemStack stack)
	{
	}
	
	public int getTimer(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            return 0;
        }

        if (!stack.getTagCompound().hasKey("timer")) {

            return 0;
        }

        return stack.getTagCompound().getInteger("timer");
    }
	
	public void setTimer(ItemStack stack, int timer) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setInteger("timer", timer);
    }
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
		if(this.isDamaged(stack))
		{
			if(player.onGround)
			{
				this.setUsing(stack, false);
				if(this.isDamaged(stack))
				{
					if(this.getTimer(stack) >= 20)
					{
						stack.setItemDamage(stack.getItemDamage() - 5);
					}
					else
					{
						this.setTimer(stack, this.getTimer(stack) + 1);
					}
				}
			}
			else
			{
				this.setTimer(stack, 0);
			}
		}
		
		if(this.getUsing(stack) && this.getDamage(stack) < this.getMaxDamage() - 1)
		{
			player.fallDistance = (float) -player.motionY * 10;
			Vec3d vec = UtilsVoids.laser(0.7F, player.rotationYaw, player.rotationPitch);
			player.motionX = vec.x;
			player.motionY = vec.y;
			player.motionZ = vec.z;
			player.world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + vec.x, player.posY + 1.4D, player.posZ + vec.z, 0, -0.1D, 0);
			stack.damageItem(1, (EntityPlayer) player);
			player.world.playSound((EntityPlayer) null, player.posX + vec.x, player.posY + 1.4D, player.posZ + vec.z, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 0.2F, 1);
			for(Entity entity : world.getEntitiesInAABBexcluding(player, player.getEntityBoundingBox().grow(0.1), null))
			{
				if(entity instanceof EntityLivingBase)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player).setDamageBypassesArmor(), 6);
					((EntityLivingBase)entity).knockBack(player, (float)3, (double)MathHelper.sin(player.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
				}
			}
			
			player.eyeHeight = 0.5F;
		}
		
		else if(this.getDamage(stack) == this.getMaxDamage() - 1)
		{
			this.setUsing(stack, false);
		}
    }
	
	public static boolean getUsing(ItemStack stack)
	{

        if (!stack.hasTagCompound()) {

            return false;
        }

        if (!stack.getTagCompound().hasKey("using")) {

            return false;
        }

        return stack.getTagCompound().getBoolean("using");
    }
	
	public static void setUsing(ItemStack stack, boolean using) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
        
        stack.getTagCompound().setBoolean("using", using);
    }
	

    
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        return HashMultimap.<String, AttributeModifier>create();
    }

    @Override
	public float getBulletMultiplier() 
	{
		return 1.0F;
	}
    
    @Override
	public float getBulletMultiplier(ItemStack stack, EntityLivingBase user) 
	{
		return this.getUsing(stack) ? 10.0F : 1.0F;
	}
}