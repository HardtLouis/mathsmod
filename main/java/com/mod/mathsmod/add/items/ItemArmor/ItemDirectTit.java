package com.mod.mathsmod.add.items.ItemArmor;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.models.ModelDirectTit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDirectTit extends ItemArmor
{
	public ItemDirectTit(String name) 
	{
		super(RegisterItems.JETPACK_ARMOR, 0, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setUnlocalizedName(name).setRegistryName(name);
		this.setMaxDamage(0);
	}
	
	@SideOnly(Side.CLIENT)
    @Nullable
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default)
    {
		return new ModelDirectTit(0.1F);
    }
	
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
		return Mathsmod.MODID + ":textures/models/armor/direct_tit.png";
    }
}
