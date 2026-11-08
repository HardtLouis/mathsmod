package com.mod.mathsmod.add.Entity.Rendermobs;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;

public class RenderMythicalItem extends RenderEntityItem
{

	public RenderMythicalItem(RenderManager renderManagerIn, RenderItem p_i46167_2_) 
	{
		super(renderManagerIn, p_i46167_2_);
	}

	public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		
		EntityPlayer player = Minecraft.getMinecraft().player;
		double x1 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double y1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double z1 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
        
        double x2 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double y2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double z2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
		
		
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        RenderHelper.disableStandardItemLighting();
        float f = 0.5F;
        float e = (float)entity.getAge() + partialTicks;
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
        GlStateManager.translate(x2 - x1, y2 - y1 + 0.1, z2 - z1);
        GlStateManager.rotate(e, 0, 1, 0);
        GlStateManager.scale(0.075, 0.075, 0.075);

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
            bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(255, 100, 100, (int)(255.0F * (1.0F - f1))).endVertex();
            
            bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 0, 0).endVertex();
            bufferbuilder.pos(0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 0, 0).endVertex();
            bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(255, 0, 0, 0).endVertex();
            bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 0, 0).endVertex();
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
	}
}
