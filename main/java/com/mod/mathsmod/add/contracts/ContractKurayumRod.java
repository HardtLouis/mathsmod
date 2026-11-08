package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractKurayumRod extends ContractsBase
{

	public ContractKurayumRod()
	{
		super("kurayum_rod");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.KURAYUM_FISHING_ROD, 1);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.FURNACE_UPGRADE;
	}

	@Override
	public int maxProgress() 
	{
		return 50;
	}

	@Override
	public String description() 
	{
		return "fish 50 things with a tfosium fishing rod";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(39,-100,0);
	}

	@Override
	public String nameInGui() 
	{
		return "advanced fishing";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.KURAYUM_INGOT);
		return list;
	}

}
