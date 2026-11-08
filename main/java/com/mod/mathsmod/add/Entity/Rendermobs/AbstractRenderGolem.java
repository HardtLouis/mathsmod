package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.AbstractGolem;
import com.mod.mathsmod.add.Entity.Models.AbstractModelGolem;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractRenderGolem extends RenderLiving<AbstractGolem>
{
	
    public AbstractRenderGolem(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new AbstractModelGolem(), 0.5F);
    }

    protected abstract ResourceLocation getEntityTexture(AbstractGolem entity);

    protected void applyRotations(AbstractGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);

        if ((double)entityLiving.limbSwingAmount >= 0.01D)
        {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }
    
    protected void preRenderCallback(AbstractGolem entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(entitylivingbaseIn.getSizeMultiplier(), entitylivingbaseIn.getSizeMultiplier(), entitylivingbaseIn.getSizeMultiplier());
    }
}
