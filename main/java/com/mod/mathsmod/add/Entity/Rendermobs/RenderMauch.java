package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderMauch extends Render<Entity>
{
    public RenderMauch(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	Entity ent = Minecraft.getMinecraft().getRenderViewEntity();
    	if(ent == null)
    	{
    		ent = Minecraft.getMinecraft().player;
    	}
    	double PposX = ent.lastTickPosX + (ent.posX - ent.lastTickPosX) * (double)partialTicks;
        double PposY = ent.lastTickPosY + (ent.posY - ent.lastTickPosY) * (double)partialTicks;
        double PposZ = ent.lastTickPosZ + (ent.posZ - ent.lastTickPosZ) * (double)partialTicks;
    	
    	double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
        double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
        double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
    	
        GlStateManager.translate(posX - PposX, posY - PposY - ent.getEyeHeight() + 6, posZ - PposZ);
    	RenderLaser.renderFlatTextureXCentered(new ResourceLocation(Mathsmod.MODID, "textures/entity/ne_surtout_pas_regarder_images_traumatisantes/non.png"), Vec3d.ZERO, -0.25F);
    	GlStateManager.translate(-posX + PposX, -posY + PposY + ent.getEyeHeight() - 6, -posZ + PposZ);
    	
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}