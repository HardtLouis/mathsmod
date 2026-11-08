package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityNChauvoput;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelChauvoput extends ModelBase
{
  //fields
    ModelRenderer RightLeg;
    ModelRenderer RightLegBottom;
    ModelRenderer LeftLeg;
    ModelRenderer LeftLegBottom;
    ModelRenderer BodyBottom;
    ModelRenderer BodyPart1;
    ModelRenderer BodyPart2;
    ModelRenderer LeftShoulder;
    ModelRenderer RightShoulder;
    ModelRenderer LeftArm;
    ModelRenderer LeftArm2;
    ModelRenderer RightArm1;
    ModelRenderer RightArm2;
    ModelRenderer RightArm3;
    ModelRenderer Jaw;
  
  public ModelChauvoput(float scale)
  {
    textureWidth = 64;
    textureHeight = 64;
    
      RightLeg = new ModelRenderer(this, 52, 43);
      RightLeg.addBox(-1F, 0F, -1.5F, 3, 6, 3, scale);
      RightLeg.setRotationPoint(2.5F, 12F, 0F);
      RightLeg.setTextureSize(64, 32);
      RightLeg.mirror = true;
      setRotation(RightLeg, 0F, 0F, -0.4833219F);
      RightLegBottom = new ModelRenderer(this, 48, 52);
      RightLegBottom.addBox(-1F, -3F, -2F, 4, 8, 4, scale);
      RightLegBottom.setRotationPoint(4.5F, 19F, 0F);
      RightLegBottom.setTextureSize(64, 32);
      RightLegBottom.mirror = true;
      setRotation(RightLegBottom, 0F, 0F, 0F);
      LeftLeg = new ModelRenderer(this, 40, 43);
      LeftLeg.addBox(-2F, 0F, -1.5F, 3, 6, 3, scale);
      LeftLeg.setRotationPoint(-2.5F, 12F, 0F);
      LeftLeg.setTextureSize(64, 32);
      LeftLeg.mirror = true;
      setRotation(LeftLeg, 0F, 0F, 0.4833166F);
      LeftLegBottom = new ModelRenderer(this, 32, 52);
      LeftLegBottom.addBox(-3F, -3F, -2F, 4, 8, 4, scale);
      LeftLegBottom.setRotationPoint(-4.5F, 19F, 0F);
      LeftLegBottom.setTextureSize(64, 32);
      LeftLegBottom.mirror = true;
      setRotation(LeftLegBottom, 0F, 0F, 0F);
      BodyBottom = new ModelRenderer(this, 24, 0);
      BodyBottom.addBox(-4.5F, -4F, -2F, 9, 4, 4, scale);
      BodyBottom.setRotationPoint(0F, 13F, 0F);
      BodyBottom.setTextureSize(64, 32);
      BodyBottom.mirror = true;
      setRotation(BodyBottom, 0F, 0F, 0F);
      BodyPart1 = new ModelRenderer(this, 24, 8);
      BodyPart1.addBox(-6F, -5F, -2.5F, 12, 5, 5, scale);
      BodyPart1.setRotationPoint(0F, 9F, 0F);
      BodyPart1.setTextureSize(64, 32);
      BodyPart1.mirror = true;
      setRotation(BodyPart1, 0F, 0F, 0F);
      BodyPart2 = new ModelRenderer(this, 22, 18);
      BodyPart2.addBox(-7.5F, -6F, -3.3F, 15, 6, 6, scale);
      BodyPart2.setRotationPoint(0F, 4F, 0F);
      BodyPart2.setTextureSize(64, 32);
      BodyPart2.mirror = true;
      setRotation(BodyPart2, 0F, 0F, 0F);
      LeftShoulder = new ModelRenderer(this, 0, 20);
      LeftShoulder.addBox(-5F, -2.5F, -2.5F, 5, 5, 5, scale);
      LeftShoulder.setRotationPoint(-7.5F, 1F, -0.2F);
      LeftShoulder.setTextureSize(64, 32);
      LeftShoulder.mirror = true;
      setRotation(LeftShoulder, 0F, 0F, 0F);
      RightShoulder = new ModelRenderer(this, 0, 20);
      RightShoulder.addBox(0F, -2.5F, -2.5F, 5, 5, 5, scale);
      RightShoulder.setRotationPoint(7.5F, 1F, -0.2F);
      RightShoulder.setTextureSize(64, 32);
      RightShoulder.mirror = true;
      setRotation(RightShoulder, 0F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 48, 30);
      LeftArm.addBox(-2F, 0F, -2F, 4, 7, 4, scale);
      LeftArm.setRotationPoint(-10F, 3.5F, -0.2F);
      LeftArm.setTextureSize(64, 32);
      LeftArm.mirror = true;
      setRotation(LeftArm, 0F, 0F, 0F);
      LeftArm2 = new ModelRenderer(this, 36, 30);
      LeftArm2.addBox(-1.5F, 0F, -1.5F, 3, 7, 3, scale);
      LeftArm2.setRotationPoint(-10F, 10.5F, -0.2F);
      LeftArm2.setTextureSize(64, 32);
      LeftArm2.mirror = true;
      setRotation(LeftArm2, 0F, 0F, 0F);
      RightArm1 = new ModelRenderer(this, 4, 30);
      RightArm1.addBox(-2F, 0F, -2F, 4, 8, 4, scale);
      RightArm1.setRotationPoint(10F, 3.5F, -0.2F);
      RightArm1.setTextureSize(64, 32);
      RightArm1.mirror = true;
      setRotation(RightArm1, 0F, 0F, 0F);
      RightArm2 = new ModelRenderer(this, 4, 42);
      RightArm2.addBox(-2F, 0F, -2F, 4, 6, 4, scale);
      RightArm2.setRotationPoint(10F, 11.5F, -0.2F);
      RightArm2.setTextureSize(64, 32);
      RightArm2.mirror = true;
      setRotation(RightArm2, 0F, 0F, 0F);
      RightArm3 = new ModelRenderer(this, 20, 30);
      RightArm3.addBox(-2.5F, 0F, -2.5F, 5, 5, 5, scale);
      RightArm3.setRotationPoint(10F, 17.5F, -0.2F);
      RightArm3.setTextureSize(64, 32);
      RightArm3.mirror = true;
      setRotation(RightArm3, 0F, 0F, 0F);
      Jaw = new ModelRenderer(this, 0, 0);
      Jaw.addBox(-3F, -5F, -4F, 6, 5, 6, scale);
      Jaw.setRotationPoint(0F, -2F, 0F);
      Jaw.setTextureSize(64, 32);
      Jaw.mirror = true;
      setRotation(Jaw, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    RightLeg.render(f5);
    RightLegBottom.render(f5);
    LeftLeg.render(f5);
    LeftLegBottom.render(f5);
    BodyBottom.render(f5);
    BodyPart1.render(f5);
    BodyPart2.render(f5);
    LeftShoulder.render(f5);
    RightShoulder.render(f5);
    LeftArm.render(f5);
    LeftArm2.render(f5);
    RightArm1.render(f5);
    RightArm2.render(f5);
    RightArm3.render(f5);
    Jaw.render(f5);
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
	
    this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.25F;
    this.RightArm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F;
    
    this.RightLegBottom.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.25F;
    this.LeftLegBottom.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F;
    
    float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
    
    if(this.LeftArm.rotateAngleX > 0)
    {
    	this.LeftArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.25F;
    }
    else
    {
    	this.LeftArm2.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.25F);
    }
    
    if(this.RightArm1.rotateAngleX > 0)
    {
    	this.RightArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F;
    	this.RightArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F;
    }
    else
    {
    	this.RightArm2.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F);
    	this.RightArm3.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.25F);
    }
    
    if (this.swingProgress > 0.0F)
    {
    	if(this.swingProgress <= 0.1F)
    	{
    		this.RightArm3.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -45F * 0.017453292F;
    		this.RightArm1.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -45F * 0.017453292F;
    		this.RightArm2.rotateAngleX = MathHelper.sin(this.swingProgress / 0.1F * 0.017453292F) * -135F * 0.017453292F;
    	}
    	else if(this.swingProgress <= 0.3F)
    	{
    		float progress = 1F - (this.swingProgress - 0.1F) / 0.2F;
    		
    		this.RightArm3.rotateAngleX = (-90F + (45 * progress)) * 0.017453292F;
    		this.RightArm2.rotateAngleX = (-90F + (45 * progress)) * 0.017453292F;
    		this.RightArm1.rotateAngleX = (-45F - (45 * progress)) * 0.017453292F;
    	}
    	else
    	{
    		float progress = 1 - (this.swingProgress - 0.3F) / 0.7F;
    		this.RightArm3.rotateAngleX = progress * -90F * 0.017453292F;
    		this.RightArm2.rotateAngleX = progress * -90F * 0.017453292F;
    		this.RightArm1.rotateAngleX = progress * -90F * 0.017453292F;
    	}
    }
    
    this.Jaw.rotateAngleY = netHeadYaw * 0.017453292F;
    this.Jaw.rotateAngleX = headPitch * 0.017453292F + 0.1858931F;
    

    
    if(entityIn instanceof EntityNChauvoput)
    {
    	EntityNChauvoput ent = (EntityNChauvoput) entityIn;
    	
    	if(ent.getUsedAttack() == "object.chauvoput.explosion")
    	{
    		float progress = (((float)ent.getTimer() + partialTicks) / 20);
    		if(progress < 0.25F)
    		{
    			this.Jaw.rotateAngleX = progress * 4 * 60 * 0.017453292F;
    		}
    	}
    	else if(ent.getUsedAttack() == "object.chauvoput.lava_attack" && ent.getTarget() != null)
    	{
    		float progress = (((float)ent.getTimer() - partialTicks) / 40);
    		
    		if(progress < 0.25F)
    		{
    			this.LeftShoulder.rotateAngleX = progress * 4 * -50 * 0.017453292F;
    			this.LeftArm.rotateAngleX = progress * 4 * -50 * 0.017453292F;
    			this.LeftArm2.rotateAngleX = progress * 4 * -135 * 0.017453292F;
    			
    			this.RightShoulder.rotateAngleX = progress * 4 * -32.5F * 0.017453292F;
    			this.RightArm1.rotateAngleX = progress * 4 * -35.5F * 0.017453292F;
    			this.RightArm2.rotateAngleX = progress * 4 * -110.5F * 0.017453292F;
    			this.RightArm3.rotateAngleX = progress * 4 * -110.5F * 0.017453292F;
    		}
    		else if(progress < 0.75F)
    		{
    			this.LeftShoulder.rotateAngleX = -50 * 0.017453292F;
    			this.LeftArm.rotateAngleX = -50 * 0.017453292F;
    			this.LeftArm2.rotateAngleX = -135 * 0.017453292F;
    			
    			this.RightShoulder.rotateAngleX = -32.5F * 0.017453292F;
    			this.RightArm1.rotateAngleX = -35.5F * 0.017453292F;
    			this.RightArm2.rotateAngleX = -110.5F * 0.017453292F;
    			this.RightArm3.rotateAngleX = -110.5F * 0.017453292F;
    		}
    		else
    		{

    			this.LeftShoulder.rotateAngleX = (1 - progress) * 4 * -50 * 0.017453292F;
    			this.LeftArm.rotateAngleX = (1 - progress) * 4 * -50 * 0.017453292F;
    			this.LeftArm2.rotateAngleX = (1 - progress) * 4 * -135 * 0.017453292F;
    			
    			this.RightShoulder.rotateAngleX = (1 - progress) * 4 * -32.5F * 0.017453292F;
    			this.RightArm1.rotateAngleX = (1 - progress) * 4 * -35.5F * 0.017453292F;
    			this.RightArm2.rotateAngleX = (1 - progress) * 4 * -110.5F * 0.017453292F;
    			this.RightArm3.rotateAngleX = (1 - progress) * 4 * -110.5F * 0.017453292F;
    		}
    		
    	}
    	else if(ent.getUsedAttack() == "object.chauvoput.earthquake" && !ent.onGround)
    	{
    		if(ent.getTimer() <= 80 && ent.getTimer() >= 70)
    		{
    			float progress = 1.0F - (((float)ent.getTimer() - 70 - partialTicks) / 10);
    			

        		this.LeftLegBottom.rotateAngleX = progress * 90 * 0.017453292F;
        		this.RightLegBottom.rotateAngleX = progress * 45 * 0.017453292F;
        		
        		this.LeftShoulder.rotateAngleX = progress * 50 * 0.017453292F;
    			this.LeftArm.rotateAngleX = progress * 50 * 0.017453292F;
    			this.LeftArm2.rotateAngleX = progress * -45 * 0.017453292F;
    			
    			this.RightShoulder.rotateAngleX = progress * 50.5F * 0.017453292F;
    			this.RightArm1.rotateAngleX = progress * 50.5F * 0.017453292F;
    			this.RightArm2.rotateAngleX = progress * -45.5F * 0.017453292F;
    			this.RightArm3.rotateAngleX = progress * -45.5F * 0.017453292F;
    		}
    		else
    		{
        		this.LeftLegBottom.rotateAngleX = 90 * 0.017453292F;
        		this.RightLegBottom.rotateAngleX = 45 * 0.017453292F;
        		
        		this.LeftShoulder.rotateAngleX = 50 * 0.017453292F;
    			this.LeftArm.rotateAngleX = 50 * 0.017453292F;
    			this.LeftArm2.rotateAngleX = -45 * 0.017453292F;
    			
    			this.RightShoulder.rotateAngleX = 50.5F * 0.017453292F;
    			this.RightArm1.rotateAngleX = 50.5F * 0.017453292F;
    			this.RightArm2.rotateAngleX = -45.5F * 0.017453292F;
    			this.RightArm3.rotateAngleX = -45.5F * 0.017453292F;
    		}
    	}
    	else if(ent.getUsedAttack() == "object.chauvoput.spikes")
    	{
    		this.RightShoulder.rotateAngleX = -87 * 0.017453292F;
			this.RightArm1.rotateAngleX = -87 * 0.017453292F;
			this.RightArm2.rotateAngleX = -185 * 0.017453292F;
			this.RightArm3.rotateAngleX = -185 * 0.017453292F;
    	}
    	else if(ent.getUsedAttack() == "object.chauvoput.charge")
    	{

			this.LeftShoulder.rotateAngleX = -27 * 0.017453292F;
			this.LeftArm.rotateAngleX = -27 * 0.017453292F;
			this.LeftArm2.rotateAngleX = -165 * 0.017453292F;
			
			this.RightShoulder.rotateAngleX = -27 * 0.017453292F;
			this.RightArm1.rotateAngleX = -27 * 0.017453292F;
			this.RightArm2.rotateAngleX = -165 * 0.017453292F;
			this.RightArm3.rotateAngleX = -165 * 0.017453292F;
    	}
    }
    
    
    //this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1F * 0.6662F) * 2.0F * limbSwingAmount * 4F;
    //this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1F * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 4F;
	
    this.BodyPart2.rotationPointY = 9 - MathHelper.cos(this.BodyPart1.rotateAngleX) * 5;
    this.BodyPart2.rotationPointZ = -MathHelper.sin(this.BodyPart1.rotateAngleX) * 5;
    
    this.Jaw.rotationPointY = this.BodyPart2.rotationPointY - MathHelper.cos(this.BodyPart2.rotateAngleX) * 6;
    this.Jaw.rotationPointZ = this.BodyPart2.rotationPointZ - MathHelper.sin(this.BodyPart2.rotateAngleX) * 6;
    
    this.RightShoulder.rotationPointZ = this.BodyPart2.rotationPointZ - 0.2F - MathHelper.sin(this.BodyPart2.rotateAngleX) * 3;
    this.LeftShoulder.rotationPointZ = this.BodyPart2.rotationPointZ - 0.2F - MathHelper.sin(this.BodyPart2.rotateAngleX) * 3;
    this.RightShoulder.rotationPointY = this.BodyPart2.rotationPointY - 3F * MathHelper.cos(this.BodyPart2.rotateAngleX);
    this.LeftShoulder.rotationPointY = this.BodyPart2.rotationPointY - 3F * MathHelper.cos(this.BodyPart2.rotateAngleX);
    
    this.LeftArm.rotationPointY = this.LeftShoulder.rotationPointY + 2.5F * MathHelper.cos(this.LeftShoulder.rotateAngleX);
    this.LeftArm.rotationPointZ = this.LeftShoulder.rotationPointZ + 2.5F * MathHelper.sin(this.LeftShoulder.rotateAngleX);
    
    this.LeftArm2.rotationPointY = this.LeftArm.rotationPointY + 7F * MathHelper.cos(this.LeftArm.rotateAngleX);
    this.LeftArm2.rotationPointZ = this.LeftArm.rotationPointZ + 7F * MathHelper.sin(this.LeftArm.rotateAngleX);
    
    //this.RightArm1.rotationPointX = this.RightShoulder.rotationPointX + 2.5F * MathHelper.sin(this.RightShoulder.rotateAngleY) * MathHelper.sin(this.RightShoulder.rotateAngleX);
    this.RightArm1.rotationPointY = this.RightShoulder.rotationPointY + 2.5F * MathHelper.cos(this.RightShoulder.rotateAngleX);
    this.RightArm1.rotationPointZ = this.RightShoulder.rotationPointZ + 2.5F * MathHelper.sin(this.RightShoulder.rotateAngleX);
    
    this.RightArm2.rotationPointX = this.RightArm1.rotationPointX + 8F * MathHelper.sin(this.RightArm1.rotateAngleY) * MathHelper.sin(this.RightArm1.rotateAngleX);
    this.RightArm2.rotationPointY = this.RightArm1.rotationPointY + 8F * MathHelper.cos(this.RightArm1.rotateAngleX);
    this.RightArm2.rotationPointZ = this.RightArm1.rotationPointZ + 8F * MathHelper.sin(this.RightArm1.rotateAngleX) * MathHelper.cos(this.RightArm1.rotateAngleY);
    
    this.RightArm3.rotationPointX = this.RightArm2.rotationPointX + 6F * MathHelper.sin(this.RightArm2.rotateAngleY) * MathHelper.sin(this.RightArm2.rotateAngleX);
    this.RightArm3.rotationPointY = this.RightArm2.rotationPointY + 6F * MathHelper.cos(this.RightArm2.rotateAngleX);
    this.RightArm3.rotationPointZ = this.RightArm2.rotationPointZ + 6F * MathHelper.sin(this.RightArm2.rotateAngleX) * MathHelper.cos(this.RightArm2.rotateAngleY);
    
    this.BodyPart1.rotateAngleX = 7.5F * 0.017453292F;
    this.BodyPart2.rotateAngleX = 15F * 0.017453292F;
    
    this.RightLegBottom.rotationPointX = 1.2F + this.RightLeg.rotationPointX - 7 * MathHelper.sin(this.RightLeg.rotationPointZ);
    this.RightLegBottom.rotationPointY = this.RightLeg.rotationPointY - 7 * MathHelper.cos(this.RightLeg.rotationPointZ) * MathHelper.cos(this.RightLeg.rotationPointX);
    this.RightLegBottom.rotationPointZ = this.RightLeg.rotationPointZ + 7 * MathHelper.sin(this.RightLeg.rotateAngleX);
    
    this.LeftLegBottom.rotationPointX = this.LeftLeg.rotationPointX - 7 * MathHelper.sin(this.LeftLeg.rotationPointZ) - 1.2F;
    this.LeftLegBottom.rotationPointY = this.LeftLeg.rotationPointY - 7 * MathHelper.cos(this.LeftLeg.rotationPointZ) * MathHelper.cos(this.RightLeg.rotationPointX);
    this.LeftLegBottom.rotationPointZ = this.LeftLeg.rotationPointZ + 7 * MathHelper.sin(this.LeftLeg.rotateAngleX);
    //this.LeftShoulder.rotateAngleX = -23F * 0.017453292F;
  }

}
