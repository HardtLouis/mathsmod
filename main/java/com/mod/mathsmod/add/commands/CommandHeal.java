package com.mod.mathsmod.add.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandHeal extends CommandBase {

	@Override
	public String getName() 
	{
		return "heal";
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
            entityplayer.setHealth(entityplayer.getMaxHealth());
            notifyCommandListener(sender, this, "commands.heal.successful", new Object[] {entityplayer.getDisplayName()});
        }
        else
        {
        	List<Entity> entities = getEntityList(server, sender, args[0]);
            if(!entities.isEmpty())
            {
            	for(Entity entity : entities)
                {
            		if(entity instanceof EntityLivingBase)
                    {
                    	((EntityLivingBase) entity).setHealth(((EntityLivingBase) entity).getMaxHealth());
                    	notifyCommandListener(sender, this, "commands.heal.successful", new Object[] {entity.getDisplayName()});
                    }
                }
            }
        }
	}

}