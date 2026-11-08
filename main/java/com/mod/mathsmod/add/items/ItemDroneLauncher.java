package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.GUI.screen.guiLaunchDrone;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.World.dims.teleporter.CopravegTeleporter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDroneLauncher extends Item
{
	public ItemDroneLauncher()
	{
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("drone_launcher");
		this.setRegistryName("drone_launcher");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(player.isSneaking() && hand == EnumHand.MAIN_HAND)
		{
			player.openGui(Mathsmod.MODID, 11, worldIn, 0, 0, 0);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.YELLOW + "right click to open an interface that make you able");
		tooltip.add(TextFormatting.YELLOW + "to launch an explosive drone to coords");
	}
	
}
