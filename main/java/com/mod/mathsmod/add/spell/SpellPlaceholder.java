package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.utils.RenderEffects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpellPlaceholder extends SpellBase 
{

	int j;
	
	public SpellPlaceholder(int i)
	{
		j = i;
	}
	
	@Override
	public EnumSpellType type() 
	{
		return EnumSpellType.OFFENSE;
	}

	@Override
	public EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellMovement.UNAFFECT;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 2;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 0 ? 200 : 200;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		RenderEffects.renderPlayerSoulContractChains(Vec3d.ZERO);
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player)
	{
	}

	@Override
	public String spellName() 
	{
		return "spellPlaceholderAttack" + j;
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 0;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 10;
	}
	
	@Override
	public String IconName(EntityPlayer player)
	{
		return "placeholder";
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellReaction.CANT;
	}

	@Override
	public int level() 
	{
		return 1;
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		
	}
	


	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("test");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player) 
	{
		return 0;
	}

}
