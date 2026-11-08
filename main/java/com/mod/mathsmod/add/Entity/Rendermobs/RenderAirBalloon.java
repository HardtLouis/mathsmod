package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAirBalloon;
import com.mod.mathsmod.add.Entity.Models.ModelAirBalloon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAirBalloon extends EasyRenderEntity<EntityAirBalloon>{

	public RenderAirBalloon(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelAirBalloon(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAirBalloon entity) {
		
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/air_balloon.png");
	}
	protected void preRenderCallback(EntityAirBalloon entity, float partialTickTime)
    {
		float f1 = (float)((double)entity.prevRotationYaw + (double)(entity.rotationYaw - entity.prevRotationYaw) * partialTickTime);
		GlStateManager.rotate(f1 + 180, 0, 1, 0);
		GlStateManager.scale(5, 5, 5);
    }
}
