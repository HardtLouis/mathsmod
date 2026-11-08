package com.mod.mathsmod.add.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ILeftClickItem {

	public void onItemLeftClick(World worldIn, EntityPlayer playerIn, int ticks);
	
	@SideOnly(Side.CLIENT)
	public default void onClientLeftClick(World worldIn, EntityPlayer playerIn, int ticks)
	{
		this.onItemLeftClick(worldIn, playerIn, ticks);
	}
	
}
