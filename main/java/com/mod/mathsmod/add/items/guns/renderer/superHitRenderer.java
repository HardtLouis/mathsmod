package com.mod.mathsmod.add.items.guns.renderer;

import net.minecraft.entity.Entity;

public class superHitRenderer 
{
	public int ticks;
	public final Entity entity;
	public final int type;
	
	public superHitRenderer(Entity entityIn, int typeIn, int ticksIn)
	{
		ticks = ticksIn;
		entity = entityIn;
		type = typeIn;
	}
	
}
