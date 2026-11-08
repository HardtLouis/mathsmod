package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.DisguiseHelper;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderDabPlayer extends RenderPlayerModified
{
    /** this field is used to indicate the 3-pixel wide arms */
    private final boolean smallArms;
    
    public RenderDabPlayer(RenderManager renderManager)
    {
        this(renderManager, true);
    }

    public RenderDabPlayer(RenderManager renderManager, boolean useSmallArms)
    {
        super(renderManager);
        this.smallArms = useSmallArms;
        this.layerRenderers.remove(this.getArmorLayer());
        this.addLayer(new LayerBipedArmor(this)
        		{
        			@Override
        			protected void initArmor()
        			{
        				this.modelLeggings = new ModelDabArmor(0.5F);
        				this.modelArmor = new ModelDabArmor(1F);
        			}
        		});
        this.mainModel = new ModelDabingPlayer(0F, useSmallArms);
    }
    
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	this.mainModel = new ModelDabingPlayer(0, TextureHelper.smallArms(this.getName(entity)));
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public ResourceLocation getEntityTexture(AbstractClientPlayer entity)
    {
    	if(DisguiseHelper.getDisguisment(entity) != null)
    	{
    		return TextureHelper.getPlayerSkin(DisguiseHelper.getDisguisment(entity).getResourcePath());
    	}
    	else
    	{
    		return super.getEntityTexture(entity);
    	}
        
    }
    
    private LayerRenderer getArmorLayer()
    {
    	for(LayerRenderer layer : this.layerRenderers)
    	{
    		if(layer instanceof LayerBipedArmor)
    		{
    			return layer;
    		}
    	}
		return null;
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
            	if(DisguiseHelper.getDisguisment(entity) != null && DisguiseHelper.getDisguisment(entity).toString().startsWith("a:"))
            	{
            		String s = DisguiseHelper.getDisguisment(entity).toString().substring(2);
                    GlStateManager.alphaFunc(516, 0.1F);
                    this.renderEntityName(entity, x, y, z, s, d0);
            	}
            	else
            	{
            		super.renderName(entity, x, y, z);
            	}
            }
        }
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderLivingEvent.Specials.Post<AbstractClientPlayer>(entity, this, x, y, z));
    }
    
    private String getName(AbstractClientPlayer entity)
    {
    	if(DisguiseHelper.getDisguisment(entity) != null && DisguiseHelper.getDisguisment(entity).toString().startsWith("a:"))
    	{
    		String s = DisguiseHelper.getDisguisment(entity).toString().substring(2);
            return s;
    	}
    	else
    	{
    		return entity.getName();
    	}
    }

}