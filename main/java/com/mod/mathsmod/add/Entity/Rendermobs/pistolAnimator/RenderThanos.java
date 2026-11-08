package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityDrillingMachine;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class RenderThanos extends RenderPlayerModified
{

	public RenderThanos(RenderManager renderManager) {
		super(renderManager, false);
		this.addLayer(new ThanosAuraLayer(127, 0, 127));
	}
	
	@Override
	protected void preRenderCallback(AbstractClientPlayer entity, float partialTickTime)
    {
		GlStateManager.scale(1.5, 1.5, 1.5);
    }
	
	public ResourceLocation getEntityTexture(AbstractClientPlayer entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/thanos.png");
    }

	public void renderName(AbstractClientPlayer entity, double x, double y, double z)
    {
        if (this.canRenderName(entity))
        {
            double d0 = entity.getDistanceSq(this.renderManager.renderViewEntity);
            float f = entity.isSneaking() ? 64 : 128;

            if (d0 < (double)(f * f))
            {
                GlStateManager.alphaFunc(516, 0.1F);
                this.renderEntityName(entity, x, y, z, TextFormatting.DARK_RED + "Thanos", d0);
            }
        }
    }
	
}
