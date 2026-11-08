package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.items.ILeftClickItem;
import com.mod.mathsmod.add.items.ItemFryingPan;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LeftClickGun implements IMessage
{
	
	int pressTicks;
	
	public LeftClickGun(int ticks)
	{
		pressTicks = MathHelper.clamp(ticks, 0, 200);
	}
	
	public LeftClickGun()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		pressTicks = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(pressTicks);
	}
	
	public static class LeftClickGunHandler implements IMessageHandler <LeftClickGun, IMessage> {

        @Override
        public IMessage onMessage(LeftClickGun msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player != null)
            	{
            		if(player.getHeldItemMainhand().getItem() instanceof ILeftClickItem)
            		{
            			ILeftClickItem item = (ILeftClickItem) player.getHeldItemMainhand().getItem();
            			item.onItemLeftClick(world, player, msg.pressTicks);
            		}
            		else if(player.getHeldItemMainhand().getItem() instanceof ItemFryingPan)
            		{
            			ItemFryingPan item = (ItemFryingPan) player.getHeldItemMainhand().getItem();
            			item.onItemLeftClick(world, player, msg.pressTicks);
            		}
            		else if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemUberArmor && 
            				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemUberArmor && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemUberArmor)
            		{
            			ItemUberArmor item = (ItemUberArmor) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
            			
            			if(player.getHeldItemMainhand().isEmpty())
            			{
            				item.onLeftClickEmptyHand(player, item, player.getItemStackFromSlot(EntityEquipmentSlot.CHEST), msg.pressTicks != 0);
            			}
            		}
            	}
        	}
        	
            return null;
        }
    }
}