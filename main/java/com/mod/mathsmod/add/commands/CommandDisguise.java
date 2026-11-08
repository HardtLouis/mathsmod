package com.mod.mathsmod.add.commands;

import java.util.List;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.messages.MessageTransform;
import com.mod.mathsmod.proxy.server_Proxy;
import com.mod.mathsmod.utils.DisguiseHelper;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class CommandDisguise extends CommandBase {

	@Override
	public String getName() 
	{
		return "disguise";
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
		DisguiseHelper.removeDisguise(entityplayer);
		
		if (args.length == 0)
        {
            notifyCommandListener(sender, this, "commands.disguise.fail", new Object[] {entityplayer.getDisplayName()});
        }
        else if(args.length == 1)
        {
        	Mathsmod.network.sendToAll(new MessageTransform(entityplayer.getName(), "disguise:" + args[0], false));
        	server_Proxy.handleRenderInfoChange(entityplayer.getName(), "disguise:" + args[0], false);
        }
        else
        {
        	List<Entity> entities = getEntityList(server, sender, args[1]);
            if(!entities.isEmpty())
            {
            	for(Entity entity : entities)
                {
            		if(entity instanceof EntityPlayer)
            		{
            			entityplayer = (EntityPlayer) entity;
            			
            			Mathsmod.network.sendToAll(new MessageTransform(entityplayer.getName(), "disguise:" + args[0], false));
                    	server_Proxy.handleRenderInfoChange(entityplayer.getName(), "disguise:" + args[0], false);
            		}
                }
            }
        }
	}

}