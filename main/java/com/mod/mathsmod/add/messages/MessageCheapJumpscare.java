package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.guns.ItemMedigun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCheapJumpscare implements IMessage
{
	
	public MessageCheapJumpscare()
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
	
	public static class dabHandler implements IMessageHandler <MessageCheapJumpscare, IMessage> {

        @Override
        public IMessage onMessage(MessageCheapJumpscare msg, MessageContext ctx)
        {
        	KeyEvent.jumpscareTicks = 40;
            return null;
        }
    }
}