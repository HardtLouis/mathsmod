package com.mod.mathsmod.add.Entity.Rendermobs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;

public class RenderHZombie extends RenderZombie
{

	public RenderHZombie(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	protected void preRenderCallback(EntityZombie entity, float partialTickTime)
    {
		GlStateManager.scale(1.2F, 0.8F, 1.5F);
		
    }

}
