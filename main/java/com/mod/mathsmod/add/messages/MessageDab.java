package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.guns.ItemMedigun;
import com.mod.mathsmod.proxy.server_Proxy;
import com.mod.mathsmod.utils.playerNbtManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDab implements IMessage
{
	
	boolean dab;
	boolean playSound;
	
	public MessageDab(boolean dabIn, boolean playSoundIn)
	{
		dab = dabIn;
		playSound = playSoundIn;
	}
	
	public MessageDab()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		dab = buf.readBoolean();
		playSound = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(dab);
		buf.writeBoolean(playSound);
	}
	
	public static class dabHandler implements IMessageHandler <MessageDab, IMessage> {

        @Override
        public IMessage onMessage(MessageDab msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(player != null)
        	{
        		if(msg.dab)
        		{
        			Mathsmod.network.sendToAll(new MessageTransform(player.getName(), "dab", false));
        			server_Proxy.handleRenderInfoChange(player.getName(), "dab", false);
        			if(msg.playSound)
        			{
        				if(player.getHeldItemMainhand().getItem() == RegisterItems.FPC)
        				{
        					Mathsmod.network.sendToAll(new MessagePlaySound(player.getPositionEyes(1.0F), 4.0F, 10.0F, MathsSounds.CLOWN.getSoundName().toString(), 1));
        				}
        				else if(player.getHeldItemMainhand().getItem() instanceof ItemMedigun)
        				{
        					player.world.playSound( ((EntityPlayer) null), player.posX,  player.posY,  player.posZ, MathsSounds.MEEM, SoundCategory.AMBIENT, 3, 1);
        				}
        				else
        				{
        					player.world.playSound( ((EntityPlayer) null), player.posX,  player.posY,  player.posZ, MathsSounds.DAB, SoundCategory.AMBIENT, 3, 1);
        				}
        			}
        		}
        		else
        		{
        			Mathsmod.network.sendToAll(new MessageTransform(player.getName(), "remove_dab", false));
        			server_Proxy.handleRenderInfoChange(player.getName(), "remove_dab", false);
        		}
        	}
        	
            return null;
        }
    }
}