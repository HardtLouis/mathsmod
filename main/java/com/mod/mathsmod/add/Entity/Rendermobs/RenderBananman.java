package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBananman;
import com.mod.mathsmod.add.Entity.Models.ModelBananman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBananman extends RenderLiving<EntityBananman>
{

    public RenderBananman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBananman(), 0.7F);
    }
    
    @Override
	protected void preRenderCallback(EntityBananman entity, float partialTickTime)
    {
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBananman entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/bananman.png");
    }
}