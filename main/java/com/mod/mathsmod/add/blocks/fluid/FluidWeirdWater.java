package com.mod.mathsmod.add.blocks.fluid;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidWeirdWater extends Fluid
{

	public FluidWeirdWater() 
	{
		super("weird_water", new ResourceLocation(Mathsmod.MODID, "blocks/weird_water_still")
				, new ResourceLocation(Mathsmod.MODID, "blocks/weird_water_flow"));
		this.setDensity(-100);
		this.setViscosity(1000);
		this.setTemperature(300);
		this.setLuminosity(3);
		this.setColor(0xffff00ff);
	}

}
