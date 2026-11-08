package com.mod.mathsmod.add.items.ItemArmor.wolf;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWolfTechArmor extends ItemWolfArmor
{

	public ItemWolfTechArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}
	
	@Override
	public void onWolfArmorTick(ItemStack stack, World worldIn, EntityWolf entityIn)
	{
		entityIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
		entityIn.fallDistance = 0;
		if(entityIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == this
		&& entityIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == this
		&& entityIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == this
		&& entityIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == this)
		{
			//entityIn.setEntityInvulnerable(true);
		}
		else
		{
			//entityIn.setEntityInvulnerable(false);
		}
	}

}
