package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageShowDamages implements IMessage
{
	
	Vec3d vec;
	
	public MessageShowDamages()
	{
	}
	
	public MessageShowDamages(Vec3d pos)
	{
		vec = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		vec = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeDouble(vec.x);
		buf.writeDouble(vec.y);
		buf.writeDouble(vec.z);
	}
	
	public static class gdamagesHandler implements IMessageHandler <MessageShowDamages, IMessage> {

        @Override
        public IMessage onMessage(MessageShowDamages msg, MessageContext ctx) 
        {
        	if(msg.vec != null)
        	{
        		KeyEvent.vectorF = msg.vec;
        		KeyEvent.showTicks = 30;
        	}
        	
            return null;
        }
    }
}
