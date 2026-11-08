package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlal extends ModelBase
{
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer Body;
    ModelRenderer Head;
    ModelRenderer stick;
    ModelRenderer rightarm1;
    ModelRenderer rightarm2;
    ModelRenderer leftarm1;
    ModelRenderer leftarm2;
  
  public ModelBlal()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      leftleg = new ModelRenderer(this, 56, 0);
      leftleg.addBox(-1F, 0F, -1F, 2, 20, 2);
      leftleg.setRotationPoint(-2F, 4F, 0F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 56, 0);
      rightleg.addBox(-1F, 0F, -1F, 2, 20, 2);
      rightleg.setRotationPoint(2F, 4F, 0F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 16);
      Body.addBox(-3.5F, -10F, -1F, 7, 10, 3);
      Body.setRotationPoint(0F, 5F, -0.5F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0.4461433F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-4F, -8F, -4F, 8, 8, 8);
      Head.setRotationPoint(0F, -4F, -4F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0.1858931F, 0F, 0.3717861F);
      stick = new ModelRenderer(this, 0, 29);
      stick.addBox(-9F, -4F, 0F, 25, 1, 1);
      stick.setRotationPoint(0F, -4F, -4F);
      stick.setTextureSize(64, 32);
      stick.mirror = true;
      setRotation(stick, 0.185895F, 0F, 0.37179F);
      rightarm1 = new ModelRenderer(this, 52, 0);
      rightarm1.addBox(-0.5F, 0F, -0.5F, 1, 11, 1);
      rightarm1.setRotationPoint(2F, -3F, -3F);
      rightarm1.setTextureSize(64, 32);
      rightarm1.mirror = true;
      setRotation(rightarm1, 0F, 0F, -0.6692116F);
      rightarm2 = new ModelRenderer(this, 44, 0);
      rightarm2.addBox(6F, 3F, 7.5F, 1, 10, 1);
      rightarm2.setRotationPoint(2F, -3F, -3F);
      rightarm2.setTextureSize(64, 32);
      rightarm2.mirror = true;
      setRotation(rightarm2, -1.226894F, 0F, 0F);
      leftarm1 = new ModelRenderer(this, 52, 0);
      leftarm1.addBox(-0.5F, 0F, -0.5F, 1, 11, 1);
      leftarm1.setRotationPoint(-2F, -3F, -3F);
      leftarm1.setTextureSize(64, 32);
      leftarm1.mirror = true;
      setRotation(leftarm1, 0F, 0F, 0.6692116F);
      leftarm2 = new ModelRenderer(this, 44, 0);
      leftarm2.addBox(-7F, 3F, 7.5F, 1, 10, 1);
      leftarm2.setRotationPoint(-2F, -3F, -3F);
      leftarm2.setTextureSize(64, 32);
      leftarm2.mirror = true;
      setRotation(leftarm2, -1.226897F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    leftleg.render(f5);
    rightleg.render(f5);
    Body.render(f5);
    Head.render(f5);
    stick.render(f5);
    rightarm1.render(f5);
    //rightarm2.render(f5);
    leftarm1.render(f5);
    //leftarm2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
  {
    super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    
    this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.Head.rotateAngleX = 0.1858931F + headPitch * 0.017453292F;
    this.copyModelAngles(this.Head, this.stick);
    this.rightarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.5F * limbSwingAmount * 0.5F;
    this.leftarm1.rotateAngleX = 0;
    this.leftarm2.rotateAngleX = -1.226894F;
    this.leftarm2.rotateAngleY = 0F;
    this.rightarm2.rotateAngleX = -1.226894F + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) *  0.5F * limbSwingAmount * 0.5F;
    this.leftarm2.rotateAngleX = -1.226894F + MathHelper.cos(limbSwing * 0.6662F) *  0.5F * limbSwingAmount * 0.5F;
    this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 4;
    this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / 4;
    
    if (this.swingProgress > 0.0F)
    {
    	this.leftarm1.rotateAngleX = this.swingProgress * 80 * -0.017453292F;
    	this.leftarm2.rotateAngleX = -1.226894F + this.swingProgress * 40 * -0.017453292F;
    	this.leftarm2.rotateAngleY = this.swingProgress * 20 * 0.017453292F;
    }
    else
    {
        this.leftarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount * 0.5F;
        this.leftarm2.rotateAngleX = -1.226894F + MathHelper.cos(limbSwing * 0.6662F) *  0.5F * limbSwingAmount * 0.5F;
        this.leftarm2.rotateAngleY = 0F;
    }
    
  }

}
