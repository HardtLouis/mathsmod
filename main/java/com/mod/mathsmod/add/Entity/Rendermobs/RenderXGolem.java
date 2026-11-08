package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.AbstractGolem;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderXGolem extends AbstractRenderGolem{

	public RenderXGolem(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractGolem entity) {
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/x_golem.png");
	}
	
	

}
