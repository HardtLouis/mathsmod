package com.mod.mathsmod.add.items;

public interface IHeavyHitterItem
{
	int hitFrame();
	
	int hitTime();
	
	public default boolean shouldUnpressAttackKey()
	{
		return true;
	}
}
