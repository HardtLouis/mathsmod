package com.mod.mathsmod.add.creativeTabs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Enchantments;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabMathsmod extends CreativeTabs
{

	public CreativeTabMathsmod() {
		super("mathsmod_tab");
		this.setBackgroundImageName(Mathsmod.MODID +".png");
		this.setRelevantEnchantmentTypes(new EnumEnchantmentType[] {Enchantments.GUNS, Enchantments.ROCKET_LAUNCHERS});
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(Item.REGISTRY.getObject(new ResourceLocation("mathsmod:tfosium_block")));
	}

	public boolean hasSearchBar()
    {
        return false;
    }
	
	
	
}
