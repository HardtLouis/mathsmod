package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityCameraDrone;
import com.mod.mathsmod.add.Entity.Models.ModelDrone;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCameraDrone extends EasyRenderEntity<EntityCameraDrone> 
{

	public RenderCameraDrone(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDrone(), 0.0F);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCameraDrone entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/camera_drone.png");
	}

	@Override
	protected void preRenderCallback(EntityCameraDrone entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw - 90, 0, 1, 0);
    }
}
