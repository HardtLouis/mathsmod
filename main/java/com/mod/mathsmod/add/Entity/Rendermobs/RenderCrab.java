package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityCrab;
import com.mod.mathsmod.add.Entity.Entity.EntityDeadHunter;
import com.mod.mathsmod.add.Entity.Models.ModelCrab;
import com.mod.mathsmod.add.Entity.Models.ModelHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDeadCommunist;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCrab extends RenderLiving<EntityLiving>
{

    public RenderCrab(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCrab(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityLiving entity, float partialTickTime)
    {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        if(entity.isChild())
        {
        	GlStateManager.scale(0.5F, 0.5F, 0.5F);
        }
        if(entity instanceof EntityCrab && ((EntityCrab) entity).isGiant() && !entity.isChild())
        {
        	GlStateManager.scale(4F, 4F, 4F);
        }
        GlStateManager.rotate(entity.isLeftHanded() ? -90 : 90, 0, 1, 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityLiving entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/crab.png");
    }
}