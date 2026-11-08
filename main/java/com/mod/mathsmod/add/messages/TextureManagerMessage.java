package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.World.UtilsVoids;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TextureManagerMessage implements IMessage
{
	String link;
	int entityID;
	
	public TextureManagerMessage()
	{
		link = "bruh";
	}
	
	public TextureManagerMessage(String linkIn, int id)
	{
		link = linkIn;
		entityID = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		String s = ByteBufUtils.readUTF8String(buf);
		String[] split = s.split("!%!");
		entityID = Integer.parseInt(split[1]);
		link = split[0];
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, link + "!%!" + entityID);
	}
	
	public static class TextureManagerHandler implements IMessageHandler <TextureManagerMessage, IMessage> {

        @Override
        public IMessage onMessage(TextureManagerMessage msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = world.getEntityByID(msg.entityID);
        	
        	if(!world.isRemote)
        	{
        		if(player != null)
        		{
        			if(entity instanceof EntityPlane)
        			{
        				((EntityPlane) entity).setTexture(msg.link);
        			}
        		}
            	
            	if(player != null)
        		{
        		}
        	}
        	
            return null;
        }
    }
}