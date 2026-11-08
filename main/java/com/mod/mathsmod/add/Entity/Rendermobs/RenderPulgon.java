package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDBossDragonPhase;
import com.mod.mathsmod.add.Entity.Models.ModelPulgon;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerPulgonDeath;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderPulgon extends AbstractRenderBaseBoss<EntityDBossDragonPhase>
{
    private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/pulgon/pulgon_exploding.png");
    private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/pulgon/pulgon.png");

    public RenderPulgon(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPulgon(0.0F), 0.5F);
        this.addLayer(new BossDeathLayer(255, 100, 100));
    }

    protected void applyRotations(EntityDBossDragonPhase entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        float f = (float)entityLiving.getMovementOffsets(7, partialTicks)[0];
        float f1 = (float)(entityLiving.getMovementOffsets(5, partialTicks)[1] - entityLiving.getMovementOffsets(10, partialTicks)[1]);
        GlStateManager.rotate(-f, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1 * 10.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 1.0F);

        if (entityLiving.deathTime > 0)
        {
            float f2 = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f2 = MathHelper.sqrt(f2);

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            //GlStateManager.rotate(f2 * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
        }
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityDBossDragonPhase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        this.bindEntityTexture(entitylivingbaseIn);
        this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        if (entitylivingbaseIn.hurtTime > 0)
        {
            GlStateManager.depthFunc(514);
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.depthFunc(515);
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityDBossDragonPhase entity)
    {
        return DRAGON_TEXTURES;
    }
}