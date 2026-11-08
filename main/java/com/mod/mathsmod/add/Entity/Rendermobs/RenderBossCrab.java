package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntitySBossCrab;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityGlider;
import com.mod.mathsmod.add.Entity.Models.ModelBossCrab;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderBossCrab extends AbstractRenderBaseBoss<EntitySBossCrab>
{

	public RenderBossCrab(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelBossCrab(), 1);
		//this.addLayer(new BossDeathLayer(200, 200, 200));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySBossCrab entity) {
		
		if(entity.hurtTime > 0 || entity.deathTime > 0)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/boss_crab/boss_crab_hurt.png");
		}
		else
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/boss_crab/boss_crab.png");
		}
	}
	
	@Override
	protected void preRenderCallback(EntitySBossCrab entity, float partialTickTime)
    {
		GlStateManager.scale(4, 4, 4);
    }

}