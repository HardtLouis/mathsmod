package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractFurnace extends ContractsBase
{

	public ContractFurnace()
	{
		super("first");
	}

	@Override
	public java.util.List<ContractsBase> fils() 
	{
		List<ContractsBase> list = Lists.newArrayList();
		list.add(RegisterContracts.REVOLVER);
		list.add(RegisterContracts.TUTU);
		return list;
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(Items.IRON_INGOT, 2);
	}

	@Override
	public int maxProgress() 
	{
		return 1;
	}

	@Override
	public String description() 
	{
		return "craft a furnace";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(0,0,0);
	}

	@Override
	public String nameInGui() 
	{
		return "the first step";
	}

}
