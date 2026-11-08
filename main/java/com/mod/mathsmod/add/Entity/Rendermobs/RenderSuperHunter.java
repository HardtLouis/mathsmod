package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityDeadHunter;
import com.mod.mathsmod.add.Entity.Entity.EntityDestroyer;
import com.mod.mathsmod.add.Entity.Entity.EntitySuperHunter;
import com.mod.mathsmod.add.Entity.Models.ModelHunter;
import com.mod.mathsmod.add.Entity.Models.ModelSuperHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDSword;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerItemSuperHunter;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderSuperHunter extends AbstractRenderBaseBoss<EntityLiving>
{
	public RenderSuperHunter(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSuperHunter(0.0F), 1);
		this.addLayer(new BossDeathLayer(200, 200, 200));
		this.addLayer(new LayerItemSuperHunter(this, null));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) 
	{
		if(entity.hurtTime > 0 || entity.deathTime > 0)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/hunter/boss_hurt.png");
		}
		else
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/hunter/boss.png");
		}
	}

	@Override
	protected void preRenderCallback(EntityLiving entity, float partialTickTime)
    {
        GlStateManager.scale(1.25F - (entity.deathTime / 100 * 1.25), 1.25F - (entity.deathTime / 100 * 1.25), 1.25F - (entity.deathTime / 100 * 1.25));
        if(entity instanceof EntitySuperHunter && ((EntitySuperHunter) entity).getCharge() != 0)
        {
        	float f = ((float)((EntitySuperHunter) entity).getCharge()) / 40F;
        	GlStateManager.color(1, 1 - f, 1 - f);
        }
    }
}