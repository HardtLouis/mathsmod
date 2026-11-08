package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDeadHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Models.ModelHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerCommunist;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDeadCommunist;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderDeadHunter extends RenderBiped<EntityDeadHunter>
{

    public RenderDeadHunter(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHunter(0), 0.5F);
        this.addLayer(new LayerDeadCommunist(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelHunter(0.5F);
                this.modelArmor = new ModelHunter(1.0F);
            }
        };
        this.addLayer(layerbipedarmor);
    }
    
    @Override
	protected void preRenderCallback(EntityDeadHunter entity, float partialTickTime)
    {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDeadHunter entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/dead_hunter/dead_hunter_" + entity.getSkin() + ".png");
    }
}