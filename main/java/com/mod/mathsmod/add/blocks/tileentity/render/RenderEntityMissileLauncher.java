package com.mod.mathsmod.add.blocks.tileentity.render;

import com.mod.mathsmod.add.blocks.tileentity.TileEntityMissileLauncher;
import com.mod.mathsmod.add.items.ItemLaunchedMissile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderEntityMissileLauncher extends TileEntitySpecialRenderer<TileEntityMissileLauncher>
{

	@Override
	public void render(TileEntityMissileLauncher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
		
		if(!te.isEmpty())
		{
			RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			Entity entry = null;
			
			if(te.getItems().get(0).getItem() instanceof ItemLaunchedMissile)
			{
				ItemLaunchedMissile item = (ItemLaunchedMissile) te.getItems().get(0).getItem();
				entry = item.getMissile(getWorld(), new BlockPos(x, y, z), new BlockPos(0, 0, 0));
				entry.rotationPitch = -90;
			}
			if(entry != null)
			{
				this.renderEntity(te, x, y, z, entry);
			}
		}
    }
	
	protected void renderEntity(TileEntityMissileLauncher te, double x, double y, double z, Entity ent)
	{
		GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.5D, (float)y + 0.2D, z + 0.5D);
        GlStateManager.rotate(0, 0.0F, 1.0F, 0.0F);
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