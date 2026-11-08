package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.event.KeyEvent;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageModifyVelocity implements IMessage
{
	
	int type;
	Vec3d vec;
	
	public MessageModifyVelocity()
	{
	}
	
	public MessageModifyVelocity(Vec3d pos)
	{
		vec = pos;
		type = 0;
	}
	
	public MessageModifyVelocity(Vec3d vec3d, int i)
	{
		vec = vec3d;
		type = i;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		vec = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		type = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeDouble(vec.x);
		buf.writeDouble(vec.y);
		buf.writeDouble(vec.z);
		buf.writeInt(type);
	}
	
	public static class velocityHandler implements IMessageHandler <MessageModifyVelocity, IMessage> {

        @Override
        public IMessage onMessage(MessageModifyVelocity msg, MessageContext ctx) 
        {
        	if(msg.vec != null)
        	{
        		if(msg.type == 0)
        		{
            		Minecraft.getMinecraft().player.motionX = msg.vec.x;
            		Minecraft.getMinecraft().player.motionY = msg.vec.y;
            		Minecraft.getMinecraft().player.motionZ = msg.vec.z;
        		}
        		else if(msg.type == 1)
        		{
            		Minecraft.getMinecraft().player.motionX += msg.vec.x;
            		Minecraft.getMinecraft().player.motionY += msg.vec.y;
            		Minecraft.getMinecraft().player.motionZ += msg.vec.z;
        		}
        		else if(msg.type == 2)
        		{
            		Minecraft.getMinecraft().player.posX += msg.vec.x;
            		Minecraft.getMinecraft().player.posY += msg.vec.y;
            		Minecraft.getMinecraft().player.posZ += msg.vec.z;
        		}
        		else if(msg.type == 3)
        		{
            		Minecraft.getMinecraft().player.posX = msg.vec.x;
            		Minecraft.getMinecraft().player.posY = msg.vec.y;
            		Minecraft.getMinecraft().player.posZ = msg.vec.z;
        		}
        	}
        	
            return null;
        }
    }
}