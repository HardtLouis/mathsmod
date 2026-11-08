package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenSilver extends Molten_metal {

	public MoltenSilver(int tts, int colorIn, int harvestLvl, float attack_damagesIn, float efficiencyIn,
			int durabilityIn, float bonusReachIn) {
		super(tts, colorIn, harvestLvl, attack_damagesIn, efficiencyIn, durabilityIn, bonusReachIn, new float[]{4, 7, 9, 4});
	}
	

	
	public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
	{
		if(part == 0 && type == 0)
		{
			attacker.heal(1.5F);
		}
	}
	
	public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
	{
	}

}