package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.EntityShark;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelShark extends ModelBase
{
  //fields
    ModelRenderer Body1;
    ModelRenderer Head;
    ModelRenderer JawUp;
    ModelRenderer JawDown;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape32;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
  
  public ModelShark()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body1 = new ModelRenderer(this, 0, 0);
      Body1.addBox(-3.5F, -4F, -2F, 7, 8, 6);
      Body1.setRotationPoint(0F, 0F, 0F);
      Body1.setTextureSize(64, 32);
      setRotation(Body1, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 26, 0);
      Head.addBox(-3F, -3F, -6F, 6, 6, 4);
      Head.setRotationPoint(0F, 0F, 0F);
      Head.setTextureSize(64, 32);
      setRotation(Head, 0F, 0F, 0F);
      JawUp = new ModelRenderer(this, 48, 0);
      JawUp.addBox(-2F, -2F, -10F, 4, 3, 4);
      JawUp.setRotationPoint(0F, 0F, 0F);
      JawUp.setTextureSize(64, 32);
      setRotation(JawUp, 0F, 0F, 0F);
      JawDown = new ModelRenderer(this, 28, 10);
      JawDown.addBox(-2F, 1F, -9F, 4, 1, 3);
      JawDown.setRotationPoint(0F, 0F, 0F);
      JawDown.setTextureSize(64, 32);
      setRotation(JawDown, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 16);
      Shape1.addBox(-2.5F, -3F, 0F, 5, 6, 6);
      Shape1.setRotationPoint(0F, 0F, 4F);
      Shape1.setTextureSize(64, 32);
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 47, 9);
      Shape2.addBox(-1F, -2F, 6F, 2, 4, 4);
      Shape2.setRotationPoint(0F, 0F, 4F);
      Shape2.setTextureSize(64, 32);
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 26, 17);
      Shape3.addBox(-0.5F, 7F, 5F, 1, 4, 2);
      Shape3.setRotationPoint(0F, 0F, 4F);
      Shape3.setTextureSize(64, 32);
      setRotation(Shape3, 0.7853982F, 0F, 0F);
      Shape32 = new ModelRenderer(this, 26, 17);
      Shape32.addBox(-0.5F, -14F, 8F, 1, 4, 2);
      Shape32.setRotationPoint(0F, 0F, 0F);
      Shape32.setTextureSize(64, 32);
      setRotation(Shape32, -0.7853982F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 34, 18);
      Shape4.addBox(-0.5F, -2.5F, 0F, 1, 4, 2);
      Shape4.setRotationPoint(0F, -5F, 0F);
      Shape4.setTextureSize(64, 32);
      setRotation(Shape4, -0.7853982F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 48, 23);
      Shape5.addBox(0F, 0F, -0.5F, 1, 4, 2);
      Shape5.setRotationPoint(2F, 4F, 0F);
      Shape5.setTextureSize(64, 32);
      setRotation(Shape5, 0.3490659F, 0F, -0.7330383F);
      Shape6 = new ModelRenderer(this, 48, 23);
      Shape6.addBox(-1F, 0F, -0.5F, 1, 4, 2);
      Shape6.setRotationPoint(-2F, 4F, 0F);
      Shape6.setTextureSize(64, 32);
      setRotation(Shape6, 0.3490659F, 0F, 0.7330383F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body1.render(f5);
    Head.render(f5);
    JawUp.render(f5);
    JawDown.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape32.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
  {
	  super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
	  
	  GlStateManager.rotate(netHeadYaw, 0, 1, 0);
	  if(entity instanceof EntityShark)
	  {
		  this.Head.rotateAngleX = limbSwingAmount * -8.521F * 0.017453292F;
		  this.JawUp.rotateAngleX = limbSwingAmount * -8.521F * 0.017453292F;
		  this.JawDown.rotateAngleX = limbSwingAmount * 4F * 0.017453292F;
		  
		  /*this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
		  this.JawUp.rotateAngleY = netHeadYaw * 0.017453292F;
		  this.JawDown.rotateAngleY = netHeadYaw * 0.017453292F;*/
		  
		  this.Shape1.rotateAngleY = ((EntityShark) entity).tail / 10 * 21 * 0.017453292F;
		  this.Shape2.rotateAngleY = ((EntityShark) entity).tail / 10 * 25 * 0.017453292F;
		  this.Shape3.rotateAngleY = ((EntityShark) entity).tail / 10 * 25 * 0.017453292F;
		  this.Shape32.rotateAngleY = ((EntityShark) entity).tail / 10 * 19 * 0.017453292F;
	  }
	  GlStateManager.rotate(-netHeadYaw, 0, 1, 0);
  }

}
