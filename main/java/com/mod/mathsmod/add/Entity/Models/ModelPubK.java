package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.NBoss.EntityPub;
import com.mod.mathsmod.utils.RenderLaser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ModelPubK extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body1U;
    ModelRenderer body1D;
    ModelRenderer rightarm;
    ModelRenderer rightarmP2;
    ModelRenderer rightleg;
    ModelRenderer rightlegP2;
    ModelRenderer Horn1;
    ModelRenderer Horn1P2;
    ModelRenderer Horn2;
    ModelRenderer leftarm;
    ModelRenderer leftarmP2;
    ModelRenderer leftleg;
    ModelRenderer leftlegP2;
    ModelRenderer CapeGauche;
    ModelRenderer CapeDroite;
  
  public ModelPubK()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, -8F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body1U = new ModelRenderer(this, 16, 16);
      body1U.addBox(-4F, -6F, -2F, 8, 6, 4);
      body1U.setRotationPoint(0F, -2F, 0F);
      body1U.setTextureSize(64, 64);
      body1U.mirror = true;
      setRotation(body1U, 0F, 0F, 0F);
      body1D = new ModelRenderer(this, 16, 26);
      body1D.addBox(-4F, 0F, -2F, 8, 6, 4);
      body1D.setRotationPoint(0F, -2F, 0F);
      body1D.setTextureSize(64, 64);
      body1D.mirror = true;
      setRotation(body1D, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-2F, -1F, -1F, 2, 11, 2);
      rightarm.setRotationPoint(-4F, -6F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      rightarmP2 = new ModelRenderer(this, 48, 16);
      rightarmP2.addBox(-1F, 0F, -1F, 2, 11, 2);
      rightarmP2.setRotationPoint(-5F, 4F, 0F);
      rightarmP2.setTextureSize(64, 64);
      rightarmP2.mirror = true;
      setRotation(rightarmP2, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
      rightleg.setRotationPoint(-2F, 4F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      rightlegP2 = new ModelRenderer(this, 0, 29);
      rightlegP2.addBox(-1F, 0F, -1F, 2, 11, 2);
      rightlegP2.setRotationPoint(-2F, 13F, 0F);
      rightlegP2.setTextureSize(64, 64);
      rightlegP2.mirror = true;
      setRotation(rightlegP2, 0F, 0F, 0F);
      Horn1 = new ModelRenderer(this, 32, 0);
      Horn1.addBox(0F, 0F, 0F, 5, 2, 2);
      Horn1.setRotationPoint(2F, -15F, -1F);
      Horn1.setTextureSize(64, 64);
      Horn1.mirror = true;
      setRotation(Horn1, 0F, 0.5585054F, -0.5948578F);
      Horn1P2 = new ModelRenderer(this, 32, 4);
      Horn1P2.addBox(0F, -1F, -1F, 5, 2, 2);
      Horn1P2.setRotationPoint(6F, -17F, -2F);
      Horn1P2.setTextureSize(64, 64);
      Horn1P2.mirror = true;
      setRotation(Horn1P2, 0.0743572F, 1.189716F, 0.1487144F);
      Horn2 = new ModelRenderer(this, 32, 0);
      Horn2.addBox(0F, 0F, -2F, 5, 2, 2);
      Horn2.setRotationPoint(-2F, -15F, -1F);
      Horn2.setTextureSize(64, 64);
      Horn2.mirror = true;
      setRotation(Horn2, 0F, 2.75762F, -0.5948606F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(0F, -1F, -1F, 2, 11, 2);
      leftarm.setRotationPoint(4F, -6F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      leftarmP2 = new ModelRenderer(this, 48, 16);
      leftarmP2.addBox(-1F, 0F, -1F, 2, 11, 2);
      leftarmP2.setRotationPoint(5F, 4F, 0F);
      leftarmP2.setTextureSize(64, 64);
      leftarmP2.mirror = true;
      setRotation(leftarmP2, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
      leftleg.setRotationPoint(2F, 4F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      leftlegP2 = new ModelRenderer(this, 0, 29);
      leftlegP2.addBox(-1F, 0F, -1F, 2, 11, 2);
      leftlegP2.setRotationPoint(2F, 13F, 0F);
      leftlegP2.setTextureSize(64, 64);
      leftlegP2.mirror = true;
      setRotation(leftlegP2, 0F, 0F, 0F);
      CapeGauche = new ModelRenderer(this, 56, 16);
      CapeGauche.addBox(-1F, 0F, -0.5F, 3, 16, 1);
      CapeGauche.setRotationPoint(1F, -7F, 2.5F);
      CapeGauche.setTextureSize(64, 64);
      CapeGauche.mirror = true;
      setRotation(CapeGauche, 0F, 0F, 0F);
      CapeDroite = new ModelRenderer(this, 56, 16);
      CapeDroite.addBox(-2F, 0F, -0.5F, 3, 16, 1);
      CapeDroite.setRotationPoint(-1F, -7F, 2.5F);
      CapeDroite.setTextureSize(64, 64);
      CapeDroite.mirror = true;
      setRotation(CapeDroite, 0F, 0F, 0F);
      RenderLaser.registerJoint("ModelPub_BodyUpper", this.body1D, this.body1U);
      RenderLaser.registerJoint("ModelPub_BodyUpper_RArm", this.body1U, this.rightarm);
      RenderLaser.registerJoint("ModelPub_BodyUpper_LArm", this.body1U, this.leftarm);
      RenderLaser.registerJoint("ModelPub_RArm", this.rightarm, this.rightarmP2);
      RenderLaser.registerJoint("ModelPub_LArm", this.leftarm, this.leftarmP2);
      RenderLaser.registerJoint("ModelPub_BodyUpper_Head", this.body1U, this.head);
      RenderLaser.registerJoint("ModelPub_Horn1", this.head, this.Horn1);
      RenderLaser.registerJoint("ModelPub_Horn1P2", this.Horn1, this.Horn1P2);
      RenderLaser.registerJoint("ModelPub_Horn2", this.head, this.Horn2);
      RenderLaser.registerJoint("ModelPub_CapeG", this.body1U, this.CapeGauche);
      RenderLaser.registerJoint("ModelPub_CapeD", this.body1U, this.CapeDroite);
      RenderLaser.registerJoint("ModelPub_RLeg", this.rightleg, this.rightlegP2);
      RenderLaser.registerJoint("ModelPub_LLeg", this.leftleg, this.leftlegP2);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body1U.render(f5);
    body1D.render(f5);
    rightarm.render(f5);
    rightarmP2.render(f5);
    rightleg.render(f5);
    rightlegP2.render(f5);
    //Horn1.render(f5);
    //Horn1P2.render(f5);
    //Horn2.render(f5);
    leftarm.render(f5);
    leftarmP2.render(f5);
    leftleg.render(f5);
    leftlegP2.render(f5);
    CapeGauche.render(f5);
    CapeDroite.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    this.setRotationAnglesP(f, f1, f2, f3, f4, f5, (EntityPub) entity);
  }
  
  	public void setRotationAnglesP(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, EntityPub entityIn)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		
		//Vec3d vec = Utils.laser(, entityIn.rotationYaw, 0);
		
		
		
  		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F + 0.1858931F;
		//this.setRotation(Horn1, head.rotateAngleX, 0.5585054F + head.rotateAngleY, -0.5948578F + head.rotateAngleZ);
		//this.setRotation(Horn1P2, 0.0743572F + head.rotateAngleX, 1.189716F + head.rotateAngleY, -0.5948606F + head.rotateAngleZ);
		//this.setRotation(Horn2, 0.0743572F + head.rotateAngleX, 2.75762F + head.rotateAngleY, -0.5948606F + head.rotateAngleZ);
		this.body1U.rotateAngleX = 10 * 0.0174532925199F;
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
		float armRotZ = entityIn.getPrepareTime() / 10 * 8 *  0.0174532925199F;
		float armRotX = entityIn.getPrepareTime() / 10 * -45 *  0.0174532925199F;
		

	    this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		
		this.rightlegP2.rotateAngleX = MathHelper.clamp(rightleg.rotateAngleX * 3, 0, 69);
		this.leftlegP2.rotateAngleX = MathHelper.clamp(leftleg.rotateAngleX * 3, 0, 69);
		
		this.CapeGauche.rotateAngleX = (float) (5 * 0.0174532925199F + MathHelper.clamp(entityIn.angleCape, 0, 110) *  0.0174532925199F);
		this.CapeDroite.rotateAngleX = (float) (5 * 0.0174532925199F + MathHelper.clamp(entityIn.angleCape, 0, 110) *  0.0174532925199F);
		this.CapeGauche.rotateAngleY = (float) (-0.1F * MathHelper.clamp(entityIn.angleCape, 0, 110) * 0.0174532925199F);
		this.CapeDroite.rotateAngleY = (float) (0.1F * MathHelper.clamp(entityIn.angleCape, 0, 110) *  0.0174532925199F);
		
		if(armRotZ != 0 || armRotX != 0)
		{
			this.setRotation(rightarm, 0, 0, armRotZ);
			this.setRotation(leftarm, 0, 0, -armRotZ);
			
			this.setRotation(rightarmP2, armRotX, 0, 0);
			this.setRotation(leftarmP2, -61 * 0.0174532925199F, 0, 0);
		}
		else
		{
			//TODO Temp
		    this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		    
		    if(this.rightarm.rotateAngleX > 0)
		    {
		    	this.rightarmP2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		    }
		    else
		    {
		    	this.rightarmP2.rotateAngleX = 3 * (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F);
		    }
		    
		    this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		    
		    if(this.leftarm.rotateAngleX > 0)
		    {
		    	this.leftarmP2.rotateAngleX = -61 * 0.0174532925199F + MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		    }
		    else
		    {
		    	this.leftarmP2.rotateAngleX = -61 * 0.0174532925199F + 3 * (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		    }
			
		}
		
		if(entityIn.getShootTime() - partialTicks > 0)
		{
			float progress = (30.0F - (((float)entityIn.getShootTime()) - partialTicks)) / 30.0F;
			
			if(progress < 0.5F)
			{
				this.leftarm.rotateAngleX = -31 * 0.0174532925199F * progress * 2;
				this.leftarmP2.rotateAngleX = 61 * 0.0174532925199F * (progress) * 2;
			}
			else
			{
				this.leftarm.rotateAngleX = -31 * 0.0174532925199F * (1-progress) * 2;
				this.leftarmP2.rotateAngleX = 61 * 0.0174532925199F * (1-progress) * 2;
			}
		}
		if(entityIn.getProtecTime() - partialTicks > 0)
		{
			float progress = MathHelper.clamp(((float)entityIn.getProtecTime() - partialTicks), 0, 20);
			
			if(progress > 20)
			{
				float p = (2.0F - (progress - 18.0F)) / 2.0F;
				this.setRotation(this.rightarm, -38 * p * 0.0174532925199F, 14 * p * 0.0174532925199F,  40 * p * 0.0174532925199F);
				this.setRotation(this.rightarmP2, -94 * p * 0.0174532925199F, -51 * p * 0.0174532925199F, 0);
			}
			else if(progress < 3)
			{
				float p = (progress) / 3.0F;
				this.setRotation(this.rightarm, -38 * p * 0.0174532925199F, 14 * p * 0.0174532925199F, 40 * p * 0.0174532925199F);
				this.setRotation(this.rightarmP2, -94 * p * 0.0174532925199F, -51 * p * 0.0174532925199F, 0);
			}
			else
			{
				this.setRotation(this.rightarm, -38 * 0.0174532925199F, 14 * 0.0174532925199F, 40 * 0.0174532925199F);
				this.setRotation(this.rightarmP2, -94 * 0.0174532925199F, -51 * 0.0174532925199F, 0);
			}
		}
		
		if(entityIn.getAttackUsed() != null && entityIn.getAttackUsed().objectID() == "attack.pub.charge" && entityIn.getTimer() >= 0)
		{
			float tp = 1;
			
			if(entityIn.getTimer() < 5)
			{
				tp = (((float)entityIn.getTimer()) + partialTicks) / 5.0F;
			}
			
			this.setRotation(this.body1U, this.body1U.rotateAngleX, -25 * tp * 0.0174532925199F,  0);
			this.setRotation(this.rightarm, 48 * tp * 0.0174532925199F, 18 * tp * 0.0174532925199F,  armRotZ + 27 * tp * 0.0174532925199F);
			this.setRotation(this.rightarmP2, 18 * tp * 0.0174532925199F, 18 * tp * 0.0174532925199F,  armRotZ + 27 * tp * 0.0174532925199F);
		}
		
		if(entityIn.getAttackUsed() != null && entityIn.getAttackUsed().objectID() == "attack.pub.spin" && entityIn.getTimer() >= 0)
		{
			float tp = 1;
			
			if(entityIn.getTimer() < 5)
			{
				tp = (((float)entityIn.getTimer()) + partialTicks) / 5.0F;
			}
			
			this.setRotation(this.body1U, this.body1U.rotateAngleX, -25 * tp * 0.0174532925199F,  0);
			this.setRotation(this.rightarm, 48 * tp * 0.0174532925199F, 18 * tp * 0.0174532925199F,  armRotZ + 27 * tp * 0.0174532925199F);
			this.setRotation(this.rightarmP2, 18 * tp * 0.0174532925199F, 18 * tp * 0.0174532925199F,  armRotZ + 27 * tp * 0.0174532925199F);
		}
		
		if(entityIn.getAttackUsed() != null && entityIn.getAttackUsed().objectID() == "attack.pub.jump_attack" && entityIn.getTimer() >= 0)
		{
			float tp = 1;
			
			if(entityIn.getTimer() < 10)
			{
				tp = (((float)entityIn.getTimer()) + partialTicks) / 10.0F;
			}
			
			this.setRotation(this.rightarm, -127 * tp * 0.0174532925199F, 0,  armRotZ - 18 * tp * 0.0174532925199F);
			this.setRotation(this.rightarmP2, -192 * tp * 0.0174532925199F, 0,  armRotZ - 18 * tp * 0.0174532925199F);
		}
		
		if(entityIn.getAttackUsed() != null && entityIn.getAttackUsed().objectID() == "attack.pub.shoot" && entityIn.getTimer() >= 0)
		{
			if(entityIn.getTimer() < 5)
			{
				float t = ((float)(entityIn.getTimer()) + partialTicks) / 5.0F;
				this.setRotation(this.leftarmP2, (-61 - 49 * t)  * 0.0174532925199F + this.head.rotateAngleX * t, this.head.rotateAngleY * t, 0);
			}
			else
			{
				float t = 1;
				float a = 0;
				if(entityIn.getTimer() < 20 && entityIn.getTimer() % 5 == 0)
				{
					a = -20;
				}
				this.setRotation(this.leftarmP2, (-110 + a)  * 0.0174532925199F + this.head.rotateAngleX * t, this.head.rotateAngleY * t, 0);
			}
		}
		
		if(entityIn.getStrikeTime() > 0)
		{
			float progress = MathHelper.clamp(((float)entityIn.getStrikeTime()) - partialTicks, 0, 199);
			float tp = progress / 7.0F;
			
			if(entityIn.getAttackAnim() == 0)
			{
				this.setRotation(this.rightarm, -110 * tp * 0.0174532925199F, -42 * tp * 0.0174532925199F,  armRotZ);
				
				if(progress <= 1)
				{
					this.setRotation(this.rightarmP2, armRotX -95 * tp * 0.0174532925199F, -163 * tp * 0.0174532925199F, 76 * partialTicks);
				}
				else
				{
					this.setRotation(this.rightarmP2, armRotX -95 * tp * 0.0174532925199F, -163 * tp * 0.0174532925199F, 76);
				}
			}
			
			if(entityIn.getAttackAnim() == 3)
			{
				if(progress > 10)
				{
					tp = (1.0F - ((progress - 10.0F) / 5.0F));
					
					this.setRotation(this.body1U, this.body1U.rotateAngleX, (-25.0F + 50.0F * tp) * 0.0174532925199F,  0);
					this.setRotation(this.rightarm, (48.0F - 161.0F * tp) * 0.0174532925199F, 18 * 0.0174532925199F,  (35) * 0.0174532925199F);
					this.setRotation(this.rightarmP2, (18.0F - 161.0F * tp) * 0.0174532925199F, 18 * 0.0174532925199F,   (35) * 0.0174532925199F);
				}
				else if(progress <= 5)
				{
					tp = progress / 5.0F;
					
					this.setRotation(this.body1U, this.body1U.rotateAngleX, (25 * tp) * 0.0174532925199F,  0);
					this.setRotation(this.rightarm, (-113 * tp) * 0.0174532925199F, 18 * tp * 0.0174532925199F, armRotZ + 27 * tp * 0.0174532925199F);
					this.setRotation(this.rightarmP2, (-143.0F * tp) * 0.0174532925199F, 18 * tp * 0.0174532925199F,  armRotZ + 27 * tp * 0.0174532925199F);
				}
				else
				{
					tp = 1;
					this.setRotation(this.body1U, this.body1U.rotateAngleX, (-25.0F + 50.0F * tp) * 0.0174532925199F,  0);
					this.setRotation(this.rightarm, (48.0F - 161.0F * tp) * 0.0174532925199F, 18 * tp  * 0.0174532925199F,  (35) * 0.0174532925199F);
					this.setRotation(this.rightarmP2, (18.0F - 161.0F * tp) * 0.0174532925199F, 18 * tp * 0.0174532925199F,   (35) * 0.0174532925199F);
				}
			}
			
			if(entityIn.getAttackAnim() == 4)
			{
				tp = (progress) / 7.0F;
					
				this.setRotation(this.rightarm, -127 * tp * 0.0174532925199F, 0,  armRotZ - 18 * tp * 0.0174532925199F);
				this.setRotation(this.rightarmP2, -192 * tp * 0.0174532925199F, 0,   armRotZ - 18 * tp * 0.0174532925199F);
			}
		}
		
		
		
		this.Skeleton();
  	}
  
  private void Skeleton()
  {
	  RenderLaser.attachPartTo("ModelPub_BodyUpper", this.body1D, this.body1U);
	  RenderLaser.attachPartTo("ModelPub_BodyUpper_RArm", this.body1U, this.rightarm);
	  RenderLaser.attachPartTo("ModelPub_BodyUpper_LArm", this.body1U, this.leftarm);
	  RenderLaser.attachPartTo("ModelPub_RArm", this.rightarm, this.rightarmP2);
	  RenderLaser.attachPartTo("ModelPub_LArm", this.leftarm, this.leftarmP2);
	  RenderLaser.attachPartTo("ModelPub_BodyUpper_Head", this.body1U, this.head);
	  RenderLaser.attachPartTo("ModelPub_Horn1", this.head, this.Horn1);
	  RenderLaser.attachPartTo("ModelPub_Horn1P2", this.Horn1, this.Horn1P2);
	  RenderLaser.attachPartTo("ModelPub_Horn2", this.head, this.Horn2);
	  RenderLaser.attachPartTo("ModelPub_CapeG", this.body1U, this.CapeGauche);
	  RenderLaser.attachPartTo("ModelPub_CapeD", this.body1U, this.CapeDroite);
	  RenderLaser.attachPartTo("ModelPub_RLeg", this.rightleg, this.rightlegP2);
	  RenderLaser.attachPartTo("ModelPub_LLeg", this.leftleg, this.leftlegP2);
  }

  public void postRenderArm(float f, EnumHandSide p_191361_1_) 
  {
	  if(p_191361_1_ == EnumHandSide.RIGHT)
	  {
		  this.rightarmP2.postRender(0.0625F);
	  }
	  else
	  {
		  this.leftarmP2.postRender(0.0625F);
	  }
  }

}
