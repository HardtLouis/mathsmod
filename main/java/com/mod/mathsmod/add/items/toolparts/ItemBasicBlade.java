package com.mod.mathsmod.add.items.toolparts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBasicBlade extends ItemToolPart
{

	public ItemBasicBlade() 
	{
		super("blade");
	}

	@Override
	public EnumParts part() 
	{
		return EnumParts.BLADE;
	}

	@Override
	public float damageMultiplier()
	{
		return 1.0F;
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
			return this.getMetal(itemStack).bonusReach;
		}
		return 0;
	}

	@Override
	public float protectionBonus() 
	{
		return 0;
	}

}
