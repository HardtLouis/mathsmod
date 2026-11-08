package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemAggressiveChestplate extends ItemStatMetalicArmor
{
	
	public ItemAggressiveChestplate()
	{
		super(RegisterItems.AGGRESSIVE, 0, EntityEquipmentSlot.CHEST, 3, -2, -1, 1.2F, 1, 5, "aggressive_chestplate");
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		if(player.isSneaking() && player.getHealth() < 6F)
		{
			EntityLivingBase target = null;
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, player, 10))
			{
				if(entity != null && entity != player)
				{
					if(target == null)
					{
						target = entity;
					}
					else
					{
						if(target.getDistance(player) > entity.getDistance(player))
						{
							target = entity;
						}
					}
				}
			}
			if(target != null)
			{
				UtilsVoids.setLookEntity(target, player);
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "when below 3 hearts, sneak to have an aimbot");
    }
}
