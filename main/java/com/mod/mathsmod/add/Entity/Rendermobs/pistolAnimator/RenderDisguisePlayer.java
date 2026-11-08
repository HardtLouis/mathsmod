package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDisguisePlayer extends RenderPlayerModified
{
	protected final String name;

	public RenderDisguisePlayer(RenderManager renderManager, boolean smallArms, String nameIn) 
	{
		super(renderManager, smallArms);
		name = nameIn;
		this.mainModel = new ModelPlayerAnim(0.0F, smallArms, null);
	}
	
	@Override
	public ResourceLocation getEntityTexture(AbstractClientPlayer entity)
    {
		return TextureHelper.getPlayerSkin(name);
    }
	
	@Override
	public void renderName(AbstractClientPlayer entity, double x, double y, double z)
    {
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre<AbstractClientPlayer>(entity, this, x, y, z))) return;
        if (this.canRenderName(entity))
        {
            double d0 = entity.getDistanceSq(this.renderManager.renderViewEntity);
            float f = entity.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;

            if (d0 < (double)(f * f))
            {
                String s = name;
                GlStateManager.alphaFunc(516, 0.1F);
                this.renderEntityName(entity, x, y, z, s, d0);
            }
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Specials.Post<AbstractClientPlayer>(entity, this, x, y, z));
    }

}
