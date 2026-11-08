package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityCrackerMiniboss;
import com.mod.mathsmod.add.Entity.Models.ModelCracker;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCracker extends RenderLiving<EntityCrackerMiniboss>
{

    public RenderCracker(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCracker(), 2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCrackerMiniboss entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/cracker.png");
    }
    

	
	protected void preRenderCallback(EntityCrackerMiniboss entitylivingbaseIn, float partialTickTime)
    {
		GlStateManager.scale(2F, 2F, 2F);
		
		/*if(entitylivingbaseIn.getHidden() > 10 && entitylivingbaseIn.getHidden() != 0)
		{
			GlStateManager.translate(0, -0.45, -0.8);
			GlStateManager.rotate(-90, 1, 0, 0);
			//
		}
		else if(entitylivingbaseIn.getHidden() != 0)
		{
			GlStateManager.translate(0, -0.45 * ((float)entitylivingbaseIn.getHidden()) / 10.0F, -0.8 * ((float)entitylivingbaseIn.getHidden()) / 10.0F);
			GlStateManager.rotate(-((float)entitylivingbaseIn.getHidden()) / 10.0F * 90.0F, 1, 0, 0);
			//GlStateManager.translate(-((float)entitylivingbaseIn.getHidden()) / 10.0F * 2.0F, 0, 0);
		}*/
    }
}