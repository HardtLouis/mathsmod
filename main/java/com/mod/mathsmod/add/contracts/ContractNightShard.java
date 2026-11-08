package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractNightShard extends ContractsBase
{

	public ContractNightShard()
	{
		super("night_shard");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.NIGHT_SHARD, 5);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.SILVER_INGOT;
	}

	@Override
	public int maxProgress() 
	{
		return 3;
	}

	@Override
	public String description() 
	{
		return "make 3 alloy ingots a caster";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(26,-60,0);
	}

	@Override
	public String nameInGui() 
	{
		return "do it yourself 2";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.FURNACE_UPGRADE);
		return list;
	}

}
