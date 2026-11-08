package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsKeys;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageUpdateSpellAnims;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class SpellFeurAdaptation extends SpellBase
{

	@Override
	public String spellName()
	{
		return "feur_adaptation";
	}

	@Override
	public EnumSpellType type()
	{
		return EnumSpellType.OFFENSE;
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player)
	{
		return EnumSpellReaction.CAN_ATTACK;
	}

	@Override
	public EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player)
	{
		return EnumSpellMovement.UNAFFECT;
	}

	@Override
	public int level() 
	{
		return 1;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 4;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 0 ? 60 : 800;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player)
	{
		if(phase == 1)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1, 1));
		}
		else if(phase == 2)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1, 1));
		}
		else if(phase == 3)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1, 1));
		}
		
		if(phase != 0 && progress == this.duration(phase, player) - 1)
		{
			client_Proxy.spellUsed = null;
			client_Proxy.spellPhase = 0;
			client_Proxy.spellProgress = 0;
			client_Proxy.spellCooldowns.put(this.spellName(), this.spellCooldown((EntityPlayer) player));
			Mathsmod.network.sendToAll(new MessageUpdateSpellAnims("null", player.getName(), 0, 0));
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			float sizeMult = (((float)progress) + Minecraft.getMinecraft().getRenderPartialTicks()) / 60.0F;
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/feur_adaptation.png"), Vec3d.ZERO.addVector(0,  0.01, 0), 0.25F * sizeMult, -90, progress + Minecraft.getMinecraft().getRenderPartialTicks());
		}
		else
		{
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/feur_adaptation.png"), Vec3d.ZERO.addVector(0,  0.01, 0), 0.25F, -90, progress + Minecraft.getMinecraft().getRenderPartialTicks());
		}
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player)
	{
		if(phase == 0)
		{
			float sizeMult = (((float)progress) + Minecraft.getMinecraft().getRenderPartialTicks()) / 60.0F;
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/feur_adaptation.png"), Vec3d.ZERO.addVector(0,  0.01, 0), 0.25F * sizeMult, -90, progress + Minecraft.getMinecraft().getRenderPartialTicks());
		}
		else
		{
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/feur_adaptation.png"), Vec3d.ZERO.addVector(0,  0.01, 0), 0.25F, -90, progress + Minecraft.getMinecraft().getRenderPartialTicks());
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		if(phase >= 1 && MathsKeys.reload.isKeyDown() && client_Proxy.selectCooldown <= 0)
		{
			client_Proxy.selectCooldown = 5;
			//MathsKeys.reload.unPressAllKeys();
			client_Proxy.spellPhase = 1 + ((client_Proxy.spellPhase) % 3);
			Mathsmod.LOGGER.info("spell phase : " + client_Proxy.spellPhase);
		}
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 60;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 80;
	}

	@Override
	public int spellCooldown(EntityPlayer player) 
	{
		return 400;
	}

	@Override
	public List<String> description()
	{
		List<String> list = Lists.<String>newArrayList();
		list.add("give the player a boost in speed, attack");
		list.add("or defense, the user has to chose with the reload key");
		return list;
	}

}
