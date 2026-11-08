package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.Entity.Entity.objects.EntityRagdoll;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class ModelRagdoll extends ModelPlayer
{
		
	    public ModelRagdoll(float modelSize, boolean smallArmsIn) 
	    {
	    	super(modelSize, smallArmsIn);
	    }
	    
	    public ModelRagdoll(float modelSize, boolean smallArmsIn, int height, int width) 
	    {
	        this(modelSize, smallArmsIn);
	        this.textureHeight = height;
	        this.textureWidth = width;
	    }
	 
	    @Override
	    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	    {
	        //super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	        
	        if(entityIn instanceof EntityRagdoll)
	        {
	        	EntityRagdoll entity = (EntityRagdoll) entityIn;
	        	Vec3d vecHead = UtilsVoids.rotateVec(entity.partArray[0].getPositionVector().subtract(entity.getPositionVector()), 0, 0, 0);
	        	if(vecHead.lengthVector() != 0)
	        	{
		        	this.bipedHead.rotateAngleZ = (float) Math.asin(vecHead.x / vecHead.lengthVector());
		        	this.bipedHead.rotateAngleX = (float) Math.asin(vecHead.y / vecHead.lengthVector());
	        	}
	        	
	        	Vec3d larm = UtilsVoids.rotateVec(entity.partArray[1].getPositionVector().subtract(entity.getPositionVector()), 0, 0, 0);
	        	if(larm.lengthVector() != 0)
	        	{
		        	this.bipedLeftArm.rotateAngleZ = -(float) Math.asin(larm.x / larm.lengthVector());
		        	this.bipedLeftArm.rotateAngleX = -(float) Math.asin(larm.y / larm.lengthVector());
		        	this.bipedRightArm.rotateAngleZ = -(float) Math.asin(larm.x / larm.lengthVector());
		        	this.bipedRightArm.rotateAngleX = -(float) Math.asin(larm.y / larm.lengthVector());
		        	this.bipedLeftLeg.rotateAngleZ = -(float) Math.asin(larm.x / larm.lengthVector());
		        	this.bipedLeftLeg.rotateAngleX = -(float) Math.asin(larm.y / larm.lengthVector());
		        	this.bipedRightLeg.rotateAngleZ = -(float) Math.asin(larm.x / larm.lengthVector());
		        	this.bipedRightLeg.rotateAngleX = -(float) Math.asin(larm.y / larm.lengthVector());
	        	}
	        }
	        
	        copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
	        copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
	        copyModelAngles(this.bipedBody, this.bipedBodyWear);
	        copyModelAngles(this.bipedHead, this.bipedHeadwear);
	        copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
	        copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
	        
	    }
	}