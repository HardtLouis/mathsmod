package com.mod.mathsmod.add.World.dims.teleporter;

import java.util.Random;

import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterPotions;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CopravegTeleporter implements net.minecraftforge.common.util.ITeleporter
{
	
	private final BlockPos pos;
	private final int id;
	
	public CopravegTeleporter(BlockPos pos, int dimID)
	{
		this.pos = pos;
		this.id = dimID;
	}

	@Override
    public void placeEntity(World world, Entity entity, float yaw)
    {
		if(id != 0)
		{
			if (entity instanceof EntityPlayerMP)
	        {
	        	((EntityPlayerMP) entity).connection.setPlayerLocation(this.pos.getX() + 0.5D, this.pos.getY(), this.pos.getZ() + 0.5D, yaw, entity.rotationPitch);
	        }
	        else
	        {
	        	entity.setPositionAndUpdate(this.pos.getX() + 0.5D, this.pos.getY(), this.pos.getZ() + 0.5D);
	        }
		}
		else
		{
			if (entity instanceof EntityPlayerMP)
	        {
	        	((EntityPlayerMP) entity).connection.setPlayerLocation(entity.posX, 200, entity.posZ, yaw, entity.rotationPitch);
	        }
	        else
	        {
	        	entity.setPositionAndUpdate(entity.posX, 200, entity.posZ);
	        }
			
			if(entity instanceof EntityLivingBase)
			{
				entity.onGround = false;
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(RegisterPotions.FALL_PROTECTION, Integer.MAX_VALUE));
			}
	        
	        if(world.getBlockState(new BlockPos(entity.posX, 200 + entity.getEyeHeight(), entity.posZ)).getMaterial() != Material.AIR && world.getBlockState(new BlockPos(entity.posX, 200 + entity.getEyeHeight(), entity.posZ)).causesSuffocation())
	        {
	        	for(int i = -3; i < 3; ++i)
	            {
	            	for(int j = -3; j < 3; ++j)
	                {
	            		for(int k = 0; k <= 3; ++k)
	                    {
	            			BlockPos pos = new BlockPos(new BlockPos(entity.posX, 200, entity.posZ)).north(i).east(j).up(k);
	            			if(world.getBlockState(pos).getMaterial() != Material.AIR)
	            			{
	            				world.setBlockToAir(pos);
	            			}
	                    }
	                }
	            }
	        }
		}
    }
}