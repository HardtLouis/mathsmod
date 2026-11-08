package com.mod.mathsmod.add.items.ItemArmor.wolf;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.World;

public class ItemWolfKurayumArmor extends ItemWolfArmor
{

	public ItemWolfKurayumArmor(EntityEquipmentSlot equipmentSlotIn) {
		super(RegisterItems.KURAYUM_ARMOR, 0, equipmentSlotIn);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	@Override
	public void onWolfArmorTick(ItemStack stack, World worldIn, EntityWolf entityIn)
	{
		entityIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
		entityIn.fallDistance = 0;
	}
	 
}
