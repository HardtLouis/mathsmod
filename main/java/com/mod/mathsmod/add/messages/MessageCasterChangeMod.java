package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityCaster;
import com.mod.mathsmod.add.crafting.MeltingRegistry;
import com.mod.mathsmod.add.crafting.MeltingRegistry.Molten_metal;
import com.mod.mathsmod.add.crafting.MeltingRegistry.alloy;
import com.mod.mathsmod.add.items.ItemAlloyIngot;
import com.mod.mathsmod.add.items.metaltools.ItemMetalicTool;
import com.mod.mathsmod.add.items.toolparts.ItemToolPart;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCasterChangeMod implements IMessage
{
	
	String itemName;
	
	public MessageCasterChangeMod()
	{
	}
	
	public MessageCasterChangeMod(String item)
	{
		itemName = item;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		itemName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, itemName);
	}
	
	public static class casterHandler implements IMessageHandler <MessageCasterChangeMod, IMessage> {

        @Override
        public IMessage onMessage(MessageCasterChangeMod msg, MessageContext ctx) 
        {
        	if(ctx.getServerHandler().player != null && msg.itemName.split(":").length == 2)
        	{
        		EntityPlayer player = ctx.getServerHandler().player;
        		Item item = Item.REGISTRY.getObject(new ResourceLocation(msg.itemName.split(":")[0], msg.itemName.split(":")[1]));
        		if(item != null && MeltingRegistry.METALIC_ITEMS != null && MeltingRegistry.METALIC_ITEMS.containsKey(item))
        		{
        			int price = MeltingRegistry.METALIC_ITEMS.get(item);
        			if(player.getHeldItemOffhand().getCount() >= price)
        			{
            			ItemStack stack = new ItemStack(item);
    					if(player.getHeldItemOffhand().getItem() == RegisterItems.ALLOY_INGOT)
    					{
    						alloy al = ItemAlloyIngot.getMoltenMaterial(player.getHeldItemOffhand());
    						ItemToolPart.setAlloy(stack, al.items[0], al.items[1]);
    					}
    					else
    					{
    						ItemToolPart.setAlloyFromString(stack, player.getHeldItemOffhand().getItem().getRegistryName().toString());
    					}
    					player.getHeldItemOffhand().shrink(price);
    					player.dropItem(stack, false);
    					player.world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F, false);
        			}
        		}
        	}
        	
            return null;
        }
    }
}