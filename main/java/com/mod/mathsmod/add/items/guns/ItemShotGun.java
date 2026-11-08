package com.mod.mathsmod.add.items.guns;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemSmasher;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public interface ItemShotGun
{
	public abstract int getBulletsSquareNumber();
	
	public abstract int stopPower();
	
	public abstract float getBuckshotDecal(EntityLivingBase entityIn, ItemStack stack);

}
