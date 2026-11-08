package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBlackSphere;
import com.mod.mathsmod.utils.RenderEffects;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderBlackSphere extends Render<EntityBlackSphere>
{
    private static final ResourceLocation SHULKER_SPARK_TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/entity/pub/proj.png");

    public RenderBlackSphere(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBlackSphere entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        RenderEffects.renderBlackSphere(x, y + 1, z, entity.ticksExisted + partialTicks, 1.0F, 1.0F);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBlackSphere entity)
    {
        return SHULKER_SPARK_TEXTURE;
    }
}