package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.proxy.server_Proxy;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandTransform extends CommandBase
{

	@Override
	public String getName() 
	{
		return "transform";
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
        	notifyCommandListener(sender, this, "commands.transform.fail", new Object[] {entityplayer.getDisplayName()});
            
        }
        else if(args.length >= 2)
        {
            Entity entity = getEntity(server, sender, args[1]);
            
            if(entity instanceof EntityPlayer)
            {
            	EntityPlayer entityplayer = (EntityPlayer) entity;
            	Mathsmod.network.sendToAll(new MessageTransform(entityplayer.getName(), args[0], false));
            	server_Proxy.handleRenderInfoChange(entityplayer.getName(), args[0], false);
            	notifyCommandListener(sender, this, "commands.transform.successful", new Object[] {entityplayer.getDisplayName()});
            }
        }
        else
        {
        	EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        	server_Proxy.handleRenderInfoChange(entityplayer.getName(), args[0], false);
        	Mathsmod.network.sendToAll(new MessageTransform(entityplayer.getName(), args[0], false));
        	notifyCommandListener(sender, this, "commands.transform.successful", new Object[] {entityplayer.getDisplayName()});
        }
	}

}