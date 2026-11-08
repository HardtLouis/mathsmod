package com.mod.mathsmod.add.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandFeed extends CommandBase {

	@Override
	public String getName() 
	{
		return "feed";
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
            entityplayer.getFoodStats().setFoodLevel(40);
            notifyCommandListener(sender, this, "commands.feed.successful", new Object[] {entityplayer.getDisplayName()});
        }
        else
        {
        	List<Entity> entities = getEntityList(server, sender, args[0]);
            if(!entities.isEmpty())
            {
            	for(Entity entity : entities)
                {
                    if(entity instanceof EntityPlayer)
                    {
                    	((EntityPlayer) entity).getFoodStats().setFoodLevel(40);
                    	notifyCommandListener(sender, this, "commands.feed.successful", new Object[] {entity.getDisplayName()});
                    }
                }
            }
        }
	}

}
