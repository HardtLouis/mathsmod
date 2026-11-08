package com.mod.mathsmod.add.enchantments;

import com.mod.mathsmod.add.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentPower extends Enchantment
{
	
	public EnchantmentPower() 
	{
		super(Rarity.COMMON, Enchantments.GUNS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setName("power");
		this.setRegistryName("power");
	}

	public int getMinLevel()
    {
        return 1;
    }
    
    public int getMaxLevel()
    {
        return 5;
    }
    
}
