package com.mod.mathsmod.add.crafting.molten_metals;

import java.awt.Color;

import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MoltenEnderite extends Molten_metal 
{

	public MoltenEnderite()
	{
		super(800, new Color(156, 0, 156).getRGB(), 6, 5.0F, 12.0F, 3000, 0.4F, new float[]{4.5F, 7.5F, 9.5F, 4.5F});
	}
	
	public void onWearerHurt(ItemStack stack, EntityLivingBase entity, EntityEquipmentSlot slot, DamageSource source)
	{
		if(source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase && !(source.getTrueSource() instanceof EntityEndermite))
		{
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100));
			EntityEndermite mite = new EntityEndermite(entity.world);
			mite.setPosition(entity.posX, entity.posY, entity.posZ);
			mite.addPotionEffect(new PotionEffect(RegisterPotions.INVUL, 20));
			mite.setAttackTarget((EntityLivingBase) source.getTrueSource());
			if(!entity.world.isRemote)
			{
				entity.world.spawnEntity(mite);
			}
		}
	}
}