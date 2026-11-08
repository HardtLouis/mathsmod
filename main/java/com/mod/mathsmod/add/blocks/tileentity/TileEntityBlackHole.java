package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackHole;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.blocks.BlockBlackHoleStabilizer;
import com.mod.mathsmod.add.items.guns.ItemGrenadeLauncher;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityBlackHole extends TileEntity implements ITickable
{
	
	int unstable;
	
	@Override
	public void update() 
	{
		unstable += 1;
		
		if(world.getBlockState(this.getPos().add(-3, 0, 0)).getBlock() == RegisterBlocks.STABILIZER && world.getBlockState(this.getPos().add(-3, 0, 0)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.EAST)
		{
			if(world.getBlockState(this.getPos().add(3, 0, 0)).getBlock() == RegisterBlocks.STABILIZER && world.getBlockState(this.getPos().add(3, 0, 0)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.WEST)
			{
				if(world.getBlockState(this.getPos().add(0, 0, -3)).getBlock() == RegisterBlocks.STABILIZER && world.getBlockState(this.getPos().add(0, 0, -3)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.SOUTH)
				{
					if(world.getBlockState(this.getPos().add(0, 0, 3)).getBlock() == RegisterBlocks.STABILIZER && world.getBlockState(this.getPos().add(0, 0, 3)).getValue(BlockBlackHoleStabilizer.FACING) == EnumFacing.NORTH)
					{
						unstable = 0;
						
						for(EntityItem entity : UtilsVoids.getEntities(EntityItem.class, world, this.getPos().getX() + 0.5F, this.getPos().getY() + 0.5F, this.getPos().getZ() + 0.5F, 1))
						{
							/*if(entity.getItem().getItem() instanceof ItemGrenadeLauncher)
							{
								EntityItem entity2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(RegisterItems.BLACK_HOLE_LAUNCHER));
								if(!world.isRemote)
								{
									world.spawnEntity(entity2);
								}
								entity.setDead();
							}*/
							
							if(entity.getItem().getItem() == RegisterItems.GOLDEN_FRYING_PAN)
							{
								EntityItem entity2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(RegisterItems.BLACK_HOLE_FRYING_PAN));
								UtilsVoids.AttractEntityPos(pos, entity2, 2, -1);
								if(!world.isRemote)
								{
									world.spawnEntity(entity2);
								}
								entity.setDead();
							}
							
							if(entity.getItem().getItem() == RegisterItems.BLACK_HOLE_FRYING_PAN)
							{
								EntityItem entity2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(RegisterItems.GOLDEN_FRYING_PAN));
								UtilsVoids.AttractEntityPos(pos, entity2, 2, -1);
								if(!world.isRemote)
								{
									world.spawnEntity(entity2);
								}
								entity.setDead();
							}
							
							if(entity.getItem().getItem() == RegisterItems.T_MISSILE)
							{
								EntityItem entity2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(RegisterItems.BLACK_HOLE_MISSILE));
								UtilsVoids.AttractEntityPos(pos, entity2, 2, -1);
								if(!world.isRemote)
								{
									world.spawnEntity(entity2);
								}
								entity.setDead();
							}
						}
					}
				}
			}
		}
		
		if(unstable > 60)
		{
			if(!world.isRemote)
			{
				world.setBlockToAir(this.getPos());
				EntityBlackHole entity = new EntityBlackHole(world);
				entity.setPosition(this.pos.getX() + 0.5F, this.pos.getY(), this.pos.getZ() + 0.5F);
				entity.setMaxSize(25F);
				world.spawnEntity(entity);
			}
		}
	}
	
	@Override
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox()
    {
		return INFINITE_EXTENT_AABB;
    }

}
