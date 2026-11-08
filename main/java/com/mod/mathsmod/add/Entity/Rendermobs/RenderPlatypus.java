package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityPlatypus;
import com.mod.mathsmod.add.Entity.Models.ModelPlatypus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlatypus extends RenderLiving<EntityPlatypus>
{

	public RenderPlatypus(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPlatypus(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPlatypus entity)
	{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/platypus.png");
	}
	
	@Override
	protected void preRenderCallback(EntityPlatypus entity, float partialTickTime)
    {
    }
}