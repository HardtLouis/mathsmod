package com.mod.mathsmod.add.Entity.Rendermobs;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.EntityBot;
import com.mod.mathsmod.add.Entity.Models.ModelHunter;
import com.mod.mathsmod.add.Entity.Rendermobs.layer.LayerBot;
import com.mod.mathsmod.utils.TextureHelper;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderBot extends RenderBiped<EntityBot>
{

    public RenderBot(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelHunter(0, 64, 64), 0.5F);
        this.addLayer(new LayerBot(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelHunter(0.5F);
                this.modelArmor = new ModelHunter(1.0F);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBot entity)
    {
    	if(entity.getSkin() != null && entity.getSkin() != "-" && entity.getSkin().length() > 0)
    	{
            return TextureHelper.getPlayerSkin(entity.getSkin());
    	}
    	else
    	{
            return new ResourceLocation(Mathsmod.MODID, "textures/entity/bot/bot.png");
    	}
    }
}