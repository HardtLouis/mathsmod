package com.mod.mathsmod.utils;

import java.awt.Color;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import org.apache.commons.compress.utils.IOUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPoulopop;
import com.mod.mathsmod.add.items.ICustomColor;
import com.mod.mathsmod.add.items.ItemPancarte;
import com.mod.mathsmod.add.items.ItemArmor.ItemUberArmor;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.obj.OBJModel.OBJBakedModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLaser 
{
	
	private static TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
	private static RenderItem render = Minecraft.getMinecraft().getRenderItem();
	private static TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
	private static final ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static HashMap<String, Tuple<Integer, Integer>> optiMap = Maps.newHashMap();
	
	private static final HashMap<String, Vec3d> RegistryDeltas = Maps.newHashMap();
	
	public static void renderLaser(Vec3d base, World world, float yOffset, Vec3d vec, int duree, int MaxDuree, ResourceLocation loc)
	{

		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		float f = (((float) duree) + partialTicks) / ((float) MaxDuree);

        float f7 = f * f;
        int j = 64 + (int)(f7 * 191.0F);
        int k = 32 + (int)(f7 * 191.0F);
        int l = 128 - (int)(f7 * 64.0F);
        
		renderLaser(base, world, yOffset, vec, duree, MaxDuree, loc, 255, j, k, l, 0.282D, 0.2D);

	}
	
	public static void renderLaser(Vec3d base, World world, float yOffset, Vec3d vec, int duree, int MaxDuree, ResourceLocation loc, int alpha, int j, int k, int l, double dchep, double dvrmp)
	{
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        float f1 = 240.0F;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        float f2 = (float)world.getTotalWorldTime() + partialTicks;
        float f3 = f2 * 0.5F % 1.0F;
        float f4 = yOffset;
        GlStateManager.pushMatrix();
        
        Vec3d vec3d = vec;
        Vec3d vec3d1 = base;
        Vec3d vec3d2 = vec3d.subtract(vec3d1);
        double d0 = vec3d2.lengthVector() + 1.0D;
        vec3d2 = vec3d2.normalize();
        float f5 = (float)Math.acos(vec3d2.y);
        float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
        Vec3d renvec = new Vec3d(0,0,0);
        if(Minecraft.getMinecraft().getRenderViewEntity() != null)
        {
        	renvec = base.subtract(getPosition(Minecraft.getMinecraft().getRenderViewEntity(), partialTicks));
        }
        GlStateManager.translate(renvec.x, renvec.y + yOffset, renvec.z);
        GlStateManager.rotate((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);

        //Vec3d vecD = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(partialTicks);
        
        double d1 = (double)f2 * 0.05D * -1.5D;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        
        double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * dchep;
        double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * dchep;
        double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * dchep;
        double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * dchep;
        double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * dchep;
        double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * dchep;
        double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * dchep;
        double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * dchep;
        double d12 = 0.0D + Math.cos(d1 + Math.PI) * dvrmp;
        double d13 = 0.0D + Math.sin(d1 + Math.PI) * dvrmp;
        double d14 = 0.0D + Math.cos(d1 + 0.0D) * dvrmp;
        double d15 = 0.0D + Math.sin(d1 + 0.0D) * dvrmp;
        double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * dvrmp;
        double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * dvrmp;
        double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d20 = 0.0D;
        double d21 = 0.4999D;
        double d22 = (double)(-1.0F + f3);
        double d23 = d0 * 2.5D + d22;
        bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, alpha).endVertex();
        double d24 = 0.0D;
           // d24 = 0.5D;

        bufferbuilder.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, alpha).endVertex();
        tessellator.draw();

        GlStateManager.translate(-renvec.x, -renvec.y - yOffset, -renvec.z);
        GlStateManager.popMatrix();
	}
	
	public static void renderLaserNA(Vec3d base, World world, float yOffset, Vec3d vec, ResourceLocation loc, int alpha, int j, int k, int l, double dchep, double dvrmp)
	{
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        float f1 = 240.0F;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        float f4 = yOffset;
        GlStateManager.pushMatrix();
        
        Vec3d vec3d = vec;
        Vec3d vec3d1 = base;
        Vec3d vec3d2 = vec3d.subtract(vec3d1);
        double d0 = vec3d2.lengthVector() + 1.0D;
        vec3d2 = vec3d2.normalize();
        float f5 = (float)Math.acos(vec3d2.y);
        float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
        Vec3d renvec = new Vec3d(0,0,0);
        if(Minecraft.getMinecraft().getRenderViewEntity() != null)
        {
        	renvec = base.subtract(getPosition(Minecraft.getMinecraft().getRenderViewEntity(), Minecraft.getMinecraft().getRenderPartialTicks()));
        }
        GlStateManager.translate(renvec.x, renvec.y + yOffset, renvec.z);
        GlStateManager.rotate((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);

        //Vec3d vecD = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(partialTicks);
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        float d1 = 0.0F;
        double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * dchep;
        double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * dchep;
        double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * dchep;
        double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * dchep;
        double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * dchep;
        double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * dchep;
        double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * dchep;
        double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * dchep;
        double d12 = 0.0D + Math.cos(d1 + Math.PI) * dvrmp;
        double d13 = 0.0D + Math.sin(d1 + Math.PI) * dvrmp;
        double d14 = 0.0D + Math.cos(d1 + 0.0D) * dvrmp;
        double d15 = 0.0D + Math.sin(d1 + 0.0D) * dvrmp;
        double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * dvrmp;
        double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * dvrmp;
        double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d20 = 0.0D;
        double d21 = 0.4999D;
        double d22 = (double)(-1.0F + 0);
        double d23 = d0 * 2.5D + d22;
        bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, alpha).endVertex();
        double d24 = 0.0D;
           // d24 = 0.5D;

        bufferbuilder.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, alpha).endVertex();
        tessellator.draw();

        GlStateManager.translate(-renvec.x, -renvec.y - yOffset, -renvec.z);
        GlStateManager.popMatrix();
	}
	
	private static Vec3d getPosition(Entity entityLivingBaseIn, float partialTick)
    {
        double d0 = entityLivingBaseIn.lastTickPosX + (entityLivingBaseIn.posX - entityLivingBaseIn.lastTickPosX) * (double)partialTick;
        double d1 = 0 + entityLivingBaseIn.lastTickPosY + (entityLivingBaseIn.posY - entityLivingBaseIn.lastTickPosY) * (double)partialTick;
        double d2 = entityLivingBaseIn.lastTickPosZ + (entityLivingBaseIn.posZ - entityLivingBaseIn.lastTickPosZ) * (double)partialTick;
        return new Vec3d(d0, d1, d2);
    }
	
	/*public static void renderLaser(Vec3d base, World world, float yOffset, Vec3d vec, int duree, int MaxDuree, ResourceLocation loc)
	{
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		float f = (((float) duree) + partialTicks) / ((float) MaxDuree);
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        float f1 = 240.0F;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        float f2 = (float)world.getTotalWorldTime() + partialTicks;
        float f3 = f2 * 0.5F % 1.0F;
        float f4 = yOffset;
        GlStateManager.pushMatrix();
        EntityPlayer player = Minecraft.getMinecraft().player;
        double x1 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double y1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double z1 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
        
        double x = base.x;
        double y = base.y;
        double z = base.z;
        GlStateManager.translate((float)x - x1, (float)y - y1 + f4, (float)z - z1);
        Vec3d vec3d = vec;
        Vec3d vec3d1 = base;
        Vec3d vec3d2 = vec3d.subtract(vec3d1);
        double d0 = vec3d2.lengthVector() + 1.0D;
        vec3d2 = vec3d2.normalize();
        float f5 = (float)Math.acos(vec3d2.y);
        float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
        GlStateManager.rotate((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        int i = 1;
        double d1 = (double)f2 * 0.05D * -1.5D;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        float f7 = f * f;
        int j = 64 + (int)(f7 * 191.0F);
        int k = 32 + (int)(f7 * 191.0F);
        int l = 128 - (int)(f7 * 64.0F);
        double d2 = 0.2D;
        double d3 = 0.282D;
        double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * 0.282D;
        double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * 0.282D;
        double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * 0.282D;
        double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * 0.282D;
        double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * 0.282D;
        double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * 0.282D;
        double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * 0.282D;
        double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * 0.282D;
        double d12 = 0.0D + Math.cos(d1 + Math.PI) * 0.2D;
        double d13 = 0.0D + Math.sin(d1 + Math.PI) * 0.2D;
        double d14 = 0.0D + Math.cos(d1 + 0.0D) * 0.2D;
        double d15 = 0.0D + Math.sin(d1 + 0.0D) * 0.2D;
        double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * 0.2D;
        double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * 0.2D;
        double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * 0.2D;
        double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * 0.2D;
        double d20 = 0.0D;
        double d21 = 0.4999D;
        double d22 = (double)(-1.0F + f3);
        double d23 = d0 * 2.5D + d22;
        bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, 255).endVertex();
        double d24 = 0.0D;
           // d24 = 0.5D;

        bufferbuilder.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, 255).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
	}*/
	
	public static void renderLaserGun(Vec3d base, World world, float yOffset, Vec3d vec, int duree, int MaxDuree, ResourceLocation loc, int alpha, int j, int k, int l, double dchep, double dvrmp)
	{
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        float f1 = 240.0F;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        float f2 = (float)world.getTotalWorldTime() + partialTicks;
        float f3 = f2 * 0.5F % 1.0F;
        float f4 = yOffset;
        GlStateManager.pushMatrix();
        
        double x = base.x;
        double y = base.y;
        double z = base.z;
        Vec3d vec3d = vec;
        Vec3d vec3d1 = base;
        Vec3d vec3d2 = vec3d.subtract(vec3d1);
        double d0 = vec3d2.lengthVector() + 1.0D;
        vec3d2 = vec3d2.normalize();
        float f5 = (float)Math.acos(vec3d2.y);
        float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
        GlStateManager.rotate((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        double d1 = (double)f2 * 0.05D * -1.5D;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        
        double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * dchep;
        double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * dchep;
        double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * dchep;
        double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * dchep;
        double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * dchep;
        double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * dchep;
        double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * dchep;
        double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * dchep;
        double d12 = 0.0D + Math.cos(d1 + Math.PI) * dvrmp;
        double d13 = 0.0D + Math.sin(d1 + Math.PI) * dvrmp;
        double d14 = 0.0D + Math.cos(d1 + 0.0D) * dvrmp;
        double d15 = 0.0D + Math.sin(d1 + 0.0D) * dvrmp;
        double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * dvrmp;
        double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * dvrmp;
        double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * dvrmp;
        double d20 = 0.0D;
        double d21 = 0.4999D;
        double d22 = (double)(-1.0F + f3);
        double d23 = d0 * 2.5D + d22;
        bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, alpha).endVertex();
        bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, alpha).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
	}
	
	public static void renderDragonDeath(double time, float partialTicks, float scale, int red, int green, int blue, float speed, float brightness)
    {
        if (time > 0)
        {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            RenderHelper.disableStandardItemLighting();
            float f = ((float)time + partialTicks) / 200.0F * speed;
            float f1 = 0.0F;

            if (f > 0.8F)
            {
                f1 = (f - 0.8F) / 0.2F;
            }

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0, 0);

            for (int i = 0; (float)i < (f + f * f) / 2.0F * 60.0F; ++i)
            {
                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
                float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
                float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
                bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
                bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(red, green, blue, (int)(255.0F * (1.0F - f1))).endVertex();
                bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(red * brightness, green * brightness, blue * brightness, 0).endVertex();
                bufferbuilder.pos(0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(red * brightness, green * brightness, blue * brightness, 0).endVertex();
                bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(red * brightness, green * brightness, blue * brightness, 0).endVertex();
                bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(red * brightness, green * brightness, blue * brightness, 0).endVertex();
                tessellator.draw();
            }

            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.shadeModel(7424);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            RenderHelper.enableStandardItemLighting();
        }
    }
	
	public static void renderFlatTextureXCentered(ResourceLocation loc, Vec3d vec, float size)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(loc);
		GlStateManager.enableRescaleNormal();
		
		EntityPlayer entity = Minecraft.getMinecraft().player;
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
		if(mc.getRenderManager() != null)
		{
			GlStateManager.translate(vec.x, vec.y, vec.z);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, -1.0F, 0.0F);
	        GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, -1.0F, 0.0F, 0.0F);
	        GlStateManager.scale(size, size, size);
			
			drawTexturedModalRect(-8, 0, 0, 0, 16, 16);
			
			 GlStateManager.scale(1 / size, 1 / size, 1 / size);
			GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-vec.x, -vec.y, -vec.z);
		}
	}
	
	public static void renderFlatTexture(ResourceLocation loc, Vec3d vec, float size)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(loc);
		GlStateManager.enableRescaleNormal();
		
		EntityPlayer entity = Minecraft.getMinecraft().player;
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
		if(mc.getRenderManager() != null)
		{
			GlStateManager.translate(vec.x, vec.y, vec.z);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, -1.0F, 0.0F);
	        GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, -1.0F, 0.0F, 0.0F);
	        GlStateManager.scale(size, size, size);
			
			drawTexturedModalRect(0, 0, 0, 0, 16, 16);
			
			 GlStateManager.scale(1 / size, 1 / size, 1 / size);
			GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-vec.x, -vec.y, -vec.z);
		}
	}
	
	public static void renderFlatTexture(ResourceLocation loc, Vec3d vec, float size, float rotZ)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(loc);
		GlStateManager.enableRescaleNormal();
		
		if(mc.getRenderManager() != null && mc.getRenderManager().options != null)
		{
			EntityPlayer entity = Minecraft.getMinecraft().player;
			float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
			
			GlStateManager.translate(vec.x, vec.y, vec.z);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, -1.0F, 0.0F);
	        GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, -1.0F, 0.0F, 0.0F);
	        GlStateManager.scale(size, size, size);
			
			drawTexturedModalRect(0, 0, 0, 0, 16, 16);
			
			GlStateManager.rotate(-rotZ, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate((float)(mc.getRenderManager().options.thirdPersonView == 2 ? -1 : 1) * -mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-vec.x, -vec.y, -vec.z);
		}
	}
	
	public static void renderFlatTexture(ResourceLocation loc, Vec3d vec, float size, float rotPitch, float rotYaw)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(loc);
		GlStateManager.enableRescaleNormal();
		
		EntityPlayer entity = Minecraft.getMinecraft().player;
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
		GlStateManager.translate(vec.x, vec.y, vec.z);
		GlStateManager.rotate(rotYaw, 0.0F, -1.0F, 0.0F);
        GlStateManager.rotate(rotPitch, -1.0F, 0.0F, 0.0F);
        GlStateManager.scale(size, size, size);
		
        drawTexturedModalRect(-8, -8, 0, 0, 16, 16);
		
		 GlStateManager.scale(1 / size, 1 / size, 1 / size);
		GlStateManager.rotate(rotPitch, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(rotYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-vec.x, -vec.y, -vec.z);
	}
	
	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), 0).tex((double)((float)(textureX + 0) * 0.0625F), (double)((float)(textureY + height) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0).tex((double)((float)(textureX + width) * 0.0625F), (double)((float)(textureY + height) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), 0).tex((double)((float)(textureX + width) * 0.0625F), (double)((float)(textureY + 0) * 0.0625F)).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), 0).tex((double)((float)(textureX + 0) * 0.0625F), (double)((float)(textureY + 0) * 0.0625F)).endVertex();
        tessellator.draw();
    }
	
	protected ResourceLocation getModelLocation(ResourceLocation location)
    {
        return new ResourceLocation(location.getResourceDomain(), "models/" + location.getResourcePath() + ".json");
    }
	
	public static void zoom(float zoomValue)
    {
    	try
    	{
    		ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, zoomValue, "fovModifierHand", "field_78507_R");
    	} 
    	catch(Exception e) 
    	{
    		System.err.println(e);
    	}
    }
    
    public static OBJBakedModel getBackedModelForRegistration(ResourceLocation loc)
    {
    	try
    	{
			OBJModel model = (OBJModel) OBJLoader.INSTANCE.loadModel(loc);
			return (OBJBakedModel) model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    	return null;
    }
    
    public static IBakedModel getBackedJsonModelForRegistration(ResourceLocation loc)
    {
    	try
    	{
    		ModelBlock modl = loadModel(loc);
			IBakedModel model = bakeNormal(modl, ModelLoader.defaultTextureGetter());
			return model;
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public static void registerModel(IRegistry<ModelResourceLocation, IBakedModel> registry, ResourceLocation loc)
    {
    	registry.putObject(new ModelResourceLocation(loc, "normal"), getBackedModelForRegistration(loc));
    }
    
    public static void registerJsonModel(IRegistry<ModelResourceLocation, IBakedModel> registry, ResourceLocation loc)
    {
    	registry.putObject(new ModelResourceLocation(loc, "normal"), getBackedJsonModelForRegistration(loc));
    }
    
    public static void renderModel(IBakedModel model, float brightness)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(model, brightness, 1, 1, 1);
	}
    
    public static void renderModel(IBakedModel model, float brightness, int color, ItemStack s)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        
        Map<EnumFacing, List<BakedQuad>> Clist = Maps.newHashMap();
		
        
        for (EnumFacing enumfacing : EnumFacing.values())
        {
			List<BakedQuad> newQuads = Lists.newArrayList();
			Clist.put(enumfacing, model.getQuads((IBlockState)null, enumfacing, 0L));
        }
		
        List<BakedQuad> Slist = model.getQuads((IBlockState)null, null, 0L);
		renderModel(Slist, Clist, color, s);
	}
	
	public static void renderModel(IBakedModel model)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(model, 1.0F, 1, 1, 1);
	}
	
	/*public static void renderModelM(ItemStack stack, ItemGun item, IBakedModel model, int index, float partialTick)
	{
		if(item.getLayer(stack)[index] != null && item.getLayer(stack)[index] != "none")
		{
			String[] str = item.getLayer(stack)[index].split(":");
			if(str.length == 2)
			{
				RenderUtils.retextureItem(stack, model, new ResourceLocation(str[0], str[1]), partialTick);
			}
			else
			{
				RenderUtils.renderModel(model);
			}
		}
		else
		{
			RenderUtils.renderModel(model);
		}
	}*/
	
	public static void renderEntity(EntityPlayer entity, double x, double y, double z, float partialTicks, Entity ent)
	{
        GlStateManager.pushMatrix();
        //GlStateManager.rotate(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        ent.rotationPitch = entity.rotationPitch;
        ent.prevRotationPitch = entity.prevRotationPitch;
        ent.rotationYaw = entity.rotationYaw;
        ent.prevRotationYaw = entity.prevRotationYaw;
        ent.setPosition(entity.posX, entity.posY, entity.posZ);
        ent.lastTickPosX = entity.lastTickPosX;
        ent.lastTickPosY = entity.lastTickPosY;
        ent.lastTickPosZ = entity.lastTickPosZ;
        ent.ticksExisted = entity.ticksExisted;
        
        if(ent instanceof EntityLivingBase)
        {
        	((EntityLivingBase) ent).limbSwing = entity.limbSwing;
        	((EntityLivingBase) ent).limbSwingAmount = entity.limbSwingAmount;
        	((EntityLivingBase) ent).prevLimbSwingAmount = entity.prevLimbSwingAmount;
        	((EntityLivingBase) ent).deathTime = entity.deathTime;
        	((EntityLivingBase) ent).hurtTime = entity.hurtTime;
        	((EntityLivingBase) ent).prevRotationYawHead = entity.prevRotationYawHead;
        	((EntityLivingBase) ent).rotationYawHead = entity.rotationYawHead;
        	((EntityLivingBase) ent).renderYawOffset = entity.renderYawOffset;
        	((EntityLivingBase) ent).prevRenderYawOffset = entity.prevRenderYawOffset;
        	((EntityLivingBase) ent).swingProgress = entity.swingProgress;
        	((EntityLivingBase) ent).swingProgressInt = entity.swingProgressInt;
        	((EntityLivingBase) ent).prevSwingProgress = entity.prevSwingProgress;
        	((EntityLivingBase) ent).setHeldItem(EnumHand.MAIN_HAND, entity.getHeldItemMainhand());
        	((EntityLivingBase) ent).setHeldItem(EnumHand.OFF_HAND, entity.getHeldItemOffhand());
        	if(entity.getActiveHand() != null)
           	{
           		((EntityLivingBase) ent).setActiveHand(entity.getActiveHand());
           	}
        	
        	if(ent instanceof EntityPoulopop)
        	{
        		((EntityPoulopop) ent).setSpawnCooldown(0);
        	}
        }
        
        if(ent instanceof EntityLiving)
        {
        	EntityLiving e = (EntityLiving) ent;
        	e.setLeftHanded(entity.getPrimaryHand() == EnumHandSide.LEFT);
        }
       // GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
        RenderHelper.enableStandardItemLighting();
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityStatic(ent, partialTicks, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
	}
	
	
	public static void retextureItem(ItemStack s, IBakedModel model, ResourceLocation loc, float partialTicks)
    {
		
		Map<EnumFacing, List<BakedQuad>> Clist = Maps.newHashMap();
		List<BakedQuad> Slist = Lists.newArrayList();
		
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
		int color = -1;
		if(s.getItem() instanceof ICustomColor)
		{
			color = ((ICustomColor) s.getItem()).getColorMultiplier(s, 0);
		}
		renderModel(Slist, Clist, color, s);
		
		String textLoc = loc.getResourcePath();;
		if(textLoc.endsWith("_anim"))
		{
			renderLayer(Slist, Clist, loc, s);
			mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		}
		else if(textLoc.endsWith("_move"))
		{
			renderLayerMove(Slist, Clist, loc, s, partialTicks);
			mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		}
    }
	
	
	private static void renderLayerPancarte(ResourceLocation loc, ItemStack stack, int i, int j)
    {
		GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        
        GlStateManager.translate(0.8F, 1.81, 0.452F);
        GlStateManager.scale(-0.6 / i, -0.5 / j, 0.6);
        
        textureManager.bindTexture(loc);
        
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
    	
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, i, j, i, j);
        
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
	private static void renderModel(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads, int color, ItemStack stack)
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
	
	public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + (float)height) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + (float)width) * f), (double)((v + (float)height) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)y, 0.0D).tex((double)((u + (float)width) * f), (double)(v * f1)).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
        tessellator.draw();
    }
	
	private static void renderLayer(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads, ResourceLocation loc, ItemStack stack)
    {
		GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        ResourceLocation loc2 = new ResourceLocation(loc.getResourceDomain(), "textures/" + loc.getResourcePath() + ".png");
        textureManager.bindTexture(loc2);
        
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
        
        GlStateManager.scale(64.0F * i, 64.0F * i, 0);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 64.0F / i;
        
        GlStateManager.translate(f, 0.0F, 0.0F);
        
        renderModel(quads, cQuads, -1, ItemStack.EMPTY);
        
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
	private static void renderLayerMove(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads, ResourceLocation loc, ItemStack stack, float partialTicks)
    {
		GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        ResourceLocation loc2 = new ResourceLocation(loc.getResourceDomain(), "textures/" + loc.getResourcePath() + ".png");
        textureManager.bindTexture(loc2);
        
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
        
        GlStateManager.scale(64.0F * i, 64.0F * i, 0);
        Entity entityIn = Minecraft.getMinecraft().player;

        double d1 = 0;
        if(entityIn != null)
        { 
        	d1 = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * (double)partialTicks;
        }
        
        double d0 = 0;
        if(entityIn != null)
        { 
        	d0 = entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * (double)partialTicks;
        }
        
        float f = (float)(d0 % 360) / 360.0F / 16.0F / i;
        float f1 = (float)(Minecraft.getSystemTime() % 20000L) / 20000.0F / 16.0F / i;
        float f2 = (float)(d1 % 360) / 360.0F / 16.0F / i;
        
        GlStateManager.translate(f + f1, f2, 0.0F);
        
        renderModel(quads, cQuads, -1, ItemStack.EMPTY);
        
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
	private static void renderEffect(List<BakedQuad> quads, Map<EnumFacing, List<BakedQuad>> cQuads)
    {
        GlStateManager.depthMask(false);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
        textureManager.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        renderModel(quads, cQuads, -8519680, ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        renderModel(quads, cQuads, -8519680, ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
    }
	
	/*____________________________________________________________________________________*/
	
	@SideOnly(Side.CLIENT)
	private static FaceBakery fb = new FaceBakery();
	
	@SideOnly(Side.CLIENT)
	@Nullable
	public static ModelBlock loadModel(ResourceLocation location) throws IOException
    {
        Reader reader = null;
        IResource iresource = null;
        ModelBlock lvt_5_2_ = null;
        IResourceManager man = Minecraft.getMinecraft().getResourceManager();

        try
        {
            String s = location.getResourcePath();

            if (!"builtin/generated".equals(s))
            {
            	iresource = man.getResource(location);
                reader = new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8);

                lvt_5_2_ = ModelBlock.deserialize(reader);
                lvt_5_2_.name = location.toString();
                ModelBlock modelblock1 = lvt_5_2_;
                return modelblock1;
            }
        }
        finally
        {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly((Closeable)iresource);
        }

        return lvt_5_2_;
    }
	
	@SideOnly(Side.CLIENT)
	public static IBakedModel bakeNormal(ModelBlock model, final Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
    {
		TRSRTransformation baseState = TRSRTransformation.identity();
        TextureAtlasSprite particle = bakedTextureGetter.apply(new ResourceLocation(model.resolveTextureName("particle")));
        SimpleBakedModel.Builder builder = (new SimpleBakedModel.Builder(model, model.createOverrides())).setTexture(particle);
        for(int i = 0; i < model.getElements().size(); i++)
        {
            BlockPart part = model.getElements().get(i);
            
			for(Map.Entry<EnumFacing, BlockPartFace> e : part.mapFaces.entrySet())
            {
                TextureAtlasSprite textureatlassprite1 = bakedTextureGetter.apply(new ResourceLocation(model.resolveTextureName(e.getValue().texture)));

                if (e.getValue().cullFace == null || !TRSRTransformation.isInteger(baseState.getMatrix()))
                {
                    builder.addGeneralQuad(makeBakedQuad(part, e.getValue(), textureatlassprite1, e.getKey(), baseState, false));
                }
                else
                {
                    
					builder.addFaceQuad(baseState .rotate(e.getValue().cullFace), makeBakedQuad(part, e.getValue(), textureatlassprite1, e.getKey(), baseState, false));
                }
            }
        }

        return new PerspectiveMapWrapper(builder.makeBakedModel(), baseState);
    }
	
	@SideOnly(Side.CLIENT)
	protected static BakedQuad makeBakedQuad(BlockPart p_177589_1_, BlockPartFace p_177589_2_, TextureAtlasSprite p_177589_3_, EnumFacing p_177589_4_, net.minecraftforge.common.model.ITransformation p_177589_5_, boolean p_177589_6_)
    {
        return fb.makeBakedQuad(p_177589_1_.positionFrom, p_177589_1_.positionTo, p_177589_2_, p_177589_3_, p_177589_4_, p_177589_5_, p_177589_1_.partRotation, p_177589_6_, p_177589_1_.shade);
    }
	
	@SideOnly(Side.CLIENT)
	public static void registerJoint(String id, ModelRenderer base, ModelRenderer attached)
	{
		Vec3d delta = new Vec3d(attached.rotationPointX - base.rotationPointX, attached.rotationPointY - base.rotationPointY, attached.rotationPointZ - base.rotationPointZ);
		RegistryDeltas.put(id, delta);
	}
	
	@SideOnly(Side.CLIENT)
	public static void attachPartTo(String id, ModelRenderer base, ModelRenderer attached)
	{
		Vec3d delta = RegistryDeltas.get(id);
		Vec3d baseCoo = new Vec3d(base.rotationPointX, base.rotationPointY, base.rotationPointZ);
		
		Vec3d newPos = baseCoo.add(rotateVec(delta, base.rotateAngleX, base.rotateAngleY, base.rotateAngleZ));
		attached.rotationPointX = (float) newPos.x;
		attached.rotationPointY = (float) newPos.y;
		attached.rotationPointZ = (float) newPos.z;
	}
	
	public static Vec3d rotateVec(Vec3d vec, double rx, double ry, double rz)
	{
		vec = vec.rotatePitch(-(float) rx);
		vec = vec.rotateYaw((float) ry);
		vec = rotateZ(vec, (float) rz);
		return vec;
	}
	
	private static Vec3d rotateZ(Vec3d vec, float z)
    {
        float f = MathHelper.cos(z);
        float f1 = MathHelper.sin(z);
        double d0 = vec.x * (double)f - vec.y * (double)f1;
        double d1 = vec.y * (double)f + vec.x * (double)f1;
        double d2 = vec.z;
        return new Vec3d(d0, d1, d2);
    }
	
	public static void renderModelM(ItemStack stack, ItemGun item, ResourceLocation layer, IBakedModel model, int index, float partialTick)
	{
		if(layer != null)
		{
			RenderLaser.retextureItem(stack, model, layer, partialTick);
		}
		else
		{
			int color = -1;
			if(item instanceof ICustomColor)
			{
				color = item.getColorMultiplier(stack, 0);
			}
			
			RenderLaser.renderModel(model, 0.8F, color, stack);
		}
	}
	
	public static void renderModelM(ItemStack stack, IBakedModel model)
	{
		RenderLaser.renderModel(model, 0.8F);
	}
	
	public static int getColorFromAngle(float angle, int Redoffset, int Greenoffset, int Blueoffset)
	{
		angle %= 360;

		int red = 255;
		int green = Greenoffset;
		int blue = Blueoffset;
		
		if(angle > 60 && angle < 300)
		{
			if(angle < 120)
			{
				red = 255 - (int) ((angle - 60) / 60 * (255 - Redoffset));
			}
			else if(angle > 240)
			{
				red = (int) ((angle - 240) / 60 * (255 - Redoffset)) + Redoffset;
			}
			else
			{
				red = Redoffset;
			}
		}

		if(angle > 0 && angle < 240)
		{
			if(angle < 60)
			{
				green = (int) ((angle) / 60 * (255 - Greenoffset)) + Greenoffset;
			}
			else if(angle > 180)
			{
				green = 255 - (int) ((angle - 180) / 60 * (255 - Greenoffset));
			}
			else
			{
				green = 255;
			}
		}
		
		if(angle > 120 && angle < 360)
		{
			if(angle < 180)
			{
				blue = (int) ((angle - 120) / 60 * (255 - Blueoffset)) + Blueoffset;
			}
			else if(angle > 300)
			{
				blue = 255 - (int) ((angle - 300) / 60 * (255 - Blueoffset));
			}
			else
			{
				blue = 255;
			}
		}
		return new Color(red, green, blue).getRGB();
	}
	
	public static int getColorFromAngle(float angle)
	{
		angle %= 360;

		int red = 255;
		int green = 0;
		int blue = 0;
		
		if(angle > 60 && angle < 300)
		{
			if(angle < 120)
			{
				red = 255 - (int) ((angle - 60) / 60 * 255);
			}
			else if(angle > 240)
			{
				red = (int) ((angle - 240) / 60 * 255);
			}
			else
			{
				red = 0;
			}
		}

		if(angle > 0 && angle < 240)
		{
			if(angle < 60)
			{
				green = (int) ((angle) / 60 * 255);
			}
			else if(angle > 180)
			{
				green = 255 - (int) ((angle - 180) / 60 * 255);
			}
			else
			{
				green = 255;
			}
		}
		
		if(angle > 120 && angle < 360)
		{
			if(angle < 180)
			{
				blue = (int) ((angle - 120) / 60 * 255);
			}
			else if(angle > 300)
			{
				blue = 255 - (int) ((angle - 300) / 60 * 255);
			}
			else
			{
				blue = 255;
			}
		}
		return new Color(red, green, blue).getRGB();
	}
	
	public static int getColorFromAngleQuarter(float angle)
	{
		angle = (angle % 90) * 4;

		int red = 255;
		int green = 0;
		int blue = 0;
		
		if(angle > 60 && angle < 300)
		{
			if(angle < 120)
			{
				red = 255 - (int) ((angle - 60) / 60 * 255);
			}
			else if(angle > 240)
			{
				red = (int) ((angle - 240) / 60 * 255);
			}
			else
			{
				red = 0;
			}
		}

		if(angle > 0 && angle < 240)
		{
			if(angle < 60)
			{
				green = (int) ((angle) / 60 * 255);
			}
			else if(angle > 180)
			{
				green = 255 - (int) ((angle - 180) / 60 * 255);
			}
			else
			{
				green = 255;
			}
		}
		
		if(angle > 120 && angle < 360)
		{
			if(angle < 180)
			{
				blue = (int) ((angle - 120) / 60 * 255);
			}
			else if(angle > 300)
			{
				blue = 255 - (int) ((angle - 300) / 60 * 255);
			}
			else
			{
				blue = 255;
			}
		}
		return new Color(red, green, blue).getRGB();
	}
	
	public static void retexturePancarte(ItemStack s, IBakedModel model, ResourceLocation loc, float partialTicks)
    {
		String name = loc.getResourcePath().replaceAll("/", "_").replaceAll(":", "_");
    	File file = new File(client_Proxy.PANCARTE_FOLDER, "loaded_" + name);
        
        int i = 1;
        int j = 1;
        
        if(!optiMap.containsKey(file.getAbsolutePath()) && s.getItem() instanceof ItemPancarte)
        {
        	ItemPancarte item = (ItemPancarte) s.getItem();
        	item.getDownloadImageSkin(name);
        	
        	try 
        	{
        		if(ImageIO.read(file) != null)
        		{
        			i = ImageIO.read(file).getWidth();
        	    	j = ImageIO.read(file).getHeight();
        		}
    		} 
        	catch (IOException e) 
        	{
    		}
        	
        	System.out.print("jsais lire fdp" + System.lineSeparator());
        	optiMap.put(file.getAbsolutePath(), new Tuple<Integer, Integer>(i, j));
        }
    	
    	if(optiMap.containsKey(file.getAbsolutePath()))
    	{
    		i = optiMap.get(file.getAbsolutePath()).getFirst();
    		j = optiMap.get(file.getAbsolutePath()).getSecond();
    	}
		
		renderLayerPancarte(loc, s, i, j);
		mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }
}
