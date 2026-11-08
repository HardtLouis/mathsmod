package com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.translation.I18n;

public abstract class Competence {
	
	public static final List<Competence> COMPETENCES = Lists.newArrayList();

	public final String name;
	public final String ID;
	public final Competence competenceToHave;
	
	public final int points;
	
	public Competence(String nameIn, String IDIn, Competence competToUnlock, int getCompetencePoints)
	{
		name = nameIn;
		ID = IDIn;
		competenceToHave = competToUnlock;
		points = getCompetencePoints;
	}
	
	@Nullable
	public static Competence getByID(String id)
	{
		for(Competence comp : COMPETENCES)
		{
			if(comp.name.equals(id))
			{
				return comp;
			}
		}
		return null;
	}
	
	public void onAttack(EntityMateGolem entity, Entity Target, float amount)
	{
		
	}
	
	public void onUpdate(EntityMateGolem entity)
	{
		
	}
	
	public void onAttacked(EntityMateGolem entity, DamageSource source, float amount)
	{
		
	}
	
	public String getName()
	{
		return I18n.translateToLocal("comp." + name + ".name");
	}
	
	public String getDesc()
	{
		return I18n.translateToLocal("comp." + name + ".desc");
	}

	public static Competence getByNumberID(int id) 
	{
		if(id < Competence.COMPETENCES.size() && id >= 0)
		{
			return Competence.COMPETENCES.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public static int getIntIDFromStringID(String string)
	{
		return Competence.COMPETENCES.indexOf(Competence.getByID(string));
	}
	
}
