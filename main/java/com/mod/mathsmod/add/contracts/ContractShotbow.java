package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;

public class ContractShotbow extends ContractsBase
{

	public ContractShotbow()
	{
		super("shotbow");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterItems.SHOTBOW);
	}
	
	public ContractsBase Needed()
	{
		return RegisterContracts.TATANATOR;
	}

	@Override
	public int maxProgress() 
	{
		return 1;
	}

	@Override
	public String description() 
	{
		return "kill a bat with a bow";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(-32,-80,0);
	}

	@Override
	public String nameInGui() 
	{
		return "easier with this thing";
	}
	

	
	public List<ContractsBase> fils()
	{
		return null;
	}

}