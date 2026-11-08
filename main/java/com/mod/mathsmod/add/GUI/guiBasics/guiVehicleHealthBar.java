package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.IVehicle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class guiVehicleHealthBar extends Gui
{
	protected static final ResourceLocation UNKNOWN = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	
    public guiVehicleHealthBar(Minecraft mc, IVehicle vehicle)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        mc.getTextureManager().bindTexture(UNKNOWN);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(width / 2 + 9, height - 39, 0, 0, 80, 8);
        this.drawTexturedModalRect(width / 2 + 9, height - 39, 0, 8, (int) (vehicle.getHealthBar() * 80), 8);
    }
}