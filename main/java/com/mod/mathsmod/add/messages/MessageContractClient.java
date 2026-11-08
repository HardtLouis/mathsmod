package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.contracts.gui.GuiContracts;
import com.mod.mathsmod.add.contractsManager.ContractManagerServer;
import com.mod.mathsmod.utils.ContractsUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageContractClient implements IMessage
{
	
	String ID;
	int r;
	
	public MessageContractClient()
	{
		ID = "none";
	}
	
	public MessageContractClient(String str, int adv)
	{
		ID = str;
		r = adv;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		ID = ByteBufUtils.readUTF8String(buf);
		r = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, ID);
		buf.writeInt(r);
	}
	
	public static class ContractMessageHandler implements IMessageHandler<MessageContractClient, IMessage> 
	{
        @Override
        public IMessage onMessage(MessageContractClient msg, MessageContext ctx) 
        {
        	EntityPlayer player = ContractsUtils.relay();
        	
        	if(!msg.ID.equals("none") && ContractsBase.getByName(msg.ID) != null)
        	{
        		ContractsBase contract = ContractsBase.getByName(msg.ID);
        		contract.setProgress(player, contract, msg.r);
        	}
        	else
        	{
        	}
        	
            return null;
        }
    }
}