package com.mod.mathsmod.add.items.ItemArmor.statArmor;

import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemArmor.ItemStatArmor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemWarriorChestplate extends ItemStatMetalicArmor
{

	public ItemWarriorChestplate() 
	{
		super(RegisterItems.WARRIOR, 0, EntityEquipmentSlot.CHEST, 0, 1, -1, 0.974F, 1.3, 3, "warrior_chestplate");
	}

	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox()))
		{
			if(entity instanceof EntityLivingBase && (player.motionX != 0 || player.motionZ != 0))
			{
				if(entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 8))
				{
					((EntityLivingBase) entity).knockBack(player, 2, player.posX - entity.posX, player.posZ - entity.posZ);
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "make you able to charge entities");
    }
}
