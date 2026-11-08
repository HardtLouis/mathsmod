package com.mod.mathsmod.add.Entity.Entity.objects;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IVehicle 
{
	@SideOnly(Side.CLIENT)
	public float getHealthBar();
}
