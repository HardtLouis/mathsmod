package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelElectricBoat extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
  
  public ModelElectricBoat()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-10F, 0F, -5F, 23, 0, 10);
      Shape1.setRotationPoint(0F, 23.9F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 10);
      Shape2.addBox(0F, 0F, 0F, 8, 3, 1);
      Shape2.setRotationPoint(7F, 21F, 5F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0.7063936F, 0F);
      Shape3 = new ModelRenderer(this, 0, 10);
      Shape3.addBox(0F, 0F, -1F, 8, 3, 1);
      Shape3.setRotationPoint(7F, 21F, -5F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, -0.7063871F, 0F);
      Shape4 = new ModelRenderer(this, 19, 10);
      Shape4.addBox(0F, 0F, 0F, 18, 3, 1);
      Shape4.setRotationPoint(-10F, 21F, 5F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 19, 10);
      Shape5.addBox(0F, 0F, -1F, 18, 3, 1);
      Shape5.setRotationPoint(-10F, 21F, -5F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 14);
      Shape6.addBox(0F, 0F, -6F, 3, 3, 12);
      Shape6.setRotationPoint(-13F, 21F, 0F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 30, 14);
      Shape7.addBox(-2F, -2F, -2F, 4, 4, 4);
      Shape7.setRotationPoint(-13F, 21F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(-1F, 0F, -1F, 1, 4, 2);
      Shape8.setRotationPoint(-14F, 23F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 50, 22);
      Shape9.addBox(-1F, -1.5F, -0.5F, 1, 3, 1);
      Shape9.setRotationPoint(-15F, 26F, 0F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
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
    this.Shape9.rotateAngleX += (entity.motionX + entity.motionZ) * 3;
  }

}
