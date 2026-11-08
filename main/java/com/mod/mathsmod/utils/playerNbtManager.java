package com.mod.mathsmod.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class playerNbtManager 
{
	public static NBTTagCompound getMathsmodTag(EntityPlayer player)
	{
		if(!player.getEntityData().hasKey("mathsmodTags"))
		{
			player.getEntityData().setTag("mathsmodTags", new NBTTagCompound());
		}
		
		return player.getEntityData().getCompoundTag("mathsmodTags");
	}
}
