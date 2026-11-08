package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemPickaxePart extends ItemToolPart
{

	public ItemPickaxePart()
	{
		super("pickaxe_part");
	}

	@Override
	public EnumParts part() {
		return EnumParts.PICKAXE;
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
