package com.mod.mathsmod.add.items.guns.accessories;

public class ItemAccuracyMuffler extends GunAccessoryBase
{

	public ItemAccuracyMuffler() 
	{
		super("accuracy_muffler", EnumAccessoryType.SCOPE);
	}

	@Override
	public float innaccuracyMultiplier() 
	{
		return 0.7F;
	}

	@Override
	public int switchCooldownBonus() 
	{
		return 1;
	}

	@Override
	public float zoomMultiplier() 
	{
		return 1.2F;
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
		return 0.9F;
	}
	
}