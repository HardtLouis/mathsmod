package com.mod.mathsmod.proxy;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.event.ServerHelperEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class server_Proxy extends common_Proxy
{
	public static HashMap<String, List<String>> renderInfos = Maps.newHashMap();
	
	public server_Proxy()
	{
		MinecraftForge.EVENT_BUS.register(new ServerHelperEvents());
	}
	
	@Override
	public void preInit()
	{
		super.preInit();
	}
	
	@Override
	public void Init()
	{
		super.Init();
	}
	
	@Override
	public void postInit()
	{
		super.postInit();
	}
	
	
	
	public static void handleRenderInfoChange(String name, String transform, boolean rd)
	{
		String[] msgTxt = transform.split(":");
		
		if(transform.equals("/reset/"))
    	{
			server_Proxy.renderInfos.remove(name);
    	}
		else if(transform.equals("dab"))
		{
			if(server_Proxy.renderInfos.containsKey(name))
    		{
				List<String> list = server_Proxy.renderInfos.get(name);
				if(!list.contains("dab"))
				{
					list.add(transform);
					server_Proxy.renderInfos.replace(name, list);
				}
    		}
			else
			{
				List<String> list = Lists.newArrayList();
				list.add(transform);
				server_Proxy.renderInfos.put(name, list);
			}
		}
    	else if(transform.equals("remove_dab"))
    	{
    		if(server_Proxy.renderInfos.containsKey(name))
    		{
				List<String> list = server_Proxy.renderInfos.get(name);
				boolean e = false;
				
				for(int i = 0; i < list.size(); ++i)
				{
					if(list.get(i).startsWith("dab"))
					{
						e = true;
						list.remove(list.get(i));
						break;
					}
				}
				server_Proxy.renderInfos.replace(name, list);
    		}
    	}
    	else if(rd)
    	{
    		if(server_Proxy.renderInfos.containsKey(name))
    		{
				List<String> list = server_Proxy.renderInfos.get(name);
				boolean e = false;
				
				for(int i = 0; i < list.size(); ++i)
				{
					if(list.get(i).startsWith("disguise"))
					{
						e = true;
						list.remove(list.get(i));
						break;
					}
				}
				server_Proxy.renderInfos.replace(name, list);
    		}
    	}
    	else if(msgTxt.length == 4 || (msgTxt[0].equals("disguise") && (msgTxt.length == 3 || msgTxt.length == 2)))
		{
			if(server_Proxy.renderInfos.containsKey(name))
    		{
				List<String> list = server_Proxy.renderInfos.get(name);
				boolean e = false;
				
				for(int i = 0; i < list.size(); ++i)
				{
					if(list.get(i).startsWith(msgTxt[0]))
					{
						e = true;
						list.set(i, transform);
					}
				}
				if(!e)
				{
					list.add(transform);
				}
				server_Proxy.renderInfos.replace(name, list);
    		}
			else
			{
				List<String> list = Lists.newArrayList();
				list.add(transform);
				server_Proxy.renderInfos.put(name, list);
			}
		}
	}
}
