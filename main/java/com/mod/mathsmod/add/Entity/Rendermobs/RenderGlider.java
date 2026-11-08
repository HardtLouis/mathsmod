package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGlider;
import com.mod.mathsmod.add.Entity.Models.ModelGlider;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGlider extends EasyRenderEntity<EntityGlider>{

	public RenderGlider(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelGlider(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGlider entity) {
		
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/glider.png");
	}
	protected void preRenderCallback(EntityGlider entity, float partialTickTime)
    {
		float f1 = (float)((double)entity.prevRotationYaw + (double)(entity.rotationYaw - entity.prevRotationYaw) * partialTickTime);
		GlStateManager.rotate(f1 + 180, 0, 1, 0);
		GlStateManager.scale(1.7, 1.7, 1.7);
		GlStateManager.translate(0, 0.75, 0);
    }
}
