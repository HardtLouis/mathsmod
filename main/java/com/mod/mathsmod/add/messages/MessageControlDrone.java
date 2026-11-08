package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityCameraDrone;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityUMissile;
import com.mod.mathsmod.add.World.UtilsVoids;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageControlDrone implements IMessage
{
	
	int forward;
	int y;
	int entityID;
	double deltaX;
	double deltaY;
	
	public MessageControlDrone(int entity, int forwardIn, int yIn, double deltaXIn, double deltaYIn)
	{
		forward = forwardIn;
		y = yIn;
		entityID = entity;
		deltaX = deltaXIn;
		deltaY = deltaYIn;
	}
	
	public MessageControlDrone()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		forward = buf.readInt();
		y = buf.readInt();
		entityID = buf.readInt();
		deltaX = buf.readDouble();
		deltaY = buf.readDouble();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(forward);
		buf.writeInt(y);
		buf.writeInt(entityID);
		buf.writeDouble(deltaX);
		buf.writeDouble(deltaY);
	}
	
	public static class droneHandler implements IMessageHandler <MessageControlDrone, IMessage> {

        @Override
        public IMessage onMessage(MessageControlDrone msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(player != null)
        	{
        		if(world.getEntityByID(msg.entityID) != null && world.getEntityByID(msg.entityID) instanceof EntityCameraDrone)
            	{
            		EntityCameraDrone entity = (EntityCameraDrone) world.getEntityByID(msg.entityID);
            		
            		if(entity.getOwner() == player)
            		{
                		entity.rotationPitch -= msg.deltaX;
                		entity.rotationYaw += msg.deltaY;
                		entity.moveRelative(0, msg.y, msg.forward, 0.3F);
                		
                		if(entity.getDistance(player) > 300)
            			{
                			entity.setDead();
            			}
            		}
            	}
        		else if(world.getEntityByID(msg.entityID) != null && world.getEntityByID(msg.entityID) instanceof EntityUMissile)
        		{
        			EntityUMissile entity = (EntityUMissile) world.getEntityByID(msg.entityID);
            		
        			if(entity.getOwner() == player && entity.isControlled())
        			{
                		entity.rotationPitch -= msg.deltaX;
                		entity.rotationYaw += msg.deltaY;
                		
                		if(entity.getDistance(player) > entity.getControlDistance())
            			{
                			entity.setControlled(false);
            			}
        			}
        		}
        	}
        	
            return null;
        }
    }
}