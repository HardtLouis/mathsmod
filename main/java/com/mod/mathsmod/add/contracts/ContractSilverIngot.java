package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractSilverIngot extends ContractsBase
{

	public ContractSilverIngot()
	{
		super("silver_ingot");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(Item.getByNameOrId("mathsmod:silver_ingot"), 3);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.REVOLVER;
	}

	@Override
	public int maxProgress() 
	{
		return 10;
	}

	@Override
	public String description() 
	{
		return "craft a metalurgic furnace and melt something 10 times";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(13,-40,0);
	}

	@Override
	public String nameInGui() 
	{
		return "do it yourself";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.NIGHT_SHARD);
		return list;
	}

}
