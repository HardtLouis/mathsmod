package com.mod.mathsmod.utils;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class HomeManager 
{

	public static BlockPos getHome(String name, EntityPlayer player)
	{
		NBTTagCompound tag = playerNbtManager.getMathsmodTag(player);
		NBTTagList tag2 = null;
		BlockPos pos = null;
		
		if(tag.hasKey("homes"))
		{
			tag2 = tag.getTagList("homes", 10);
		}
		else
		{
			tag2 = new NBTTagList();
		}
		
		for (int i = 0; i < tag2.tagCount(); ++i)
        {
            NBTTagCompound compound = tag2.getCompoundTagAt(i);
            if(compound.hasKey(name) && compound.getString(name) != null && compound.getString(name).length() >= 5)
            {
            	String[] str = compound.getString(name).split("-");
            	if(str.length == 3)
            	{
            		int x = NumsHelper.getIntFromString(str[0]);
            		int y = NumsHelper.getIntFromString(str[1]);
            		int z = NumsHelper.getIntFromString(str[2]);
            		pos = new BlockPos(x, y, z);
            	}
            }
        }
		return pos;
	}
	
	public static List<String> getHomes(EntityPlayer player)
	{
		List<String> list = Lists.newArrayList();
		NBTTagCompound tag = playerNbtManager.getMathsmodTag(player);
		NBTTagList tag2 = null;
		if(tag.hasKey("homes"))
		{
			tag2 = tag.getTagList("homes", 10);
		}
		else
		{
			tag2 = new NBTTagList();
		}
		
		for (int i = 0; i < tag2.tagCount(); ++i)
        {
            NBTTagCompound compound = tag2.getCompoundTagAt(i);
            list.add(compound.toString());
        }
		
		return list;
	}
	
	public static void addHome(String name, BlockPos pos, EntityPlayer player)
	{
		NBTTagCompound tag = playerNbtManager.getMathsmodTag(player);
		NBTTagList tag2 = null;
		if(tag.hasKey("homes"))
		{
			tag2 = tag.getTagList("homes", 10);
		}
		else
		{
			tag2 = new NBTTagList();
		}
		
		if(tag2.tagCount() >= 5)
		{
			player.sendStatusMessage(new TextComponentString("you already have 5 homes"), true);
		}
		else
		{
			NBTTagCompound t = new NBTTagCompound();
			t.setString(name, pos.getX() + "-" + pos.getY() + "-" + pos.getZ());
			tag2.appendTag(t);
			tag.setTag("homes", tag2);
			player.sendStatusMessage(new TextComponentString("successfully created a new home"), true);
		}
	}
	
	public static void delHome(String name, EntityPlayer player)
	{
		NBTTagCompound tag = playerNbtManager.getMathsmodTag(player);
		NBTTagList tag2 = null;
		BlockPos pos = null;
		
		if(tag.hasKey("homes"))
		{
			tag2 = tag.getTagList("homes", 10);
		}
		else
		{
			tag2 = new NBTTagList();
		}
		
		for (int i = 0; i < tag2.tagCount(); ++i)
        {
            NBTTagCompound compound = tag2.getCompoundTagAt(i);
            if(compound.hasKey(name))
            {
            	tag2.removeTag(i);
            }
        }
	}
}
