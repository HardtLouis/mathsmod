package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.util.DamageSource;

public class CompetenceThorns extends Competence
{

	public CompetenceThorns() 
	{
		super("thorns", "thorns", GolemCompetences.MORE_DAMAGES_3, 5);
	}

	public void onAttacked(EntityMateGolem entity, DamageSource source, float amount)
	{
		if(source.getTrueSource() != null && !source.isProjectile() && !source.isExplosion())
		{
			source.getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(entity), 2 + entity.getLevel() / 50);
		}
	}
	
}
