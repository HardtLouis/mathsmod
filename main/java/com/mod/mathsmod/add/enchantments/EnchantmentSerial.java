package com.mod.mathsmod.add.enchantments;

import com.mod.mathsmod.add.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentSerial extends Enchantment
{

	public EnchantmentSerial() 
	{
		super(Rarity.COMMON, com.mod.mathsmod.add.Enchantments.GUNS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("serial");
		this.setRegistryName("serial");
	}

	public int getMinLevel()
    {
        return 1;
    }
    
    public int getMaxLevel()
    {
        return 1;
    }
    
    protected boolean canApplyTogether(Enchantment ench)
    {
        return this != ench && ench != Enchantments.POWER;
    }
}