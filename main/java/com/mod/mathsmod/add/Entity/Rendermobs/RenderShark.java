package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityShark;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Models.ModelShark;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderShark extends RenderLiving<EntityShark>
{

	public RenderShark(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelShark(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShark entity)
	{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/shark.png");
	}
	
	@Override
	protected void preRenderCallback(EntityShark entity, float partialTickTime)
    {
		GlStateManager.translate(0, 4F, 0);
        GlStateManager.scale(3F, 3F, 3F);
    }
}