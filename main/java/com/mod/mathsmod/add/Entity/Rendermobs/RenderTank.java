package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityDrillingMachine;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Models.modelTank.ModelTank;
import com.mod.mathsmod.add.Entity.Models.modelTank.ModelTank2;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTank extends EasyRenderEntity<EntityTank> {

	public RenderTank(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTank(), 0);
	}
	
	protected void renderModel(EntityTank entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        boolean flag = this.isVisible(entitylivingbaseIn);
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
        	if(entitylivingbaseIn.getExplode() != 0)
        	{
        		GlStateManager.color(1, 0, 0);
        	}
            if (!this.bindEntityTexture(entitylivingbaseIn))
            {
                return;
            }

            if (flag1)
            {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            
            this.renderManager.renderEngine.bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/entity/tank/tank_2.png"));

            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = entitylivingbaseIn.dis;
            GlStateManager.translate(0.0F, f * -0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            //GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            
            new ModelTank2().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);

            if (flag1)
            {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityTank entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/tank/tank.png");
	}
	
	protected void preRenderCallback(EntityTank entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.scale(5, 5, 5);
    }

}
