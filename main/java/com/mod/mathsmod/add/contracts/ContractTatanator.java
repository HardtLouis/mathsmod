package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractTatanator extends ContractsBase
{

	public ContractTatanator()
	{
		super("tatanator");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.TATANATOR);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.REBONDI_BOOTS;
	}

	@Override
	public int maxProgress() 
	{
		return 1;
	}

	@Override
	public String description() 
	{
		return "kill 1 mosquitoes";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(-26,-60,0);
	}

	@Override
	public String nameInGui() 
	{
		return "Louisaurus like";
	}
	

	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.SHOTBOW);
		return list;
	}

}