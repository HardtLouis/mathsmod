package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.GUI.screen.guiInstructionBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemInstructionBook extends Item
{
	public ItemInstructionBook()
	{
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("instruction_book");
		this.setRegistryName("instruction_book");
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + " I have a big flemme to update it");
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(hand == EnumHand.MAIN_HAND)
		{
			player.openGui(Mathsmod.instance, 12, worldIn, 0, 0, 0);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }
	
}