package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractKurayumJetpack extends ContractsBase
{

	public ContractKurayumJetpack()
	{
		super("kurayum_jetpack");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.KURAYUM_JETPACK, 1);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.TECH_SHIELD;
	}

	@Override
	public int maxProgress() 
	{
		return 10;
	}

	@Override
	public String description() 
	{
		return "kill 10 shulkers";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(39,-160,0);
	}

	@Override
	public String nameInGui() 
	{
		return "i am the fly";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		return list;
	}

}