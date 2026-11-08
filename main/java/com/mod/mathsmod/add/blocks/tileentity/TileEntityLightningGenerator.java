package com.mod.mathsmod.add.blocks.tileentity;

import java.util.Random;

import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;

public class TileEntityLightningGenerator extends TileEntity implements ITickable
{

	@Override
	public void update() 
	{
		Random rand = new Random();
		if(rand.nextInt(120) == 0 && !UtilsVoids.getEntitiesPos(EntityPlayer.class, world, this.pos, 50).isEmpty())
		{
			if(!world.isRemote)
			{
				Vec3d vec = new Vec3d(pos.getX() + rand.nextInt(11) - 5, pos.getY(), pos.getZ() + rand.nextInt(11) - 5);
				world.addWeatherEffect(new EntityLightningBolt(world, vec.x, vec.y, vec.z, true));
			}
		}
	}

}
