package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemFlameThrower;
import com.mod.mathsmod.add.items.guns.ItemMinigun;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageInfiniteBullet implements IMessage
{
	boolean infinite;
	
	public MessageInfiniteBullet() 
	{
		infinite = false;
	}
	
	public MessageInfiniteBullet(boolean inf) 
	{
		infinite = inf;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		infinite = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(infinite);
	}
	
	public static class MessageIBHandler implements IMessageHandler <MessageInfiniteBullet, IMessage>
	{
        @Override
        public IMessage onMessage(MessageInfiniteBullet msg, MessageContext ctx)
        {
        	if(Minecraft.getMinecraft().player.getHeldItemMainhand() != null && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemMinigun)
        	{
        		ItemMinigun.setUseIB(Minecraft.getMinecraft().player.getHeldItemMainhand(), msg.infinite);
        	}
			return null;
        }
	}
	
}