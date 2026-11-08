package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractFurnaceUpgrade extends ContractsBase
{

	public ContractFurnaceUpgrade()
	{
		super("furnace_upgrade");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.METALURGIC_FURNACE_UPGRADE, 1);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.NIGHT_SHARD;
	}

	@Override
	public int maxProgress() 
	{
		return 10;
	}

	@Override
	public String description() 
	{
		return "find 10 pyromancer where earth is burning and kill them";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(39,-80,0);
	}

	@Override
	public String nameInGui() 
	{
		return "Higher temperatures";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.KURAYUM_ROD);
		return list;
	}

}
