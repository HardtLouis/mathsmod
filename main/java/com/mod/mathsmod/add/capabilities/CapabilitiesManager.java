package com.mod.mathsmod.add.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilitiesManager 
{	
	@CapabilityInject(CapabilityContracts.class)
    public static Capability<CapabilityContracts> CONTRACTS = null;
	
	public static void register()
	{
    	CapabilityManager.INSTANCE.register(CapabilityContracts.class, new CapabilityUtils.StorageContracts(), new CapabilityUtils.FactoryContracts());
	}
}
