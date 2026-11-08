package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenThorium extends Molten_metal {

	public MoltenThorium(int tts, int colorIn, int harvestLvl, float attack_damagesIn, float efficiencyIn,
			int durabilityIn, float bonusReachIn) {
		super(tts, colorIn, harvestLvl, attack_damagesIn, efficiencyIn, durabilityIn, bonusReachIn, new float[]{12, 15, 18, 12});
	}
	

	
	public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
	{
		if(part == 0 && type == 0)
		{
			if(entity instanceof EntityLivingBase)
			{
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1));
			}
		}
	}
	
	public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
	{
	}
	
	public float explosionMultiplier()
	{
		return 0.94F;
	}

	public float bonusHealth()
	{
		return 5.0F;
	}
}