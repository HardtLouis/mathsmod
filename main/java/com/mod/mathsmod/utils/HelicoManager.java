package com.mod.mathsmod.utils;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHelicopter;
import com.mod.mathsmod.add.messages.MessageHelicopter;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HelicoManager {

	public static void HelicoAction(int index)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player != null)
    	{
			Entity entity = player.getRidingEntity();
			
			if(entity != null && entity instanceof EntityHelicopter && entity.getControllingPassenger() == player)
			{
				EntityHelicopter ent = (EntityHelicopter) entity;
				if(ent.getFuel() > 0)
				{
					if(index == 0)
	    			{
						ent.motionY -= 0.02D;
	    			}
	    			else if(index == 1)
	    			{
	    				if(ent.boostToStart >= 1.0F)
						{
							ent.motionY += 0.006D;
						}
						else
						{
							ent.boostToStart = MathHelper.clamp(ent.boostToStart + 0.05F, 0, 1);
						}
	    			}
				}
    			if(index == 2)
    			{
    				if(ent.canShoot())
    				{
    					ent.Shoot(player);
    				}
    			}
			}
			
    	}
		
		Mathsmod.network.sendToServer(new MessageHelicopter(index));
	}
	
}
