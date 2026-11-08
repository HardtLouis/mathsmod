package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemOffenseGuard extends ItemToolPart
{

	public ItemOffenseGuard()
	{
		super("offense_guard");
	}

	@Override
	public EnumParts part() {
		return EnumParts.GUARD;
	}

	@Override
	public float damageMultiplier() {
		return 1.1F;
	}

	@Override
	public float efficiencyMultiplier() {
		return 1;
	}

	@Override
	public float protectionBonus()
	{
		return -1.0F;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		return 0F;
	}
}