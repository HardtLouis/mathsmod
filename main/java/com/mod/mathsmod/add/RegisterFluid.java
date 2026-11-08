package com.mod.mathsmod.add;

import com.mod.mathsmod.add.blocks.fluid.FluidFrozenWater;
import com.mod.mathsmod.add.blocks.fluid.FluidGreenLava;
import com.mod.mathsmod.add.blocks.fluid.FluidWeirdWater;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RegisterFluid
{
	public static final Fluid GREEN_LAVA = new FluidGreenLava();
	public static final Fluid FROZEN_WATER = new FluidFrozenWater();
	public static final Fluid WEIRD_WATER = new FluidWeirdWater();
	
	public static void registerFluids()
	{
		RegisterFluid.createFluid(GREEN_LAVA);
		RegisterFluid.createFluid(FROZEN_WATER);
		RegisterFluid.createFluid(WEIRD_WATER);
	}
	
	public static void createFluid(Fluid fluid)
	{
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
