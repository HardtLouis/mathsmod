package com.mod.mathsmod.add.contracts;

import java.util.List;

import com.google.common.collect.Lists;
import com.mod.mathsmod.add.RegisterBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class ContractBasicLootbox extends ContractsBase
{

	public ContractBasicLootbox()
	{
		super("basic_loot_box");
	}

	@Override
	public ItemStack recompense() 
	{
		return new ItemStack(RegisterBlocks.BASIC_LOOTBOX, 5);
	}
	
	public ContractsBase Needed()
	{
		return null;
	}

	@Override
	public int maxProgress() 
	{
		return 50;
	}

	@Override
	public String description() 
	{
		return "kill 30 enemies in the copraveg dimension (like blals, blobs or copracrabs)";
	}

	@Override
	public Vec3i getPosInGui() 
	{
		return new Vec3i(120,0,0);
	}

	@Override
	public String nameInGui() 
	{
		return "gambling addict";
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