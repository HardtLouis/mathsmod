package com.mod.mathsmod.add.Entity.Entity.objects;

import java.util.Random;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityChauvoputEruptionBlock extends Entity
{
	
	int userID;
	
	public EntityChauvoputEruptionBlock(World worldIn)
	{
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}

	@Override
	protected void entityInit() 
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		userID = compound.getInteger("user_id");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("user_id", userID);
	}
	
	public EntityLivingBase getUser()
	{
		if(world.getEntityByID(userID) != null && world.getEntityByID(userID) instanceof EntityLivingBase)
		{
			return (EntityLivingBase) world.getEntityByID(userID);
		}
		else
		{
			return null;
		}
	}
	
	public void setUser(EntityLivingBase entity)
	{
		userID = entity.getEntityId();
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		
		BlockPos pos2 = new BlockPos(this).down();
		for(EntityLivingBase around : UtilsVoids.getEntities(EntityLivingBase.class, world, this, 5))
		{
			if(this.getUser() != null && this.getUser() != around)
			{
				around.attackEntityFrom(DamageSource.causeMobDamage(this.getUser()).setDamageBypassesArmor(), 5);
			}
			else
			{
				around.attackEntityFrom(DamageSource.GENERIC.setDamageBypassesArmor(), 5);
			}
		}
		
		int  y = 0;
		
		if(this.ticksExisted % 10 == 0 && this.ticksExisted > 0 && this.ticksExisted <= 50)
		{
			y = (this.ticksExisted - 10) / 10;
			
			if(y < 3)
			{
				for(int a = -2; a <= 2; a += 4)
				{
					IBlockState state = Blocks.COBBLESTONE.getDefaultState();
					if(new Random().nextInt(6) == 0)
					{
						state = Blocks.MAGMA.getDefaultState();
					}
					world.setBlockState(pos2.add(0, y, a), state);
				}
				
				for(int b = -2; b <= 2; b += 4)
				{
					IBlockState state = Blocks.COBBLESTONE.getDefaultState();
					if(new Random().nextInt(6) == 0)
					{
						state = Blocks.MAGMA.getDefaultState();
					}
					world.setBlockState(pos2.add(b, y, 0), state);
				}
			}
			
			for(int a = -1; a <= 1; ++a)
			{
				for(int b = -1; b <= 1; ++b)
				{
					IBlockState state = Blocks.COBBLESTONE.getDefaultState();
					if(new Random().nextInt(6) == 0)
					{
						state = Blocks.MAGMA.getDefaultState();
					}
					world.setBlockState(pos2.add(a, y, b), state);
					for(int t = 0; t < 10; ++t)
					{
						world.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos2.add(a, y, b).getX(), pos2.add(a, y, b).getY(), pos2.add(a, y, b).getZ(), new Random().nextDouble() * 2 - 1, 0.3, new Random().nextDouble() * 2 - 1, Block.getStateId(Blocks.LAVA.getDefaultState()));
					}
				}
			}
		}
		else if(this.ticksExisted % 10 == 0 && this.ticksExisted <= 120)
		{
			int c = (this.ticksExisted - 70) / 10;
			
			IBlockState state = Blocks.COBBLESTONE.getDefaultState();
			if(new Random().nextInt(6) == 0)
			{
				state = Blocks.MAGMA.getDefaultState();
			}
			world.setBlockState(pos2.add(0, 4 + c, 0), state);
			
			for(int a = -1; a <= 1; a += 2)
			{
				IBlockState state2 = Blocks.COBBLESTONE.getDefaultState();
				if(new Random().nextInt(6) == 0)
				{
					state2 = Blocks.MAGMA.getDefaultState();
				}
				world.setBlockState(pos2.add(0, 4 + y, a), state2);
			}
			
			for(int b = -1; b <= 1; b += 2)
			{
				IBlockState state2 = Blocks.COBBLESTONE.getDefaultState();
				if(new Random().nextInt(6) == 0)
				{
					state2 = Blocks.MAGMA.getDefaultState();
				}
				world.setBlockState(pos2.add(b, 4 + y, 0), state2);
			}
		}
		else if(this.ticksExisted > 120)
		{
			this.setDead();
		}
	}

}
