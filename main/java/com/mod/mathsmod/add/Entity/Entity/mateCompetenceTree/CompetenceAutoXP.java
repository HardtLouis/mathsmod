package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import com.mod.mathsmod.add.GolemCompetences;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class CompetenceAutoXP extends Competence
{

	public CompetenceAutoXP() 
	{
		super("auto_XP", "auto_XP", GolemCompetences.REGEN, 5);
	}

	public void onUpdate(EntityMateGolem entity)
	{
		if(entity.getRNG().nextInt(20) == 0 && !entity.world.isRemote)
		{
			entity.addXp(10);
		}
	}
	
}