package com.mod.mathsmod.add.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SavePlayerEvents {
	
	@SubscribeEvent
	public void onLoadPlayer(PlayerEvent.LoadFromFile event)
	{
	}
	
	@SubscribeEvent
	public void onSavePlayer(PlayerEvent.SaveToFile event)
	{
	}
}
