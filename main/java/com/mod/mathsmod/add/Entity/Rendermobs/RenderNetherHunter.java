package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityHunter;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNetherHunter extends RenderHunter
{
	
	public RenderNetherHunter(RenderManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	protected ResourceLocation getEntityTexture(EntityHunter entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/nether_hunter/hunter_" + entity.getSkin() + ".png");
    }
}
