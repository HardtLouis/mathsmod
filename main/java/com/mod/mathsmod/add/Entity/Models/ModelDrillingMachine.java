package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityDrillingMachine;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTerraformer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDrillingMachine extends ModelBase
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
  
  public ModelDrillingMachine()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-4F, -4F, -4F, 8, 6, 8);
      Shape1.setRotationPoint(0F, 21F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, -1F, -1F, 1, 2, 2);
      Shape2.setRotationPoint(4F, 23F, 3F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, -1F, -1F, 1, 2, 2);
      Shape3.setRotationPoint(4F, 23F, -3F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(-1F, -1F, -1F, 1, 2, 2);
      Shape4.setRotationPoint(-4F, 23F, -3F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-1F, -1F, -1F, 1, 2, 2);
      Shape5.setRotationPoint(-4F, 23F, 3F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 32, 0);
      Shape6.addBox(-3F, -3F, 0F, 6, 6, 1);
      Shape6.setRotationPoint(0F, 20F, 4F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 46, 5);
      Shape7.addBox(-2.5F, -2.5F, 0F, 5, 5, 1);
      Shape7.setRotationPoint(0F, 20F, 5F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 46, 0);
      Shape8.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
      Shape8.setRotationPoint(0F, 20F, 6F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
      Shape9.setRotationPoint(0F, 20F, 8F);
      Shape9.setTextureSize(64, 32);
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
    if(entity instanceof EntityDrillingMachine)
    {
    	this.Shape6.rotateAngleZ += ((EntityDrillingMachine)entity).getForward;
        this.Shape7.rotateAngleZ += ((EntityDrillingMachine)entity).getForward;
        this.Shape8.rotateAngleZ += ((EntityDrillingMachine)entity).getForward;
        this.Shape9.rotateAngleZ += ((EntityDrillingMachine)entity).getForward;
    }
    
    if(entity instanceof EntityTerraformer)
    {
    	this.Shape6.rotateAngleZ += ((EntityTerraformer)entity).getForward;
        this.Shape7.rotateAngleZ += ((EntityTerraformer)entity).getForward;
        this.Shape8.rotateAngleZ += ((EntityTerraformer)entity).getForward;
        this.Shape9.rotateAngleZ += ((EntityTerraformer)entity).getForward;
    }
  }

}
