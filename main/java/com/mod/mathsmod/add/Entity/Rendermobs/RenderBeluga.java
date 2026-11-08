package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBeluga;
import com.mod.mathsmod.add.Entity.Models.ModelBeluga;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBeluga extends RenderLiving<EntityBeluga>
{

	public RenderBeluga(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new ModelBeluga(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBeluga entity) 
	{
		if(entity.getCustomNameTag().equals("Blanquer"))
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/beluga/beluga_blanquer.png");
		}
		if(entity.isSaddled())
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/beluga/beluga_saddled.png");
		}
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/beluga/beluga.png");
	}
	
	protected void preRenderCallback(EntityBeluga entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.translate(0, 1F, 0);
		GlStateManager.scale(2.5F, 2.5F, 2.5F);
    }

}
