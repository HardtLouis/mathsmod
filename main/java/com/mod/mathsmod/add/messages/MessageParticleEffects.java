package com.mod.mathsmod.add.messages;

import java.util.Set;

import com.google.common.collect.Sets;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageParticleEffects implements IMessage
{
	int id;
	float sizeIn;
	Vec3d pos;
	
	public MessageParticleEffects(int pid, Vec3d posIn, float size)
	{
		id = pid;
		sizeIn = size;
		pos = posIn;
	}
	
	public MessageParticleEffects()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		id = buf.readInt();
		sizeIn = buf.readFloat();
		pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(id);
		buf.writeFloat(sizeIn);
		buf.writeDouble(pos.x);
		buf.writeDouble(pos.y);
		buf.writeDouble(pos.z);
	}
	
	public static class ParticleHandler implements IMessageHandler <MessageParticleEffects, IMessage>
	{

        @Override
        public IMessage onMessage(MessageParticleEffects msg, MessageContext ctx) 
        {
        	Minecraft mc = Minecraft.getMinecraft();
        	if(mc.world != null)
        	{
        		if(msg.id == 0)
        		{
        			Set<BlockPos> set = Sets.<BlockPos>newHashSet();
        			
        			mc.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, msg.pos.x, msg.pos.y, msg.pos.z, 0, 0, 0);
        			
        			for(int j = 0; j < 10 * msg.sizeIn + 10; ++j)
					{
    					mc.world.spawnParticle(EnumParticleTypes.FLAME, msg.pos.x, msg.pos.y, msg.pos.z, (mc.world.rand.nextDouble() - 0.5F) * 2 * msg.sizeIn, (mc.world.rand.nextDouble() - 0.5F) * 2 * msg.sizeIn, (mc.world.rand.nextDouble() - 0.5F) * 2 * msg.sizeIn);
    					mc.world.spawnParticle(EnumParticleTypes.CLOUD, msg.pos.x, msg.pos.y, msg.pos.z, (mc.world.rand.nextDouble() - 0.5F) * msg.sizeIn, (mc.world.rand.nextDouble() - 0.5F) * msg.sizeIn, (mc.world.rand.nextDouble() - 0.5F) * msg.sizeIn);
					}
        			
        		}
        		else if(msg.id == 1)
        		{
        			for(int i = 0; i < 20; i++)
        			{
        				mc.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, msg.pos.x, msg.pos.y + 0.25F, msg.pos.z, mc.world.rand.nextDouble() * 3 - 1.5, mc.world.rand.nextDouble() * 3 - 1.5, mc.world.rand.nextDouble() * 3 - 1.5, Block.getIdFromBlock(Blocks.PACKED_ICE));
        			}            		
        		}
        	}
        	
            return null;
        }
    }
}