package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTargetDrone;
import com.mod.mathsmod.add.items.ItemDroneLauncher;
import com.mod.mathsmod.utils.NumsHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LaunchDroneMessage implements IMessage
{
	int x;
	int y;
	int z;
	
	public LaunchDroneMessage()
	{
	}
	
	public LaunchDroneMessage(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		String s = ByteBufUtils.readUTF8String(buf);
		String[] str = s.split("//");
		x = NumsHelper.getIntFromString(str[0]);
		y = NumsHelper.getIntFromString(str[1]);
		z = NumsHelper.getIntFromString(str[2]);
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, x + "//" + y + "//" + z);
	}
	
	public static class launchHandler implements IMessageHandler <LaunchDroneMessage, IMessage> {

        @Override
        public IMessage onMessage(LaunchDroneMessage msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	
        	if(!world.isRemote)
        	{
        		if(player != null)
            	{
            		if(player.getHeldItemMainhand().getItem() instanceof ItemDroneLauncher)
            		{
            			world.spawnEntity(new EntityTargetDrone(world, player, new BlockPos(msg.x, msg.y, msg.z)));
            			player.getHeldItemMainhand().shrink(1);
            			System.out.print("drone summoned with target at " + msg.x + " " + msg.y + " " + msg.z);
            			System.out.print(System.lineSeparator());
            		}
            	}
        	}
        	
            return null;
        }
    }
}
