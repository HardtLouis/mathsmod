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

public class ItemAttackerLeggings extends ItemStatMetalicArmor
{

	public ItemAttackerLeggings()
	{
		super(RegisterItems.ATTACKER, 0, EntityEquipmentSlot.LEGS, 1, 0, 0.5, 1, 1, 3, "attacker_leggings");
	}
	
	@Override
	protected void Tick(World world, EntityPlayer player, ItemStack itemStack) 
	{
		super.Tick(world, player, itemStack);
		
		for(Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox()))
		{
			if(entity != null && entity != player)
			{
				if(entity instanceof EntityLivingBase && !((EntityLivingBase) entity).isPotionActive(MobEffects.WITHER))
				{
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 1));
				}
			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GREEN + "gives weakness to collided entities");
    }

}
