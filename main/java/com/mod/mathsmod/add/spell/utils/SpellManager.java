package com.mod.mathsmod.add.spell.utils;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.messages.MessageUpdateUserSpells;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.utils.TwoIntAndString;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class SpellManager 
{
	private static Random rand = new Random();
	
	public static void giveRandomSpells(EntityPlayer player)
	{
		NBTTagCompound tag = player.getEntityData();
		List<SpellBase> list = Lists.newArrayList();
		
		if(tag != null)
		{
			for(int i = 0; i < 4; i++)
			{
				String key = (String) SpellRegistry.REGISTRY.keySet().toArray()[rand.nextInt(SpellRegistry.REGISTRY.size())];
				SpellBase spell1 = SpellRegistry.REGISTRY.get(key);
				
				while(list.contains(spell1))
				{
					key = (String) SpellRegistry.REGISTRY.keySet().toArray()[rand.nextInt(SpellRegistry.REGISTRY.size())];
					spell1 = SpellRegistry.REGISTRY.get(key);
				}

				list.add(spell1);
				tag.setString("spell" + i, key);
			}
		}
		
		if(player instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageUpdateUserSpells(list.get(0).spellName(), list.get(1).spellName(), list.get(2).spellName(), list.get(3).spellName()), (EntityPlayerMP) player);
		}
	}
	
	public static List<String> copySpells(EntityPlayer original, EntityPlayer player)
	{
		List<String> list = Lists.newArrayList();
		NBTTagCompound tag = original.getEntityData();
		
		for(int i = 0; i < 4; i++)
		{
			if(tag != null && tag.hasKey("spell" + i))
			{
				list.add(tag.getString("spell" + i));
				player.getEntityData().setString("spell" + i, list.get(i));
			}
		}
		
		if(player instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageUpdateUserSpells(list.get(0), list.get(1), list.get(2), list.get(3)), (EntityPlayerMP) player);
		}
		
		return list;
	}
	
	public static List<String> loadSpells(EntityPlayer player)
	{
		List<String> list = Lists.newArrayList();
		NBTTagCompound tag = player.getEntityData();
		
		for(int i = 0; i < 4; i++)
		{
			if(tag != null && tag.hasKey("spell" + i))
			{
				list.add(tag.getString("spell" + i));
			}
		}
		
		if(player instanceof EntityPlayerMP && list.size() > 3)
		{
			Mathsmod.network.sendTo(new MessageUpdateUserSpells(list.get(0), list.get(1), list.get(2), list.get(3)), (EntityPlayerMP) player);
		}
		
		return list;
	}
	
	public static boolean spellExists(EntityPlayer player)
	{
		NBTTagCompound tag = player.getEntityData();
		int dec = 0;
		
		for(int i = 0; i < 4; i++)
		{
			if(tag != null && tag.hasKey("spell" + i) && SpellRegistry.REGISTRY.containsKey(tag.getString("spell" + i)))
			{
				dec += 1;
			}
		}
		
		return dec == 4;
	}

	public static void setSpellString(EntityPlayer entityplayer, int index, String string)
	{
		NBTTagCompound tag = entityplayer.getEntityData();
		List<String> list = loadSpells(entityplayer);
		list.set(index, string);
		
		for(int i = 0; i < 4; i++)
		{
			if(tag != null && tag.hasKey("spell" + i))
			{
				tag.setString("spell" + i, list.get(i));
			}
		}
		
		if(entityplayer instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageUpdateUserSpells(list.get(0), list.get(1), list.get(2), list.get(3)), (EntityPlayerMP) entityplayer);
		}
		
		if(!spellExists(entityplayer))
		{
			giveRandomSpells(entityplayer);
		}
		
		
	}
	
	public static void setSpell(EntityPlayer entityplayer, int index, SpellBase spell)
	{
		setSpellString(entityplayer, index, spell.spellName());
	}
	
	public static void setActiveSpell(EntityPlayer player, SpellBase spell, int phase)
	{
		if(spell != null && player.getEntityData() != null)
		{
			player.getEntityData().setString("active_spell", spell.spellName());
			player.getEntityData().setInteger("active_spell_phase", phase);
		}
		else
		{
			player.getEntityData().setString("active_spell", "null");
			player.getEntityData().setInteger("active_spell_phase", -1);
		}
	}
	
	public static TwoIntAndString getActiveSpellServer(EntityPlayer player)
	{
		if(player.getEntityData() != null)
		{
			return new TwoIntAndString(player.getEntityData().getInteger("active_spell_phase"), 0, player.getEntityData().getString("active_spell"));
		}
		else
		{
			return null;
		}
	}
	
	public static boolean hasSpell(EntityPlayer player, SpellBase spell)
	{
		NBTTagCompound tag = player.getEntityData();
		
		for(int i = 0; i < 4; i++)
		{
			if(tag != null && tag.hasKey("spell" + i))
			{
				if(player.getEntityData().getString("spell" + i).equals(spell.spellName()))
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
