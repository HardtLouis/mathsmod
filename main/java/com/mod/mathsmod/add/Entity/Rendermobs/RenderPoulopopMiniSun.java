package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityPoulopopMiniSun;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.model.ModelShulkerBullet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderPoulopopMiniSun extends Render<EntityPoulopopMiniSun>
{
    private static final ResourceLocation SHULKER_SPARK_TEXTURE = new ResourceLocation("textures/entity/shulker/spark.png");
    private final ModelShulkerBullet model = new ModelShulkerBullet();

    public RenderPoulopopMiniSun(RenderManager manager)
    {
        super(manager);
    }

    private float rotLerp(float p_188347_1_, float p_188347_2_, float p_188347_3_)
    {
        float f;

        for (f = p_188347_2_ - p_188347_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_188347_1_ + p_188347_3_ * f;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityPoulopopMiniSun entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        float f = this.rotLerp(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
        float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        float f2 = (float)entity.ticksExisted + partialTicks;
        
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(f2 * 20, 0, 1, 0);
		RenderLaser.renderDragonDeath(40D, partialTicks, 1, 255, 255, 255, 3F, 0.9F);
		GlStateManager.rotate(-f2 * 20, 0, 1, 0);
		GlStateManager.translate(-x, -y, -z);
		
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(MathHelper.sin(f2 * 0.1F) * 180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(MathHelper.cos(f2 * 0.1F) * 180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(MathHelper.sin(f2 * 0.15F) * 360.0F, 0.0F, 0.0F, 1.0F);
        float f3 = 0.03125F;
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.bindEntityTexture(entity);
        GlStateManager.scale(5, 5, 5);
        GlStateManager.disableLighting();
        this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 0.5F, 0.5F, 0.25F);
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 0.03125F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityPoulopopMiniSun entity)
    {
        return SHULKER_SPARK_TEXTURE;
    }
}