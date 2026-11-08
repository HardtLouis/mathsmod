package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBlob;
import com.mod.mathsmod.add.Entity.Models.ModelBlob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderBlob extends RenderLiving<EntityLiving>
{

    public RenderBlob(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBlob(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityLiving entity, float partialTickTime)
    {
    	float t = entity.ticksExisted + partialTickTime;
        GlStateManager.scale(1.5 - 0.5 * Math.abs(MathHelper.cos(t * 0.1F)), 0.5 + 0.5 * Math.abs(MathHelper.cos(t * 0.1F)), 1.5 - 0.5 * Math.abs(MathHelper.cos(t * 0.1F)));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/blob.png");
    }
}