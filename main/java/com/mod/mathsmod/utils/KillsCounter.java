package com.mod.mathsmod.utils;

import com.mod.mathsmod.add.MathsTriggers;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityMythicalItem;

import net.minecraft.advancements.Advancement;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class KillsCounter {

	public static int getKills(EntityPlayer player)
	{
		if(playerNbtManager.getMathsmodTag(player).hasKey("kills"))
		{
			return playerNbtManager.getMathsmodTag(player).getInteger("kills");
		}
		else
		{
			return 0;
		}
	}
	
	public static void setKills(EntityPlayer player, int kills)
	{
		playerNbtManager.getMathsmodTag(player).setInteger("kills", kills);
		KillsCounter.recompense(player.world, player, kills);
	}
	
	
	public static void recompense(World world, EntityPlayer player, int kills)
	{
		if(kills == 10)
		{
			if(MathsTriggers.FRYING_PAN.listeners != null && player instanceof EntityPlayerMP)
			{
				if(MathsTriggers.FRYING_PAN.listeners.get(((EntityPlayerMP) player).getAdvancements()) != null)
				{
					MathsTriggers.FRYING_PAN.listeners.get(((EntityPlayerMP) player).getAdvancements()).trigger((EntityPlayerMP) player);
				}
			}
			player.sendMessage(new TextComponentString(TextFormatting.GOLD + player.getName() + " got a new item !"));
			world.spawnEntity(new EntityMythicalItem(world, player.posX, player.posY, player.posZ, new ItemStack(RegisterItems.FRYING_PAN)));
		}
		
		if(kills == -1)
		{
			player.sendMessage(new TextComponentString(TextFormatting.GOLD + player.getName() + " got a new item !"));
			world.spawnEntity(new EntityMythicalItem(world, player.posX, player.posY, player.posZ, new ItemStack(RegisterItems.GOLDEN_FRYING_PAN)));
		}
	}
	
}
