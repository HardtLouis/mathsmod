package com.mod.mathsmod.add.Entity.Rendermobs.layer;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.RenderHunter;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class LayerCommunist implements LayerRenderer<EntityHunter>
{
    private static final ResourceLocation JACKET = new ResourceLocation(Mathsmod.MODID, "textures/entity/hunter/communist.png");
    private final RenderHunter hunterRenderer;

    public LayerCommunist(RenderHunter hunterRendererIn)
    {
        this.hunterRenderer = hunterRendererIn;
    }

    public void doRenderLayer(EntityHunter entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getCommunist() && !entitylivingbaseIn.isInvisible())
        {
            this.hunterRenderer.bindTexture(JACKET);
            this.hunterRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}