package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityObus;
import com.mod.mathsmod.add.Entity.Models.ModelObus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderObus extends EasyRenderEntity<EntityObus> {

	public RenderObus(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelObus(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityObus entity) {
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/obus.png");
	}

}
