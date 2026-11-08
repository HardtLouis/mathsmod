package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.MathHelper;

public class ModelWolfArmor  extends ModelWolf
{
    /** main box for the wolf head */
    public ModelRenderer wolfHeadMainA;
    /** The wolf's body */
    public ModelRenderer wolfBodyA;
    /** Wolf'se first leg */
    public ModelRenderer wolfLeg1A;
    /** Wolf's second leg */
    public ModelRenderer wolfLeg2A;
    /** Wolf's third leg */
    public ModelRenderer wolfLeg3A;
    /** Wolf's fourth leg */
    public ModelRenderer wolfLeg4A;
    /** The wolf's tail */
    public ModelRenderer wolfTailA;
    /** The wolf's mane */
    public ModelRenderer wolfManeA;

    public ModelWolfArmor(float scale)
    {
        float f = 0.0F;
        float f1 = 13.5F;
        this.wolfHeadMainA = new ModelRenderer(this, 0, 0);
        this.wolfHeadMainA.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, scale);
        this.wolfHeadMainA.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.wolfBodyA = new ModelRenderer(this, 18, 14);
        this.wolfBodyA.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, scale);
        this.wolfBodyA.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.wolfManeA = new ModelRenderer(this, 21, 0);
        this.wolfManeA.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, scale);
        this.wolfManeA.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.wolfLeg1A = new ModelRenderer(this, 0, 18);
        this.wolfLeg1A.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.wolfLeg1A.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.wolfLeg2A = new ModelRenderer(this, 0, 18);
        this.wolfLeg2A.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.wolfLeg2A.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.wolfLeg3A = new ModelRenderer(this, 0, 18);
        this.wolfLeg3A.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.wolfLeg3A.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.wolfLeg4A = new ModelRenderer(this, 0, 18);
        this.wolfLeg4A.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.wolfLeg4A.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.wolfTailA = new ModelRenderer(this, 9, 18);
        this.wolfTailA.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.wolfTailA.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.wolfHeadMainA.setTextureOffset(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, scale);
        this.wolfHeadMainA.setTextureOffset(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, scale);
        this.wolfHeadMainA.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, scale);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        //super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.wolfHeadMainA.renderWithRotation(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.wolfBodyA.render(scale);
            this.wolfLeg1A.render(scale);
            this.wolfLeg2A.render(scale);
            this.wolfLeg3A.render(scale);
            this.wolfLeg4A.render(scale);
            this.wolfTailA.renderWithRotation(scale);
            this.wolfManeA.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.wolfHeadMainA.renderWithRotation(scale);
            this.wolfBodyA.render(scale);
            this.wolfLeg1A.render(scale);
            this.wolfLeg2A.render(scale);
            this.wolfLeg3A.render(scale);
            this.wolfLeg4A.render(scale);
            this.wolfTailA.renderWithRotation(scale);
            this.wolfManeA.render(scale);
        }
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        EntityWolf entitywolf = (EntityWolf)entitylivingbaseIn;

        if (entitywolf.isAngry())
        {
            this.wolfTailA.rotateAngleY = 0.0F;
        }
        else
        {
            this.wolfTailA.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        if (entitywolf.isSitting())
        {
            this.wolfManeA.setRotationPoint(-1.0F, 16.0F, -3.0F);
            this.wolfManeA.rotateAngleX = ((float)Math.PI * 2F / 5F);
            this.wolfManeA.rotateAngleY = 0.0F;
            this.wolfBodyA.setRotationPoint(0.0F, 18.0F, 0.0F);
            this.wolfBodyA.rotateAngleX = ((float)Math.PI / 4F);
            this.wolfTailA.setRotationPoint(-1.0F, 21.0F, 6.0F);
            this.wolfLeg1A.setRotationPoint(-2.5F, 22.0F, 2.0F);
            this.wolfLeg1A.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.wolfLeg2A.setRotationPoint(0.5F, 22.0F, 2.0F);
            this.wolfLeg2A.rotateAngleX = ((float)Math.PI * 3F / 2F);
            this.wolfLeg3A.rotateAngleX = 5.811947F;
            this.wolfLeg3A.setRotationPoint(-2.49F, 17.0F, -4.0F);
            this.wolfLeg4A.rotateAngleX = 5.811947F;
            this.wolfLeg4A.setRotationPoint(0.51F, 17.0F, -4.0F);
        }
        else
        {
            this.wolfBodyA.setRotationPoint(0.0F, 14.0F, 2.0F);
            this.wolfBodyA.rotateAngleX = ((float)Math.PI / 2F);
            this.wolfManeA.setRotationPoint(-1.0F, 14.0F, -3.0F);
            this.wolfManeA.rotateAngleX = this.wolfBody.rotateAngleX;
            this.wolfTailA.setRotationPoint(-1.0F, 12.0F, 8.0F);
            this.wolfLeg1A.setRotationPoint(-2.5F, 16.0F, 7.0F);
            this.wolfLeg2A.setRotationPoint(0.5F, 16.0F, 7.0F);
            this.wolfLeg3A.setRotationPoint(-2.5F, 16.0F, -4.0F);
            this.wolfLeg4A.setRotationPoint(0.5F, 16.0F, -4.0F);
            this.wolfLeg1A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.wolfLeg2A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.wolfLeg3A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.wolfLeg4A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        this.wolfHeadMainA.rotateAngleZ = entitywolf.getInterestedAngle(partialTickTime) + entitywolf.getShakeAngle(partialTickTime, 0.0F);
        this.wolfManeA.rotateAngleZ = entitywolf.getShakeAngle(partialTickTime, -0.08F);
        this.wolfBodyA.rotateAngleZ = entitywolf.getShakeAngle(partialTickTime, -0.16F);
        this.wolfTailA.rotateAngleZ = entitywolf.getShakeAngle(partialTickTime, -0.2F);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.wolfHeadMainA.rotateAngleX = headPitch * 0.017453292F;
        this.wolfHeadMainA.rotateAngleY = netHeadYaw * 0.017453292F;
        this.wolfTailA.rotateAngleX = ageInTicks;
    }
}