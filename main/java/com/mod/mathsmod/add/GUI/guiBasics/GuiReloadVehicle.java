package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiReloadVehicle extends Gui
{
	protected static final ResourceLocation RELOAD = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	
    public GuiReloadVehicle(Minecraft mc, IReloadVehicle vehicle)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        mc.getTextureManager().bindTexture(RELOAD);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(width / 2 - 128, height - 57, 0, 108, 256, 12);
        float i = vehicle.getReloadBar() * 256;
        this.drawTexturedModalRect(width / 2 - 128, height - 57, 0, 120, (int) i, 12);
    }
}