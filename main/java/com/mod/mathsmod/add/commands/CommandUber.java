package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.utils.KillsCounter;
import com.mod.mathsmod.utils.NumsHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandUber extends CommandBase {

	@Override
	public String getName() 
	{
		return "uber";
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
            notifyCommandListener(sender, this, "UBER !!!", new Object[] {entityplayer.getDisplayName()});
            
            entityplayer.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(RegisterItems.UBER_HEAD));
            entityplayer.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(RegisterItems.UBER_CHESTPLATE));
            entityplayer.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(RegisterItems.UBER_LEGS));
            entityplayer.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(RegisterItems.UBER_BOOTS));
        }
	}

}