package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemHandleS2 extends ItemToolPart
{

	public ItemHandleS2()
	{
		super("handle_style_2");
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
