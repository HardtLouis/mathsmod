package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.bulletproof_vest_model;
import com.mod.mathsmod.add.items.ItemArmor.models.model_jetpack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemJetpack extends ItemArmor implements IBulletProtection 
{
	
	private double motionY;
	private int reloadSpeed;

	public ItemJetpack(String name, double yBoost, int reloadSpeed) 
	{
		super(RegisterItems.JETPACK_ARMOR, 0, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.motionY = yBoost;
		this.reloadSpeed = reloadSpeed;
		this.setUnlocalizedName(name).setRegistryName(name);
		this.setMaxDamage(100);
	}

	@Override
	public float getBulletMultiplier() 
	{
		return 1.2F;
	}
	
	public double getYBoost()
	{
		return this.motionY;
	}
	
	public boolean isDamageable()
    {
        return false;
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
				if(this.isDamaged(stack))
				{
					if(this.getTimer(stack) >= 20)
					{
						stack.setItemDamage(stack.getItemDamage() - reloadSpeed);
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
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        return HashMultimap.<String, AttributeModifier>create();
    }
	
	public void setDamage(ItemStack stack, int damage)
    {
        if(damage >= this.getMaxDamage())
        {
        	damage = this.getMaxDamage();
        }
        
        super.setDamage(stack, damage);
    }
	
	public int damageItemOnShoot()
	{
		return 100;
	}

}
