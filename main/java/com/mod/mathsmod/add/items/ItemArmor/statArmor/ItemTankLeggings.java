package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemTankLeggings extends ItemStatMetalicArmor
{
	
	public ItemTankLeggings()
	{
		super(RegisterItems.TANK, 0, EntityEquipmentSlot.LEGS, 0, 2, -2, 0.974F, 0.6F, 3, "tank_leggings");
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		if(player.isSneaking())
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 1));
			player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 0, 1));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "sneak to activate give yourself resistance and slowness");
    }
}
