package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPlayerTracker extends Item
{
	public ItemPlayerTracker()
	{
		this.setMaxStackSize(1);
		this.setRegistryName("player_tracker").setUnlocalizedName("player_tracker").setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "track a player in a 60 blocks radius");
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		playerIn.openGui(Mathsmod.instance, 15, worldIn, 0, 0, 0);
		return  new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
