package com.mod.mathsmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class CustomScaledResolution extends ScaledResolution
{

	public CustomScaledResolution(Minecraft minecraftClient) 
	{
		super(minecraftClient);
	}

	public int getScaleFactor()
    {
        return 4;
    }
	
}
