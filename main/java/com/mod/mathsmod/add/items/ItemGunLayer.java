package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.renderer.IGoldenWeapon;
import com.mod.mathsmod.add.items.guns.renderer.RecipeGunLayered;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class ItemGunLayer extends Item
{
	
	public static final IRecipe LAYERED = new RecipeGunLayered().setRegistryName("layered_guns");
	
	public ItemGunLayer(String string)
	{
		this.setMaxStackSize(32);
		this.setUnlocalizedName(string);
		this.setRegistryName(string);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "when put into a crafting table with a gun,");
		tooltip.add(TextFormatting.YELLOW + "will change its texture");
    }
	
	public abstract ResourceLocation getLayer();
}
