package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIssouDisk extends ItemRecord
{
	
	public ItemIssouDisk() {
		super("issou", MathsSounds.ISSOU);
		this.setRegistryName("issou_disk");
		this.setUnlocalizedName("issou_disk");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public EnumRarity getRarity(ItemStack stack)
    {
        return RegisterItems.FRENCH;
    }

}