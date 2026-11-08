package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTargetDrone;
import com.mod.mathsmod.add.Entity.Models.ModelDrone;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDrone extends EasyRenderEntity<EntityTargetDrone> 
{

	public RenderDrone(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDrone(), 0.0F);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTargetDrone entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/drone.png");
	}

	@Override
	protected void preRenderCallback(EntityTargetDrone entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw - 90, 0, 1, 0);
    }
}
