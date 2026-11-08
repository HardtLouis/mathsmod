package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPubProj;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SpellSilverfish extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "silverfish";
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
		return phase == 0 ? 100 : 2;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(!player.world.isRemote && phase == 1 && progress == 1)
		{
			for(int i = 0; i < 10; i++)
			{
				EntitySilverfish entity = new EntitySilverfish(player.world);
				entity.getEntityData().setString("manipulation_owner", player.getName());
				if(entity instanceof EntityLiving)
				{
					Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch);
					entity.setPosition(player.posX + vec.x, player.posY + vec.y, player.posZ + vec.z);
					((EntityLiving) entity).targetTasks.taskEntries.clear();
					player.world.spawnEntity(entity);
				}
			}
		}
		
		if(!player.world.isRemote && phase == 2 && progress == 1)
		{
			if(player.getHeldItemMainhand().getItem() instanceof ItemMobSoul)
			{
				EntityPubProj entity = new EntityPubProj(player.world);
				Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch);
				entity.setPosition(player.posX + vec.x, player.posY + vec.y + 1, player.posZ + vec.z);
				entity.shoot(player.rotationPitch, player.rotationYaw, 0.1F, player);
				player.world.spawnEntity(entity);
				player.getHeldItemMainhand().shrink(1);
			}
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		RenderEffects.renderPlayerSoulContractChains(Vec3d.ZERO);
		if(phase == 0 && progress >= 60)
		{
			Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch).addVector(0, 1, 0);
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/mob_manip.png"), vec, 0.25F, -90, progress * 360);
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			if(progress >= 60)
			{
				client_Proxy.spellProgress = 60 + client_Proxy.spellProgress % 20;
			}
			
			if(Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && progress >= 60)
			{
				client_Proxy.spellProgress = 0;
				client_Proxy.spellPhase = 1;
			}
		}
		
		if(phase != 0 && progress == 1)
		{
			client_Proxy.spellUsed = null;
			client_Proxy.spellPhase = 0;
			client_Proxy.spellProgress = 0;
			client_Proxy.spellCooldowns.put(this.spellName(), this.spellCooldown((EntityPlayer) player));
			Mathsmod.network.sendToAll(new MessageUpdateSpellAnims("null", player.getName(), 0, 0));
		}
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 100;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 80;
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
		RenderEffects.renderPlayerSoulContractChains(Vec3d.ZERO);
		
		if(phase == 0 && progress >= 60)
		{
			Vec3d vec = UtilsVoids.laser(3, player.rotationYaw, player.rotationPitch).addVector(0, 1, 0);
			RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/effects/silverfish.png"), vec, 0.25F, -90, progress * 360);
		}
	}

	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		model.bipedRightArm.rotateAngleX = (float) (-44.0F * Math.PI / 180.0F);
		model.bipedLeftArm.rotateAngleX = (float) (-44.0F * Math.PI / 180.0F);
		
		model.bipedRightArm.rotateAngleY = 0;
		model.bipedLeftArm.rotateAngleY = 0;
		
		model.bipedRightArm.rotateAngleZ = 0;
		model.bipedLeftArm.rotateAngleZ = 0;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("allow the user to summon 10");
		list.add("annoying silverfishes");
		list.add("right click to summon when ready");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 1200;
	}
}