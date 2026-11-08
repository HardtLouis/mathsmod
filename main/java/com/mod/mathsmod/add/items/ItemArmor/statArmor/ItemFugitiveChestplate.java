package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFugitiveChestplate extends ItemStatMetalicArmor
{

	public ItemFugitiveChestplate() 
	{
		super(RegisterItems.FUGITIVE, 0, EntityEquipmentSlot.CHEST, 0, 1, -1, 0.987F, 1.1, 5, "fugitive_chestplate");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox()))
		{
			if(entity instanceof EntityLivingBase)
			{
				if(!((EntityLivingBase) entity).isPotionActive(MobEffects.BLINDNESS))
				{
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40));
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "blind entities on collision");
    }
}
