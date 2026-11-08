package com.mod.mathsmod.add.blocks.tileentity;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityEAbsorber extends TileEntity implements ITickable
{

	@Override
	public void update() 
	{
		for(EntityLiving entity : UtilsVoids.getEntitiesPos(EntityLiving.class, world, this.getPos(), 7))
		{
			if(entity.getMaxHealth() <= 200)
			{
				UtilsVoids.AttractEntityAbsorber(pos, entity, 7, 0.2F);
			}
		}
	}

}
