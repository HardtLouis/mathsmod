package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDrone extends ModelBase
{
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer e1;
   ModelRenderer e2;
   ModelRenderer e3;
   ModelRenderer e4;
   ModelRenderer Shape4;
  
   public ModelDrone()
   {
	   	textureWidth = 32;
		textureHeight = 32;
	    
	    Shape1 = new ModelRenderer(this, 0, 0);
	    Shape1.addBox(3F, 0F, -4F, 1, 1, 8);
	    Shape1.setRotationPoint(0F, 22F, 0F);
	    Shape1.setTextureSize(32, 32);
	    Shape1.mirror = true;
	    setRotation(Shape1, 0F, 0F, 0F);
	    Shape2 = new ModelRenderer(this, 0, 0);
	    Shape2.addBox(-4F, 0F, -4F, 1, 1, 8);
	    Shape2.setRotationPoint(0F, 22F, 0F);
	    Shape2.setTextureSize(32, 32);
	    Shape2.mirror = true;
	    setRotation(Shape2, 0F, 0F, 0F);
	    Shape3 = new ModelRenderer(this, 0, 9);
	    Shape3.addBox(-3F, 0F, -3F, 6, 1, 6);
	    Shape3.setRotationPoint(0F, 23F, 0F);
	    Shape3.setTextureSize(32, 32);
	    Shape3.mirror = true;
	    setRotation(Shape3, 0F, 0F, 0F);
	    e1 = new ModelRenderer(this, 0, 0);
	    e1.addBox(-1.5F, 0F, -0.5333334F, 3, 1, 1);
	    e1.setRotationPoint(3.5F, 21F, 3.5F);
	    e1.setTextureSize(32, 32);
	    e1.mirror = true;
	    setRotation(e1, 0F, 0F, 0F);
	    e2 = new ModelRenderer(this, 0, 0);
	    e2.addBox(-1.5F, 0F, -0.5333334F, 3, 1, 1);
	    e2.setRotationPoint(3.5F, 21F, -3.5F);
	    e2.setTextureSize(32, 32);
	    e2.mirror = true;
	    setRotation(e2, 0F, 0F, 0F);
	    e3 = new ModelRenderer(this, 0, 0);
	    e3.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
	    e3.setRotationPoint(-3.5F, 21F, 3.5F);
      	e3.setTextureSize(32, 32);
      	e3.mirror = true;
      	setRotation(e3, 0F, 0F, 0F);
      	e4 = new ModelRenderer(this, 0, 0);
      	e4.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
      	e4.setRotationPoint(-3.5F, 21F, -3.5F);
      	e4.setTextureSize(32, 32);
      	e4.mirror = true;
      	setRotation(e4, 0F, 0F, 0F);
      	Shape4 = new ModelRenderer(this, 16, 16);
      	Shape4.addBox(-1F, -1F, -3F, 2, 2, 6);
      	Shape4.setRotationPoint(0F, 22F, 0F);
      	Shape4.setTextureSize(32, 32);
      	Shape4.mirror = true;
      	setRotation(Shape4, 0F, 0F, 0F);
  	}
  
  	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  	{
	  	super.render(entity, f, f1, f2, f3, f4, f5);
	  	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  	Shape1.render(f5);
	  	Shape2.render(f5);
	  	Shape3.render(f5);
	  	e1.render(f5);
	  	e2.render(f5);
	  	e3.render(f5);
	  	e4.render(f5);
	  	Shape4.render(f5);
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
  		if(f2 >= 0)
  		{
  			e1.rotateAngleY = f2 * 5;
  		  	e2.rotateAngleY = f2 * 5;
  		  	e3.rotateAngleY = f2 * 5;
  		  	e4.rotateAngleY = f2 * 5;
  		}
  	}

}
