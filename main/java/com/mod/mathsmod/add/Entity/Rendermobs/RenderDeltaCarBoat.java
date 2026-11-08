package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityHappyMazeFoe;
import com.mod.mathsmod.add.Entity.Models.ModelElectricBoat;
import com.mod.mathsmod.add.Entity.Models.ModelUltimateVehicle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDeltaCarBoat extends EasyRenderEntity
{

	public RenderDeltaCarBoat(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelUltimateVehicle(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/dcb.png");
	}
	
	protected void preRenderCallback(Entity entity, float partialTickTime)
    {
		if(entity instanceof EntityHappyMazeFoe)
		{
			GlStateManager.rotate(((EntityHappyMazeFoe) entity).getCommonYaw() - 90, 0, 1, 0);
		}
		else
		{
			GlStateManager.rotate(entity.rotationYaw - 90, 0, 1, 0);
		}
        GlStateManager.translate(0, -0.45D, 0);
        GlStateManager.scale(2F, 2F, 2F);
    }

}
