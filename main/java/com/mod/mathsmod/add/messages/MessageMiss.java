package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMiss implements IMessage
{
	
	public MessageMiss()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	public static class missHandler implements IMessageHandler <MessageMiss, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageMiss msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player.getHeldItemMainhand().getItem() instanceof ItemGun)
            	{
        			ItemGun gun = (ItemGun) player.getHeldItemMainhand().getItem();
        			if(gun.bulletUsed(player.getHeldItemMainhand(), player) != null)
        			{
        				gun.bulletUsed(player.getHeldItemMainhand(), player).onImpact(new RayTraceResult(RayTraceResult.Type.MISS, player.getPositionVector(), null, null), world, player, null, player.rotationYaw, player.rotationPitch, 0, -1, 0, player.getHeldItemMainhand(), gun, player.posX, player.posY, player.posZ, false);
        			}
            	}
        	}
        	
            return null;
        }
    }
}