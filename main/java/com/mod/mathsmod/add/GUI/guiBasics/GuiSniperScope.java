package com.mod.mathsmod.add.GUI.guiBasics;

import java.awt.Color;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiSniperScope extends Gui
{
	
	public GuiSniperScope(Minecraft mc, IGunScope scope)
    {
		ScaledResolution scaled = new ScaledResolution(mc);
		float e = scaled.getScaleFactor() / 4F;
        int width = scaled.getScaledWidth() / 2;
        int height = scaled.getScaledHeight() / 2;
        
        double x = (width / e - width);
        double y = (height / e - height);
        
        double r = 0;
        if(scaled.getScaleFactor() == 1)
        {
        	r = 1F;
        }
        if(scaled.getScaleFactor() == 2)
        {
        	r = 0.5F;
        }
        if(scaled.getScaleFactor() == 3)
        {
        	r = 0.25F;
        }
        
        GlStateManager.translate(-x - r, -y - r, 0);
        GlStateManager.scale(1 / e , 1 / e, 0);
        
        if(scope instanceof ItemGun)
        {
        	if(((ItemGun) scope).getUsed(mc.player.getHeldItemMainhand()) > 0 && !((ItemGun) scope).ChargerEmpty(mc.player.getHeldItemMainhand()))
        	{
        		float yTranslate = ((float) MathHelper.clamp(((ItemGun) scope).getUsed(mc.player.getHeldItemMainhand()), 0, scope.timeToFullScope())) / ((float)scope.timeToFullScope());
        		mc.getTextureManager().bindTexture(scope.getScope());
                this.drawTexturedModalRect(width - 128, height - 128 * yTranslate, 0, 0, 256, 256);
        	}
        }
        
        GlStateManager.scale(e, e, 0);
        GlStateManager.translate(x + r, y + r, 0);
    }
}
