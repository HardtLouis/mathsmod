package com.mod.mathsmod.add.blocks.tileentity.render;

import java.io.IOException;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityEProjector;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RenderEntityProjector extends TileEntitySpecialRenderer<TileEntityEProjector>
{

	@Override
	public void render(TileEntityEProjector te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
		
		if(te.entityName != null)
		{
			RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			net.minecraftforge.fml.common.registry.EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(new ResourceLocation(te.entityName));
			
			if(te.holoRender)
			{
	            GlStateManager.matrixMode(5890);
	            GlStateManager.loadIdentity();
	            GlStateManager.matrixMode(5888);
	            GlStateManager.enableBlend();
	            float f3 = 0.5F;
	            GlStateManager.color(1F, 1F, 1F, 1.0F);
	            GlStateManager.disableLighting();
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			}
			if(entry != null)
			{
				this.renderEntity(te, x, y, z, entry.newInstance(getWorld()));
			}
			else
			{
				if(te.getWorld().getPlayerEntityByName(te.entityName) != null)
				{
					this.renderEntity(te, x, y, z, te.getWorld().getPlayerEntityByName(te.entityName));
				}
				else
				{
					this.renderEntity(te, x, y, z, Minecraft.getMinecraft().player);
				}
				
			}
			
			if(te.holoRender)
			{
				GlStateManager.matrixMode(5890);
	            GlStateManager.loadIdentity();
	            GlStateManager.matrixMode(5888);
	            GlStateManager.enableLighting();
	            GlStateManager.disableBlend();
			}
		}
		
    }
	
	protected void renderEntity(TileEntityEProjector te, double x, double y, double z, Entity ent)
	{
		GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5D, (float)y + 0.2D, z + 0.5D);
        GlStateManager.rotate(te.rotation, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
}
