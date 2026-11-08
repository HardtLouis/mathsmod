package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBlal;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroideBlal;
import com.mod.mathsmod.add.Entity.Models.ModelBlal;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSteroideBlal extends RenderLiving<EntitySteroideBlal>
{

    public RenderSteroideBlal(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBlal(), 0.75F);
    }
    
    @Override
	protected void preRenderCallback(EntitySteroideBlal entity, float partialTickTime)
    {
        GlStateManager.scale(2.2F, 2.2F, 2.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySteroideBlal entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/steroide_blal.png");
    }
}