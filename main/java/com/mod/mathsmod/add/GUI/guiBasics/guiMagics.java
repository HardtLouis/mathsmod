package com.mod.mathsmod.add.GUI.guiBasics;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.items.guns.ItemMedigun;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class guiMagics extends Gui
{
	protected static final ResourceLocation TEXTURE = new ResourceLocation(Mathsmod.MODID, "textures/gui/gui_spell_selection.png");
	
    public guiMagics(Minecraft mc)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
        mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if(KeyEvent.selectedSpell != null && client_Proxy.spellCooldowns.containsKey(KeyEvent.selectedSpell) && SpellRegistry.REGISTRY.containsKey(KeyEvent.selectedSpell) && mc.player != null)
        {
            this.drawTexturedModalRect(width / 2 - 57, height - 67, 0, 90, 114, 4);
            float i = ((float) client_Proxy.spellCooldowns.get(KeyEvent.selectedSpell)) / ((float) SpellRegistry.REGISTRY.get(KeyEvent.selectedSpell).spellCooldown(mc.player)) * 114F;
            this.drawTexturedModalRect(width / 2 - 57, height - 67, 0, 94, (int) i, 4);
        }
        
        this.drawTexturedModalRect(width - 57, height - 57, 0, 98, 34, 34);
        float j = ((float) client_Proxy.spellEnergy) / ((float) Mathsmod.MAX_SPELL_ENERGY) * 28F;
        int p = (int) j;
        this.drawTexturedModalRect(width - 57, height - 54 - p + 28, 34, 101 - p + 28, 34, (int) p);
    }
}