package com.mod.mathsmod.add.messages;

import java.util.UUID;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.guns.renderer.superHitRenderer;
import com.mod.mathsmod.utils.SoundHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSuperHit implements IMessage
{
	
	String entityId;
	int type;
	
	public MessageSuperHit()
	{
	}
	
	public MessageSuperHit(Entity ent, int typeIn)
	{
		entityId = ent.getUniqueID().toString();
		type = typeIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		type = buf.readInt();
		entityId = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(type);
		ByteBufUtils.writeUTF8String(buf, entityId);
	}
	
	public static class shHandler implements IMessageHandler <MessageSuperHit, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageSuperHit msg, MessageContext ctx) 
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	World world = mc.player.world;
        	if(world != null && UUID.fromString(msg.entityId) != null && UtilsVoids.getEntityByUUID(world, UUID.fromString(msg.entityId)) != null && mc.player != null)
        	{
        		KeyEvent.listSuperHits.add(new superHitRenderer(UtilsVoids.getEntityByUUID(world, UUID.fromString(msg.entityId)), msg.type, 20));
        		for(int i = 0; i < 5; ++i)
        		{
        			SoundHelper.play(SoundEvents.BLOCK_GLASS_BREAK, 1.2F, 1.0F);
        		}
        	}
            return null;
        }
    }
}