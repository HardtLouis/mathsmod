package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenNightShard extends Molten_metal {

	public MoltenNightShard(int tts, int colorIn, int harvestLvl, float attack_damagesIn, float efficiencyIn,
			int durabilityIn, float bonusReachIn) {
		super(tts, colorIn, harvestLvl, attack_damagesIn, efficiencyIn, durabilityIn, bonusReachIn, new float[]{2.5F, 5.5F, 6.5F, 2.5F});
	}
	

	
	public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
	{
		if(part == 0 && type == 0)
		{
			if(entity.isBurning())
			{
				entity.extinguish();
				attacker.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 1));
			}
		}
	}
	
	public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
	{
	}
	
	public float bonusHealth()
	{
		return 1.0F;
	}
}