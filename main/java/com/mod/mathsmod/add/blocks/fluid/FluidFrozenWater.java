package com.mod.mathsmod.add.blocks.fluid;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidFrozenWater extends Fluid
{

	public FluidFrozenWater() 
	{
		super("frozen_water", new ResourceLocation(Mathsmod.MODID, "blocks/frozen_water_still")
				, new ResourceLocation(Mathsmod.MODID, "blocks/frozen_water_flow"));
		this.setDensity(100);
		this.setViscosity(3000);
		this.setTemperature(-200);
		this.setLuminosity(0);
	}

}
