package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemDefenceGuard extends ItemToolPart
{

	public ItemDefenceGuard()
	{
		super("defence_guard");
	}

	@Override
	public EnumParts part() {
		return EnumParts.GUARD;
	}

	@Override
	public float damageMultiplier() 
	{
		return 0.95F;
	}

	@Override
	public float efficiencyMultiplier() {
		return 1;
	}

	@Override
	public float protectionBonus()
	{
		return 2.0F;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		return 0F;
	}
}