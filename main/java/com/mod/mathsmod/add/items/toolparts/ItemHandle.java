package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHandle extends ItemToolPart
{

	public ItemHandle()
	{
		super("handle");
	}

	@Override
	public EnumParts part() {
		return EnumParts.HANDLE;
	}

	@Override
	public float damageMultiplier() {
		return 1;
	}

	@Override
	public float efficiencyMultiplier() {
		return 1;
	}

	@Override
	public float protectionBonus()
	{
		return 0;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		return 0;
	}
}
