package com.mod.mathsmod.add.contracts;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.capabilities.CapabilitiesManager;
import com.mod.mathsmod.add.contractsManager.ContractManager;
import com.mod.mathsmod.add.messages.MessageContractClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ContractsBase 
{
	public static final List<ContractsBase> REGISTRY = Lists.newArrayList();
	public final String id;
	
	public ContractsBase(String registryid)
	{
		id = registryid;
	}
	
	public abstract List<ContractsBase> fils();
	
	public final void register()
	{
		if(id != null && !REGISTRY.contains(this))
		{
			REGISTRY.add(this);
		}
		else
		{
			System.out.print(this.getClass().getName() + " need a unique registry id" + System.lineSeparator());
		}
	}
	
	public int getIntId()
	{
		if(this.REGISTRY.contains(this))
		{
			return this.REGISTRY.indexOf(this);
		}
		else
		{
			return -1;
		}
	}
	
	public static final ContractsBase getByName(String name)
	{
		for(ContractsBase contract : ContractsBase.REGISTRY)
		{
			if(contract.id.equals(name))
			{
				return contract;
			}
		}
		return null;
	}
	
	public void Unlock(EntityPlayer attacked, World world)
	{
	}
	
	public boolean resetAdvOnComplete()
	{
		return false;
	}
	
	public abstract ItemStack recompense();

	public abstract int maxProgress();
	
	public ContractsBase Needed()
	{
		return null;
	}
	
	public abstract String description();
	
	public static boolean addProgress(EntityPlayer player, ContractsBase contract, int i)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(contract.isUnlocked(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player), player))
			{
				if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(contract))
				{
					if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(contract) >= 0)
					{
						int maxAdv = contract.maxProgress();
						int adv = MathHelper.clamp(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(contract) + i, 0, maxAdv);
						if(player instanceof EntityPlayerMP)
						{
							Mathsmod.network.sendTo(new MessageContractClient(contract.id, adv), (EntityPlayerMP) player);
						}
						player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).replace(contract, adv);
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					if(player instanceof EntityPlayerMP)
					{
						Mathsmod.network.sendTo(new MessageContractClient(contract.id, i), (EntityPlayerMP) player);
					}
					player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).put(contract, i);
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean setProgress(EntityPlayer player, ContractsBase contract, int adv)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(contract.isUnlocked(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player), player))
			{
				if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(contract))
				{
					player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).replace(contract, adv);
					return true;
				}
				else
				{
					player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).put(contract, adv);
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean ratiboise(EntityPlayer player, ContractsBase contract, int adv)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(player instanceof EntityPlayerMP)
			{
				Mathsmod.network.sendTo(new MessageContractClient(contract.id, 0), (EntityPlayerMP) player);
			}
			return player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).remove(contract) != null;
		}
		return false;
	}
	
	public static boolean resetProgress(EntityPlayer player, ContractsBase contract)
	{
		if(player instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageContractClient(contract.id, 0), (EntityPlayerMP) player);
		}
		contract.setProgress(player, contract, 0);
		if(contract.fils() != null)
		{
			for(ContractsBase contr : contract.fils())
			{
				resetProgressRatiboise(player, contr);
			}
		}
		return false;
	}
	
	public static boolean resetProgressRatiboise(EntityPlayer player, ContractsBase contract)
	{
		if(player instanceof EntityPlayerMP)
		{
			Mathsmod.network.sendTo(new MessageContractClient(contract.id, 0), (EntityPlayerMP) player);
		}
		contract.ratiboise(player, contract, 0);
		
		if(contract.fils() != null)
		{
			for(ContractsBase contr : contract.fils())
			{
				resetProgressRatiboise(player, contr);
			}
		}
		return false;
	}
	
	public static int getAdv(EntityPlayer player, ContractsBase contract)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(contract))
			{
				int feur = player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(contract);
				if(player instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageContractClient(contract.id, feur), (EntityPlayerMP) player);
				}
				
				return feur;
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}
	
	public int getProgress(EntityPlayer player)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(this))
			{
				int feur = player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(this);
				if(player instanceof EntityPlayerMP)
				{
					Mathsmod.network.sendTo(new MessageContractClient(this.id, feur), (EntityPlayerMP) player);
				}
				
				return feur;
			}
		}
		return 0;
	}
	
	public float getAdvancment(EntityPlayer player)
	{
		if(player != null && player.hasCapability(CapabilitiesManager.CONTRACTS, null))
		{
			if(player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).containsKey(this))
			{
				return ((float) player.getCapability(CapabilitiesManager.CONTRACTS, null).getContracts(player).get(this)) / this.maxProgress();
			}
		}
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean full(EntityPlayer player)
	{
		return this.maxProgress() <= getProgress(player);
	}
	
	public boolean isUnlocked(HashMap<ContractsBase, Integer> hashMap, EntityPlayer player)
	{
		return this.Needed() == null || (hashMap.containsKey(this.Needed()) && hashMap.get(this.Needed()) < 0);
	}
	
	public void onCompletion(EntityPlayer player, World world)
	{
	}
	
	public abstract Vec3i getPosInGui();

	public abstract String nameInGui();
}
