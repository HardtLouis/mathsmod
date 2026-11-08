package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurretBase extends ModelBase
{
  //fields
    ModelRenderer Foot1;
    ModelRenderer Foot2;
    ModelRenderer Foot3;
    ModelRenderer Shape1;
  
  public ModelTurretBase()
  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      Foot1 = new ModelRenderer(this, 0, 0);
	      Foot1.addBox(0F, -5F, -0.5F, 1, 10, 1);
	      Foot1.setRotationPoint(0F, 23F, 0F);
	      Foot1.setTextureSize(64, 32);
	      Foot1.mirror = true;
	      setRotation(Foot1, 0F, 0.90F, 0F);
	      Foot2 = new ModelRenderer(this, 0, 0);
	      Foot2.addBox(-0.5F, -5F, -1F, 1, 10, 1);
	      Foot2.setRotationPoint(0F, 23F, 0F);
	      Foot2.setTextureSize(64, 32);
	      Foot2.mirror = true;
	      setRotation(Foot2, 0F, 0.90F, 0.90F);
	      Foot3 = new ModelRenderer(this, 0, 0);
	      Foot3.addBox(-0.5F, 0F, -0.5F, 1, 6, 1);
	      Foot3.setRotationPoint(0F, 18F, 0F);
	      Foot3.setTextureSize(64, 32);
	      Foot3.mirror = true;
	      setRotation(Foot3, 0F, 0F, 0F);
	      Shape1 = new ModelRenderer(this, 4, 0);
	      Shape1.addBox(-1F, 0F, -1F, 2, 2, 2);
	      Shape1.setRotationPoint(0F, 17F, 0F);
	      Shape1.setTextureSize(64, 32);
	      Shape1.mirror = true;
	      setRotation(Shape1, 0F, 0F, 0F);
	  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    //Foot1.render(f5);
    //Foot2.render(f5);
    Foot3.render(f5);
    Shape1.render(f5);
  }
  
  public void renderAll()
  {
	  //Foot1.render(0.0625F);
	    //Foot2.render(0.0625F);
	    Foot3.render(0.0625F);
	    Shape1.render(0.0625F);
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
