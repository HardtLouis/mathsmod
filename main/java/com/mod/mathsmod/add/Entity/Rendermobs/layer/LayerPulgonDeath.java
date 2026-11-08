package com.mod.mathsmod.add.Entity.Rendermobs.layer;

import java.util.Random;

import com.mod.mathsmod.add.Entity.Entity.EntityDBossDragonPhase;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class LayerPulgonDeath implements LayerRenderer<EntityDBossDragonPhase>
{
    public void doRenderLayer(EntityDBossDragonPhase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.deathTicks > 0)
        {
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}