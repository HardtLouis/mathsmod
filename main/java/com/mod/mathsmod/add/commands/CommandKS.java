package com.mod.mathsmod.add.commands;

import java.util.List;

import com.mod.mathsmod.utils.KillsCounter;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandKS extends CommandBase {

	@Override
	public String getName() 
	{
		return "ks";
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
            notifyCommandListener(sender, this, "commands.heal.fail", new Object[] {entityplayer.getDisplayName()});
        }
        else
        {
        	if(NumsHelper.isInteger(args[0]))
        	{
        		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        		KillsCounter.setKills(entityplayer, NumsHelper.getIntFromString(args[0]));
                notifyCommandListener(sender, this, "commands.heal.success", new Object[] {entityplayer.getDisplayName()});
        	}
        	else if(args[0].equals("get"))
        	{
        		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        		entityplayer.sendStatusMessage(new TextComponentString("kill streak : " + KillsCounter.getKills(entityplayer)), true);
                notifyCommandListener(sender, this, "commands.heal.fail", new Object[] {entityplayer.getDisplayName()});
        	}
        	else
        	{
        		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
                notifyCommandListener(sender, this, "commands.heal.fail", new Object[] {entityplayer.getDisplayName()});
        	}
        }
	}

}