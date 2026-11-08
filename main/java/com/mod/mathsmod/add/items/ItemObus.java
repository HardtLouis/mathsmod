package com.mod.mathsmod.add.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityObus;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemObus extends ItemMissile
{

	public ItemObus() {
		super("obus");
	}

	@Override
	public EntityMissile PlaneMissile(EntityPlane plane) {
		EntityObus obus = new EntityObus(plane.world);
		obus.setPosition(plane.posX, plane.posY - 1, plane.posZ);
		return obus;
	}
	
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.YELLOW + "have to be placed on a plane to be used");
    }

}
