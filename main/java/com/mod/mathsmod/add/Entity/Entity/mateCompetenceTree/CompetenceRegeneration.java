package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class CompetenceRegeneration extends Competence
{

	public CompetenceRegeneration() 
	{
		super("regeneration", "regeneration", GolemCompetences.FIRE_RESISTANCE, 3);
	}

	public void onUpdate(EntityMateGolem entity)
	{
		if(entity.getHealth() > 0)
		{
			entity.heal(0.1F);
		}
	}
	
}
