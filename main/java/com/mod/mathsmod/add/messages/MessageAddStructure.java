package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityRandomizedStructurePlacer;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAddStructure implements IMessage
{
	
	String itemName;
	BlockPos pos;
	
	public MessageAddStructure()
	{
	}
	
	public MessageAddStructure(BlockPos posIn, String str)
	{
		itemName = str;
		pos = posIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		itemName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		ByteBufUtils.writeUTF8String(buf, itemName);
	}
	
	public static class structureHandler implements IMessageHandler <MessageAddStructure, IMessage> {

        @Override
        public IMessage onMessage(MessageAddStructure msg, MessageContext ctx) 
        {
        	World world = ctx.getServerHandler().player.world;
        	if(world.getTileEntity(msg.pos) instanceof TileEntityRandomizedStructurePlacer)
        	{
        		TileEntityRandomizedStructurePlacer te = (TileEntityRandomizedStructurePlacer) world.getTileEntity(msg.pos);
        		te.data.add(msg.itemName);
        	}
            return null;
        }
    }
}