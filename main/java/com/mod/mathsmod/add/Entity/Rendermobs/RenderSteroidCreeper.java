package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;

public class RenderSteroidCreeper extends RenderCreeper
{
	
	private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/steroid_creeper.png");

	public RenderSteroidCreeper(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCreeper entity)
    {
        return CREEPER_TEXTURES;
    }

}
