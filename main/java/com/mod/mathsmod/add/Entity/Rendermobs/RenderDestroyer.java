package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityDestroyer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDSword;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderDestroyer extends AbstractRenderBaseBoss<EntityDestroyer>
{

	public RenderDestroyer(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBiped(0.0F, 0.0F, 64, 32), 1);
		this.addLayer(new BossDeathLayer(200, 200, 200));
		this.addLayer(new LayerDSword(this, new ItemStack(RegisterItems.DSWORD)));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDestroyer entity) {
		
		if(entity.hurtTime > 0 || entity.deathTime > 0)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/destroyer/destroyer_hurt.png");
		}
		else
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/destroyer/destroyer.png");
		}
	}

}
