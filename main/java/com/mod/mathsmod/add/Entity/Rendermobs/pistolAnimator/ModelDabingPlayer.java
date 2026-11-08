package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.add.event.PlayerRenderManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModelDabingPlayer extends ModelPlayer
{

	public ModelDabingPlayer(float modelSize, boolean smallArmsIn) 
	{
		super(modelSize, smallArmsIn);
	}
    
    public ModelDabingPlayer(float modelSize, boolean smallArmsIn, int height, int width) 
    {
        this(modelSize, smallArmsIn);
        this.textureHeight = height;
        this.textureWidth = width;
    }

	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
        copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
        copyModelAngles(this.bipedBody, this.bipedBodyWear);
 
        this.bipedRightArm.rotateAngleX = -95 * 0.017453292F;
        this.bipedRightArm.rotateAngleY = -57 * 0.017453292F;
        this.bipedRightArm.rotateAngleZ = 0;
        
        this.bipedLeftArm.rotateAngleX = -110 * 0.017453292F;
        this.bipedLeftArm.rotateAngleY = -87 * 0.017453292F;
        this.bipedLeftArm.rotateAngleZ = 0;
        
        this.bipedHead.rotateAngleX = 36 * 0.017453292F;
        this.bipedHead.rotateAngleY = 0;
        this.bipedHead.rotateAngleZ = 0;
        
        copyModelAngles(this.bipedHead, this.bipedHeadwear);
        copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
        copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
        
        if(entityIn instanceof EntityPlayer && PlayerRenderManager.isUsingSpell((EntityPlayer) entityIn))
        {
        	PlayerRenderManager.tryModifyRendererForSpell(this, (EntityPlayer) entityIn);
        }
    }
}
