package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBlal;
import com.mod.mathsmod.add.Entity.Models.ModelBlal;
import com.mod.mathsmod.add.Entity.Models.ModelDevilHand;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDeadCommunist;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderBlal extends RenderLiving<EntityBlal>
{

    public RenderBlal(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBlal(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityBlal entity, float partialTickTime)
    {
        GlStateManager.scale(1.4F, 1.4F, 1.4F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBlal entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/blal.png");
    }
}