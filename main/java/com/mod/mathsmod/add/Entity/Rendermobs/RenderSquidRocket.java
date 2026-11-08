package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityAMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityBMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityNuclearMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityRocketSquid;
import com.mod.mathsmod.add.Entity.Entity.objects.EntitySuperNuclearMissile;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTMissile;
import com.mod.mathsmod.add.Entity.Models.ModelAMissile;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSquidRocket extends EasyRenderEntity<EntityRocketSquid>{

	public RenderSquidRocket(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSquid(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRocketSquid entity) {
		
		return new ResourceLocation("textures/entity/squid.png");
	}
	
	@Override
	protected void preRenderCallback(EntityRocketSquid entity, float partialTickTime)
    {
		GlStateManager.rotate(entity.rotationYaw + 180, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch + 90, 1, 0, 0);
		GlStateManager.scale(2, 2, 2);
    }
}