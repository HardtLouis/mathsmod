package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityNuclearRocket;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.Entity.Models.ModelRocket;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNuclearRocket extends EasyRenderEntity<EntityNuclearRocket> {

	public RenderNuclearRocket(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelRocket(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNuclearRocket entity) {
		
		return new ResourceLocation("textures/blocks/concrete_powder_yellow.png");
	}
	
	protected void preRenderCallback(EntityNuclearRocket entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
        GlStateManager.translate(0, 5.5D, 0);
        GlStateManager.scale(4F, 4F, 8F);
    }

}