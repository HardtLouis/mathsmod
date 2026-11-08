package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTitank extends RenderTank
{

	public RenderTitank(RenderManager renderManagerIn) {
		super(renderManagerIn);
		// TODO Auto-generated constructor stub
	}

	protected void preRenderCallback(EntityTank entity, float partialTickTime)
    {
		super.preRenderCallback(entity, partialTickTime);
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTank entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/tank/tank.png");
	}
}
