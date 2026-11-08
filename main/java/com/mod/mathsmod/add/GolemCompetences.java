package com.mod.mathsmod.add;

import com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree.*;

public class GolemCompetences 
{
	public static final Competence MORE_DAMAGES_1 = new MoreDamagesCompetence(0);
	public static final Competence MORE_DAMAGES_2 = new MoreDamagesCompetence(1);
	public static final Competence MORE_DAMAGES_3 = new MoreDamagesCompetence(2);
	public static final Competence MORE_DAMAGES_4 = new MoreDamagesCompetence(3);
	public static final Competence MORE_DAMAGES_5 = new MoreDamagesCompetence(4);
	public static final Competence THORNS = new CompetenceThorns();
	public static final Competence FIRE_RESISTANCE = new CompetenceFireProtection();
	public static final Competence REGEN = new CompetenceRegeneration();
	public static final Competence AUTO_XP = new CompetenceAutoXP();
	
	public static void registerGolemCompetence()
	{
		Competence.COMPETENCES.add(MORE_DAMAGES_1);
		Competence.COMPETENCES.add(MORE_DAMAGES_2);
		Competence.COMPETENCES.add(MORE_DAMAGES_3);
		Competence.COMPETENCES.add(MORE_DAMAGES_4); 
		Competence.COMPETENCES.add(MORE_DAMAGES_5);
		Competence.COMPETENCES.add(THORNS);
		Competence.COMPETENCES.add(FIRE_RESISTANCE);
		Competence.COMPETENCES.add(REGEN);
		Competence.COMPETENCES.add(AUTO_XP);
	}
}
