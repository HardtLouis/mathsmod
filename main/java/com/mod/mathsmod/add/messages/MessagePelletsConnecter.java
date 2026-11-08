package com.mod.mathsmod.add.messages;

import java.util.UUID;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.IPelletConnecter;
import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePelletsConnecter implements IMessage
{
	int pm;
	int entId;
	
	public MessagePelletsConnecter()
	{
	}
	
	public MessagePelletsConnecter(int pelletsMissed, Entity target)
	{
		pm = pelletsMissed;
		if(target != null)
		{
			entId = target.getEntityId();
		}
		else
		{
			entId = -1;
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		pm = buf.readInt();
		entId = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(pm);
		buf.writeInt(entId);
	}
	
	public static class pelletsHandler implements IMessageHandler <MessagePelletsConnecter, IMessage> 
	{
        @Override
        public IMessage onMessage(MessagePelletsConnecter msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	
        	if(!world.isRemote && msg.entId > 0)
        	{
        		Entity entity = world.getEntityByID(msg.entId);
        		if(player.getHeldItemMainhand().getItem() instanceof IPelletConnecter && entity != null)
            	{
        			IPelletConnecter gun = (IPelletConnecter) player.getHeldItemMainhand().getItem();
        			gun.onPelletsHit(player.getHeldItemMainhand(), player, entity, msg.pm);
            	}
        	}
        	
            return null;
        }
    }
}