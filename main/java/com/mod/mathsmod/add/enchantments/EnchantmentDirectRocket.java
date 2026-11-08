package com.mod.mathsmod.add.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentDirectRocket extends Enchantment
{

	public EnchantmentDirectRocket() 
	{
		super(Rarity.COMMON, com.mod.mathsmod.add.Enchantments.ROCKET_LAUNCHERS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("direct");
		this.setRegistryName("direct");
	}

	public int getMinLevel()
    {
        return 1;
    }
    
    public int getMaxLevel()
    {
        return 2;
    }
}