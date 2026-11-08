package com.mod.mathsmod.add.GUI.spellManager;

import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.stats.ICustomGunStats;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.common.config.Configuration;

public class GuiSpellHelper extends Gui
{
    public GuiSpellHelper(Minecraft mc)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
 
        if(MConfig.getConfig().get(Configuration.CATEGORY_CLIENT, "showSpell", true).getBoolean())
        {
        	if(KeyEvent.selectedSpell != null)
         	{
         		this.drawString(mc.fontRenderer, KeyEvent.selectedSpell, 3, height - 20, Integer.parseInt("FF0008", 16));
         	}
        }
    }
}