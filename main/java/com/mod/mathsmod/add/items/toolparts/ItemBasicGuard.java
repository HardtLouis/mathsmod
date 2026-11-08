package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBasicGuard extends ItemToolPart
{

	public ItemBasicGuard()
	{
		super("guard");
	}

	@Override
	public EnumParts part() {
		return EnumParts.GUARD;
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