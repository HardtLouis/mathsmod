package com.mod.mathsmod.add.Entity.Rendermobs.pistolAnimator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.playerCosmetics.LayerGod;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.utils.DisguiseHelper;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerEntityOnShoulder;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RenderPistolPlayer extends RenderPlayerModified
{
    /** this field is used to indicate the 3-pixel wide arms */
    private final boolean smallArms;
    ItemGun gun;
    
    public RenderPistolPlayer(RenderManager renderManager, ItemGun gunIn)
    {
        this(renderManager, true, gunIn);
    }

    public RenderPistolPlayer(RenderManager renderManager, boolean useSmallArms, ItemGun gunIn)
    {
        super(renderManager);
        this.smallArms = useSmallArms;
        this.layerRenderers.remove(this.getArmorLayer());
        this.addLayer(new LayerBipedArmor(this)
        		{
        			@Override
        			protected void initArmor()
        			{
        				this.modelLeggings = new ModelArmorAnim(0.5F, gunIn);
        				this.modelArmor = new ModelArmorAnim(1F, gunIn);
        			}
        		});
        this.mainModel = new ModelPlayerAnim(0, useSmallArms, gunIn);
        gun = gunIn;
    }
    
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	this.mainModel = new ModelPlayerAnim(0, TextureHelper.smallArms(this.getName(entity)), gun);
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public ResourceLocation getEntityTexture(AbstractClientPlayer entity)
    {
    	if(DisguiseHelper.getDisguisment(entity) != null)
    	{
    		return TextureHelper.getPlayerSkin(DisguiseHelper.getDisguisment(entity).toString().substring(2));
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
    
    private String getName(AbstractClientPlayer entity)
    {
    	if(DisguiseHelper.getDisguisment(entity) != null)
    	{
    		String s = DisguiseHelper.getDisguisment(entity).toString().substring(2);
            return s;
    	}
    	else
    	{
    		return entity.getName();
    	}
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
            	if(DisguiseHelper.getDisguisment(entity) != null)
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

}