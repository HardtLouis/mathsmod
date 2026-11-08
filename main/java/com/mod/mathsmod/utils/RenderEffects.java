package com.mod.mathsmod.utils;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEffects  
{
	public static final ResourceLocation ICE_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/effects/ice.png");
	public static final ResourceLocation CHAINS = new ResourceLocation(Mathsmod.MODID, "textures/effects/celestial_chains.png");
	public static final ResourceLocation BANDAGES = new ResourceLocation(Mathsmod.MODID, "textures/effects/bandages.png");
	
	public static final ResourceLocation BLACK_SPHERE = new ResourceLocation(Mathsmod.MODID, "textures/effects/black_sphere.png");

    public static void renderPlayerInIce(Vec3d pos, float height, float width)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(pos.x, pos.y, pos.z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        Minecraft.getMinecraft().renderEngine.bindTexture(ICE_TEXTURE);
        float f1 = 240.0F;
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        bufferbuilder.pos(-width / 2, 0, -width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, 0, -width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, -width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, height, -width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        bufferbuilder.pos(-width / 2, 0, -width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, 0, -width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, 0, width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, 0, width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        bufferbuilder.pos(-width / 2, 0, width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, 0, width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, height, width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        bufferbuilder.pos(-width / 2, height, -width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, -width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, height, width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        bufferbuilder.pos(-width / 2, 0, -width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, height, -width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, height, width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(-width / 2, 0, width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        bufferbuilder.pos(width / 2, 0, -width / 2).tex(0, 0).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, -width / 2).tex(0, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, height, width / 2).tex(1, 1).color(255, 255, 255, 125).endVertex();
        bufferbuilder.pos(width / 2, 0, width / 2).tex(1, 0).color(255, 255, 255, 125).endVertex();
        
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderPlayerSpellSoulContractTomb(Vec3d pos)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(pos.x, pos.y, pos.z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        Minecraft.getMinecraft().renderEngine.bindTexture(BANDAGES);
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        bufferbuilder.pos(-0.25, -0.1, -0.25).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.25, -0.1, -0.25).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.25, -0.1, 0.25).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.25, -0.1, 0.25).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.25, -0.1, -0.25).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.25, -0.1, -0.25).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, -0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 0.6, -0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.25, -0.1, -0.25).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.25, -0.1, 0.25).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 0.6, 0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 0.6, -0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
       
        bufferbuilder.pos(0.25, -0.1, 0.25).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.25, -0.1, 0.25).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 0.6, 0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, 0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(0.25, -0.1, -0.25).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.25, -0.1, 0.25).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, 0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, -0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.6, 0.6, -0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, -0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, -0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, -0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.6, 0.6, 0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, 0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, 0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, 0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.6, 0.6, -0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 0.6, 0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, 0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, -0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(0.6, 0.6, -0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 0.6, 0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, 0.6).tex(1, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, -0.6).tex(1, 1).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.6, 1.5, -0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, -0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, -0.35).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.35, 1.9, -0.35).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(0.6, 1.5, 0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.6, 1.5, -0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, -0.35).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, 0.35).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(0.6, 1.5, 0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, 0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.35, 1.9, 0.35).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, 0.35).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.6, 1.5, -0.6).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.6, 1.5, 0.6).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.35, 1.9, 0.35).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.35, 1.9, -0.35).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.35, 1.9, -0.35).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.35, 1.9, 0.35).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, 0.35).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.35, 1.9, -0.35).tex(1, 0).color(255, 255, 255, 255).endVertex();
        
        tessellator.draw();
        
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static void renderPlayerSoulContractChains(Vec3d pos)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(pos.x, pos.y, pos.z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        Minecraft.getMinecraft().renderEngine.bindTexture(CHAINS);
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        bufferbuilder.pos(-0.61, 1.2, -0.61).tex(1, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.61, 1.2, 0.61).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.61, 1.4, 0.61).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.61, 1.4, -0.61).tex(1, 1).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.61, 1.2, -0.61).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.2, -0.61).tex(1, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.4, -0.61).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.61, 1.4, -0.61).tex(0, 1).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(-0.61, 1.2, 0.61).tex(1, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.2, 0.61).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.4, 0.61).tex(0, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(-0.61, 1.4, 0.61).tex(1, 1).color(255, 255, 255, 255).endVertex();
        
        bufferbuilder.pos(0.61, 1.2, -0.61).tex(0, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.2, 0.61).tex(1, 0).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.4, 0.61).tex(1, 1).color(255, 255, 255, 255).endVertex();
        bufferbuilder.pos(0.61, 1.4, -0.61).tex(0, 1).color(255, 255, 255, 255).endVertex();
        
        tessellator.draw();
        
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderLittleBlackSphere(double x, double y, double z, double rot, double size)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().renderEngine.bindTexture(BLACK_SPHERE);
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        double faces = 6;
        
        for(int i = 0; i < faces; i++)
        {
        	for(int j = 0; j < faces; ++j)
        	{
        		double v = 3.1516;
        		double x1 = size * Math.cos(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double x2 = size * Math.cos(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double x3 = size * Math.cos(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		double x4 = size * Math.cos(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		
        		double y1 = size * Math.cos(2.0 * ((double) j) / faces * v);
        		double y2 = size * Math.cos(2.0 * ((double) j + 1) / faces * v);
        		
        		double z1 = size * Math.sin(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double z2 = size * Math.sin(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double z3 = size * Math.sin(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		double z4 = size * Math.sin(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        	
        		float colorMult = ((i + j) + (i + j) * (i + j)) % 6 == 0 ? 0.0F : 1.0F;
        		//System.out.print(x1 + "/" + x2 + "/" + x3 + "/" + x4 + "\n");
        		
        		bufferbuilder.pos(x1, y1, z1).tex((double)( i % 16) / 4.0, (double)( j % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
                bufferbuilder.pos(x2, y1, z2).tex((double)( (i + 1) % 16) / 4.0, (double)( j % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
                bufferbuilder.pos(x4, y2, z4).tex((double)( (i+1) % 16) / 4.0, (double)( (j+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
                bufferbuilder.pos(x3, y2, z3).tex((double)( i % 16) / 4.0, (double)( (j+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
        	}
        }
        
        tessellator.draw();
        
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void renderBlackSphere(double x, double y, double z, double rot, double size, float alpha)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().renderEngine.bindTexture(BLACK_SPHERE);
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        double faces = 32;
        
        for(int i = 0; i < faces; i++)
        {
        	for(int j = 0; j < faces; ++j)
        	{
        		double v = 3.1516;
        		double x1 = size * Math.cos(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double x2 = size * Math.cos(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double x3 = size * Math.cos(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		double x4 = size * Math.cos(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		
        		double y1 = size * Math.cos(2.0 * ((double) j) / faces * v);
        		double y2 = size * Math.cos(2.0 * ((double) j + 1) / faces * v);
        		
        		double z1 = size * Math.sin(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double z2 = size * Math.sin(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j) / faces * v);
        		double z3 = size * Math.sin(2.0 * ((double) i + rot) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        		double z4 = size * Math.sin(2.0 * ((double) i + rot + 1) / faces * v) * Math.sin(2.0 * ((double) j + 1) / faces * v);
        	
        		float colorMult = ((i + j) + (i + j) * (i + j)) % 6 == 0 ? 0.0F : 1.0F;
        		//System.out.print(x1 + "/" + x2 + "/" + x3 + "/" + x4 + "\n");
        		
        		bufferbuilder.pos(x1, y1, z1).tex((double)( i % 16) / 4.0, (double)( j % 16) / 4.0).color(1.0F, colorMult, colorMult, alpha).endVertex();
                bufferbuilder.pos(x2, y1, z2).tex((double)( (i + 1) % 16) / 4.0, (double)( j % 16) / 4.0).color(1.0F, colorMult, colorMult, alpha).endVertex();
                bufferbuilder.pos(x4, y2, z4).tex((double)( (i+1) % 16) / 4.0, (double)( (j+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, alpha).endVertex();
                bufferbuilder.pos(x3, y2, z3).tex((double)( i % 16) / 4.0, (double)( (j+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, alpha).endVertex();
        	}
        }
        
        tessellator.draw();
        
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static void renderBlackCircle(double x, double y, double z, double rot, double size)
    {
    	GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().renderEngine.bindTexture(BLACK_SPHERE);
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.disableLighting();
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        double faces = 32;
        
        for(int i = 0; i < faces; i++)
        {
        	double v = 3.1516;
        	double x1 = size * Math.cos(2.0 * ((double) i + rot) / faces * v);
        	double x2 = size * Math.cos(2.0 * ((double) i + rot + 1) / faces * v);
        		
        	double y1 = 0;
        	double y2 = 0.01;
        		
        	double z1 = size * Math.sin(2.0 * ((double) i + rot) / faces * v);
        	double z2 = size * Math.sin(2.0 * ((double) i + rot + 1) / faces * v);
        	
        	float colorMult = 1.0F;//((i + j) + (i + j) * (i + j)) % 6 == 0 ? 0.0F : 1.0F;
        		//System.out.print(x1 + "/" + x2 + "/" + x3 + "/" + x4 + "\n");
        		
        	bufferbuilder.pos(x1, y2, z1).tex((double)( i % 16) / 4.0, (double)( i % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
            bufferbuilder.pos(x2, y2, z2).tex((double)( (i + 1) % 16) / 4.0, (double)( i % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
            bufferbuilder.pos(0, y2, 0).tex((double)( (i+1) % 16) / 4.0, (double)( (i+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
            bufferbuilder.pos(0, y2, 0).tex((double)( i % 16) / 4.0, (double)( (i+1) % 16) / 4.0).color(1.0F, colorMult, colorMult, 1F).endVertex();
        }
        
        tessellator.draw();
        
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static void renderIcyJavelin(Vec3d pos, float pitch, float yaw, float scale)
    {
    	GlStateManager.pushMatrix();
        GlStateManager.translate(pos.x, pos.y, pos.z);
        GlStateManager.rotate(-yaw, 0, 1, 0);
        GlStateManager.rotate(-pitch, 0, 0, 1);
        
    	Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        Minecraft.getMinecraft().renderEngine.bindTexture(ICE_TEXTURE);
        float f1 = 240.0F;
        //GlStateManager.disableBlend();
        //permet le rendu de toutes les faces dans les deux sens
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask(true);
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        bufferbuilder.pos(-0.125 * scale, -0.125 * scale, -0.5 * scale).tex(0, 0).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, -0.125 * scale, -0.5 * scale).tex(0, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, 0.125 * scale, -0.5 * scale).tex(1, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(-0.125 * scale, 0.125 * scale, -0.5 * scale).tex(1, 0).color(255, 255, 255, 225).endVertex();
        
        bufferbuilder.pos(-0.125 * scale, -0.125 * scale, -0.5 * scale).tex(0, 0).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(0, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(1, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, -0.125 * scale, -0.5 * scale).tex(1, 0).color(255, 255, 255, 225).endVertex();
        
        bufferbuilder.pos(0.125 * scale, -0.125 * scale, -0.5 * scale).tex(0, 0).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(0, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(1, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, 0.125 * scale, -0.5 * scale).tex(1, 0).color(255, 255, 255, 225).endVertex();
        
        bufferbuilder.pos(-0.125 * scale, 0.125 * scale, -0.5 * scale).tex(0, 0).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(0, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(1, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, 0.125 * scale, -0.5 * scale).tex(1, 0).color(255, 255, 255, 225).endVertex();
        
        bufferbuilder.pos(0.125 * scale, -0.125 * scale, -0.5 * scale).tex(0, 0).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(0, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0, 0, 1 * scale).tex(1, 1).color(255, 255, 255, 225).endVertex();
        bufferbuilder.pos(0.125 * scale, 0.125 * scale, -0.5 * scale).tex(1, 0).color(255, 255, 255, 225).endVertex();
        
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
