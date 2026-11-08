package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.tank.IReloadVehicle;
import com.mod.mathsmod.add.items.guns.ItemMedigun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class guiUberProgress extends Gui
{
	protected static final ResourceLocation RELOAD = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	
    public guiUberProgress(Minecraft mc, ItemMedigun item)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        mc.getTextureManager().bindTexture(RELOAD);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.color(0, 1, 1);
        this.drawTexturedModalRect(width / 2 - 128, height - 57, 0, 108, 256, 12);
        float i = ((float) item.getUber(mc.player.getHeldItemMainhand()) / ((float) item.uberTime())) * 256F;
        this.drawTexturedModalRect(width / 2 - 128, height - 57, 0, 120, (int) i, 12);
    }
}