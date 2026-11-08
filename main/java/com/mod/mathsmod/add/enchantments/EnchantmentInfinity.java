package com.mod.mathsmod.add.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentInfinity extends Enchantment
{

	public EnchantmentInfinity() 
	{
		super(Rarity.UNCOMMON, com.mod.mathsmod.add.Enchantments.GUNS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("infinity");
		this.setRegistryName("infinity");
	}

	public int getMinLevel()
    {
        return 1;
    }
    
    public int getMaxLevel()
    {
        return 1;
    }
}
