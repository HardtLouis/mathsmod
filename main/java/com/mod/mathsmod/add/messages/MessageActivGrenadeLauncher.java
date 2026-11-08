package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.accessories.GunAccessoryBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageActivGrenadeLauncher implements IMessage
{
	
	public MessageActivGrenadeLauncher()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
	}
	
	public static class MiddleClickGunHandler implements IMessageHandler <MessageActivGrenadeLauncher, IMessage> {

        @Override
        public IMessage onMessage(MessageActivGrenadeLauncher msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	if(!world.isRemote)
        	{
        		if(player != null)
            	{
            		if(player.getHeldItemMainhand().getItem() instanceof ItemMachineGun)
            		{
            			ItemStack stack = player.getHeldItemMainhand();
            			ItemMachineGun item = (ItemMachineGun) player.getHeldItemMainhand().getItem();
            			
            			if(item.canEquipModifier() && !item.getModifiers(stack).get(0).isEmpty())
            			{
            				ItemStack modifier = item.getModifiers(stack).get(0);
            				if(modifier.getItem() instanceof GunAccessoryBase)
            				{
            					GunAccessoryBase gl = (GunAccessoryBase) modifier.getItem();
            					gl.middleClick(world, item, stack, player);
            				}
            			}
            		}
            	}
        	}
        	
            return null;
        }
    }
}