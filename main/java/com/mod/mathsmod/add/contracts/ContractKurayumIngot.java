package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractKurayumIngot extends ContractsBase
{

	public ContractKurayumIngot()
	{
		super("kurayum_ingot");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(Item.getByNameOrId("mathsmod:kurayum_ingot"), 6);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.KURAYUM_ROD;
	}

	@Override
	public int maxProgress() 
	{
		return 10;
	}

	@Override
	public String description() 
	{
		return "kill 10 nether hunters";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(39,-120,0);
	}

	@Override
	public String nameInGui() 
	{
		return "red like blood";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		list.add(RegisterContracts.TECH_SHIELD);
		return list;
	}

}
