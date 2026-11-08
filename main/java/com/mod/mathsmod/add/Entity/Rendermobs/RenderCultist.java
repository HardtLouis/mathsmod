package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityCultist;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderCultist extends RenderLiving<EntityCultist>
{

    public RenderCultist(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityCultist entity, float partialTickTime)
    {
    	//GlStateManager.translate(0, Math.abs(MathHelper.sin(entity.getWalkProgress() * 0.6662F * 3) * 0.2F), 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCultist entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/cultist.png");
    }
}