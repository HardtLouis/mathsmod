package com.mod.mathsmod.add.Entity.Rendermobs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityZombie;

public class RenderSteroideSkeleton extends RenderSkeleton
{

	public RenderSteroideSkeleton(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	protected void preRenderCallback(AbstractSkeleton entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }
}
