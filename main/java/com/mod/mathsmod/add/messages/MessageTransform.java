package com.mod.mathsmod.add.messages;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Lists;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTransform implements IMessage
{
	String chang;
	String playerName;
	boolean rd;
	
	public MessageTransform()
	{
	}
	
	public MessageTransform(String playerNameIn, String change, boolean removeDisguise)
	{
		chang = change;
		if(change == null)
		{
			chang = "/reset/";
		}
		playerName = playerNameIn;
		rd = removeDisguise;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		playerName = ByteBufUtils.readUTF8String(buf);
		chang = ByteBufUtils.readUTF8String(buf);
		rd = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, playerName);
		ByteBufUtils.writeUTF8String(buf, chang);
		buf.writeBoolean(rd);
	}
	
	public static class MessageTransformHandler implements IMessageHandler<MessageTransform, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageTransform msg, MessageContext ctx) 
        {
        	String[] msgTxt = msg.chang.split(":");
        	
        	if(msg.chang.equals("/reset/"))
        	{
        		client_Proxy.renderInfos.remove(msg.playerName);
        	}
    		else if(msg.chang.equals("dab"))
    		{
    			if(client_Proxy.renderInfos.containsKey(msg.playerName))
        		{
    				CopyOnWriteArrayList<String> list = client_Proxy.renderInfos.get(msg.playerName);
    				if(!list.contains("dab"))
    				{
    					list.add("dab");
    					client_Proxy.renderInfos.replace(msg.playerName, list);
    				}
        		}
    			else
    			{
    				CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
    				list.add("dab");
    				client_Proxy.renderInfos.put(msg.playerName, list);
    			}
    		}
        	else if(msg.chang.equals("remove_dab"))
        	{
        		if(client_Proxy.renderInfos.containsKey(msg.playerName))
        		{
        			CopyOnWriteArrayList<String> list = client_Proxy.renderInfos.get(msg.playerName);
    				
    				list.remove("dab");
    				client_Proxy.renderInfos.replace(msg.playerName, list);
        		}
        	}
        	else if(msg.rd)
        	{
        		if(client_Proxy.renderInfos.containsKey(msg.playerName))
        		{
        			CopyOnWriteArrayList<String> list = client_Proxy.renderInfos.get(msg.playerName);
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
    				client_Proxy.renderInfos.replace(msg.playerName, list);
        		}
        	}
        	else if(msgTxt.length == 4 || (msgTxt[0].equals("disguise") && (msgTxt.length == 3 || msgTxt.length == 2)))
    		{
    			if(client_Proxy.renderInfos.containsKey(msg.playerName))
        		{
    				CopyOnWriteArrayList<String> list = client_Proxy.renderInfos.get(msg.playerName);
    				boolean e = false;
    				
    				for(int i = 0; i < list.size(); ++i)
    				{
    					if(list.get(i).startsWith(msgTxt[0]))
    					{
    						e = true;
    						list.set(i, msg.chang);
    					}
    				}
    				if(!e)
    				{
    					list.add(msg.chang);
    				}
    				client_Proxy.renderInfos.replace(msg.playerName, list);
        		}
    			else
    			{
    				CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
    				list.add(msg.chang);
    				client_Proxy.renderInfos.put(msg.playerName, list);
    			}
    		}
        	
            return null;
        }
    }
}