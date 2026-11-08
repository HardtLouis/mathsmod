package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.EntityBeluga;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHelicopter;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHelicopter implements IMessage
{
	
	int flag;
	
	public MessageHelicopter(int flagIn)
	{
		flag = MathHelper.clamp(flagIn, 0, 200);
	}
	
	public MessageHelicopter()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		flag = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(flag);
	}
	
	public static class HelicoHandler implements IMessageHandler <MessageHelicopter, IMessage> {

        @Override
        public IMessage onMessage(MessageHelicopter msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = ctx.getServerHandler().player.getRidingEntity();
        	
        	if(!world.isRemote)
        	{
        		if(player != null && entity != null)
            	{
        			if(entity instanceof EntityHelicopter && entity.getControllingPassenger() == player)
        			{
        				EntityHelicopter ent = (EntityHelicopter) entity;
        				if(ent.getFuel() > 0)
        				{
        					if(msg.flag == 0)
        					{
        						ent.motionY -= 0.02D;
        					}
        					else if(msg.flag == 1)
        					{
        						if(ent.getOnGroundSync())
        						{
        							ent.timeOnGround = 0;
        						}
        						if(ent.boostToStart >= 1.0F)
        						{
        							ent.motionY += 0.006D;
        						}
        						else
        						{
        							ent.boostToStart = MathHelper.clamp(ent.boostToStart + 0.05F, 0, 1);
        						}
        					}
        				}
            			if(msg.flag == 2)
            			{
            				if(ent.canShoot())
            				{
            					ent.cooldown = 0;
            					world.playSound((EntityPlayer) null, ent.posX, ent.posY, ent.posZ, MathsSounds.AR15, SoundCategory.AMBIENT, 10, 0.8F);
            					//ent.Shoot(player);
            				}
            			}
        			}
        			if(entity instanceof EntityBeluga && ((EntityBeluga) entity).cooldown <= 0)
        			{
        				((EntityBeluga) entity).setAttacking(100);
        			}
            	}
        	}
        	
            return null;
        }
    }
}