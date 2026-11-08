package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.add.event.PlayerRenderManager;
import com.mod.mathsmod.add.items.ICustomAnimation;
import com.mod.mathsmod.add.items.guns.ItemGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelArmorAnim extends ModelBiped
{
	 
		static ItemGun gun;
		
	    public ModelArmorAnim(float modelSize, ItemGun gunIn) 
	    {
	    	super(modelSize);
	    	gun = gunIn;
	    }
	 
	    @Override
	    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	    {
	        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
	 
	        if(gun != null && entityIn instanceof EntityPlayer)
	        {
	        	ItemStack stack = ((EntityPlayer) entityIn).getHeldItemMainhand();

	        	if(gun.Animated(stack, (EntityPlayer) entityIn))
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
	        	else
	        	{
	        		this.bipedRightArm.rotateAngleX = -63 * 0.017453292F;
	            	this.bipedRightArm.rotateAngleY = -51 * 0.017453292F;
	            	this.bipedLeftArm.rotateAngleX = -55 * 0.017453292F;
	        	}
	        }
	        
	        if(entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getHeldItemMainhand().getItem() instanceof ICustomAnimation)
	        {
	        	((ICustomAnimation) ((EntityPlayer) entityIn).getHeldItemMainhand().getItem()).renderThirdPersonPre(((EntityPlayer) entityIn).getHeldItemMainhand(), (EntityLivingBase) entityIn, partialTicks, limbSwing, limbSwingAmount, this);
	        }
	        
	        if(entityIn instanceof EntityPlayer && PlayerRenderManager.isUsingSpell((EntityPlayer) entityIn))
	        {
	        	PlayerRenderManager.tryModifyRendererForSpell(this, (EntityPlayer) entityIn);
	        }
	        
	    }
}