package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.add.spell.utils.SpellManager;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandSpellsUse extends CommandBase {

	@Override
	public String getName() 
	{
		return "spellSet";
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
		
		if (args.length > 1)
        {
            SpellManager.setSpellString(entityplayer, NumsHelper.getIntFromString(args[0]), args[1]);
        }
	}

}