package com.mod.mathsmod.add.Entity.Models;

import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelHunter extends ModelBiped
{

	    public ModelHunter(float modelSize) 
	    {
	    	super(modelSize);
	    }
	    
	    public ModelHunter(float modelSize, int height, int width)
	    {
	        super(modelSize, 0.0F, height, width);
	    }
	 
	    @Override
	    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	    {
	        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	        
	        if(entityIn instanceof EntityLivingBase)
	        {
	        	if(((EntityLivingBase) entityIn).getHeldItemMainhand().getItem() instanceof ItemGun)
	        	{
	        		ItemGun gun = (ItemGun) ((EntityLivingBase) entityIn).getHeldItemMainhand().getItem();
					ItemStack stack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
					float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
	        		
	        		if(gun != null)
	    	        {
	        			if((!gun.scopeToShoot()) || gun.getUsed(((EntityLivingBase) entityIn).getHeldItemMainhand()) != 0)
	                	{
	        				if(gun.rightArmRotation(entityIn, stack, partialTicks) != null)
		    	        	{
		    	        		this.bipedRightArm.rotateAngleX = gun.rightArmRotation(entityIn, stack, partialTicks).x + headPitch * 0.017453292F;
		    	            	this.bipedRightArm.rotateAngleY = gun.rightArmRotation(entityIn, stack, partialTicks).y + netHeadYaw * 0.017453292F;
		    	            	this.bipedRightArm.rotateAngleZ = gun.rightArmRotation(entityIn, stack, partialTicks).z;
		    	        	}
		    	        	if(gun.leftArmRotation(entityIn, stack, partialTicks) != null)
		    	        	{
		    	        		this.bipedLeftArm.rotateAngleX = gun.leftArmRotation(entityIn, stack, partialTicks).x + headPitch * 0.017453292F;
		    	            	this.bipedLeftArm.rotateAngleY = gun.leftArmRotation(entityIn, stack, partialTicks).y + netHeadYaw * 0.017453292F;
		    	            	this.bipedLeftArm.rotateAngleZ = gun.leftArmRotation(entityIn, stack, partialTicks).z;
		    	        	}
	                	}
	        			else if(!gun.isPistol())
	        			{
	        				this.bipedRightArm.rotateAngleX = -63 * 0.017453292F;
	                    	this.bipedRightArm.rotateAngleY = -51 * 0.017453292F;
	                    	this.bipedLeftArm.rotateAngleX = -55 * 0.017453292F;
	        			}
	        			
	    	        }
	        	}
	        }
	    }
}
