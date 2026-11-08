package com.mod.mathsmod.add.Entity.Rendermobs.layer;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;
import com.mod.mathsmod.add.Entity.Models.ModelChauvoput;
import com.mod.mathsmod.add.Entity.Rendermobs.RenderChauvoput;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerChauvoputLava implements LayerRenderer<EntityNChauvoput>
{
    private static final ResourceLocation CHAUVOT = new ResourceLocation(Mathsmod.MODID, "textures/entity/chauvoput/chauvoput_layer.png");
    private final RenderChauvoput chauvRenderer;

    public LayerChauvoputLava(RenderChauvoput chauvoRendererIn)
    {
        this.chauvRenderer = chauvoRendererIn;
    }

    public void doRenderLayer(EntityNChauvoput entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	this.chauvRenderer.bindTexture(CHAUVOT);
        float afloat = 0.15F + entitylivingbaseIn.getHealth() / entitylivingbaseIn.getMaxHealth() * 0.55F;
        Color col = new Color(RenderLaser.getColorFromAngleQuarter(((float)(entitylivingbaseIn.ticksExisted + partialTicks)) / 30000.0F * 20000.0F));
		GlStateManager.color(((float)col.getRed()) / 255 * afloat, ((float)col.getGreen()) / 255 * afloat, ((float)col.getBlue()) / 255 * afloat);
        ModelChauvoput chauv = (ModelChauvoput) chauvRenderer.getMainModel();
        chauv.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}