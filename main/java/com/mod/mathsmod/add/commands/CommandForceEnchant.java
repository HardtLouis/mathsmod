package com.mod.mathsmod.add.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.mod.mathsmod.utils.ItemStackUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandForceEnchant  extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getName()
    {
        return "fenchant";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getUsage(ICommandSender sender)
    {
        return "commands.enchant.usage";
    }

    /**
     * Callback for when the command is executed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("commands.enchant.usage", new Object[0]);
        }
        else
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)getEntity(server, sender, args[0], EntityLivingBase.class);
            sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 0);
            Enchantment enchantment;

            try
            {
                enchantment = Enchantment.getEnchantmentByID(parseInt(args[1], 0));
            }
            catch (NumberInvalidException var12)
            {
                enchantment = Enchantment.getEnchantmentByLocation(args[1]);
            }

            if (enchantment == null)
            {
                throw new NumberInvalidException("commands.enchant.notFound", new Object[] {args[1]});
            }
            else
            {
                String a = "1";
                ItemStack itemstack = entitylivingbase.getHeldItemMainhand();

                if (args.length >= 3)
                {
                    a = args[2];
                }

                if (!itemstack.hasTagCompound())
                {
                	itemstack.setTagCompound(new NBTTagCompound());
                }
                
                NBTTagList nbttaglist = itemstack.getEnchantmentTagList();

                for (int j = 0; j < nbttaglist.tagCount(); ++j)
                {
                    int k = nbttaglist.getCompoundTagAt(j).getShort("id");

                    if (Enchantment.getEnchantmentByID(k) != null)
                    {
                        Enchantment enchantment1 = Enchantment.getEnchantmentByID(k);
                    }
                }

                ItemStackUtils.addEnchantment(itemstack, enchantment, a);
                notifyCommandListener(sender, this, "commands.enchant.success", new Object[0]);
                sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, 1);
            }
        }
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        else
        {
            return args.length == 2 ? getListOfStringsMatchingLastWord(args, Enchantment.REGISTRY.getKeys()) : Collections.emptyList();
        }
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}