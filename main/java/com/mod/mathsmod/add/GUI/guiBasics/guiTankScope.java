package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.Entity.Entity.objects.EntityTank;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.stats.IGunScope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class guiTankScope extends Gui
{
	protected static final ResourceLocation RELOAD = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	
	public guiTankScope(Minecraft mc)
    {
		ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        
        if(mc.player.getRidingEntity() instanceof EntityTank)
    	{
    		mc.getTextureManager().bindTexture(RELOAD);
            this.drawTexturedModalRect(width / 2 - 22, height / 2 - 22, 5, 138, 44, 44);
    	}
    }
}
