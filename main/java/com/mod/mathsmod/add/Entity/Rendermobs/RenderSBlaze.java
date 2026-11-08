package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderSBlaze extends RenderBlaze
{
	private static final ResourceLocation BLAZE_TEXTURES = new ResourceLocation(Mathsmod.MODID, "textures/entity/blaze.png");

    public RenderSBlaze(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    
    protected ResourceLocation getEntityTexture(EntityBlaze entity)
    {
        return BLAZE_TEXTURES;
    }
}
