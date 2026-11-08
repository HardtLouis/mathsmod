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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class SpellBlueFlames extends SpellBase
{

	@Override
	public String spellName() 
	{
		return "blue_flames";
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
		return phase == 1 ? 100 : 200;
	}

	@Override
	public void spellUseCommon(int phase, int progress, EntityLivingBase player) 
	{
		if(!player.world.isRemote && phase == 1 && player instanceof EntityPlayer)
		{
			Mathsmod.network.sendToAllAround(new MessageAddsCSE((EntityPlayer) player, 2.5F, 20, 5.0F, 4, -0.4F), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 40));
		}
	}

	@Override
	public void renderUse(int phase, int progress, EntityLivingBase player) 
	{
		if(phase == 0 && progress < 60)
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(Blocks.FIRE), 10);
		}
		else
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(RegisterBlocks.FIRE), 10);
		}
		//player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(RegisterBlocks.FIRE), 10);
	}

	@Override
	public void renderFirstPerson(int phase, int progress, EntityPlayer player) 
	{
		if(phase == 0 && progress < 60)
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(Blocks.FIRE), 10);
		}
		else
		{
			player.world.spawnAlwaysVisibleParticle(61, player.posX, player.posY + 1, player.posZ, this.rand.nextGaussian() * 0.2F, 0.2F, this.rand.nextGaussian() * 0.2F, Block.getIdFromBlock(RegisterBlocks.FIRE), 10);
		}
		
	}

	@Override
	public void spellUseClient(int phase, int progress, EntityLivingBase player) 
	{
		
		if(phase == 1)
		{
			for(int i = 0; i < 20; ++i)
    		{
    			Random rand = new Random();
            	float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)5.0F);
            	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)5.0F);
            	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)5.0F);
            	
    			Vec3d vec = UtilsVoids.laserWithInnacuracy(1F, player.rotationYaw, player.rotationPitch, x, y, z);
    			ClientSideEnt cse = new ClientSideEnt(player.getPositionEyes(1.0F).add(vec.scale(0.4)).addVector(0, -0.7, 0), vec, vec.scale(3.0F), player.getName(), 0, null, player.rotationPitch, player.rotationYaw, 10, player.dimension);
        		cse.type = 4;
        		cse.damages = 0.075F;
    			client_Proxy.CSEL.add(cse);
    		}
		}
		
		if(phase == 0 && client_Proxy.spellProgress > 60)
		{
			client_Proxy.spellProgress = 61;
		}
		
		if(phase == 0 && progress > 20 && Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown())
		{
			client_Proxy.spellPhase = 1;
		}
	}

	@Override
	public int cost(EntityPlayer player) 
	{
		return 80;
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
		list.add("summons a stream of blue flames");
		list.add("that burns the enemy, left click to release it");
		return list;
	}

	@Override
	public int spellCooldown(EntityPlayer player)
	{
		return 400;
	}

}
