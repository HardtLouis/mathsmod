package com.mod.mathsmod.utils;

import javax.annotation.Nullable;

import org.lwjgl.util.glu.Project;

import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class RenderMHelper {

	public static void renderEntity(double x, double y, double z, float partialTicks, EntityLivingBase ent, @Nullable Entity entity)
	{
        GlStateManager.pushMatrix();
        //GlStateManager.rotate(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        if(entity != null)
        {
        	ent.rotationPitch = entity.rotationPitch;
            ent.prevRotationPitch = entity.prevRotationPitch;
            ent.rotationYaw = entity.rotationYaw;
            ent.prevRotationYaw = entity.prevRotationYaw;
            ((EntityLivingBase) ent).prevRotationYawHead = entity.prevRotationYaw;
        	((EntityLivingBase) ent).rotationYawHead = entity.rotationYaw;
            ent.ticksExisted = entity.ticksExisted;
        }
        

    	((EntityLivingBase) ent).renderYawOffset = 0;
    	((EntityLivingBase) ent).prevRenderYawOffset = 0;
    	((EntityLivingBase) ent).swingProgress = 0;
    	((EntityLivingBase) ent).swingProgressInt = 0;
    	((EntityLivingBase) ent).prevSwingProgress = 0;
        ent.setPosition(x, y, z);
        ent.lastTickPosX = x;
        ent.lastTickPosY = y;
        ent.lastTickPosZ = z;
        
        ((EntityLivingBase) ent).limbSwing = 0;
    	((EntityLivingBase) ent).limbSwingAmount = 0;
    	((EntityLivingBase) ent).prevLimbSwingAmount = 0;
    	((EntityLivingBase) ent).deathTime = 0;
    	((EntityLivingBase) ent).hurtTime = 0;
        
        if(ent instanceof EntityLiving)
        {
        	EntityLiving e = (EntityLiving) ent;
        	e.setLeftHanded(false);
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
	
	public static void renderEntityStatic(Entity entityIn, float partialTicks, boolean p_188388_3_, RenderManager renderManager)
    {
        if (entityIn.ticksExisted == 0)
        {
            entityIn.lastTickPosX = entityIn.posX;
            entityIn.lastTickPosY = entityIn.posY;
            entityIn.lastTickPosZ = entityIn.posZ;
        }

        double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double)partialTicks;
        double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double)partialTicks;
        double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double)partialTicks;
        float f = entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks;
        int i = entityIn.getBrightnessForRender();

        if (entityIn.isBurning())
        {
            i = 15728880;
        }

        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
        renderManager.renderEntity(entityIn, d0, d1, d2, f, partialTicks, p_188388_3_);
    }
	
	public static void renderWorldPass(float partialTicks, Entity entity)
    {
		int pass = 0;
		long finishTimeNano = System.nanoTime();
		
		Minecraft mc = Minecraft.getMinecraft();
        RenderGlobal renderglobal = mc.renderGlobal;
        ParticleManager particlemanager = mc.effectRenderer;
        boolean flag = false;
        GlStateManager.enableCull();
        mc.mcProfiler.endStartSection("clear");
        GlStateManager.viewport(0, 0, mc.displayWidth, mc.displayHeight);
        GlStateManager.clear(16640);
        mc.mcProfiler.endStartSection("camera");
        //mc.entityRenderer.setupCameraTransform(partialTicks, pass);
        ActiveRenderInfo.updateRenderInfo(entity, mc.gameSettings.thirdPersonView == 2); //Forge: MC-46445 Spectator mode particles and sounds computed from where you have been before
        mc.mcProfiler.endStartSection("frustum");
        ClippingHelperImpl.getInstance();
        mc.mcProfiler.endStartSection("culling");
        ICamera icamera = new Frustum();
        double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
        icamera.setPosition(d0, d1, d2);

        if (mc.gameSettings.renderDistanceChunks >= 4)
        {
        	//mc.entityRenderer.setupFog(-1, partialTicks);
            mc.mcProfiler.endStartSection("sky");
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            //Project.gluPerspective(mc.entityRenderer.getFOVModifier(partialTicks, true), (float)mc.displayWidth / (float)mc.displayHeight, 0.05F, mc.entityRenderer.farPlaneDistance * 2.0F);
            GlStateManager.matrixMode(5888);
            renderglobal.renderSky(partialTicks, pass);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            //Project.gluPerspective(this.getFOVModifier(partialTicks, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * MathHelper.SQRT_2);
            GlStateManager.matrixMode(5888);
        }

        //this.setupFog(0, partialTicks);
        GlStateManager.shadeModel(7425);

        /*if (entity.posY + (double)entity.getEyeHeight() < 128.0D)
        {
            this.renderCloudsCheck(renderglobal, partialTicks, pass, d0, d1, d2);
        }*/

        mc.mcProfiler.endStartSection("prepareterrain");
        //this.setupFog(0, partialTicks);
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        RenderHelper.disableStandardItemLighting();
        mc.mcProfiler.endStartSection("terrain_setup");
        renderglobal.setupTerrain(entity, (double)partialTicks, icamera, 1, mc.player.isSpectator());

        if (pass == 0 || pass == 2)
        {
            mc.mcProfiler.endStartSection("updatechunks");
            mc.renderGlobal.updateChunks(finishTimeNano);
        }

        mc.mcProfiler.endStartSection("terrain");
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        renderglobal.renderBlockLayer(BlockRenderLayer.SOLID, (double)partialTicks, pass, entity);
        GlStateManager.enableAlpha();
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, mc.gameSettings.mipmapLevels > 0); // FORGE: fix flickering leaves when mods mess up the blurMipmap settings
        renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT_MIPPED, (double)partialTicks, pass, entity);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        renderglobal.renderBlockLayer(BlockRenderLayer.CUTOUT, (double)partialTicks, pass, entity);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
        GlStateManager.shadeModel(7424);
        GlStateManager.alphaFunc(516, 0.1F);

        if (true)
        {
            GlStateManager.matrixMode(5888);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            RenderHelper.enableStandardItemLighting();
            mc.mcProfiler.endStartSection("entities");
            net.minecraftforge.client.ForgeHooksClient.setRenderPass(0);
            renderglobal.renderEntities(entity, icamera, partialTicks);
            net.minecraftforge.client.ForgeHooksClient.setRenderPass(0);
            RenderHelper.disableStandardItemLighting();
            mc.entityRenderer.disableLightmap();
        }

        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();

        if (flag && mc.objectMouseOver != null && !entity.isInsideOfMaterial(Material.WATER))
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            GlStateManager.disableAlpha();
            mc.mcProfiler.endStartSection("outline");
            if (!net.minecraftforge.client.ForgeHooksClient.onDrawBlockHighlight(renderglobal, entityplayer, mc.objectMouseOver, 0, partialTicks))
            renderglobal.drawSelectionBox(entityplayer, mc.objectMouseOver, 0, partialTicks);
            GlStateManager.enableAlpha();
        }

        if (mc.debugRenderer.shouldRender())
        {
            mc.debugRenderer.renderDebug(partialTicks, finishTimeNano);
        }

        mc.mcProfiler.endStartSection("destroyProgress");
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        renderglobal.drawBlockDamageTexture(Tessellator.getInstance(), Tessellator.getInstance().getBuffer(), entity, partialTicks);
        mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
        GlStateManager.disableBlend();

        if (true)
        {
        	mc.entityRenderer.enableLightmap();
            mc.mcProfiler.endStartSection("litParticles");
            particlemanager.renderLitParticles(entity, partialTicks);
            RenderHelper.disableStandardItemLighting();
            //this.setupFog(0, partialTicks);
            mc.mcProfiler.endStartSection("particles");
            particlemanager.renderParticles(entity, partialTicks);
            mc.entityRenderer.disableLightmap();
        }

        GlStateManager.depthMask(false);
        GlStateManager.enableCull();
        mc.mcProfiler.endStartSection("weather");
        //mc.entityRenderer.renderRainSnow(partialTicks);
        GlStateManager.depthMask(true);
        renderglobal.renderWorldBorder(entity, partialTicks);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(516, 0.1F);
        //this.setupFog(0, partialTicks);
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.shadeModel(7425);
        mc.mcProfiler.endStartSection("translucent");
        renderglobal.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, (double)partialTicks, pass, entity);
        if (true) //Only render if render pass 0 happens as well.
        {
            RenderHelper.enableStandardItemLighting();
            mc.mcProfiler.endStartSection("entities");
            net.minecraftforge.client.ForgeHooksClient.setRenderPass(1);
            renderglobal.renderEntities(entity, icamera, partialTicks);
            // restore blending function changed by RenderGlobal.preRenderDamagedBlocks
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            net.minecraftforge.client.ForgeHooksClient.setRenderPass(-1);
            RenderHelper.disableStandardItemLighting();
        }
        GlStateManager.shadeModel(7424);
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableFog();

        if (entity.posY + (double)entity.getEyeHeight() >= 128.0D)
        {
            mc.mcProfiler.endStartSection("aboveClouds");
            //mc.entityRenderer.renderCloudsCheck(renderglobal, partialTicks, pass, d0, d1, d2);
        }

        mc.mcProfiler.endStartSection("forge_render_last");
        net.minecraftforge.client.ForgeHooksClient.dispatchRenderLast(renderglobal, partialTicks);

        mc.mcProfiler.endStartSection("hand");

        /*if (this.renderHand)
        {
            GlStateManager.clear(256);
            this.renderHand(partialTicks, pass);
        }*/
    }
	
}
