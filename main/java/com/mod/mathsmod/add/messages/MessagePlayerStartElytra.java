package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemJetpack;
import com.mod.mathsmod.add.items.ItemArmor.ItemThoriumArmor;
import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerStartElytra  implements IMessage
{
	
	public MessagePlayerStartElytra()
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
	
	public static class MessageElytraHandler implements IMessageHandler <MessagePlayerStartElytra, IMessage> {

        @Override
        public IMessage onMessage(MessagePlayerStartElytra msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        	
        	if(stack.getItem() instanceof ItemJetpack)
        	{
        		ItemJetpack item = (ItemJetpack) stack.getItem();
        		if(stack.getItemDamage() < item.getMaxDamage())
        		{
        			if(item.getYBoost() > player.motionY)
        			{
        				player.motionY = item.getYBoost();
        				item.onUse(player, stack);
            			player.fallDistance = 0;
            			Vec3d vec = UtilsVoids.laser(-0.3, player.rotationYaw, 0);
            			player.world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + vec.x, player.posY + 1.4D, player.posZ + vec.z, 0, -0.1D, 0);
            			stack.damageItem(1, player);
            			player.world.playSound((EntityPlayer) null, player.posX + vec.x, player.posY + 1.4D, player.posZ + vec.z, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 1, 1);
        			}
        		}
        	}
        	else if(stack.getItem() instanceof ItemExplosiveBelt)
        	{
        		ItemExplosiveBelt item = (ItemExplosiveBelt) stack.getItem();
        		item.onExplUsed(stack, world, player);
        	}
        	
        	return null;
        }
    }
}
	
