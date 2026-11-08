package com.mod.mathsmod.add.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;

public class EnchantmentSmelt extends Enchantment {

	public EnchantmentSmelt() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("smelt");
		this.setRegistryName("smelt");
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
