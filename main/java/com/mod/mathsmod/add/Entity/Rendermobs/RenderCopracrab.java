package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityCrab;
import com.mod.mathsmod.add.Entity.Models.ModelCrab;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCopracrab extends RenderLiving<EntityLiving>
{

    public RenderCopracrab(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCrab(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityLiving entity, float partialTickTime)
    {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.scale(5F, 5F, 5F);
        GlStateManager.rotate(entity.isLeftHanded() ? -90 : 90, 0, 1, 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/copracrab.png");
    }
}