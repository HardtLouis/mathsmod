package com.mod.mathsmod.add.Entity.Models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ModelNightGolem extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer body2;
    ModelRenderer rightarmPart2;
    ModelRenderer leftarmPart2;
  
  public ModelNightGolem()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -6F, -6F, 8, 6, 8);
      head.setRotationPoint(0F, 2F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0.1858931F, 0F, 0F);
      body = new ModelRenderer(this, 20, 16);
      body.addBox(-5F, 0F, -3F, 10, 8, 6);
      body.setRotationPoint(0F, 10F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 32, 0);
      rightarm.addBox(-4F, -2F, -2F, 4, 9, 4);
      rightarm.setRotationPoint(-7F, 5F, 0F);
      rightarm.setTextureSize(64, 32);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 32, 0);
      leftarm.addBox(0F, -2F, -2F, 4, 9, 4);
      leftarm.setRotationPoint(7F, 5F, 0F);
      leftarm.setTextureSize(64, 32);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 6, 4);
      rightleg.setRotationPoint(-3F, 18F, 0F);
      rightleg.setTextureSize(64, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 4, 6, 4);
      leftleg.setRotationPoint(3F, 18F, 0F);
      leftleg.setTextureSize(64, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      body2 = new ModelRenderer(this, 16, 16);
      body2.addBox(-7F, 0F, -3F, 14, 8, 6);
      body2.setRotationPoint(0F, 2F, 0F);
      body2.setTextureSize(64, 32);
      body2.mirror = true;
      setRotation(body2, 0F, 0F, 0F);
      rightarmPart2 = new ModelRenderer(this, 32, 0);
      rightarmPart2.addBox(-2F, 0F, -2F, 4, 9, 4);
      rightarmPart2.setRotationPoint(-9F, 12F, 0F);
      rightarmPart2.setTextureSize(64, 32);
      rightarmPart2.mirror = true;
      setRotation(rightarmPart2, -0.4089647F, 0F, 0F);
      leftarmPart2 = new ModelRenderer(this, 32, 0);
      leftarmPart2.addBox(-1F, 0F, -2F, 4, 9, 4);
      leftarmPart2.setRotationPoint(8F, 12F, 0F);
      leftarmPart2.setTextureSize(64, 32);
      leftarmPart2.mirror = true;
      setRotation(leftarmPart2, -0.4089647F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    body2.render(f5);
    rightarmPart2.render(f5);
    leftarmPart2.render(f5);
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
    
    this.rightarm.rotateAngleZ = ((1 + MathHelper.sin((float) (ageInTicks * 5 * 0.017453292F))) * 4 * 0.017453292F);
    this.leftarm.rotateAngleZ = ((-1 - MathHelper.sin((float) (ageInTicks * 5 * 0.017453292F))) * 4 * 0.017453292F);
    
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.head.rotateAngleX = headPitch * 0.017453292F + 0.1858931F;
    
    this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
    this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
    
    if(this.rightarm.rotateAngleX > 0)
    {
    	this.rightarmPart2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
    }
    else
    {
    	this.rightarmPart2.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F);
    }
    
    this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    
    if(this.leftarm.rotateAngleX > 0)
    {
    	this.leftarmPart2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
    }
    else
    {
    	this.leftarmPart2.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
    }
    
    if (this.swingProgress > 0.0F)
    {
    	if(this.swingProgress <= 0.1F)
    	{
    		this.rightarm.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -45F * 0.017453292F;
    		this.rightarmPart2.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -135F * 0.017453292F;
    	}
    	else if(this.swingProgress <= 0.3F)
    	{
    		float progress = 1F - (this.swingProgress - 0.1F) / 0.2F;
    		
    		this.rightarmPart2.rotateAngleX = (-135F + (45 * progress)) * 0.017453292F;
    		this.rightarm.rotateAngleX = (-45F - (45 * progress)) * 0.017453292F;
    	}
    	else
    	{
    		float progress = 1 - (this.swingProgress - 0.3F) / 0.7F;
    		this.rightarmPart2.rotateAngleX = progress * -90F * 0.017453292F;
    		this.rightarm.rotateAngleX = progress * -90F * 0.017453292F;
    	}
    }
    
    this.leftarmPart2.rotationPointX = 8.01F - (MathHelper.sin(leftarm.rotateAngleZ) * 7);
    this.leftarmPart2.rotationPointY = 4.5F + ((MathHelper.cos(leftarm.rotateAngleX) * MathHelper.cos(leftarm.rotateAngleZ)) * 7);
    this.leftarmPart2.rotationPointZ = (MathHelper.sin(leftarm.rotateAngleX) * 7);
    
    this.rightarmPart2.rotationPointX = -8.99F - (MathHelper.sin(rightarm.rotateAngleZ) * 7);
    this.rightarmPart2.rotationPointY = 4.5F + ((MathHelper.cos(rightarm.rotateAngleX) * MathHelper.cos(rightarm.rotateAngleZ)) * 7);
    this.rightarmPart2.rotationPointZ = (MathHelper.sin(rightarm.rotateAngleX) * 7);
  }

}
