package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAttackerChestplate extends ItemStatMetalicArmor
{

	public ItemAttackerChestplate()
	{
		super(RegisterItems.ATTACKER, 0, EntityEquipmentSlot.CHEST, 1, 0, -1, 1, 1.3, 4, "attacker_chestplate");
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, player, 2))
		{
			if(entity != player)
			{
				entity.setFire(6);
				if(entity.isPotionActive(MobEffects.FIRE_RESISTANCE))
				{
					entity.removePotionEffect(MobEffects.FIRE_RESISTANCE);
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "burn surrounding entities, remove fire resistant if active");
    }
}
