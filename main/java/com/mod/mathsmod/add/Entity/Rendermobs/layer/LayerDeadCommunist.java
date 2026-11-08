package com.mod.mathsmod.add.Entity.Rendermobs.layer;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDeadHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.RenderDeadHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.RenderHunter;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerDeadCommunist implements LayerRenderer<EntityDeadHunter>
{
    private static final ResourceLocation JACKET = new ResourceLocation(Mathsmod.MODID, "textures/entity/dead_hunter/communist.png");
    private final RenderDeadHunter hunterRenderer;

    public LayerDeadCommunist(RenderDeadHunter hunterRendererIn)
    {
        this.hunterRenderer = hunterRendererIn;
    }

    public void doRenderLayer(EntityDeadHunter entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
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