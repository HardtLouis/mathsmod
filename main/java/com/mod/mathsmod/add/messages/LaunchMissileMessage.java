package com.mod.mathsmod.add.messages;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityMissileLauncher;
import com.mod.mathsmod.utils.NumsHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LaunchMissileMessage implements IMessage
{
	int x;
	int y;
	int z;
	
	int baseX;
	int baseY;
	int baseZ;
	
	boolean s;
	
	public LaunchMissileMessage()
	{
	}
	
	public LaunchMissileMessage(int x, int y, int z, BlockPos pos, boolean save)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.baseX = pos.getX();
		this.baseY = pos.getY();
		this.baseZ = pos.getZ();
		s = save;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		String m = ByteBufUtils.readUTF8String(buf);
		String[] str = m.split("/");
		x = NumsHelper.getIntFromString(str[0]);
		y = NumsHelper.getIntFromString(str[1]);
		z = NumsHelper.getIntFromString(str[2]);
		baseX = NumsHelper.getIntFromString(str[3]);
		baseY = NumsHelper.getIntFromString(str[4]);
		baseZ = NumsHelper.getIntFromString(str[5]);
		if(str[6].equals("true"))
		{
			s = true;
		}
		else
		{
			s = false;
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, x + "/" + y + "/" + z + "/" + baseX + "/" + baseY + "/" + baseZ + "/" + s);
	}
	
	public static class launchHandler implements IMessageHandler <LaunchMissileMessage, IMessage> 
	{
        @Override
        public IMessage onMessage(LaunchMissileMessage msg, MessageContext ctx) {
        	
        	World world = ctx.getServerHandler().player.world;
        	EntityPlayer player = ctx.getServerHandler().player;
        	
        	if(player != null)
        	{
    			BlockPos pos = new BlockPos(msg.baseX, msg.baseY, msg.baseZ);
        		if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof TileEntityMissileLauncher)
        		{
        			TileEntityMissileLauncher te = (TileEntityMissileLauncher) world.getTileEntity(pos);
        			if(msg.s)
        			{
        				te.target = new BlockPos(msg.x, msg.y, msg.z);
        				
        				System.out.print("set target " + msg.x + " " + msg.y + " " + msg.z);
        				//System.out.print("set target " + msg.x + " " + msg.y + " " + msg.z);
            			System.out.print(System.lineSeparator());
        			}
        			else
        			{
        				te.target = new BlockPos(msg.x, msg.y, msg.z);
        				te.launchMissile();
            			
            			System.out.print("drone missile with target at " + msg.x + " " + msg.y + " " + msg.z);
            			System.out.print(System.lineSeparator());
        			}
        		}
        	}
        	
            return null;
        }
    }
}
