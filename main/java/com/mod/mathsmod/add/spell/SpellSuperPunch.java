package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.CSE.EffectClientSideEnt;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.messages.MessageModifyVelocity;
import com.mod.mathsmod.add.messages.MessageUpdateSpellAnims;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class SpellSuperPunch extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "super_punch";
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
		return 2;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 1 ? 6 : 200;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 1));
		}
		else
		{
			player.motionX = 0;
			player.motionZ = 0;
		}
		
		if(phase == 1 && progress == 4)
		{
			Vec3d vec = UtilsVoids.laser(1.5, player.rotationYaw, player.rotationPitch).add(player.getPositionEyes(1.0F));
			AxisAlignedBB box = new AxisAlignedBB(vec.x - 1.5F, vec.y - 1.5F, vec.z - 1.5F, vec.x + 1.5F, vec.y + 1.5F, vec.z + 1.5F);
			
			try 
			{
				for(Entity entity : player.world.getEntitiesInAABBexcluding(player, box, null))
				{
					if(entity != null && entity instanceof EntityLivingBase)
					{
						System.out.print("ta mer \n");
						entity.attackEntityFrom(DamageSource.causeMobDamage(player).setDamageBypassesArmor(), 20.0F);
						Vec3d vec2 = UtilsVoids.laser(1.5F, player.rotationYaw, -20);
						entity.motionX = vec2.x;
						entity.motionY = vec2.y;
						entity.motionZ = vec2.z;
						if(entity instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageModifyVelocity(vec2), (EntityPlayerMP) entity);
						}
					}
				}
			} 
			catch (Exception e) 
			{
				// Ouais, encore éviter cette merde
			}
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/tel_texture.png"), Vec3d.ZERO, 0.25F, -90, progress * 360);
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/tel_texture.png"), Vec3d.ZERO, 0.25F, -90, progress * 360);
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		if(player.getHealth() <= 0.0F)
		{
			client_Proxy.spellUsed = null;
			client_Proxy.spellPhase = 0;
			client_Proxy.spellProgress = 0;
			Mathsmod.network.sendToAll(new MessageUpdateSpellAnims("null", player.getName(), 0, 0));
		}
		
		if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown())
		{
			client_Proxy.spellPhase = 1;
		}
		
		client_Proxy.spellProgress = client_Proxy.spellProgress % 40;
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 200;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 0;
	}

	@Override
	public List<String> description() 
	{
		List<String> list = Lists.newArrayList();
		list.add("when active, will charge a super punch");
		list.add("left click to use it");
		return list;
	}
	
	@Override
	public void animateModel(ModelBiped model, Entity entityIn, int phase, int progress,
			float limbSwing, float limbSwingAmount, float partialTick) 
	{
		if(phase == 1)
		{
			model.bipedRightArm.rotateAngleX = (float) (((float)progress) / 6.0F * -150.0F * Math.PI / 180.0F);
			
			model.bipedRightArm.rotateAngleY = 0;
			
			model.bipedRightArm.rotateAngleZ = 0;
		}
	}

	@Override
	public int spellCooldown(EntityPlayer player) 
	{
		return 600;
	}
}