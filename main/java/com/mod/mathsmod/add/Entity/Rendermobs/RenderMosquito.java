package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityMosquito;
import com.mod.mathsmod.add.Entity.Models.ModelMosquito;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderMosquito extends RenderLiving<EntityMosquito>
{

    public RenderMosquito(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelMosquito(), 0F);
    }
    
    @Override
	protected void preRenderCallback(EntityMosquito entity, float partialTickTime)
    {
        GlStateManager.scale(0.15, 0.15, 0.15);
        if(entity.isHanging())
        {
            GlStateManager.scale(1, -1, 1);
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMosquito entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/mosquito.png");
    }
}