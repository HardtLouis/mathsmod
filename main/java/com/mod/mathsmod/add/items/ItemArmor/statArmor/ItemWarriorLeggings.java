package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemWarriorLeggings extends ItemStatMetalicArmor
{

	public ItemWarriorLeggings() 
	{
		super(RegisterItems.WARRIOR, 0, EntityEquipmentSlot.LEGS, 1, 0.5, 0.5, 1F, 1.3, 3, "warrior_leggings");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		if(player.getHealth() < 15 && player.getHealth() > 7.5F)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH));
		}
		if(player.getHealth() <= 7.5F)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1, 1));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "gives you strength 1 then 2 as you lose health");
    }
}
