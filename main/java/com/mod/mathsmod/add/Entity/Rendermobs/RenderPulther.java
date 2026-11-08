package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDBossWitherPhase;
import com.mod.mathsmod.add.Entity.Models.ModelPulther;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerPultherAura;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;

public class RenderPulther extends AbstractRenderBaseBoss<EntityDBossWitherPhase>
{
    private static final ResourceLocation INVULNERABLE_WITHER_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/pulther/inv.png");
    private static final ResourceLocation WITHER_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/pulther/pulther.png");

    public RenderPulther(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPulther(0.0F), 1.0F);
        this.addLayer(new LayerPultherAura(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDBossWitherPhase entity)
    {
        int i = entity.getInvulTime();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? INVULNERABLE_WITHER_TEXTURES : WITHER_TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityDBossWitherPhase entitylivingbaseIn, float partialTickTime)
    {
        float f = 2.0F;
        int i = entitylivingbaseIn.getInvulTime();

        if (i > 0)
        {
            f -= ((float)i - partialTickTime) / 220.0F * 0.5F;
        }

        GlStateManager.scale(f, f, f);
        GlStateManager.scale(1.5, 1.5, 1.5);
    }
}