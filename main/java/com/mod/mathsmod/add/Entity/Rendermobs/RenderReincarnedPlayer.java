package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityPlane;
import com.mod.mathsmod.add.Entity.Entity.objects.magic.EntityReincarnedPlayer;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderReincarnedPlayer extends RenderBiped<EntityReincarnedPlayer>
{

    public RenderReincarnedPlayer(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPlayer(0, false), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this);
        this.addLayer(layerbipedarmor);
    }
    
    public void doRender(EntityReincarnedPlayer entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	this.mainModel = new ModelPlayer(0, TextureHelper.smallArms(entity.getPlayer()));
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityReincarnedPlayer entity)
    {
        return TextureHelper.getPlayerSkin(entity.getPlayer());
    }
    
    @Override
    protected void preRenderCallback(EntityReincarnedPlayer entity, float partialTickTime)
    {
		GlStateManager.color(0.5F, 0.5F, 0.5F);
    }
	
}