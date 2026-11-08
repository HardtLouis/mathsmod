package com.mod.mathsmod.add.Entity.Rendermobs.layer;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Rendermobs.RenderBot;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerBot implements LayerRenderer<EntityBot>
{
    private static final ResourceLocation BOT_LAYER = new ResourceLocation(Mathsmod.MODID, "textures/entity/bot/bot_layer.png");
    private final RenderBot botRenderer;

    public LayerBot(RenderBot botRendererIn)
    {
        this.botRenderer = botRendererIn;
    }

    public void doRenderLayer(EntityBot entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entitylivingbaseIn.getSkin() != null && entitylivingbaseIn.getSkin() != "-" && entitylivingbaseIn.getSkin().length() > 0)
        {
            this.botRenderer.bindTexture(BOT_LAYER);
            GlStateManager.color(1, 1, 1);
            this.botRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}