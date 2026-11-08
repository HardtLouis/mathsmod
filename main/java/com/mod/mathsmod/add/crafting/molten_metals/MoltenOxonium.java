package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class MoltenOxonium extends Molten_metal
{

	public MoltenOxonium() 
	{
		super(400, 6579455, 4, 4.0F, 6.0F, 300, 0.2F, new float[] { 2, 5, 7, 3 });
	}
	
	public void onWearerHurt(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot, DamageSource source)
	{
		if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase)
		{
			((EntityLivingBase) source.getTrueSource()).getHeldItemMainhand().damageItem(1, (EntityLivingBase) source.getTrueSource());
		}
	}

}
