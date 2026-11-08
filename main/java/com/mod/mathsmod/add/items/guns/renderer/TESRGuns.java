package com.mod.mathsmod.add.items.guns.renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.Mathsmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TESRGuns extends TileEntitySpecialRenderer<TileEntity>
{
	
	public static TESRGuns instance;
	private TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
	private RenderItem render = Minecraft.getMinecraft().getRenderItem();
	private TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
	private final ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
	
	public void renderSpecial(ItemStack s, IGoldenWeapon gold, double x, double y, double z, float partialTicks)
    {
		IBakedModel model = render.getItemModelMesher().getItemModel(new ItemStack(gold.model()));
		
		Map<EnumFacing, List<BakedQuad>> Clist = Maps.newHashMap();
		List<BakedQuad> Slist = Lists.newArrayList();
		
		ResourceLocation loc = gold.getBaseTexture();
		
        if(gold.getLayer(s) != null)
        {
        	loc =  gold.getLayer(s);
        }
		
		TextureAtlasSprite text = map.getAtlasSprite(loc.toString());
		
		if(text == null)
		{
			text = map.getMissingSprite();
		}
		
		for (EnumFacing enumfacing : EnumFacing.values())
        {
			List<BakedQuad> newQuads = Lists.newArrayList();
			
            for(BakedQuad quad : model.getQuads((IBlockState)null, enumfacing, 0L))
            {
            	BakedQuad nq = new BakedQuadRetextured(quad, text);
            	newQuads.add(nq);
            }
            
            Clist.put(enumfacing, newQuads);
        }
		
		for(BakedQuad quad : model.getQuads((IBlockState)null, null, 0L))
        {
        	Slist.add(new BakedQuadRetextured(quad, text));
        }
		this.renderModel(Slist, Clist, -1, s);
		
		if(gold.getLayer(s) != null && (gold.getLayer(s).toString().endsWith("_anim") || gold.getLayer(s).toString().endsWith("_move")))
		{
			this.renderLayer(Slist, Clist, gold, s);
		}
		if(s.hasEffect())
		{
			this.renderEffect(Slist, Clist);
		}
    }
	
	private void renderLayer(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads, IGoldenWeapon gold, ItemStack stack)
    {
		GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        

		ResourceLocation loc = gold.getBaseTexture();
        if(gold.getLayer(stack) != null)
        {
        	loc = new ResourceLocation(gold.getLayer(stack).getResourceDomain(), "textures/" + gold.getLayer(stack).getResourcePath() + ".png");
        }
        this.textureManager.bindTexture(loc);
        
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        
        float i = 1;
        
        try
        {
			i = TextureUtil.readBufferedImage(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream()).getHeight() / 16;
		}
        catch (IOException e)
        {
		}
        
        GlStateManager.scale(32.0F * i, 32.0F * i, 0);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 16.0F / i;
        if(gold.getLayer(stack) != null && (gold.getLayer(stack).toString().endsWith("_anim") || gold.getLayer(stack).toString().endsWith("_move")))
        {
            GlStateManager.translate(f, 0.0F, 0.0F);
        }
        this.renderModel(quads, cQuads, -1, ItemStack.EMPTY);
        
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super.setRendererDispatcher(rendererDispatcherIn);
        instance = this;
    }
	
	private void renderModel(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads, int color, ItemStack stack)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.ITEM);

        for (EnumFacing enumfacing : EnumFacing.values())
        {
        	Minecraft.getMinecraft().getRenderItem().renderQuads(bufferbuilder, cQuads.get(enumfacing), color, stack);
        }

        Minecraft.getMinecraft().getRenderItem().renderQuads(bufferbuilder, quads, color, stack);
        tessellator.draw();
    }
	
	private void renderEffect(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads)
    {
        GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
        this.textureManager.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(quads, cQuads, -8519680, ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(quads, cQuads, -8519680, ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
}
