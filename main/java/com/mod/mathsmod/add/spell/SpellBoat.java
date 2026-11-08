package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPubProj;
import com.mod.mathsmod.add.Entity.Entity.objects.EntitySpeedingBoat;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemMobSoul;
import com.mod.mathsmod.add.messages.MessageUpdateSpellAnims;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderEffects;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class SpellBoat extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "boat";
	}

	@Override
	public EnumSpellType type() 
	{
		return EnumSpellType.OFFENSE;
	}

	@Override
	public EnumSpellMovement movementType(int phase, int progress, EntityLivingBase player) 
	{
		return EnumSpellMovement.SLOW;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 1;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 0 ? 10 : 2;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(!player.world.isRemote && phase == 0 && progress == 9)
		{
			for(int i = 0; i < 1; i++)
			{
				EntitySpeedingBoat entity = new EntitySpeedingBoat(player.world);
				entity.userName = player.getName();
				entity.rotationPitch = 0;
				entity.rotationYaw = player.rotationYaw;
				Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch);
				entity.setPosition(player.posX + vec.x, player.posY + vec.y, player.posZ + vec.z);
				player.world.spawnEntity(entity);
			}
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
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
		return 50;
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

	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		model.bipedRightArm.rotateAngleX = (float) (-180.0F * Math.PI / 180.0F);
		model.bipedLeftArm.rotateAngleX = (float) (-180.0F * Math.PI / 180.0F);
		
		model.bipedRightArm.rotateAngleY = 0;
		model.bipedLeftArm.rotateAngleY = 0;
		
		model.bipedRightArm.rotateAngleZ = 0;
		model.bipedLeftArm.rotateAngleZ = 0;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("allow the user to summon a");
		list.add("speeding boat");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 600;
	}
}