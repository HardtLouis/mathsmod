package com.mod.mathsmod.add.items.ItemArmor.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class model_jetpack extends ModelBiped
{
  //fields
	private ModelBiped baseModel;
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape4;
    public ModelRenderer Shape3;
  
  public model_jetpack(ModelBiped model, float size)
  {
    textureWidth = 64;
    textureHeight = 32;
    
    baseModel = model;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-2.5F, 1F, 2F, 5, 5, 3, size);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 16, 0);
      Shape2.addBox(1F, 1F, 3F, 17, 3, 1, size);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0.4363323F);
      Shape4 = new ModelRenderer(this, 16, 0);
      Shape4.addBox(-18F, 1F, 3F, 17, 3, 1, size);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, -0.4363323F);
      Shape3 = new ModelRenderer(this, 52, 0);
      Shape3.addBox(-1F, 4F, 2.3F, 2, 5, 2, size);
      Shape3.setRotationPoint(0F, 0F, 1F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    
	    GlStateManager.pushMatrix();
	    
	    if(this.isChild)
	    {
	    	GlStateManager.scale(0.5F, 0.5F, 0.5F);
	        GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
	        this.bipedBody.render(f5);
	        Shape1.render(f5);
	        Shape2.render(f5);
	        Shape3.render(f5);
	        Shape4.render(f5);
	    }
	    else
	    {
	    	if (entity.isSneaking())
	        {
	            GlStateManager.translate(0.0F, 0.2F, 0.0F);
	        }
	    	this.bipedBody.render(f5);
	        Shape1.render(f5);
	        Shape2.render(f5);
	        Shape3.render(f5);
	        Shape4.render(f5);
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
    if(this.baseModel != null)
    {
    	this.copyModelAngles(this.baseModel.bipedBody, this.Shape1);
    	this.copyModelAngles(this.baseModel.bipedBody, this.Shape3);
    }
  }

}
