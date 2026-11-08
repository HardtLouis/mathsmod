package com.mod.mathsmod.add.items.guns.accessories;

public class ItemHalfMuffler extends GunAccessoryBase
{

	public ItemHalfMuffler() 
	{
		super("half_muffler", EnumAccessoryType.MUFFLER);
	}

	@Override
	public float innaccuracyMultiplier() 
	{
		return 0.8F;
	}

	@Override
	public int switchCooldownBonus() 
	{
		return 1;
	}

	@Override
	public float zoomMultiplier() 
	{
		return 1F;
	}

	@Override
	public float soundMultiplier() 
	{
		return 0.3F;
	}

	@Override
	public float smoothMultiplier()
	{
		return 1;
	}

	@Override
	public float damageMultiplier()
	{
		return 1F;
	}

}