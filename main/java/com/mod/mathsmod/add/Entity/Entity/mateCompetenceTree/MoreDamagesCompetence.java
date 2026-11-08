package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class MoreDamagesCompetence extends Competence
{

	int level;
	
	public MoreDamagesCompetence(int levelIn) 
	{
		super("more_damages_" + levelIn, "more_damages_" + levelIn, levelIn == 0 ? null : levelIn == 1 ? GolemCompetences.MORE_DAMAGES_1 : levelIn == 2 ? GolemCompetences.MORE_DAMAGES_2 : levelIn == 3 ? GolemCompetences.MORE_DAMAGES_3 : GolemCompetences.MORE_DAMAGES_4, 2 + levelIn);
		
		level = levelIn;
	}

	public void onUpdate(EntityMateGolem entity)
	{
	}
	
	@Override
	public void onAttack(EntityMateGolem entity, Entity Target, float amount)
	{
		amount *= 1 + (level + 1) / 10;
	}
}
