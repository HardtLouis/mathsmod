package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRagdoll;
import com.mod.mathsmod.add.Entity.Models.ModelRagdoll;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;

public class RenderRagdoll extends EasyRenderEntity<EntityRagdoll> 
{

	public RenderRagdoll(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelRagdoll(0, true), 0.0F);
	}
	
	public void doRender(EntityRagdoll entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		this.mainModel = new ModelRagdoll(0, TextureHelper.smallArms(entity.getCustomNameTag()));
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityRagdoll entity)
	{
		if(entity.getCustomNameTag() == null || entity.getCustomNameTag().isEmpty())
		{
			return DefaultPlayerSkin.getDefaultSkinLegacy();
		}
		return TextureHelper.getPlayerSkin(entity.getCustomNameTag());
	}

	@Override
	protected void preRenderCallback(EntityRagdoll entity, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(90, 1, 0, 0);
		GlStateManager.translate(0, 0.6, 0.1);
    }
}
