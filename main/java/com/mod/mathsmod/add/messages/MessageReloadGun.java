package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageReloadGun implements IMessage
{
	
	public MessageReloadGun()
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
	
	public static class MessageReloadHandler implements IMessageHandler <MessageReloadGun, IMessage> {

        @Override
        public IMessage onMessage(MessageReloadGun msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player != null)
            	{
            		if(player.getHeldItemMainhand().getItem() instanceof ItemGun)
            		{
            			ItemGun item = (ItemGun) player.getHeldItemMainhand().getItem();
            			item.setReloading(player.getHeldItemMainhand(), true);
            			item.setUsed(player.getHeldItemMainhand(), 0);
            		}
            	}
        	}
        	
            return null;
        }
    }
	
}
