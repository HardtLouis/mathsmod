package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.World.UtilsVoids;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTrackPlayer implements IMessage
{
	
	String playerName;
	
	public MessageTrackPlayer(String nameIn)
	{
		playerName = nameIn;
	}
	
	public MessageTrackPlayer()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		playerName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, playerName);
	}
	
	public static class trackHandler implements IMessageHandler <MessageTrackPlayer, IMessage> {

        @Override
        public IMessage onMessage(MessageTrackPlayer msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(player != null)
        	{
        		if(world.getPlayerEntityByName(msg.playerName) != null)
        		{
        			EntityPlayer target = world.getPlayerEntityByName(msg.playerName);
        			
        			if(UtilsVoids.distanceIgnoreY(target.posX, target.posZ, player) < 60)
        			{
        				target.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 200));
        			}
        			
        			target.sendMessage(new TextComponentString(TextFormatting.DARK_RED + player.getName() + " tracks you"));
        			player.sendMessage(new TextComponentString(TextFormatting.GREEN + "tracked " + target.getName() + ("/distance:" + UtilsVoids.distanceIgnoreY(target.posX, target.posZ, player))));
        		}
        	}
        	
            return null;
        }
    }
}