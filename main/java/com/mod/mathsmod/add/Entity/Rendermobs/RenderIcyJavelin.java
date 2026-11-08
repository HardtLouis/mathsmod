package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityIcyJavelin;
import com.mod.mathsmod.utils.RenderEffects;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderIcyJavelin extends Render<EntityIcyJavelin>
{
    private static final ResourceLocation SHULKER_SPARK_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/entity/pub/proj.png");

    public RenderIcyJavelin(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityIcyJavelin entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        RenderEffects.renderIcyJavelin(new Vec3d(x, y, z), entity.rotationPitch, entityYaw, 1.0F);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityIcyJavelin entity)
    {
        return SHULKER_SPARK_TEXTURE;
    }
}