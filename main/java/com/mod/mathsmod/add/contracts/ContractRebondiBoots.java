package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractRebondiBoots extends ContractsBase
{

	public ContractRebondiBoots()
	{
		super("rebondiboots");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.REBONDI_BOOTS);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.REVOLVER;
	}

	@Override
	public int maxProgress() 
	{
		return 5;
	}

	@Override
	public String description() 
	{
		return "fall to your death 5 times";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(-13,-40,0);
	}

	@Override
	public String nameInGui() 
	{
		return "tired of falling ? try this";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.TATANATOR);
		return list;
	}

}