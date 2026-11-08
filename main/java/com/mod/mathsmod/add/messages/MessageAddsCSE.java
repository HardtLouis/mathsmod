package com.mod.mathsmod.add.messages;

import java.util.Random;

import com.mod.mathsmod.add.CSE.ClientSideEnt;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAddsCSE implements IMessage
{
	
	String name;
	float speed;
	int num;
	int type;
	float spread;
	float yOffset;
	
	float rotationYaw;
	float rotationPitch;
	
	public MessageAddsCSE()
	{
	}
	
	public MessageAddsCSE(EntityPlayer owner, float speedIn, int nombre, float spreadIn, int typeIn, float yOffsetIn)
	{
		name = owner.getName();
		speed = speedIn;
		num = nombre;
		spread = spreadIn;
		type = typeIn;
		yOffset = yOffsetIn;
		
		rotationYaw = owner.rotationYaw;
		rotationPitch = owner.rotationPitch;
	}
	
	public MessageAddsCSE(EntityPlayer owner, float rotationPitchIn, float rotationYawIn, float speedIn, int nombre, float spreadIn, int typeIn, float yOffsetIn)
	{
		name = owner.getName();
		speed = speedIn;
		num = nombre;
		spread = spreadIn;
		type = typeIn;
		yOffset = yOffsetIn;
		
		rotationYaw = rotationYawIn;
		rotationPitch = rotationPitchIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		speed = buf.readFloat();
		spread = buf.readFloat();
		yOffset = buf.readFloat();
		rotationYaw = buf.readFloat();
		rotationPitch = buf.readFloat();
		num = buf.readInt();
		type = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeFloat(speed);
		buf.writeFloat(spread);
		buf.writeFloat(yOffset);
		buf.writeFloat(rotationYaw);
		buf.writeFloat(rotationPitch);
		buf.writeInt(num);
		buf.writeInt(type);
		ByteBufUtils.writeUTF8String(buf, name);
	}
	
	public static class cseHandler implements IMessageHandler <MessageAddsCSE, IMessage> {

        @Override
        public IMessage onMessage(MessageAddsCSE msg, MessageContext ctx)
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	World world = mc.player.world;
        	if(world.getPlayerEntityByName(msg.name) != null && mc.player.dimension == world.getPlayerEntityByName(msg.name).dimension && (mc.player != world.getPlayerEntityByName(msg.name)))
        	{
        		EntityPlayer player = world.getPlayerEntityByName(msg.name);
        		for(int i = 0; i < msg.num; ++i)
        		{
        			Random rand = new Random();
                	float x = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)msg.spread);
                	float y = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)msg.spread);
                	float z = (float) (rand.nextGaussian() * 0.007499999832361937D * (double)msg.spread);
                	
        			Vec3d vec = UtilsVoids.laserWithInnacuracy(msg.speed, msg.rotationYaw, msg.rotationPitch, x, y, z);
        			ClientSideEnt cse = new ClientSideEnt(player.getPositionEyes(1.0F).addVector(0, msg.yOffset, 0), vec, vec, msg.name, 0, null, msg.rotationPitch, msg.rotationYaw, msg.type == 1 || msg.type == 4 ? 40 : 20, player.dimension);
            		cse.type = msg.type;
        			client_Proxy.CSEL.add(cse);
        		}
        	}
            return null;
        }
    }
}