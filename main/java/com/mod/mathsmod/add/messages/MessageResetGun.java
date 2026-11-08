package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageResetGun implements IMessage
{
	
	public MessageResetGun()
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
	
	public static class resetGunHandler implements IMessageHandler <MessageResetGun, IMessage> {

        @Override
        public IMessage onMessage(MessageResetGun msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player.getHeldItemMainhand().getItem() instanceof ItemGun && !player.getCooldownTracker().hasCooldown(player.getHeldItemMainhand().getItem()))
            	{
            		if(!((ItemGun) player.getHeldItemMainhand().getItem()).getReset(player.getHeldItemMainhand()))
            		{
            			((ItemGun) player.getHeldItemMainhand().getItem()).setReset(player.getHeldItemMainhand(), true);
            		}
            	}
        	}
        	
            return null;
        }
    }
}
