package com.mod.mathsmod.add.items.ItemArmor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemUseableArmors extends ItemArmor
{

	public ItemUseableArmors(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	public abstract void onChestUsed(ItemStack stack, World world, EntityLivingBase entity);
}
