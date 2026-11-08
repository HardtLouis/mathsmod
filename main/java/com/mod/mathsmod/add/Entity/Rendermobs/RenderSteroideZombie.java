package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.EntitySkeledon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;

public class RenderSteroideZombie extends RenderZombie
{

	public RenderSteroideZombie(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	protected void preRenderCallback(EntityZombie entity, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		
    }

}
