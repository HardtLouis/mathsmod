package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;
import com.mod.mathsmod.add.items.ISpecialRightClickItem;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRightClickMachineGun implements IMessage
{
	
	int ticks;
	
	public MessageRightClickMachineGun()
	{
	}
	
	public MessageRightClickMachineGun(int ticksIn)
	{
		ticks = ticksIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		ticks = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(ticks);
	}
	
	public static class rightClickGunHandler implements IMessageHandler <MessageRightClickMachineGun, IMessage> 
	{

        @Override
        public IMessage onMessage(MessageRightClickMachineGun msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player != null && world != null)
            	{
        			if(player.getRidingEntity() != null && player.getRidingEntity() instanceof IVehicle)
        			{
        				return null;
        			}
            		if(player.getHeldItemMainhand().getItem() != null && player.getHeldItemMainhand().getItem() instanceof ISpecialRightClickItem)
            		{
            			ISpecialRightClickItem item = (ISpecialRightClickItem) player.getHeldItemMainhand().getItem();
            			item.onRightClickPressed(world, player, msg.ticks);
            		}
            	}
        	}
        	
            return null;
        }
    }
}