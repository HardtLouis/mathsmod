package com.mod.mathsmod.add.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandTop extends CommandBase
{

	@Override
	public String getName() 
	{
		return "top";
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
            EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        	World world = server.getWorld(entityplayer.dimension);
        	BlockPos pos = new BlockPos(entityplayer);
        	pos = new BlockPos(pos.getX(), world.getHeight(pos.getX(), pos.getZ()), pos.getZ());
        	entityplayer.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        }
        else
        {
        	List<Entity> entities = getEntityList(server, sender, args[0]);
        	for(Entity entity : entities)
        	{
            	World world = server.getWorld(entity.dimension);
            	BlockPos pos = new BlockPos(entity);
            	pos = new BlockPos(pos.getX(), world.getHeight(pos.getX(), pos.getZ()), pos.getZ());
            	entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        	}
        }
	}

}