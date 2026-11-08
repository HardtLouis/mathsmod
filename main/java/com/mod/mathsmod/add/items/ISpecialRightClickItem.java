package com.mod.mathsmod.add.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpecialRightClickItem 
{
	public void onRightClickPressed(World worldIn, EntityPlayer playerIn, int ticks);
}
