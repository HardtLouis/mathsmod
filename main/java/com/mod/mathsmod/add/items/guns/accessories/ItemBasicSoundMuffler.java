package com.mod.mathsmod.add.items.guns.accessories;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class ItemBasicSoundMuffler extends GunAccessoryBase
{

	public ItemBasicSoundMuffler() 
	{
		super("basic_sound_muffler", EnumAccessoryType.MUFFLER);
	}

	@Override
	public float innaccuracyMultiplier() 
	{
		return 0.8F;
	}

	@Override
	public int switchCooldownBonus() 
	{
		return 2;
	}

	@Override
	public float zoomMultiplier() 
	{
		return 1F;
	}

	@Override
	public float soundMultiplier() 
	{
		return 0.1F;
	}

	@Override
	public float smoothMultiplier()
	{
		return 1;
	}

	@Override
	public float damageMultiplier()
	{
		return 0.95F;
	}

}
