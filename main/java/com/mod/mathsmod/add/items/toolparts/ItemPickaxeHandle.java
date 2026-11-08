package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemPickaxeHandle extends ItemToolPart
{

	public ItemPickaxeHandle()
	{
		super("handle_pickaxe");
	}

	@Override
	public EnumParts part() {
		return EnumParts.HANDLE_TOOL;
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
