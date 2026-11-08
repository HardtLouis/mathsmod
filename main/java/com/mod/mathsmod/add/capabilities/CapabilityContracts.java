package com.mod.mathsmod.add.capabilities;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.contracts.ContractsBase;
import com.mod.mathsmod.add.event.ServerHelperEvents;
import com.mod.mathsmod.add.messages.MessageContractClient;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityContracts implements ICapabilityProvider, ICapabilitySerializable<NBTTagCompound>
{

    public CapabilityContracts() 
    {
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) 
    {
        return capability != null && capability == CapabilitiesManager.CONTRACTS;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
    	return CapabilitiesManager.CONTRACTS != null && capability == CapabilitiesManager.CONTRACTS ? capability.getDefaultInstance() : null;
    }
    
    HashMap<ContractsBase, Integer> contracts = Maps.newHashMap();
    int equipedPrimary;
    int equipedSecondary;
    
    
	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList nbtTagList = new NBTTagList();
        int size = contracts.size();
        for (int i = 0; i < size; i++)
        {
            String cap = ((ContractsBase) contracts.keySet().toArray()[i]).id;
            
            if (!cap.isEmpty())
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setString("contract_" + i, cap);
                if(contracts.get(ContractsBase.getByName(cap)) != null)
                {
                    itemTag.setInteger("progresses_" + i, contracts.get(ContractsBase.getByName(cap)));
                }
                else
                {
                	itemTag.setInteger("progresses_" + i, 0);
                }
                nbtTagList.appendTag(itemTag);
            }
        }
        
        tag.setTag("contracts_list", nbtTagList);
        
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		
		NBTTagList tagList = nbt.getTagList("contracts_list", 10);
		if(tagList != null && !tagList.hasNoTags())
		{
	        for (int i = 0; i < tagList.tagCount(); i++)
	        {
	            NBTTagCompound itemTags = tagList.getCompoundTagAt(i);
	            ContractsBase j = ContractsBase.getByName(itemTags.getString("contract_" + i));
	            int adv = itemTags.getInteger("progresses_" + i);

	            if (!contracts.containsKey(j) && j != null)
	            {
	            	contracts.put(j, i);
	            }
	        }
		}
	}
	

	public void savePlayerContracts(EntityPlayer player, HashMap<String, HashMap<ContractsBase, Integer>> map) 
	{
		NBTTagCompound tag = player.getEntityData();
		NBTTagList nbtTagList = new NBTTagList();
		
		if(map.containsKey(player.getName()))
		{
			int size = map.get(player.getName()).size();
	        
	        for (int i = 0; i < size; i++)
	        {
	            String cap = ((ContractsBase) map.get(player.getName()).keySet().toArray()[i]).id;
	            
	            if (!cap.isEmpty())
	            {
	                NBTTagCompound itemTag = new NBTTagCompound();
	                itemTag.setString("contract_" + i, cap);
	                
	                System.out.print(cap + "/" + map.get(player.getName()).get(ContractsBase.getByName(cap)) + System.lineSeparator());
	                
	                if(map.get(player.getName()).get(ContractsBase.getByName(cap)) != null)
	                {
	                    itemTag.setInteger("progresses_" + i, map.get(player.getName()).get(ContractsBase.getByName(cap)));
	                }
	                else
	                {
	                	itemTag.setInteger("progresses_" + i, 0);
	                }
	                nbtTagList.appendTag(itemTag);
	            }
	        }
	        
	        tag.setTag("contracts_list", nbtTagList);
		}
        
	}

	public HashMap<ContractsBase, Integer> loadPlayerContracts(EntityPlayer player)
	{
		NBTTagCompound nbt = player.getEntityData();
		NBTTagList tagList = nbt.getTagList("contracts_list", 10);
		HashMap<ContractsBase, Integer> contracts = Maps.newHashMap();
		
		if(tagList != null && !tagList.hasNoTags())
		{
	        for (int i = 0; i < tagList.tagCount(); i++)
	        {
	            NBTTagCompound itemTags = tagList.getCompoundTagAt(i);
	            ContractsBase j = ContractsBase.getByName(itemTags.getString("contract_" + i));
	            int adv = itemTags.getInteger("progresses_" + i);

	            System.out.print(j.id + "/" + adv + System.lineSeparator());
	            
	            if (!contracts.containsKey(j) && j != null)
	            {
		           	
		           	contracts.put(j, adv);

					if(player instanceof EntityPlayerMP)
					{
						Mathsmod.network.sendTo(new MessageContractClient(j.id, adv), (EntityPlayerMP) player);
					}
	            }
	        }
		}
		
		return contracts;
	}
	
	public static void updateContracts(EntityPlayer player)
	{
		if(ServerHelperEvents.contracts.get(player.getName()) != null)
		{
			for(ContractsBase c : ServerHelperEvents.contracts.get(player.getName()).keySet())
			{
				if(player instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageContractClient(c.id, player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(c)), (EntityPlayerMP) player);
				}
			}
		}
	}
	
	public HashMap<ContractsBase, Integer> getContracts(EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			return ServerHelperEvents.contracts.get(player.getName());
		}
		else
		{
			return contracts;
		}
	}
	
	public void setContracts(HashMap<ContractsBase, Integer> list, EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			ServerHelperEvents.contracts.replace(player.getName(), list);
		}
		else
		{
			contracts = list;
		}
	}
	
	public void resetContracts(EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			ServerHelperEvents.contracts.replace(player.getName(), Maps.newHashMap());
		}
		else
		{
			this.contracts.clear();
			
			for(ContractsBase str : this.contracts.keySet())
			{
				this.contracts.put(str, 0);
			}
		}
	}
	
	public ContractsBase getContract(int id, EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			return (ContractsBase) ServerHelperEvents.contracts.get(player.getName()).keySet().toArray()[id];
		}
		else
		{
			return (ContractsBase) contracts.keySet().toArray()[id];
		}
	}
	
	public boolean setContract(String capabilitiesID, int advancment, int oldAdvancment, EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			return ServerHelperEvents.contracts.get(player.getName()).replace(ContractsBase.getByName(capabilitiesID), oldAdvancment, advancment);
		}
		else
		{
			return contracts.replace(ContractsBase.getByName(capabilitiesID), oldAdvancment, advancment);
		}
		
	}
	
	public int addContracts(String capabilityID, int advancment, EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP)
		{
			return ServerHelperEvents.contracts.get(player.getName()).put(ContractsBase.getByName(capabilityID), advancment);
		}
		else
		{
			return contracts.put(ContractsBase.getByName(capabilityID), advancment);
		}
	}
}