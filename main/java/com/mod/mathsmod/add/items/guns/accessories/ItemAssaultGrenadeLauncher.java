package com.mod.mathsmod.add.items.guns.accessories;

public class ItemAssaultGrenadeLauncher extends GunAccessoryBase
{

	public ItemAssaultGrenadeLauncher() {
		super("assault_grenade_launcher", EnumAccessoryType.GRENADE_LAUNCHER);
	}

	@Override
	public float innaccuracyMultiplier()
	{
		return 1;
	}

	@Override
	public int switchCooldownBonus() 
	{
		return 5;
	}

	@Override
	public float zoomMultiplier() 
	{
		return 1;
	}

	@Override
	public float soundMultiplier() 
	{
		return 1F;
	}

	@Override
	public float smoothMultiplier() 
	{
		return 1;
	}

	@Override
	public float damageMultiplier() 
	{
		return 1;
	}

}
