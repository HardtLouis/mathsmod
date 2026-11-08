package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.stats.ICustomGunStats;
import com.mod.mathsmod.utils.IGunCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class GuiGunStats  extends Gui
{
    public GuiGunStats(Minecraft mc)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
 
        if(mc.player.getHeldItemMainhand().getItem() instanceof ItemGun && !(mc.player.getHeldItemMainhand().getItem() instanceof ICustomGunStats))
        {
        	ItemGun item = (ItemGun) mc.player.getHeldItemMainhand().getItem();
        	this.drawString(mc.fontRenderer, "reload time : " + item.getMaxReloadTime() / 20, 3, 0, Integer.parseInt("FF0008", 16));
        	this.drawString(mc.fontRenderer, "damage : " + (item.getDamagesCaused() * item.damageMultiplier(mc.player.getHeldItemMainhand(), null, null)) / 2, 3, 10, Integer.parseInt("FF0008", 16));
        	this.drawString(mc.fontRenderer, "clip size : " + item.getChargerSize(), 3, 20, Integer.parseInt("FF0008", 16));
        	this.drawString(mc.fontRenderer, "clip : " + item.getCharger(mc.player.getHeldItemMainhand()), 3, 30, Integer.parseInt("FF0008", 16));
        	//this.drawString(mc.fontRenderer, "bullet : " + item.bulletUsed(mc.player.getHeldItemMainhand()) != null ? I18n.translateToLocal(item.bulletUsed(mc.player.getHeldItemMainhand()).getUnlocalizedName()) : "" , 3, 40, Integer.parseInt("FF0008", 16));
        }
        if(mc.player.getHeldItemMainhand().getItem() instanceof ICustomGunStats)
        {
        	ICustomGunStats item = (ICustomGunStats) mc.player.getHeldItemMainhand().getItem();
        	for(int i = 0; i < item.stats(mc.player.getHeldItemMainhand()).size(); ++i)
        	{
        		this.drawString(mc.fontRenderer, item.stats(mc.player.getHeldItemMainhand()).get(i).displayName + " : " + item.stats(mc.player.getHeldItemMainhand()).get(i).displayValue, 3, i * 10, Integer.parseInt("FF0008", 16));
        	}
        }
        
    }
}