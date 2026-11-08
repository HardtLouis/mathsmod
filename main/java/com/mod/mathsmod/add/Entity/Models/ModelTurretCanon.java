package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurret;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurretCanon extends ModelBase
{
  //fields
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
  
  public ModelTurretCanon()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape2 = new ModelRenderer(this, 0, 11);
      Shape2.addBox(-5F, 0F, -5F, 10, 1, 10);
      Shape2.setRotationPoint(0F, 16F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 12, 0);
      Shape3.addBox(-4F, -7F, 4F, 8, 8, 1);
      Shape3.setRotationPoint(0F, 15F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 9, 22);
      Shape4.addBox(-1F, -1F, -1F, 2, 2, 2);
      Shape4.setRotationPoint(0F, 18F, -4F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 30, 0);
      Shape5.addBox(-2F, -2F, 0F, 4, 4, 5);
      Shape5.setRotationPoint(0F, 12F, -10F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 48, 0);
      Shape6.addBox(-1F, 0F, 0F, 2, 4, 2);
      Shape6.setRotationPoint(0F, 12F, -6F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 40, 11);
      Shape7.addBox(2F, -1F, -2F, 1, 1, 8);
      Shape7.setRotationPoint(0F, 12F, -12F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 40, 11);
      Shape8.addBox(-3F, -1F, -2F, 1, 1, 8);
      Shape8.setRotationPoint(0F, 12F, -12F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    this.setRotation(Shape7, entity.rotationPitch / 180, 0, 0);
    this.setRotation(Shape8, entity.rotationPitch / 180, 0, 0);
    
    if(entity.getControllingPassenger() == null || entity.getControllingPassenger() != Minecraft.getMinecraft().player || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
    {
    	Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
    }
  }
  
  public void renderAll(EntityTurret te)
  {
	  Shape2.render(0.0625F);
	    Shape3.render(0.0625F);
	    Shape4.render(0.0625F);
	    if(te.getControllingPassenger() == null || te.getControllingPassenger() == Minecraft.getMinecraft().player)
	    {
	    	Shape5.render(0.0625F);
	        Shape6.render(0.0625F);
	        Shape7.render(0.0625F);
	        Shape8.render(0.0625F);
	    }
	    this.setRotation(Shape7, te.rotationPitch / 180, 0, 0);
	    this.setRotation(Shape8, te.rotationPitch / 180, 0, 0);
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
  }

}
