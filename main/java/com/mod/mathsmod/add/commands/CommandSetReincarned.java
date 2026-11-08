package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.utils.NumsHelper;
import com.mod.mathsmod.utils.playerNbtManager;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class CommandSetReincarned extends CommandBase {

	@Override
	public String getName() 
	{
		return "bind_soul";
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
            notifyCommandListener(sender, this, "commands.disguise.fail", new Object[] {entityplayer.getDisplayName()});
        }
        else
        {
        	int i = NumsHelper.getIntFromStringFailNegative(args[0]);
            if(i > -1 && i < 2)
            {
            	if(args[1].length() > 0)
            	{
            		playerNbtManager.getMathsmodTag(entityplayer).setString("reincarned_" + i, args[1]);
            	}
            }
        }
	}

}