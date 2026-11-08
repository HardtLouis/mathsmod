package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.capabilities.CapabilityContracts;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageContract implements IMessage
{
	
	String ID;
	boolean r;
	
	public MessageContract()
	{
		ID = "none";
	}
	
	public MessageContract(String str, boolean reset)
	{
		ID = str;
		r = reset;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		ID = ByteBufUtils.readUTF8String(buf);
		r = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, ID);
		buf.writeBoolean(r);
	}
	
	public static class ContractMessageHandler implements IMessageHandler<MessageContract, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageContract msg, MessageContext ctx) 
        {
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	
        	if(msg.ID != "none" && ContractsBase.getByName(msg.ID) != null)
        	{
        		ContractsBase contract = ContractsBase.getByName(msg.ID);
        		
        		if(msg.r)
        		{
        			contract.resetProgress(player, contract);
        		}
        		else
        		{
        			ContractManagerServer.replace(player, ContractsBase.getByName(msg.ID), -1);
    				
    				ItemStack stack = contract.recompense();
    				boolean flag = player.inventory.addItemStackToInventory(stack);
    				
    	            if (flag)
    	            {
    	                player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.PLAYERS, 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
    	                player.inventoryContainer.detectAndSendChanges();
    	            }
    	            else
    	            {
    	            	EntityItem entityitem = player.dropItem(stack, false);

                        if (entityitem != null)
                        {
                            entityitem.setNoPickupDelay();
                            entityitem.setOwner(player.getName());
                        }
    	            }
    				
    				contract.onCompletion(player, world);
        		}
        		
        	}
        	else
        	{
        		if(player instanceof EntityPlayerMP)
        		{
        			CapabilityContracts.updateContracts(player);
        		}
        	}
        	
            return null;
        }
    }
}