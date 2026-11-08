package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.CSE.EffectClientSideEnt;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityIcyJavelin;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderEffects;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class SpellIceAttack extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "icy_javelin";
	}

	@Override
	public EnumSpellType type() 
	{
		return EnumSpellType.OFFENSE;
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellReaction.CANT;
	}

	@Override
	public EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player)
	{
		return EnumSpellMovement.NO_MOVEMENT;
	}

	@Override
	public int level() 
	{
		return 3;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 2;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 0 ? 20 : 10;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			player.motionX *= 0.7;
			player.motionZ *= 0.7;
		}
		else
		{
			player.motionX *= 0.7;
			player.motionZ *= 0.7;
			if(progress == 9)
			{
				EntityIcyJavelin entity = new EntityIcyJavelin(player.world);
				entity.setPosition(player.posX, player.posY + 2, player.posZ);
				entity.shoot(player.rotationPitch, player.rotationYaw, 3.0F, player);
				if(!player.world.isRemote)
				{
					player.world.spawnEntity(entity);
				}
			}
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			RenderEffects.renderIcyJavelin(new Vec3d(0,2,0), player.rotationPitch, player.rotationYaw, ((float) progress) / 20.0F);
		}
		else
		{
			if(progress < 9)
			{
				RenderEffects.renderIcyJavelin(new Vec3d(0,2,0), player.rotationPitch, player.rotationYaw, 1.0F);
			}
		}
		
		for(int i = 0; i < 2; ++i)
		{
			Vec3d vec = UtilsVoids.laser(rand.nextFloat() * 2, rand.nextFloat() * 360.0F, 0.0F).add(player.getPositionVector()).addVector(0, 2 - rand.nextFloat(), 0);
			EffectClientSideEnt cse = new EffectClientSideEnt(vec, new Vec3d(0, -0.1, 0), 0, 0, 100, player.dimension);
			cse.type = 3;
			client_Proxy.CSEL.add(cse);
		}
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		if(phase == 0)
		{
			float degToRad = (float) (Math.PI / 180.0F);
			Vec3d vec = UtilsVoids.laser(1, player.rotationYaw, player.rotationPitch);
			GlStateManager.translate(0, 4, 0);
			RenderEffects.renderIcyJavelin(Vec3d.ZERO, player.rotationPitch, player.rotationYaw, ((float) progress) / 20.0F);
			GlStateManager.translate(0, -4, 0);
		}
		else
		{
			if(progress < 9)
			{
				float degToRad = (float) (Math.PI / 180.0F);
				Vec3d vec = UtilsVoids.laser(1, player.rotationYaw, player.rotationPitch);
				GlStateManager.translate(0, 4, 0);
				RenderEffects.renderIcyJavelin(Vec3d.ZERO, player.rotationPitch, player.rotationYaw, 1.0F);
				GlStateManager.translate(0, -4, 0);
			}
		}
		
		for(int i = 0; i < 2; ++i)
		{
			Vec3d vec = UtilsVoids.laser(rand.nextFloat() * 2, rand.nextFloat() * 360.0F, 0.0F).add(player.getPositionVector()).addVector(0, 2 - rand.nextFloat(), 0);
			EffectClientSideEnt cse = new EffectClientSideEnt(vec, new Vec3d(0, -0.05, 0), 0, 0, 100, player.dimension);
			cse.type = 3;
			client_Proxy.CSEL.add(cse);
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 100;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 60;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("summons a icy javelin and throws it");
		list.add("deals a lot of damages");
		return list;
	}
	
	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		
		float pp = progress+partialTick;
		
		if(phase == 0)
		{
			model.bipedRightArm.rotateAngleX = (float) (-180.0F * Math.PI / 180.0F);
			model.bipedRightArm.rotateAngleY = 0;
			model.bipedRightArm.rotateAngleZ = 0;
		}
		else if(pp < 5)
		{
			model.bipedRightArm.rotateAngleX = (float) ((-90.0F - 90.0F * (5.0F - pp) / 5.0F) * Math.PI / 180.0F);
			model.bipedRightArm.rotateAngleY = 0;
			model.bipedRightArm.rotateAngleZ = 0;
		}
		else
		{
			model.bipedRightArm.rotateAngleX = (float) ((-90.0F) * Math.PI / 180.0F);
			model.bipedRightArm.rotateAngleY = 0;
			model.bipedRightArm.rotateAngleZ = 0;
		}
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 60;
	}

}
