package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.items.ItemPancarte;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandPancarte extends CommandBase
{
    /**
     * Gets the name of the command
     */
    @Override
    public String getName()
    {
        return "pancarte";
    }

    /**
     * Gets the usage string for the command.
     */
    @Override
    public String getUsage(ICommandSender sender)
    {
        return "";
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    /**
     * Check if the given ICommandSender has permission to execute this command
     */
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    /**
     * Callback for when the command is executed
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
    	if(sender.getCommandSenderEntity() instanceof EntityPlayerMP && args != null && args.length > 0)
    	{
    		EntityPlayerMP entity = (EntityPlayerMP) sender.getCommandSenderEntity();
    		if(entity.getHeldItemMainhand().getItem() == RegisterItems.PANCARTE)
    		{
    			ItemPancarte item = (ItemPancarte) entity.getHeldItemMainhand().getItem();
    			ItemStack stack = entity.getHeldItemMainhand();
    			
    			item.setDrawing(stack, args[0]);
    		}
    		else
    		{
                sender.sendMessage(new TextComponentString("you need to hold a pancarte in your main hand"));
    		}
    	}
    }
}