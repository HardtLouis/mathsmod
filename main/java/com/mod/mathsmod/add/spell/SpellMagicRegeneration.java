package com.mod.mathsmod.add.spell;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageAddsCSE;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class SpellMagicRegeneration extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "magic_regeneration";
	}

	@Override
	public EnumSpellType type() 
	{
		return EnumSpellType.UTILITY;
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellReaction.CANT;
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
		return 60;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		player.heal(0.35F);
		player.motionX *= 0.3;
		player.motionZ *= 0.3;
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0 && progress < 60)
		{
			player.world.spawnParticle(EnumParticleTypes.HEART, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, 10);
		}
		//player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(RegisterBlocks.FIRE), 10);
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		if(phase == 0 && progress < 60)
		{
			player.world.spawnParticle(EnumParticleTypes.HEART, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, 10);
		}
		
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 40;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 100;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("just heal yourself");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 1800;
	}
	
	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		model.bipedRightArm.rotateAngleX = (float) (-170.0F * Math.PI / 180.0F);
		model.bipedLeftArm.rotateAngleX = (float) (-170.0F * Math.PI / 180.0F);
		
		model.bipedRightArm.rotateAngleY = 0;
		model.bipedLeftArm.rotateAngleY = 0;
		
		model.bipedRightArm.rotateAngleZ = (float) (25.0F * Math.PI / 180.0F);
		model.bipedLeftArm.rotateAngleZ = (float) (-25.0F * Math.PI / 180.0F);
	}

}
