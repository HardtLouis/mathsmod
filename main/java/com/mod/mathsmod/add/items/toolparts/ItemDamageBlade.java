package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.item.ItemStack;

public class ItemDamageBlade extends ItemToolPart
{

	public ItemDamageBlade() 
	{
		super("damage_blade");
	}

	@Override
	public EnumParts part() 
	{
		return EnumParts.BLADE;
	}

	@Override
	public float damageMultiplier()
	{
		return 1.1F;
	}

	@Override
	public float efficiencyMultiplier() 
	{
		return 1.0F;
	}

	@Override
	public float reachBonus(ItemStack itemStack) 
	{
		if(this.getMetal(itemStack) != null)
		{
			return this.getMetal(itemStack).bonusReach - 0.2F;
		}
		return 0;
	}

	@Override
	public float protectionBonus() 
	{
		return 0;
	}

}