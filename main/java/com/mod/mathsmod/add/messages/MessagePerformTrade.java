package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHelicopter;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePerformTrade implements IMessage
{
	
	int tradeIndex;
	int id;
	
	public MessagePerformTrade(int tid, int entityID)
	{
		tradeIndex = tid;
		id = entityID;
	}
	
	public MessagePerformTrade()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		tradeIndex = buf.readInt();
		id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(tradeIndex);
		buf.writeInt(id);
	}
	
	public static class TradeHandler implements IMessageHandler <MessagePerformTrade, IMessage> {

        @Override
        public IMessage onMessage(MessagePerformTrade msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = world.getEntityByID(msg.id);
        	
        	if(!world.isRemote)
        	{
        		if(player != null && entity != null)
            	{
        			if(entity instanceof EntityHunter)
        			{
        				EntityHunter ent = (EntityHunter) entity;
        				ent.performTrade(msg.tradeIndex, player);
        			}
        			
            	}
        	}
        	
            return null;
        }
    }
}