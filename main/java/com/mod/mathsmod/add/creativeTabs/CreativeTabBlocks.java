package com.mod.mathsmod.add.creativeTabs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CreativeTabBlocks extends CreativeTabs
{

	public CreativeTabBlocks() {
		super("unbreakable_copies");
		this.setBackgroundImageName(Mathsmod.MODID +".png");
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("minecraft:grass")));
	}

	public boolean hasSearchBar()
    {
        return true;
    }
	
	
	
}
