package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree.Competence;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.NumsHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBotChanges implements IMessage
{
	int index;
	int context;
	String uuid;
	
	public MessageBotChanges()
	{
	}
	
	public MessageBotChanges(String entityID, int contextIn, int indexIn)
	{
		context = contextIn;
		index = indexIn;
		uuid = entityID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		String s = ByteBufUtils.readUTF8String(buf);
		
		if(s != null)
		{
			String[] numbers = s.split("//");
			if(numbers.length >= 3)
			{
				uuid = numbers[0];
				context = NumsHelper.getIntFromString(numbers[1]);
				index = NumsHelper.getIntFromString(numbers[2]);
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, uuid + "//" + context + "//" + index);
	}
	
	public static class EntityMessageHandler implements IMessageHandler <MessageBotChanges, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageBotChanges msg, MessageContext ctx) 
        {
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	EntityBot entity = null;

        	if(msg.uuid != null && !msg.uuid.isEmpty())
    		{
    			for(Entity entity2 : UtilsVoids.getEntities(world, player, 40))
    			{
    				if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityBot)
    				{
    					entity = (EntityBot) entity2;
    				}
    			}
    		}
        	
    		if(entity != null && (entity instanceof EntityBot && (((EntityBot) entity).getOwner() == player || player.capabilities.isCreativeMode)))
			{
    			if(msg.context == 0)
    			{
    				entity.setBasePos(player.getPositionVector());
					System.out.print("bot(id:" + msg.uuid + ") has changed his pos (" + player.getPositionVector().toString() + ")");
					System.out.print(System.lineSeparator());
    			}
    			
    			if(msg.context == 1)
    			{
    				entity.setFriendly(!entity.isFriendly());
    				if(entity.isFriendly())
    				{
    					if(entity.getAttackTarget() != null && (entity.getAttackTarget() instanceof EntityPlayer || entity.getAttackTarget() instanceof EntityBot))
    					{
    						entity.setAttackTarget(null);
    					}
    					
    					player.sendStatusMessage(new TextComponentString("your bot is friendly"), true);
    				}
    				else
    				{
    					player.sendStatusMessage(new TextComponentString("your bot is aggressive"), true);
    				}
    			}
			}
        	
            return null;
        }
    }
}