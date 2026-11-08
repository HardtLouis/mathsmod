package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.add.event.PlayerRenderManager;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelDabArmor extends ModelBiped
{

	public ModelDabArmor(float modelSize) 
	{
		super(modelSize);
	}

	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
 
        this.bipedRightArm.rotateAngleX = -95 * 0.017453292F;
        this.bipedRightArm.rotateAngleY = -57 * 0.017453292F;
        this.bipedRightArm.rotateAngleZ = 0;
        
        this.bipedLeftArm.rotateAngleX = -110 * 0.017453292F;
        this.bipedLeftArm.rotateAngleY = -87 * 0.017453292F;
        this.bipedLeftArm.rotateAngleZ = 0;
        
        this.bipedHead.rotateAngleX = 36 * 0.017453292F;
        this.bipedHead.rotateAngleY = 0;
        this.bipedHead.rotateAngleZ = 0;
        this.bipedHeadwear.rotateAngleX = 36 * 0.017453292F;
        this.bipedHeadwear.rotateAngleY = 0;
        this.bipedHeadwear.rotateAngleZ = 0;
        
        if(entityIn instanceof EntityPlayer && PlayerRenderManager.isUsingSpell((EntityPlayer) entityIn))
        {
        	PlayerRenderManager.tryModifyRendererForSpell(this, (EntityPlayer) entityIn);
        }
    }
}