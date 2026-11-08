package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.IHeavyHitterItem;
import com.mod.mathsmod.add.items.IReachWeapon;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageReachSword implements IMessage
{
	
	int id;
	
	public MessageReachSword()
	{
	}
	
	public MessageReachSword(Entity entity)
	{
		id = entity.getEntityId();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(id);
	}
	
	public static class leftClickReachHandler implements IMessageHandler <MessageReachSword, IMessage> {

        @Override
        public IMessage onMessage(MessageReachSword msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	Entity entity = world.getEntityByID(msg.id);
        	if(player.getHeldItemMainhand().getItem() instanceof IReachWeapon && !(player.getHeldItemMainhand().getItem() instanceof IHeavyHitterItem))
        	{
        		IReachWeapon item = (IReachWeapon) player.getHeldItemMainhand().getItem();
        		
        		if (entity != null)
                {
                    boolean flag = player.canEntityBeSeen(entity);
                    double d0 = 36.0D;

                    if (!flag)
                    {
                        d0 = 3.0D + item.bonusReach(player, player.getHeldItemMainhand());
                    }

                    if (player.getDistance(entity) < d0 && player.getDistance(entity) > 3)
                    {
                    	UtilsVoids.attackTargetEntityWithCurrentReachItem(entity, player, item);
                    }
                }
        	}
        	else if(player.getHeldItemMainhand().getItem() instanceof IHeavyHitterItem)
        	{
        		if (entity != null)
                {
                    boolean flag = player.canEntityBeSeen(entity);
                    double d0 = 36.0D;

                    if (!flag)
                    {
                        d0 = 3.0D;
                    }
                    
                    if(player.getHeldItemMainhand().getItem() instanceof IReachWeapon)
                    {
                    	d0 += ((IReachWeapon) player.getHeldItemMainhand().getItem()).bonusReach(player, player.getHeldItemMainhand());
                    }

                    if (player.getDistance(entity) < d0)
                    {
                    	UtilsVoids.attackTargetEntityWithCurrentReachItem(entity, player, (player.getHeldItemMainhand().getItem() instanceof IReachWeapon ? ((IReachWeapon) player.getHeldItemMainhand().getItem()) : null));
                    }
                }
        	}
        	
            return null;
        }
    }
}