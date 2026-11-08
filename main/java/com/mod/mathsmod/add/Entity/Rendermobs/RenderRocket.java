package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocket;
import com.mod.mathsmod.add.Entity.Models.ModelRocket;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderRocket extends EasyRenderEntity<EntityRocket> {

	public RenderRocket(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelRocket(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRocket entity) {
		
		return new ResourceLocation("textures/blocks/concrete_powder_white.png");
	}
	
	protected void preRenderCallback(EntityRocket entity, float partialTickTime)
    {
		Vec3d vec = entity.colorChanges();
		GlStateManager.color((float)vec.x, (float)vec.y, (float)vec.z);
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
        GlStateManager.translate(0, 1.325D, 0);
        GlStateManager.scale(1F, 1F, 1.5F);
    }

}