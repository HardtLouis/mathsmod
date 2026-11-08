package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDBoss;
import com.mod.mathsmod.add.Entity.Models.ModelDBoss;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDBoss extends AbstractRenderBaseBoss<EntityDBoss>
{

	public RenderDBoss(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelDBoss(), 1);
		//this.addLayer(new BossDeathLayer(200, 200, 200));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDBoss entity) {
		
		if(entity.hurtTime > 0 || entity.deathTime > 0)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/d_boss.png");
		}
		else
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/d_boss.png");
		}
	}
	
	@Override
	protected void preRenderCallback(EntityDBoss entity, float partialTickTime)
    {
		GlStateManager.scale(2, 2, 2);
		if(entity.getSpawnTime() > 20)
		{
			GlStateManager.color(0, 0, 0);
		}
		else
		{
			float a = ((float)entity.getSpawnTime() / 20); 
			GlStateManager.color(1 - a, 1 - a, 1 - a);
		}
    }

}