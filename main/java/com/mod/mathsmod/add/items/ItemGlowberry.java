package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.recipe.RecipeGlowberryFood;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemGlowberry extends ItemFood
{
	public static final IRecipe GLOWBERRY = new RecipeGlowberryFood().setRegistryName("glowberry_trap");

	public ItemGlowberry()
	{
		super(1, false);
		this.setRegistryName("glowberry").setUnlocalizedName("glowberry");
		this.setAlwaysEdible();
		this.setPotionEffect(new PotionEffect(MobEffects.GLOWING, 100), 1.0F);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "can be eaten or used in a crafting table with another food");
		tooltip.add(TextFormatting.YELLOW + "item trap it (make a player glow on consumption)");
    }
}
