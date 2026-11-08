package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBananaStrongman;
import com.mod.mathsmod.add.Entity.Models.ModelBananaStrongman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBananaStrongman extends RenderLiving<EntityBananaStrongman>
{

    public RenderBananaStrongman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBananaStrongman(), 0.7F);
    }
    
    @Override
	protected void preRenderCallback(EntityBananaStrongman entity, float partialTickTime)
    {
    	GlStateManager.scale(2, 2, 2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBananaStrongman entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/banana_strongman.png");
    }
}