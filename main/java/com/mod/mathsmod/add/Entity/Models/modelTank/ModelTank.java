package com.mod.mathsmod.add.Entity.Models.modelTank;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTank extends ModelBase
{
  //fields
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
  
  public ModelTank()
  {
    textureWidth = 64;
    textureHeight = 64;
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-6F, 0F, -8F, 12, 1, 16);
      Shape3.setRotationPoint(0F, 22F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 3, 2);
      Shape4.addBox(-5F, 0F, -7F, 10, 1, 14);
      Shape4.setRotationPoint(0F, 21F, 0F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 4, 2);
      Shape5.addBox(-5F, 0F, -7F, 10, 1, 14);
      Shape5.setRotationPoint(0F, 20F, 0F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 22);
      Shape6.addBox(-7F, 0F, -8F, 14, 5, 16);
      Shape6.setRotationPoint(0F, 15F, 0F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 47);
      Shape7.addBox(-4F, 0F, -4F, 8, 2, 12);
      Shape7.setRotationPoint(0F, 13F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 4, 51);
      Shape8.addBox(-4F, 0F, 0F, 8, 1, 8);
      Shape8.setRotationPoint(0F, 12F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 40, 10);
      Shape9.addBox(-1F, -1F, -14F, 2, 2, 10);
      Shape9.setRotationPoint(0F, 15F, 0F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
    if(entity instanceof EntityTank)
    {
    	Shape9.rotateAngleX = MathHelper.clamp(((EntityTank) entity).canonRotationPitch, -45, 30) * 0.017453292F;
    	
    	Shape9.rotateAngleY = (entity.rotationYaw - ((EntityTank) entity).canonRotationYaw) * -0.017453292F;
    	Shape8.rotateAngleY = (entity.rotationYaw - ((EntityTank) entity).canonRotationYaw) * -0.017453292F;
    	Shape7.rotateAngleY = (entity.rotationYaw - ((EntityTank) entity).canonRotationYaw) * -0.017453292F;
    	Shape6.rotateAngleY = (entity.rotationYaw - ((EntityTank) entity).canonRotationYaw) * -0.017453292F;
    	Shape5.rotateAngleY = (entity.rotationYaw - ((EntityTank) entity).canonRotationYaw) * -0.017453292F;
    }
    
    
  }

}
