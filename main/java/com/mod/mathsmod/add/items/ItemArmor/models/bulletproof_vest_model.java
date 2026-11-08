package com.mod.mathsmod.add.items.ItemArmor.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class bulletproof_vest_model extends ModelBiped
{
  //fields
    public ModelRenderer body1;
    public ModelRenderer body2;
  
  public bulletproof_vest_model(float i, ModelBiped model)
  {
	  
	  super(i);
	  textureWidth = 64;
	  textureHeight = 32;
    
      body1 = new ModelRenderer(this, 0, 6);
      body1.addBox(-3F, 2F, 2F, 6, 8, 1, i);
      body1.setRotationPoint(0F, 0F, 0F);
      body1.setTextureSize(64, 32);
      body1.mirror = true;
      setRotation(body1, 0F, 0F, 0F);
      this.copyModelAngles(model.bipedBody, body1);
      body2 = new ModelRenderer(this, 0, 6);
      body2.addBox(-3F, 2F, -3F, 6, 8, 1, i);
      body2.setRotationPoint(0F, 0F, 0F);
      body2.setTextureSize(64, 32);
      body2.mirror = true;
      setRotation(body2, 0F, 0F, 0F);
      this.copyModelAngles(model.bipedBody, body2);
      this.copyModelAngles(model.bipedBody, this.bipedBody);
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
        body1.render(f5);
        body2.render(f5);
    }
    else
    {
    	if (entity.isSneaking())
        {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }
    	this.bipedBody.render(f5);
        body1.render(f5);
        body2.render(f5);
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
    this.copyModelAngles(bipedBody, body1);
    this.copyModelAngles(bipedBody, body2);
  }

}
