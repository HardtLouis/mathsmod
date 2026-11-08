package com.mod.mathsmod.add.items.ItemArmor.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelExplosiveBelt extends ModelBiped
{
  //fields
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
    ModelBiped base;
  
  public ModelExplosiveBelt(ModelBiped biped, float scale)
  {
    textureWidth = 64;
    textureHeight = 32;
    
    base = biped;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-3F, 5F, -3F, 1, 3, 1, scale);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 5F, -3F, 1, 3, 1, scale);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(3F, 5F, -3F, 1, 3, 1, scale);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(4F, 5F, 0F, 1, 3, 1, scale);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(2F, 5F, 2F, 1, 3, 1, scale);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(-1F, 5F, 2F, 1, 3, 1, scale);
      Shape6.setRotationPoint(0F, 0F, 0F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(-4F, 5F, 2F, 1, 3, 1, scale);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(-5F, 5F, -1F, 1, 3, 1, scale);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    GlStateManager.pushMatrix();
    
    if(this.isChild)
    {
        GlStateManager.scale(0.75F, 0.75F, 0.75F);
        GlStateManager.translate(0.0F, 16.0F * f5, 0.0F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        bipedBody.render(f5);
    }
    else
    {
    	if (entity.isSneaking())
        {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }
    	Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        bipedBody.render(f5);
    }
    
    GlStateManager.popMatrix();
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  
    this.a(base.bipedBody, Shape1);
    this.a(base.bipedBody, Shape2);
    this.a(base.bipedBody, Shape3);
    this.a(base.bipedBody, Shape4);
    this.a(base.bipedBody, Shape5);
    this.a(base.bipedBody, Shape6);
    this.a(base.bipedBody, Shape7);
    this.a(base.bipedBody, Shape8);
    if(this.isSneak)
    {
    	this.Shape1.rotateAngleX = 0.5F;
    	this.Shape2.rotateAngleX = 0.5F;
    	this.Shape3.rotateAngleX = 0.5F;
    	this.Shape4.rotateAngleX = 0.5F;
    	this.Shape5.rotateAngleX = 0.5F;
    	this.Shape6.rotateAngleX = 0.5F;
    	this.Shape7.rotateAngleX = 0.5F;
    	this.Shape8.rotateAngleX = 0.5F;
    }
  }
  
  private void a(ModelRenderer a, ModelRenderer b)
  {
	  b.rotateAngleX = a.rotateAngleX;
      b.rotateAngleY = a.rotateAngleY;
      b.rotateAngleZ = a.rotateAngleZ;
  }

}
