package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityTankRocket;
import com.mod.mathsmod.add.Entity.Models.ModelRocket;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTankRocket  extends EasyRenderEntity<EntityTankRocket> {

	public RenderTankRocket(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelRocket(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTankRocket entity) {
		
		return new ResourceLocation("textures/blocks/concrete_powder_black.png");
	}
	
	protected void preRenderCallback(EntityTankRocket entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
		GlStateManager.color(0.2F, 0, 0);
        GlStateManager.translate(0, 5.5D, 0);
        GlStateManager.scale(4F, 4F, 8F);
    }

}