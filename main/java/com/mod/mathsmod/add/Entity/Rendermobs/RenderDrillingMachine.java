package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityDrillingMachine;
import com.mod.mathsmod.add.Entity.Models.ModelDrillingMachine;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDrillingMachine extends EasyRenderEntity<EntityDrillingMachine>{

	public RenderDrillingMachine(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelDrillingMachine(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDrillingMachine entity) {
		
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/drilling_machine.png");
	}
	
	protected void preRenderCallback(EntityDrillingMachine entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		GlStateManager.scale(7, 7, 7);
    }
}