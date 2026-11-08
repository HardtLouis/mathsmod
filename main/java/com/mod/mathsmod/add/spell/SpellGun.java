package com.mod.mathsmod.add.spell;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class SpellGun extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "gun_goes_boom";
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
		return 2;
	}

	@Override
	public int phases(EntityLivingBase player) 
	{
		return 2;
	}

	@Override
	public int duration(int phase, EntityLivingBase player) 
	{
		return phase == 0 ? 60 : 600;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0)
		{
			player.motionX *= 0.5F;
			player.motionZ *= 0.5F;
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player)
	{
		if(progress == 1)
		{
			Vec3d vec = UtilsVoids.laser(2, progress, 0);
			player.world.spawnAlwaysVisibleParticle(61, player.posX + vec.x, player.posY + 1, player.posZ + vec.z, this.rand.nextGaussian(), 0.5F, this.rand.nextGaussian(), Block.getIdFromBlock(RegisterBlocks.SILVER_BLOCK), 10);
		}
		else
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian(), 0.5F, this.rand.nextGaussian(), Block.getIdFromBlock(RegisterBlocks.SILVER_BLOCK), 10);
		}
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		if(progress == 1)
		{
			Vec3d vec = UtilsVoids.laser(2, progress, 0);
			player.world.spawnAlwaysVisibleParticle(61, player.posX + vec.x, player.posY + 1, player.posZ + vec.z, this.rand.nextGaussian(), 0.5F, this.rand.nextGaussian(), Block.getIdFromBlock(RegisterBlocks.SILVER_BLOCK), 10);
		}
		else
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian(), 0.5F, this.rand.nextGaussian(), Block.getIdFromBlock(RegisterBlocks.SILVER_BLOCK), 10);
		}
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 300;
	}

	@Override
	public int cooldown(EntityPlayer player) 
	{
		return 10;
	}

	@Override
	public List<String> description() 
	{
		List list = Lists.newArrayList();
		list.add("can only be used with a shotgun");
		list.add("when active, make your shots explosive");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player) 
	{
		return 2400;
	}

}
