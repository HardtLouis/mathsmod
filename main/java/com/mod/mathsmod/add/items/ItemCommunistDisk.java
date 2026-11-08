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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCommunistDisk extends ItemRecord
{
	
	public ItemCommunistDisk() {
		super("communist", MathsSounds.COMMUNIST_HYMN);
		this.setRegistryName("communist_disk");
		this.setUnlocalizedName("communist_disk");
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TextFormatting.DARK_RED + I18n.translateToLocal("communist.sym") + " Stalin loves you " + I18n.translateToLocal("communist.sym"));
    }
	
	public EnumRarity getRarity(ItemStack stack)
    {
        return RegisterItems.COMMUNIST;
    }

}
