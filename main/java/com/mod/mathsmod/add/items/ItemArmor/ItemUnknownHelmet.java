package com.mod.mathsmod.add.items.ItemArmor;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemUnknownHelmet extends ItemArmor
{

	public ItemUnknownHelmet() 
	{
		super(RegisterItems.UNKNOWN, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public ItemUnknownHelmet(ArmorMaterial material) 
	{
		super(material, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}

	public ItemUnknownHelmet(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}
	
}
