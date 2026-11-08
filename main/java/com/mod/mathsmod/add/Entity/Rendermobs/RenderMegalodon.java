package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityShark;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMegalodon extends RenderShark
{

	public RenderMegalodon(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityShark entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/megalodon.png");
	}
	
	@Override
	protected void preRenderCallback(EntityShark entity, float partialTickTime)
    {
		GlStateManager.translate(0, 16F, 0);
        GlStateManager.scale(12F, 12F, 12F);
    }

}
