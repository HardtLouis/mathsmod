package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMininana;
import com.mod.mathsmod.add.Entity.Models.ModelBananman;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMininana extends RenderLiving<EntityMininana>
{

    public RenderMininana(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBananman(), 0.7F);
    }
    
    @Override
	protected void preRenderCallback(EntityMininana entity, float partialTickTime)
    {
    	GlStateManager.scale(0.5, 0.5, 0.5);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMininana entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/mininana.png");
    }
}