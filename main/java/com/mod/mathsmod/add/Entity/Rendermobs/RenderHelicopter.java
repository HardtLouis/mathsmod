package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityHelicopter;
import com.mod.mathsmod.add.Entity.Models.ModelHelicopter;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHelicopter extends EasyRenderEntity<EntityHelicopter>{

	public RenderHelicopter(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelHelicopter(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHelicopter entity) {
		
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/helicopter.png");
	}
	protected void preRenderCallback(EntityHelicopter entity, float partialTickTime)
    {
		float f1 = (float)((double)entity.prevRotationYaw + (double)(entity.rotationYaw - entity.prevRotationYaw) * partialTickTime);
		GlStateManager.rotate(f1 + 180, 0, 1, 0);
		if(!entity.getOnGroundSync() && entity.getControllingPassenger() != null && entity.getControllingPassenger() instanceof EntityLivingBase)
		{
			EntityLivingBase ridder = (EntityLivingBase) entity.getControllingPassenger();
			float e = ridder.moveStrafing;
			float i = ridder.moveForward;
			GlStateManager.rotate(e * 5, 0, 0, 1);
			GlStateManager.rotate(i * 10, 1, 0, 0);
		}
		GlStateManager.scale(4, 4, 4);
		GlStateManager.translate(0, 0, 0);
    }
}