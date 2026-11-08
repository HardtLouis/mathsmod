package com.mod.mathsmod.add.crafting.molten_metals;

import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.functions.Smelt;

public class MoltenKurayum extends Molten_metal {

	public MoltenKurayum(int tts, int colorIn, int harvestLvl, float attack_damagesIn, float efficiencyIn,
			int durabilityIn, float bonusReachIn) {
		super(tts, colorIn, harvestLvl, attack_damagesIn, efficiencyIn, durabilityIn, bonusReachIn, new float[]{8, 10, 12, 8});
	}
	

	
	public void effectOnHit(int part, int type, World world, EntityLivingBase attacker, Entity entity, ItemStack stack)
	{
		if(part == 0 && type == 0)
		{
			entity.setFire(5);
		}
	}
	
	public void effectOnBreakBlock(int part, int type, World world, EntityLivingBase user, BlockPos pos, ItemStack stack)
	{
		
	}
	
	public float bonusHealth()
	{
		return 2.0F;
	}

}
