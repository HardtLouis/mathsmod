package com.mod.mathsmod.add.capabilities;

import java.util.concurrent.Callable;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityUtils
{
	
	public static class StorageContracts implements Capability.IStorage<CapabilityContracts>
	{
		@Override
        public NBTBase writeNBT(Capability<CapabilityContracts> capability, CapabilityContracts instance, EnumFacing side)
        {
			return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<CapabilityContracts> capability, CapabilityContracts instance, EnumFacing side, NBTBase nbt)
        {
        	instance.deserializeNBT((NBTTagCompound) nbt);
        }
	}
	 
	public static class FactoryContracts implements Callable<CapabilityContracts>
	{
		
		public static final CapabilityContracts prov = new CapabilityContracts();
		
	    @Override
	    public CapabilityContracts call() throws Exception
	    {
	        return prov;
	    }
	}
}
