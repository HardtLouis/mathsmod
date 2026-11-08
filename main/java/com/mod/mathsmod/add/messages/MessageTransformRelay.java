package com.mod.mathsmod.add.messages;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.proxy.server_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class MessageTransformRelay implements IMessage
{
	String chang;
	String playerName;
	boolean rd;
	
	public MessageTransformRelay()
	{
	}
	
	public MessageTransformRelay(String playerNameIn, String change, boolean removeDisguise)
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
	
	public static class RelayHandler implements IMessageHandler<MessageTransformRelay, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageTransformRelay msg, MessageContext ctx) 
        {
        	String[] strl = msg.chang.split(":");
        	if(strl.length <= 2)
        	{
        		strl = new String[] {"disguise", " ", " "};
        	}
        	
        	EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(strl[1], strl[2]));
        	if(strl[0].equals("disguise"))
	        {
        		if((entry != null && entry.newInstance(ctx.getServerHandler().player.world) != null && entry.newInstance(ctx.getServerHandler().player.world).height >= 0.5F && (entry.newInstance(ctx.getServerHandler().player.world) instanceof EntityLivingBase || entry.newInstance(ctx.getServerHandler().player.world) instanceof IVehicle || entry.newInstance(ctx.getServerHandler().player.world) instanceof EntityBoat || entry.newInstance(ctx.getServerHandler().player.world) instanceof EntityMinecart)) || entry == null || msg.rd || msg.chang.equals("/reset/"))
	        	{
	            	server_Proxy.handleRenderInfoChange(msg.playerName, msg.chang, msg.rd);
	            	Mathsmod.network.sendToAll(new MessageTransform(msg.playerName, msg.chang, msg.rd));
	        	}
        	}
        	else
        	{
            	server_Proxy.handleRenderInfoChange(msg.playerName, msg.chang, msg.rd);
            	Mathsmod.network.sendToAll(new MessageTransform(msg.playerName, msg.chang, msg.rd));
        	}
        	
            return null;
        }
    }
}