package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;

public class CommandSuicide extends CommandBase 
{

	@Override
	public String getName() 
	{
		return "suicide";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "bah tu te tues fdp";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
        entityplayer.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.POSITIVE_INFINITY);
	}

}