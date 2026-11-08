package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityCultistPriest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCultistPriest extends RenderLiving<EntityCultistPriest>
{

    public RenderCultistPriest(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelBiped(), 0.5F);
    }
    
    @Override
	protected void preRenderCallback(EntityCultistPriest entity, float partialTickTime)
    {
    	//GlStateManager.translate(0, Math.abs(MathHelper.sin(entity.getWalkProgress() * 0.6662F * 3) * 0.2F), 0);
    }
    
    @Override
    public void doRender(EntityCultistPriest entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
    	if(entity.getSpell() != null)
    	{
    		entity.getSpell().spellUseClient(entity.getPhase(), entity.getProgress(), entity);
        	entity.getSpell().renderUse(entity.getPhase(), entity.getProgress(), entity);
    	}
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
    
    @Override
    protected void renderModel(EntityCultistPriest entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
    	if(entitylivingbaseIn.getSpell() != null && this.getMainModel() instanceof ModelBiped)
    	{
    		entitylivingbaseIn.getSpell().animateModel((ModelBiped) this.getMainModel(), entitylivingbaseIn, entitylivingbaseIn.getPhase(), entitylivingbaseIn.getProgress(), limbSwing, limbSwingAmount, Minecraft.getMinecraft().getRenderPartialTicks());
    	}
    	
    	super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityCultistPriest entity)
    {
        return new ResourceLocation(Mathsmod.MODID, "textures/entity/cultist_priest.png");
    }
}