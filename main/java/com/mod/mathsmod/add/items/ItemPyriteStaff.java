package com.mod.mathsmod.add.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHarpoon;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPyriteStaff extends Item
{
	public ItemPyriteStaff()
	{
		this.setRegistryName("pyrite_staff");
		this.setUnlocalizedName("pyrite_staff");
		this.setMaxStackSize(1);
		this.setCreativeTab(Mathsmod.MATHSMOD_TAB);
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "on right click, gives a little velocity boost");
    }
		
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		BlockPos a = new BlockPos(playerIn).down();
		playerIn.motionY = 1.2F;
		playerIn.motionX *= 2F;
		playerIn.motionZ *= 2F;
		
		playerIn.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 0, 100000));
		
		playerIn.getCooldownTracker().setCooldown(this, 60);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }
}