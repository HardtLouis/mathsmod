package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.CSE.EffectClientSideEnt;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageUpdateClientSpell;
import com.mod.mathsmod.add.messages.MessageUpdateSpellAnims;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;

public class SpellDisintegratingHand extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "disintegrating_hand";
	}

	@Override
	public EnumSpellType type() 
	{
		return EnumSpellType.OFFENSE;
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellReaction.CAN_MELEE;
	}

	@Override
	public EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellMovement.UNAFFECT;
	}

	@Override
	public int level() 
	{
		return 3;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 1;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return 200;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		for(int i = 0; i < 2; ++i)
		{
			Vec3d vec = UtilsVoids.laser(rand.nextFloat() * 2, rand.nextFloat() * 360.0F, 0.0F).add(player.getPositionVector()).addVector(0, rand.nextFloat() * 1.8F, 0);
			EffectClientSideEnt cse = new EffectClientSideEnt(vec, new Vec3d(0, 0.05, 0), 0, 0, 100, player.dimension);
			cse.type = 2;
			client_Proxy.CSEL.add(cse);
		}
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		for(int i = 0; i < 2; ++i)
		{
			Vec3d vec = UtilsVoids.laser(rand.nextFloat() * 2, rand.nextFloat() * 360.0F, 0.0F).add(player.getPositionVector());
			EffectClientSideEnt cse = new EffectClientSideEnt(vec, new Vec3d(0, 0.05, 0), 0, 0, 100, player.dimension);
			cse.type = 2;
			client_Proxy.CSEL.add(cse);
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		if(player.getHealth() <= 0.0F && player instanceof EntityPlayer)
		{
			client_Proxy.spellUsed = null;
			client_Proxy.spellPhase = 0;
			client_Proxy.spellProgress = 0;
			client_Proxy.spellCooldowns.put(this.spellName(), this.spellCooldown((EntityPlayer) player));
			Mathsmod.network.sendToAll(new MessageUpdateSpellAnims("null", player.getName(), 0, 0));
		}
		
		client_Proxy.spellProgress = client_Proxy.spellProgress % 40;
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 0;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 0;
	}
	
	public void spellUseMelee(int phase, int progress, EntityLivingBase player, Entity entity, ItemStack stack)
	{
		if(entity instanceof EntityLivingBase && stack.isEmpty())
		{
			if(((EntityLivingBase) entity).getMaxHealth() <= 100.0F)
			{
				if(((EntityLivingBase) entity).getEntityData() != null)
				{
					if(!((EntityLivingBase) entity).getEntityData().hasKey("disintegrating_hand_touched_counter"))
					{
						((EntityLivingBase) entity).getEntityData().setInteger("disintegrating_hand_touched_counter", 1);
					}
					else
					{
						((EntityLivingBase) entity).getEntityData().setInteger("disintegrating_hand_touched_counter", ((EntityLivingBase) entity).getEntityData().getInteger("disintegrating_hand_touched_counter") + 1);
					}
					
					//System.out.print(((EntityLivingBase) entity).getEntityData().getInteger("disintegrating_hand_touched_counter") + "\n");
					if(((EntityLivingBase) entity).getEntityData().getInteger("disintegrating_hand_touched_counter") >= 5)
					{
						((EntityLivingBase) entity).attackEntityFrom(DamageSource.causeMobDamage(player).setDamageBypassesArmor(), Float.POSITIVE_INFINITY);
						if(player instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageUpdateClientSpell("null", 0, 0, true), (EntityPlayerMP) player);
						}
					}
				}
			}
		}
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("when active, will stay active until death");
		list.add("shooting become impossible");
		list.add("hitting someone with your bare hand 6 times");
		list.add("without getting hit will just kill him");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player) 
	{
		return 3600;
	}
}
