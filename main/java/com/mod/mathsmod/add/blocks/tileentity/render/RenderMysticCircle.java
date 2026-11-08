package com.mod.mathsmod.add.blocks.tileentity.render;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.blocks.tileentity.TileEntityMysticCircle;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderMysticCircle extends TileEntitySpecialRenderer<TileEntityMysticCircle>
{

	@Override
	public void render(TileEntityMysticCircle te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
		GlStateManager.pushMatrix();
		RenderLaser.renderFlatTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/poulopop/spawn_texture.png"), new Vec3d(x + 0.5F, y + 0.01F, z + 0.5F), 0.35F * 1, -90, 0 * 360);
		GlStateManager.popMatrix();
    }
}