package com.mod.mathsmod.add.messages;

import java.util.Random;

import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.proxy.client_Proxy;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlaySound implements IMessage
{
	
	String sound;
	float volume;
	float pitch;
	Vec3d pos;
	int type;
	
	public MessagePlaySound()
	{
	}
	
	public MessagePlaySound(Vec3d posIn, float volumeIn, float pitchIn, String soundId, int typeIn)
	{
		pos = posIn;
		sound = soundId;
		volume = volumeIn;
		pitch = pitchIn;
		type = typeIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		volume = buf.readFloat();
		pitch = buf.readFloat();
		type = buf.readInt();
		sound = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeDouble(pos.x);
		buf.writeDouble(pos.y);
		buf.writeDouble(pos.z);
		buf.writeFloat(volume);
		buf.writeFloat(pitch);
		buf.writeInt(type);
		ByteBufUtils.writeUTF8String(buf, sound);
	}
	
	public static class soundHandler implements IMessageHandler <MessagePlaySound, IMessage> 
	{
        @Override
        public IMessage onMessage(MessagePlaySound msg, MessageContext ctx) 
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	if(mc.player != null && mc.world != null)
        	{
        		SoundEvent sound = SoundEvent.REGISTRY.getObject(new ResourceLocation(msg.sound));
    			if(msg.type == 0 && sound != null)
    			{
            		mc.world.playSound(null, msg.pos.x, msg.pos.y, msg.pos.z, sound, SoundCategory.AMBIENT, msg.volume, msg.pitch);
    			}
    			else if(msg.type == 1)
    			{
    				Random rand = new Random();
    				int r1 = rand.nextInt(17);
    				for(int i = 0; i < 1000; ++i)
    				{
    					int r2 = r1;
    					if(r1 == 16)
    					{
    						r2 = rand.nextInt(16);
    					}
    					mc.world.spawnAlwaysVisibleParticle(61, msg.pos.x, msg.pos.y, msg.pos.z, (rand.nextFloat() - 0.5F) * 2, (rand.nextFloat() - 0.5F) * 2, (rand.nextFloat() - 0.5F) * 2, Blocks.WOOL.getStateId(Blocks.WOOL.getStateFromMeta(r2)), 20 + rand.nextInt(10));
    				}
    			}
    		}
            return null;
        }
    }
}