
package com.mod.mathsmod.add.items.guns.accessories;

public class ItemBasicSight extends GunAccessoryBase
{

	public ItemBasicSight() 
	{
		super("basic_sight", EnumAccessoryType.SCOPE);
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
		return 1.2F;
	}

	@Override
	public float soundMultiplier()
	{
		return 1;
	}

	@Override
	public float smoothMultiplier()
	{
		return 1;
	}

	@Override
	public float damageMultiplier()
	{
		return 0.975F;
	}

	public boolean isScope()
	{
		return false;
	}
}
