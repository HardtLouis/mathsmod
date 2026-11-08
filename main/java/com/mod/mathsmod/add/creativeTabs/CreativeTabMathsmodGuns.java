package com.mod.mathsmod.add.creativeTabs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemGunLayer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabMathsmodGuns extends CreativeTabs{

	public CreativeTabMathsmodGuns() {
		super("mathsmod_guns_tab");
		this.setBackgroundImageName("guns.png");
		
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(RegisterItems.R870);
	}

	public boolean hasSearchBar()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_)
    {
        super.displayAllRelevantItems(p_78018_1_);
        for (Item item : Item.REGISTRY)
        {
        	if(item instanceof ItemGunLayer)
        	{
        		p_78018_1_.add(new ItemStack(item));
        	}
        }
    }
	
}
