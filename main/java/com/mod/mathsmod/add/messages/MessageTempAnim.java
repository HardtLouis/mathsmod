package com.mod.mathsmod.add.messages;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.Entity.Rendermobs.tempAnim.TempAnim;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTempAnim implements IMessage
{
	String entityId;
	int animId;
	
	public MessageTempAnim()
	{
	}
	
	public MessageTempAnim(Entity ent, int animIdIn)
	{
		entityId = ent.getUniqueID().toString();
		animId = animIdIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entityId = ByteBufUtils.readUTF8String(buf);
		animId = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		if(entityId != null)
		{
			ByteBufUtils.writeUTF8String(buf, entityId);
		}
		buf.writeInt(animId);
	}
	
	public static class MessageAnimHandler implements IMessageHandler<MessageTempAnim, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageTempAnim msg, MessageContext ctx) 
        {
        	if(msg.animId == -2)
        	{
        		for(TempAnim anim : client_Proxy.anims)
        		{
        			if(anim.entityId == msg.entityId && anim.animIndex == 1)
        			{
        				client_Proxy.anims.remove(anim);
        			}
        		}
        	}
        	else if(msg.animId == -1)
        	{
        		for(TempAnim anim : client_Proxy.anims)
        		{
        			if(anim.entityId == msg.entityId)
        			{
        				client_Proxy.anims.remove(anim);
        			}
        		}
        	}
        	else if(msg.animId == 0)
        	{
        		client_Proxy.anims.add(new TempAnim(msg.entityId, msg.animId, 60));
        	}
        	else if(msg.animId == 1)
        	{
        		client_Proxy.anims.add(new TempAnim(msg.entityId, msg.animId, -1));
        	}
        	else if(msg.animId == 2)
        	{
        		client_Proxy.anims.add(new TempAnim(msg.entityId, msg.animId, 400));
        	}
        	else if(msg.animId == 3)
        	{
        		client_Proxy.anims.add(new TempAnim(msg.entityId, msg.animId, 40));
        	}
        	
            return null;
        }
    }
}