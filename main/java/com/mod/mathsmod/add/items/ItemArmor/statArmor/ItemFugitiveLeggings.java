package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFugitiveLeggings extends ItemStatMetalicArmor
{

	public ItemFugitiveLeggings() 
	{
		super(RegisterItems.FUGITIVE, 0, EntityEquipmentSlot.LEGS, 0, 0.5, 0.5, 1, 1.5, 5, "fugitive_leggings");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		if(!player.isSneaking())
		{
			if(world.getBlockState(new BlockPos(player.posX, player.posY - 0.25, player.posZ)).getMaterial() == Material.WATER)
			{
				player.fallDistance = 0;
				player.onGround = true;
				if(player.isInWater())
				{
					player.motionY = 0.2F;
				}
				else
				{
					player.motionY = 0F;
				}
			}
		}
		
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "make you able to walk on water");
    }
}
