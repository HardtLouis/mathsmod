package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractTechShield extends ContractsBase
{

	public ContractTechShield()
	{
		super("tech_shield");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.TECH_SHIELD, 1);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.KURAYUM_INGOT;
	}

	@Override
	public int maxProgress() 
	{
		return 20;
	}

	@Override
	public String description() 
	{
		return "kill 20 hunters";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(39,-140,0);
	}

	@Override
	public String nameInGui() 
	{
		return "i need a cover";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.KURAYUM_JETPACK);
		return list;
	}

}