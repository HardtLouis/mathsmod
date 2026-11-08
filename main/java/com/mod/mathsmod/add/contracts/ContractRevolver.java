package com.mod.mathsmod.add.contracts;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractRevolver extends ContractsBase
{

	public ContractRevolver()
	{
		super("revolver");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.REVOLVER);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.FIRST_STEP;
	}

	@Override
	public int maxProgress() 
	{
		return 1;
	}

	@Override
	public String description() 
	{
		return "just die";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(0,-20,0);
	}

	@Override
	public String nameInGui() 
	{
		return "(in)volontary death";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.REBONDI_BOOTS);
		list.add(RegisterContracts.SILVER_INGOT);
		return list;
	}

}
