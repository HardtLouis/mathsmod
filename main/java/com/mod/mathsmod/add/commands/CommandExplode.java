package com.mod.mathsmod.add.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandExplode extends CommandBase {

	@Override
	public String getName() 
	{
		return "explode";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
		
		if (args.length == 0)
        {
            notifyCommandListener(sender, this, "failed to explode", new Object[] {entityplayer.getDisplayName()});
        }
        else
        {
            List<Entity> entities = getEntityList(server, sender, args[0]);
            if(!entities.isEmpty())
            {
            	for(Entity entity : entities)
                {
                	if(args.length == 1)
                    {
                    	entityplayer.world.newExplosion(entity, entity.posX, entity.posY, entity.posZ, 4, false, true);
                    }
                    else
                    {
                    	if(args.length == 2)
                        {
                    		entityplayer.world.newExplosion(entity, entity.posX, entity.posY, entity.posZ, NumsHelper.getIntFromString(args[1]), false, true);
                        }
                        else
                        {
                        	entityplayer.world.newExplosion(entity, entity.posX, entity.posY, entity.posZ, NumsHelper.getIntFromString(args[1]), args[2].equals("true"), true);
                        }
                    }
                }
            }
        }
	}
	
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if(args.length == 1)
        {
        	return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        else
        {
        	if(args.length == 2)
        	{
        		List<String> list = Lists.newArrayList();
            	list.add("4");
            	return list;
        	}
        	else
        	{
        		List<String> list = Lists.newArrayList();
            	list.add("true");
            	list.add("false");
            	return args.length == 3 ? list : Collections.emptyList();
        	}
        }
    }

}