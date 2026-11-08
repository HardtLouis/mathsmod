package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.utils.NumsHelper;
import com.mod.mathsmod.utils.playerNbtManager;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandVanish extends CommandBase {

	@Override
	public String getName() 
	{
		return "vanish";
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
            notifyCommandListener(sender, this, "commands.vanish.success", new Object[] {entityplayer.getDisplayName()});
            entityplayer.setCustomNameTag(entityplayer.getCustomNameTag() + "-Im-Vanished-Baby");
        }
	}

}