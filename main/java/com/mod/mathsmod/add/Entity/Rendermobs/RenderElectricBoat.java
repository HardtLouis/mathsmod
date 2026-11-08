package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Models.ModelElectricBoat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderElectricBoat extends EasyRenderEntity
{

	public RenderElectricBoat(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelElectricBoat(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/electric_boat.png");
	}
	
	protected void preRenderCallback(Entity entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw - 90, 0, 1, 0);
        GlStateManager.translate(0, -0.35D, 0);
        GlStateManager.scale(2F, 2F, 2F);
    }

}
