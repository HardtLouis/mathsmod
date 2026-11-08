package com.mod.mathsmod.add;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.mod.mathsmod.add.spell.*;

public class SpellRegistry 
{
	public static final HashMap<String, SpellBase> REGISTRY = Maps.<String, SpellBase>newHashMap();
	
	public static SpellBase BLACK_SPHERE = new SpellBlackSphere();
	public static SpellBase DISINTEGRATING_HAND = new SpellDisintegratingHand();
	public static SpellBase ICY_JAVELIN = new SpellIceAttack();
	//public static SpellBase SUPER_PUNCH = new SpellSuperPunch();
	public static SpellBase BLUE_FLAMES = new SpellBlueFlames();
	public static SpellBase GUN_GOES_BOOM = new SpellGun();
	public static SpellBase MOB_MANIPULATION = new SpellMobManipulation();
	public static SpellBase FEUR_ADAPTATION = new SpellFeurAdaptation();
	public static SpellBase SILVERFISH = new SpellSilverfish();
	public static SpellBase BOAT = new SpellBoat();
	public static SpellBase MAGIC_REGENERATION = new SpellMagicRegeneration();
	
	public static void registerAll()
	{
		SpellRegistry.registerSpell(BLACK_SPHERE);
		SpellRegistry.registerSpell(DISINTEGRATING_HAND);
		SpellRegistry.registerSpell(ICY_JAVELIN);
		//SpellRegistry.registerSpell(SUPER_PUNCH);
		SpellRegistry.registerSpell(BLUE_FLAMES);
		SpellRegistry.registerSpell(GUN_GOES_BOOM);
		SpellRegistry.registerSpell(MOB_MANIPULATION);
		SpellRegistry.registerSpell(FEUR_ADAPTATION);
		SpellRegistry.registerSpell(SILVERFISH);
		SpellRegistry.registerSpell(BOAT);
		SpellRegistry.registerSpell(MAGIC_REGENERATION);
	}
	
	public static void registerSpell(SpellBase spell)
	{
		if(REGISTRY.containsKey(spell.spellName()))
		{
			System.out.print("duplicate spell with name " + spell.spellName() + "\n");
		}
		else
		{
			REGISTRY.put(spell.spellName(), spell);
		}
	}
}
