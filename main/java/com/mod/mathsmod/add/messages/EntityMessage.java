package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Entity.EntityMateGolem;
import com.mod.mathsmod.add.Entity.Entity.mateCompetenceTree.Competence;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityCameraDrone;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGlider;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityUMissile;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.utils.NumsHelper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityMessage implements IMessage
{
	String skin;
	String uuid;
	boolean sentryMessage;
	
	public EntityMessage()
	{
	}
	
	public EntityMessage(String entityID, int golemUpgrade)
	{
		skin = golemUpgrade + "";
		uuid = entityID;
	}
	
	public EntityMessage(String entityID, String playerSkin)
	{
		skin = playerSkin;
		uuid = entityID;
	}
	
	public EntityMessage(String entityID, boolean isSentryMessage)
	{
		sentryMessage = isSentryMessage;
		uuid = entityID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		String s = ByteBufUtils.readUTF8String(buf);
		
		if(s != null)
		{
			String[] numbers = s.split("//");
			
			uuid = numbers[0];
			
			if(numbers.length >= 2)
			{
				if(!NumsHelper.isInteger(numbers[1]))
				{
					skin = numbers[1];
					sentryMessage = numbers[1].equals("true");
				}
				else
				{
					skin = numbers[1];
				}
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		if(!sentryMessage)
		{
			//Flemme de changer
			String str = skin;
			String aa = uuid;
			if(aa != "//" && str != "//")
			{
				ByteBufUtils.writeUTF8String(buf, aa + "//" + str);
			}
		}
		else
		{
			ByteBufUtils.writeUTF8String(buf, uuid + "//" + "true");
		}
	}
	
	public static class EntityMessageHandler implements IMessageHandler <EntityMessage, IMessage> 
	{
        @Override
        public IMessage onMessage(EntityMessage msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = null;
        	
        	
        	if(msg.uuid != null && !msg.uuid.isEmpty())
    		{
    			for(Entity entity2 : UtilsVoids.getEntities(world, player, 40))
    			{
    				if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityMateGolem)
    				{
    					entity = entity2;
    				}
    				if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityBot)
    				{
    					entity = entity2;
    				}
    				else if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityCameraDrone)
    				{
    					entity = entity2;
    				}
    				else if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityUMissile)
    				{
    					entity = entity2;
    				}
    				else if(entity2.getUniqueID().toString().equals(msg.uuid) && entity2 instanceof EntityGlider)
    				{
    					entity = entity2;
    				}
    			}
    		}
        	if(entity != null && player != null && entity instanceof EntityMateGolem)
    		{
        		if(NumsHelper.isInteger(msg.skin))
        		{
        			int upgradeIndex = NumsHelper.getIntFromString(msg.skin);
        			if(((EntityMateGolem) entity).canHaveCompetence(Competence.COMPETENCES.get(upgradeIndex), player))
    				{
    					((EntityMateGolem) entity).addComp(upgradeIndex);
    					((EntityMateGolem) entity).addPoints(-Competence.COMPETENCES.get(upgradeIndex).points);
    					System.out.print(player.getName() + " added " + Competence.COMPETENCES.get(upgradeIndex).ID + " to golem(id:" + msg.uuid + ")");
    					System.out.print(System.lineSeparator());
    				}
        		}
        		
        	}
        	else if(entity instanceof EntityBot && (((EntityBot) entity).getOwner() == player || player.capabilities.isCreativeMode))
			{
				if(msg.sentryMessage)
				{
					((EntityBot) entity).isSentry = !((EntityBot) entity).isSentry;
					System.out.print("bot(id:" + msg.uuid + ") has changes of mod");
					System.out.print(System.lineSeparator());
					if(((EntityBot) entity).isSentry)
					{
						player.sendStatusMessage(new TextComponentString("bot (id:" + msg.uuid + ")" + " is sentry"), true);
					}
					else
					{
						player.sendStatusMessage(new TextComponentString("bot (id:" + msg.uuid + ")" + " is following"), true);
					}
				}
				else if(!msg.skin.isEmpty())
				{
					((EntityBot) entity).setSkin(msg.skin);
					System.out.print("set " + msg.skin + "'s skin to bot(id:" + msg.uuid + ")");
					System.out.print(System.lineSeparator());
				}
			}
    		else if(entity instanceof EntityCameraDrone)
    		{
    			entity.setDead();
    			world.spawnEntity(new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(RegisterItems.CAMERA_DRONE)));
    		}
    		else if(entity instanceof EntityUMissile)
    		{
    			((EntityUMissile) entity).setControlled(false);
    		}
        	
            return null;
        }
    }
}