package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterBlocks;
import com.mod.mathsmod.add.RegisterContracts;
import com.mod.mathsmod.add.RegisterItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class ContractLootBox extends ContractsBase
{

	public ContractLootBox()
	{
		super("loot_box_polarite");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterBlocks.LOOTBOX, 3);
	}
	
	public ContractsBase Needed()
	{
		return null;
	}

	@Override
	public int maxProgress() 
	{
		return 30;
	}

	@Override
	public String description() 
	{
		return "kill 30 frozen enemies";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(100,0,0);
	}

	@Override
	public String nameInGui() 
	{
		return "I can't find polarite :(";
	}
	
	public List<ContractsBase> fils()
	{
		List list = Lists.newArrayList();
		return list;
	}
	
	public void onCompletion(EntityPlayer player, World world)
	{
		this.setProgress(player, this, 0);
	}

}