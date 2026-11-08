package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityDestroyer;
import com.mod.mathsmod.add.Entity.Entity.EntityShark;
import com.mod.mathsmod.add.Entity.Entity.EntityZomboss;
import com.mod.mathsmod.add.Entity.Models.ModelZomboss;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDSword;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerZombossItem;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderZomboss extends AbstractRenderBaseBoss<EntityZomboss>
{

	public RenderZomboss(RenderManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelZomboss(), 1);
		this.addLayer(new LayerZombossItem(this, new ItemStack(RegisterItems.HEAD_CUTTER)));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZomboss entity) 
	{
		return new ResourceLocation(Mathsmod.MODID, "textures/entity/brute.png");
	}
	
	@Override
	protected void preRenderCallback(EntityZomboss entity, float partialTickTime)
    {
        GlStateManager.scale(2F, 2F, 2F);
    }

}
