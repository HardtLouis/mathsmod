package com.mod.mathsmod.add.creativeTabs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CreativeTabMetalurgy extends CreativeTabs
{

	public CreativeTabMetalurgy() {
		super("mathsmod_metalurgy_tab");
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(RegisterItems.THERMORESISTANT_BUCKET);
	}

	public boolean hasSearchBar()
    {
        return true;
    }
}
	
	