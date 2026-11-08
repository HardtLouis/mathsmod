package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.MathsSounds;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurret;
import com.mod.mathsmod.add.items.ItemArmor.ItemExplosiveBelt;
import com.mod.mathsmod.add.items.ItemArmor.ItemUseableArmors;
import com.mod.mathsmod.add.items.guns.ItemGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlaneBulletMessage implements IMessage
{
	boolean plane;
	
	public PlaneBulletMessage()
	{
	}
	
	public PlaneBulletMessage(boolean a)
	{
		plane = a;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		plane = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(plane);
	}
	
	public static class BulletHandler implements IMessageHandler <PlaneBulletMessage, IMessage> {

        @Override
        public IMessage onMessage(PlaneBulletMessage msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity ridden = ctx.getServerHandler().player.getRidingEntity();
        	
        	if(!world.isRemote)
        	{
        		if(ridden != null)
            	{
            		if(ridden instanceof EntityPlane)
            		{
            			if(msg.plane)
            			{
            				if(((EntityPlane)ridden).canLaunchMissile(player))
                			{
                				((EntityPlane) ridden).LaunchMissile(player);
                			}
            			}
            			else
            			{
            				if(((EntityPlane)ridden).canShoot())
                			{
                				((EntityPlane) ridden).Shoot();;
                			}
            			}
            		}
            		else if(ridden != null)
                	{
                		if(ridden instanceof EntityTurret)
                		{
                			if(((EntityTurret) ridden).getControllingPassenger() == player && ((EntityTurret) ridden).canShoot())
                			{
                				if(player.getHeldItemMainhand().getItem() instanceof ItemGun)
            					{
            						((ItemGun) player.getHeldItemMainhand().getItem()).setTakeTime(player.getHeldItemMainhand(), 0);
            					}
                				((EntityTurret) ridden).shoot();
                			}
                		}
                		
                	}
            	}
        		else
        		{
        			if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemUseableArmors)
        			{
        				ItemUseableArmors item = (ItemUseableArmors) player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        				item.onChestUsed(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST), world, player);
        			}
        		}
        	}
        	
            return null;
        }
    }
	
}