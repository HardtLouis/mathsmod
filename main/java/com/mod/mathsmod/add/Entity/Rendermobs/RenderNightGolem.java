package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityNightGolem;
import com.mod.mathsmod.add.Entity.Entity.EntitySteroidNightGolem;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderNightGolem extends RenderLiving<EntityNightGolem>
{

	public RenderNightGolem(RenderManager rendermanagerIn, ModelBase modelbaseIn) 
	{
		super(rendermanagerIn, modelbaseIn, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityNightGolem entity) 
	{
		if(entity instanceof EntitySteroidNightGolem)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/steroid_night_golem.png");
		}
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/night_golem.png");
	}
	
	protected void preRenderCallback(EntityNightGolem entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

}
