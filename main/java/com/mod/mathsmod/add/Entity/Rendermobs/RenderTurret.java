package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurret;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTurretAuto;
import com.mod.mathsmod.add.Entity.Models.ModelTurretBase;
import com.mod.mathsmod.add.Entity.Models.ModelTurretCanon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTurret extends EasyRenderEntity<EntityTurret> {

	public RenderTurret(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTurretBase(), 0);
	}
	
	protected void renderModel(EntityTurret entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        boolean flag = this.isVisible(entitylivingbaseIn);
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
        	
        	GlStateManager.translate(0, -1.5, 0);
        	GlStateManager.scale(2, 2, 2);
        	if(entitylivingbaseIn instanceof EntityTurretAuto)
        	{
        		float healthColor = ((EntityTurretAuto) entitylivingbaseIn).getHealth() / ((EntityTurretAuto) entitylivingbaseIn).getMaxHealth();
        		GlStateManager.color(((EntityTurretAuto) entitylivingbaseIn).getActivated() ? ((EntityTurretAuto) entitylivingbaseIn).tempColor() : 1, 0, (float) (1 * (healthColor * 0.5) - ((EntityTurretAuto) entitylivingbaseIn).tempColor()));
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
            
            GlStateManager.rotate(entitylivingbaseIn.rotationYaw + 180, 0, 1, 0);
            new ModelTurretCanon().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

            if (flag1)
            {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityTurret entity)
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/tileentity/turret.png");
	}

}
