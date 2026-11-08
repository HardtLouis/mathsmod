package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityWoodGolem;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWoodGolem extends RenderLiving<EntityWoodGolem>
{

	public RenderWoodGolem(RenderManager rendermanagerIn, ModelBase modelbaseIn) 
	{
		super(rendermanagerIn, modelbaseIn, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWoodGolem entity) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/poulopop_golem.png");
	}
	
	protected void preRenderCallback(EntityWoodGolem entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

}