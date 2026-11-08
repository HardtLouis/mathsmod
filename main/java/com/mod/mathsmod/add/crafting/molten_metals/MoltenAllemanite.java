package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class MoltenAllemanite extends Molten_metal
{

	public MoltenAllemanite() 
	{
		super(90, 16729670, 4, 3.5F, 12.0F, 900, 0.2F, new float[] { 2.5F, 5F, 7F, 3.5F });
	}
	
	public void onWearerHurt(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot, DamageSource source)
	{
		if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase)
		{
			((EntityLivingBase) source.getTrueSource()).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 10));
		}
	}

}
