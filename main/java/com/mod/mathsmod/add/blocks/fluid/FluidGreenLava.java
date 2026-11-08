package com.mod.mathsmod.add.blocks.fluid;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidGreenLava extends Fluid
{

	public FluidGreenLava() 
	{
		super("green_lava", new ResourceLocation(Mathsmod.MODID, "blocks/green_lava_still")
				, new ResourceLocation(Mathsmod.MODID, "blocks/green_lava_flow"));
		this.setDensity(100);
		this.setViscosity(6000);
		this.setTemperature(20000);
		this.setLuminosity(15);
	}

}
