package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.RegisterItems;
import com.mod.mathsmod.add.Entity.Entity.EntityDevHand;
import com.mod.mathsmod.add.Entity.Entity.EntityDevilHand;
import com.mod.mathsmod.add.Entity.Models.ModelDevilHand;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.BossDeathLayer;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerDSword;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderDevilHand extends AbstractRenderBaseBoss<EntityLiving>
{
	
	public RenderDevilHand(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelDevilHand(), 0);
		this.addLayer(new BossDeathLayer(200, 0, 0));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) {
		
		if(entity.hurtTime > 0 || entity.deathTime > 0)
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/devil_hand/devil_hand_hurt.png");
		}
		else
		{
			return new ResourceLocation(Mathsmod.MODID, "textures/entity/devil_hand/devil_hand.png");
		}
	}
	

	
	@Override
	protected void preRenderCallback(EntityLiving entity, float partialTickTime)
    {
		GlStateManager.translate(0, 0.5F, 0);
        GlStateManager.scale(3F, 3F, 3F);
        
        if(entity instanceof EntityDevHand)
        {
        	GlStateManager.color(1F, 0, 0);
        }
    }

}
