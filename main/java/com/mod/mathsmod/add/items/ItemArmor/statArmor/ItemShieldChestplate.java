package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemShieldChestplate extends ItemStatMetalicArmor
{
	
	public ItemShieldChestplate() 
	{
		super(RegisterItems.SHIELD, 0, EntityEquipmentSlot.CHEST, 0.5, 1, -1, 0.96F, 1.2, 4, "shield_chestplate");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		if(player.isSneaking())
		{
			for(EntityLivingBase entity : UtilsVoids.getEntities(EntityLivingBase.class, world, player, 3))
			{
				if(entity != player)
				{
					entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 1));
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "sneak to make surrounding entities week");
    }
}
