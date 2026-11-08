package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractTutu extends ContractsBase
{

	public ContractTutu()
	{
		super("tutu");
	}

	@Override
	public java.util.List<ContractsBase> fils() 
	{
		List<ContractsBase> list = Lists.newArrayList();
		return list;
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.TUTU, 1);
	}

	@Override
	public int maxProgress() 
	{
		return 1;
	}

	@Override
	public String description() 
	{
		return "make a wool block with 4 strings";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(-20,0,0);
	}

	@Override
	public String nameInGui() 
	{
		return "dress maker";
	}

	public ContractsBase Needed()
	{
		return RegisterContracts.FIRST_STEP;
	}
}
