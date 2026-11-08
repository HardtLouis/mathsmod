package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityImprovedArrow;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderImprovedArrow extends RenderArrow<EntityImprovedArrow>
{

	public RenderImprovedArrow(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityImprovedArrow entity) 
	{
		return new ResourceLocation("textures/entity/projectiles/arrow.png");
	}

}
