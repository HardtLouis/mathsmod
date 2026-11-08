package com.mod.mathsmod.add.commands;

import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.World.UtilsVoids;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class CommandHelp extends CommandBase 
{

	@Override
	public String getName() 
	{
		return "mathsmod_help";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "";
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
        if(!UtilsVoids.playerHasItemMain(entityplayer, RegisterItems.INSTRUC_BOOK))
        {
        	notifyCommandListener(sender, this, "commands.help.successful", new Object[] {entityplayer.getDisplayName()});
        	if(!entityplayer.world.isRemote)
        	{
        		EntityItem item = new EntityItem(entityplayer.world, entityplayer.posX, entityplayer.posY + 1, entityplayer.posZ, new ItemStack(RegisterItems.INSTRUC_BOOK));
        		item.setPickupDelay(0);
        		entityplayer.world.spawnEntity(item);
        	}
        }
        
	}

}