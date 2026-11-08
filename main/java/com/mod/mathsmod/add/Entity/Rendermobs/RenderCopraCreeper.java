package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderCopraCreeper extends RenderCreeper
{
	
	private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/copracreeper.png");
	
	public RenderCopraCreeper(RenderManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	protected void preRenderCallback(EntityCreeper entitylivingbaseIn, float partialTickTime)
    {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.2F + f * 0.4F) * f1;
        float f3 = (1.2F + f * 0.1F) / f1;
        GlStateManager.scale(f2, f3, f2);
    }
	
	protected ResourceLocation getEntityTexture(EntityCreeper entity)
    {
        return CREEPER_TEXTURES;
    }
}
