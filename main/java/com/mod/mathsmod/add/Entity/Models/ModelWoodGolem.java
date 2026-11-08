package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWoodGolem extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
  
  public ModelWoodGolem()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-5F, 0F, -5F, 10, 14, 10);
      body.setRotationPoint(0F, 2F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 48, 12);
      rightarm.addBox(-4F, -2F, -2F, 4, 16, 4);
      rightarm.setRotationPoint(-5F, 5F, 0F);
      rightarm.setTextureSize(64, 32);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 48, 12);
      leftarm.addBox(0F, -2F, -2F, 4, 16, 4);
      leftarm.setRotationPoint(5F, 5F, 0F);
      leftarm.setTextureSize(64, 32);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 48, 12);
      rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
      rightleg.setRotationPoint(-2F, 16F, 0F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 48, 12);
      leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
      leftleg.setRotationPoint(2F, 16F, 0F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
  {
	  super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

    super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    
    this.rightarm.rotateAngleZ = ((1 + MathHelper.sin((float) (ageInTicks * 5 * 0.017453292F))) * 4 * 0.017453292F);
    this.leftarm.rotateAngleZ = ((-1 - MathHelper.sin((float) (ageInTicks * 5 * 0.017453292F))) * 4 * 0.017453292F);
    
    this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
    this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
    
    
    
    this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    
    if (this.swingProgress > 0.0F)
    {
    	if(this.swingProgress <= 0.1F)
    	{
    		this.rightarm.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -95F * 0.017453292F;
    		//this.rightarmPart2.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -135F * 0.017453292F;
    	}
    	else if(this.swingProgress <= 0.3F)
    	{
    		float progress = 1F - (this.swingProgress - 0.1F) / 0.2F;
    		
    		this.rightarm.rotateAngleX = (-95F - (95 * progress)) * 0.017453292F;
    	}
    	else
    	{
    		float progress = 1 - (this.swingProgress - 0.3F) / 0.7F;
    		this.rightarm.rotateAngleX = progress * -90F * 0.017453292F;
    	}
    }
  }

}
