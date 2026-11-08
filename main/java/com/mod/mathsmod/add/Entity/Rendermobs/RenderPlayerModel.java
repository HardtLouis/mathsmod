package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPlayerModel extends EasyRenderEntity<Entity>
{
	
	String name;
	public boolean frozen;

	public RenderPlayerModel(RenderManager renderManagerIn, String nameIn, boolean smallArms) 
	{
		super(renderManagerIn, new ModelPlayer(0, smallArms), 0);
		name = nameIn;
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
    {
		return TextureHelper.getPlayerSkin(name);
    }
	
	protected void preRenderCallback(Entity entitylivingbaseIn, float partialTickTime)
    {
		this.getMainModel().isChild = false;
		if(frozen)
		{
			GlStateManager.color(200, 200, 255);
		}
		else
		{
			GlStateManager.color(155, 155, 0);
		}
    }

}
