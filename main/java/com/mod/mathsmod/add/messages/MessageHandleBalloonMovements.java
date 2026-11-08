package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAirBalloon;
import com.mod.mathsmod.add.World.UtilsVoids;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandleBalloonMovements implements IMessage
{
	
	int forward;
	int up;
	int id;
	int dismount;
	
	public MessageHandleBalloonMovements(int forwardIn, int upIn, int dismountIn, int entityId)
	{
		forward = forwardIn;
		up = upIn;
		id = entityId;
		dismount = dismountIn;
	}
	
	public MessageHandleBalloonMovements()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		forward = buf.readInt();
		up = buf.readInt();
		dismount = buf.readInt();
		id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(forward);
		buf.writeInt(up);
		buf.writeInt(dismount);
		buf.writeInt(id);
	}
	
	public static class BalloonHandler implements IMessageHandler <MessageHandleBalloonMovements, IMessage> {

        @Override
        public IMessage onMessage(MessageHandleBalloonMovements msg, MessageContext ctx) 
        {
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = world.getEntityByID(msg.id);
        	
        	if(!world.isRemote)
        	{
        		if(player != null && entity != null && entity instanceof EntityAirBalloon && entity.getControllingPassenger() == player)
            	{
        			EntityAirBalloon balloon = (EntityAirBalloon) entity;
        			if(msg.up > 0)
        			{
        				if(balloon.getMontee() < 0.1)
        				{
            				balloon.setMontee(balloon.getMontee() + 0.001F);
        				}
        			}
        			
        			else if(msg.up < 0)
        			{
        				if(balloon.getMontee() > -0.05)
        				{
        					balloon.setMontee(balloon.getMontee() - 0.001F);
        				}
        			}
        			
        			if(msg.forward > 0)
        			{
        				
        				double dy = (balloon.rotationYaw - player.rotationYaw) % 360;
        				
						if(Math.abs(dy) < balloon.manSpeed())
						{
							balloon.rotationYaw = (float) player.rotationYaw;
						}
						else
						{
							if(dy < 0)
							{
								balloon.rotationYaw += balloon.manSpeed();
							}
							else if(dy > 0)
							{
								balloon.rotationYaw -= balloon.manSpeed();
							}
						}
						
        				Vec3d vel = UtilsVoids.laser(0.001, balloon.rotationYaw, 0);
        				Vec3d velBalloon = new Vec3d(balloon.motionX, 0, balloon.motionZ);
        				Vec3d trueVec = velBalloon.add(vel);
        				if(trueVec.lengthVector() > 0.2)
        				{
        					trueVec = trueVec.normalize().scale(0.2);
        				}
        				
        				balloon.motionX = trueVec.x;
        				balloon.motionZ = trueVec.z;
        			}
        			
        			if(msg.dismount != 0)
        			{
        				player.dismountRidingEntity();
        			}
            	}
        	}
        	
            return null;
        }
    }
}