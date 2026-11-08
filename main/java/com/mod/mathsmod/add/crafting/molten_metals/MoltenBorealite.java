package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenBorealite extends Molten_metal {

	public MoltenBorealite(int tts, int colorIn, int harvestLvl, float attack_damagesIn, float efficiencyIn,
			int durabilityIn, float bonusReachIn)
	{
		super(tts, colorIn, harvestLvl, attack_damagesIn, efficiencyIn, durabilityIn, bonusReachIn, new float[]{13.4F, 16.4F, 19.4F, 13.4F});
	}
	

	
	public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
	{
		if(part == 0 && type == 0)
		{
			attacker.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 2));;
		}
	}
	
	public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
	{
	}
	
	public float explosionMultiplier()
	{
		return 0.92F;
	}
	
	public float bonusHealth()
	{
		return 5.0F;
	}
}