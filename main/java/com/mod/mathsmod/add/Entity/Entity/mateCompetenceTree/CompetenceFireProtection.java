package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class CompetenceFireProtection extends Competence
{

	public CompetenceFireProtection() 
	{
		super("fire_protection", "fire_protection", GolemCompetences.MORE_DAMAGES_1, 2);
	}

	public void onUpdate(EntityMateGolem entity)
	{
		if(!entity.isPotionActive(MobEffects.FIRE_RESISTANCE))
		{
			entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2000));
		}
	}
	
}
