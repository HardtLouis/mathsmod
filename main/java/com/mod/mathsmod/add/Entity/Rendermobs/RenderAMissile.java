package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.*;
import com.mod.mathsmod.add.Entity.Models.ModelAMissile;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAMissile extends EasyRenderEntity<EntityAMissile>{

	public RenderAMissile(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelAMissile(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAMissile entity) {
		
		if(entity instanceof EntityNuclearMissile || entity instanceof EntitySuperNuclearMissile)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/nuclear_missile.png");
		}
		if(entity instanceof EntityBMissile)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/b_missile.png");
		}
		if(entity instanceof EntityTMissile)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/t_missile.png");
		}
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/a_missile.png");
	}
	
	@Override
	protected void preRenderCallback(EntityAMissile entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch + 90, 1, 0, 0);
		GlStateManager.scale(2, 2, 2);
		if(entity instanceof EntitySuperNuclearMissile)
		{
			GlStateManager.color(1, 0, 0);
		}
    }
}