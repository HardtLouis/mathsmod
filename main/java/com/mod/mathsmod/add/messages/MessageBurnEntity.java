package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemFlameThrower;
import com.mod.mathsmod.utils.MDamageSource;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBurnEntity implements IMessage
{
	
	int entityId;
	boolean flameThrower;
	float amount;
	
	
	public MessageBurnEntity() 
	{
		entityId = -1;
	}
	
	public MessageBurnEntity(Entity entity, boolean flameThrowerIn, float amountIn) 
	{
		entityId = entity.getEntityId();
		flameThrower = flameThrowerIn;
		amount = amountIn;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		entityId = buf.readInt();
		flameThrower = buf.readBoolean();
		amount = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(entityId);
		buf.writeBoolean(flameThrower);
		buf.writeFloat(amount);
	}
	
	public static class MessageBurnHandler implements IMessageHandler <MessageBurnEntity, IMessage>
	{

        @Override
        public IMessage onMessage(MessageBurnEntity msg, MessageContext ctx)
        {
        	EntityPlayer player = ctx.getServerHandler().player;
        	World world = ctx.getServerHandler().player.world;
        	
        	if(msg.flameThrower && msg.entityId != -1 && player.getHeldItemMainhand().getItem() instanceof ItemFlameThrower)
        	{
        		ItemFlameThrower item = (ItemFlameThrower) player.getHeldItemMainhand().getItem();
        		item.damageEntity(world.getEntityByID(msg.entityId), player.getHeldItemMainhand(), item, player);
        	}
        	else if(!msg.flameThrower && msg.entityId != -1)
        	{
        		if(world.getEntityByID(msg.entityId) != null && world.getEntityByID(msg.entityId) instanceof EntityLivingBase)
        		{
            		UtilsVoids.attackEntityFrom((EntityLivingBase) world.getEntityByID(msg.entityId), MDamageSource.FLAME_THROWER, msg.amount);
        		}
        		
        	}
			return null;
        }
	}
	
}
