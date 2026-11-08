package com.mod.mathsmod.add.items.guns.accessories;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ItemBasicScope extends GunAccessoryBase
{

	public ItemBasicScope() 
	{
		super("basic_scope", EnumAccessoryType.SCOPE);
	}

	@Override
	public float innaccuracyMultiplier() 
	{
		return 0.7F;
	}

	@Override
	public int switchCooldownBonus() 
	{
		return 4;
	}

	@Override
	public float zoomMultiplier() 
	{
		return 5F;
	}

	@Override
	public float soundMultiplier() 
	{
		return 1F;
	}

	@Override
	public float smoothMultiplier()
	{
		return 1F;
	}

	@Override
	public float damageMultiplier()
	{
		return 1F;
	}
	
}