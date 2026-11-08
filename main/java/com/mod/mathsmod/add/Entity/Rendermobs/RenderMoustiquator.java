package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMosquito;
import com.mod.mathsmod.add.Entity.Entity.EntityMoustiquator;
import com.mod.mathsmod.add.Entity.Models.ModelMosquito;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMoustiquator extends RenderLiving<EntityMoustiquator>
{

    public RenderMoustiquator(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelMosquito(), 0F);
    }
    
    @Override
	protected void preRenderCallback(EntityMoustiquator entity, float partialTickTime)
    {
        GlStateManager.scale(1.5, 1.5, 1.5);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMoustiquator entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/mosquito.png");
    }
}