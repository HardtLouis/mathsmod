package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterPotions;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackSphere;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.RenderEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;

public class SpellBlackSphere extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "black_ball";
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
		return 1;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return 120;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(!player.world.isRemote && phase == 0 && progress == 119)
		{
			EntityBlackSphere entity = new EntityBlackSphere(player.world);
			Vec3d vec = UtilsVoids.laser(1, player.rotationYaw, player.rotationPitch);
			entity.setPosition(player.posX, player.posY + 1, player.posZ);
			entity.shoot(player.rotationPitch, player.rotationYaw, 0.3F, player);
			player.world.spawnEntity(entity);
			player.addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, 1000000));
		}
		
		if(phase == 0)
		{
			player.motionX = 0;
			player.motionZ = 0;
		}
		
		player.fallDistance = 0;
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			float prog = (((float)(progress)) + Minecraft.getMinecraft().getRenderPartialTicks());
			float size = 1.0F;
			
			if(prog < 10)
			{
				size = prog * 0.1F;
			}
			
			for(int i = 0; i < 6; ++i)
			{
				double d = 4.0;
				if(progress > 100)
				{
					d = 4.0 * ((double)(120 - progress)) / 20.0;
				}
				Vec3d vec = UtilsVoids.laser(d, i * 60F + (prog) * prog * prog * 0.004F, 0);
				Vec3d vec2 = UtilsVoids.laser(2, player.rotationYaw, player.rotationPitch).addVector(0, 2, 0).add(vec);
				RenderEffects.renderLittleBlackSphere(vec2.x, vec2.y, vec2.z, 0, 0.25 * size);
			}
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			player.motionY = 0;
			double d = 0;
			if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
			{
				d += 1;
			}
			
			if(Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown())
			{
				d -= 1;
			}
			
			player.motionY = d;
		}
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 120;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 40;
	}

	@Override
	public EnumSpellReaction reaction(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellReaction.CANT;
	}

	@Override
	public int level() 
	{
		return 3;
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		if(phase == 0)
		{
			float prog = (((float)(progress)) + Minecraft.getMinecraft().getRenderPartialTicks());
			float size = 1.0F;
			
			if(prog < 10)
			{
				size = prog * 0.1F;
			}
			
			for(int i = 0; i < 6; ++i)
			{
				double d = 4.0;
				if(progress > 100)
				{
					d = 4.0 * ((double)(120 - progress)) / 20.0;
				}
				Vec3d vec = UtilsVoids.laser(d, i * 60F + (prog) * prog * prog * 0.004F, 0);
				Vec3d vec2 = UtilsVoids.laser(2, player.rotationYaw, player.rotationPitch).addVector(0, 2, 0).add(vec);
				RenderEffects.renderLittleBlackSphere(vec2.x, vec2.y, vec2.z, 0, 0.25 * size);
			}
		}
	}

	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		model.bipedRightArm.rotateAngleX = (float) (-84.0F * Math.PI / 180.0F);
		model.bipedLeftArm.rotateAngleX = (float) (-84.0F * Math.PI / 180.0F);
		
		model.bipedRightArm.rotateAngleY = 0;
		model.bipedLeftArm.rotateAngleY = 0;
		
		model.bipedRightArm.rotateAngleZ = 0;
		model.bipedLeftArm.rotateAngleZ = 0;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("when active, you can't shoot or hit someone");
		list.add("summons a black ball that deal great damages on hit");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 60;
	}
}
