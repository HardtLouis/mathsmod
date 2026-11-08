package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;
import com.mod.mathsmod.add.Entity.Models.ModelHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerCommunist;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderHunter extends RenderBiped<EntityHunter>
{

    public RenderHunter(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHunter(0), 0.5F);
        this.addLayer(new LayerCommunist(this));
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

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityHunter entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/hunter/hunter_" + entity.getSkin() + ".png");
    }
}