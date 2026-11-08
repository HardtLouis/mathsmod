package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.MathsDims;
import com.mod.mathsmod.add.World.dims.teleporter.UselessTp;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.proxy.server_Proxy;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ITeleporter;

public class CommandWarp extends CommandBase
{

	@Override
	public String getName() 
	{
		return "warp";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if (args.length == 0)
        {
            EntityPlayerMP entityplayer = getCommandSenderAsPlayer(sender);
        	notifyCommandListener(sender, this, "commands.warp.fail", new Object[] {entityplayer.getDisplayName()});
            
        }
        else if(args.length == 1)
        {
        	EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        	
            Entity entity = getEntity(server, sender, args[0]);
            if(entity != null)
            {
            	int dim = entity.dimension;
                BlockPos pos = new BlockPos(entity);
                if(dim == player.dimension)
                {
                	player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                }
                else
                {
                	UselessTp tp = new UselessTp(pos, dim);
    				player.timeUntilPortal = 100;
    				player.isInvulnerableDimensionChange();
    				ITeleporter teleporter = new UselessTp(pos, dim);
    				player.mcServer.getPlayerList().transferPlayerToDimension(player, dim, teleporter);
                	
                }
            }
        }
		
        else if(args.length == 2)
        {
        	Entity player = getEntity(server, sender, args[0]);
            Entity entity = getEntity(server, sender, args[1]);
            if(player != null && entity != null)
            {
            	int dim = entity.dimension;
                BlockPos pos = new BlockPos(entity);
                if(dim == player.dimension)
                {
                	player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                }
                else
                {
                	UselessTp tp = new UselessTp(pos, dim);
    				player.timeUntilPortal = 100;
    				if(player instanceof EntityPlayerMP)
    				{
    					((EntityPlayerMP) player).isInvulnerableDimensionChange();
    					ITeleporter teleporter = new UselessTp(pos, dim);
    					((EntityPlayerMP) player).mcServer.getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, dim, teleporter);
    				}
    				else if(player.getServer() != null)
    				{
    					player.changeDimension(dim, tp);
    					player.setPosition(pos.getX(), pos.getY(), pos.getZ());
    				}
                }
            }
            
        }
		
        else if(args.length == 4)
        {
        	EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        	
            int dim = NumsHelper.getIntFromString(args[0]);
            BlockPos pos = new BlockPos(NumsHelper.getIntFromString(args[1]), NumsHelper.getIntFromString(args[2]), NumsHelper.getIntFromString(args[3]));
            if(DimensionManager.isDimensionRegistered(dim))
            {
            	if(dim == player.dimension)
                {
                	player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                }
                else
                {
                	UselessTp tp = new UselessTp(pos, dim);
    				player.timeUntilPortal = 100;
    				player.isInvulnerableDimensionChange();
    				ITeleporter teleporter = new UselessTp(pos, dim);
    				player.mcServer.getPlayerList().transferPlayerToDimension(player, dim, teleporter);
                	
                }
            }
        }
		
        else if(args.length == 5)
        {
        	Entity player = getEntity(server, sender, args[0]);
        	int dim = NumsHelper.getIntFromString(args[1]);
            BlockPos pos = new BlockPos(NumsHelper.getIntFromString(args[2]), NumsHelper.getIntFromString(args[3]), NumsHelper.getIntFromString(args[4]));
            if(DimensionManager.isDimensionRegistered(dim))
            {
            	if(dim == player.dimension)
                {
                	player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                }
                else
                {
                	UselessTp tp = new UselessTp(pos, dim);
    				player.timeUntilPortal = 100;
    				if(player instanceof EntityPlayerMP)
    				{
    					((EntityPlayerMP) player).isInvulnerableDimensionChange();
    					ITeleporter teleporter = new UselessTp(pos, dim);
    					((EntityPlayerMP) player).mcServer.getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, dim, teleporter);
    				}
    				else if(player.getServer() != null)
    				{
    					player.changeDimension(dim, tp);
    					player.setPosition(pos.getX(), pos.getY(), pos.getZ());
    				}
                }
            }
        }
	}

}