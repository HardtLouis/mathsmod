package com.mod.mathsmod.utils;

import java.util.List;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.proxy.client_Proxy;
import com.mod.mathsmod.proxy.server_Proxy;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class DisguiseHelper 
{
	public static ResourceLocation getDisguisment(AbstractClientPlayer player) 
	{
		String[] disguise = null;
		
		if(client_Proxy.renderInfos.containsKey(player.getName()))
    	{
    		List<String> list = client_Proxy.renderInfos.get(player.getName());
    		for(String str : list)
    		{
    			if(str.startsWith("disguise:"))
    			{
    				String[] str2 = str.split(":");
    				if(str2.length == 3)
    				{
        				disguise = new String[] {str2[1], str2[2]};
            			return new ResourceLocation(disguise[0], disguise[1]);
    				}
    				else if(str2.length == 2)
    				{
        				disguise = new String[] {"minecraft", str2[1]};
            			return new ResourceLocation(disguise[0], disguise[1]);
    				}
    			}
    			
    		}
    	}
		return null;
	}
	
	public static void removeDisguise(EntityPlayer player)
	{
		if(!player.world.isRemote)
		{
			server_Proxy.handleRenderInfoChange(player.getName(), "", true);
		}
		Mathsmod.network.sendToAll(new MessageTransform(player.getName(), "", true));
	}
}
