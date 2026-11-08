package com.mod.mathsmod.add.GUI.guiBasics;

import org.lwjgl.input.Mouse;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.SpellRegistry;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.event.KeyEvent;
import com.mod.mathsmod.add.spell.SpellBase;
import com.mod.mathsmod.config.MConfig;
import com.mod.mathsmod.proxy.client_Proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemCloth;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class guiSpellNew extends Gui
{
	
	public guiSpellNew(Minecraft mc)
    {
		GlStateManager.color(1, 1, 1);
		ScaledResolution scaled = new ScaledResolution(mc);
		float e = scaled.getScaleFactor() / 4F;
        int width = scaled.getScaledWidth() / 2;
        int height = scaled.getScaledHeight() / 2;
        
        double x = (width / e - width);
        double y = (height / e - height);
        
        mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/gui_spell_selection.png"));
        
        this.drawTexturedModalRect(width - 45, height - 45, 0, 0, 90, 90);
        
        if(KeyEvent.selectedSpell != null && client_Proxy.userSpells != null && client_Proxy.userSpells.size() > 3)
        {
        	if(KeyEvent.selectedSpell.equals(client_Proxy.userSpells.get(0).spellName()))
        	{
        		this.drawTexturedModalRect(width - 15, height - 45, 90, 0, 30, 30);
        	}
        	else if(KeyEvent.selectedSpell.equals(client_Proxy.userSpells.get(2).spellName()))
        	{
        		this.drawTexturedModalRect(width - 15, height + 15, 90, 0, 30, 30);
        	}
        	else if(KeyEvent.selectedSpell.equals(client_Proxy.userSpells.get(1).spellName()))
        	{
        		this.drawTexturedModalRect(width + 15, height - 15, 90, 0, 30, 30);
        	}
        	else if(KeyEvent.selectedSpell.equals(client_Proxy.userSpells.get(3).spellName()))
        	{
        		this.drawTexturedModalRect(width - 45, height - 15, 90, 0, 30, 30);
        	}
        }
        
        if(mc.player != null && mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemCloth)
        {
        	this.drawTexturedModalRect(width - 45, height + 95, 0, 0, 90, 30);
        }
        
        if(client_Proxy.userSpells != null && client_Proxy.userSpells.size() > 3)
        {
        	SpellBase spell0 = client_Proxy.userSpells.get(0);
    		mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/spell_icons/" + spell0.IconName(mc.player) + ".png"));
    		this.drawModalRectWithCustomSizedTexture(width - 15, height - 45, 0, 0, 30, 30, 30, 30);
    		
    		SpellBase spell1 = client_Proxy.userSpells.get(1);
    		mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/spell_icons/" + spell1.IconName(mc.player) + ".png"));
    		this.drawModalRectWithCustomSizedTexture(width + 15, height - 15, 0, 0, 30, 30, 30, 30);
    		
    		SpellBase spell2 = client_Proxy.userSpells.get(2);
    		mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/spell_icons/" + spell2.IconName(mc.player) + ".png"));
    		this.drawModalRectWithCustomSizedTexture(width - 15, height + 15, 0, 0, 30, 30, 30, 30);
    		
    		SpellBase spell3 = client_Proxy.userSpells.get(3);
    		mc.getTextureManager().bindTexture(new ResourceLocation(Mathsmod.MODID, "textures/gui/spell_icons/" + spell3.IconName(mc.player) + ".png"));
    		this.drawModalRectWithCustomSizedTexture(width - 45, height - 15, 0, 0, 30, 30, 30, 30);
        }
        
        this.drawCenteredString(mc.fontRenderer, KeyEvent.selectedSpell, width, height - 60, 16777215);
        
        if(KeyEvent.selectedSpell != null && SpellRegistry.REGISTRY.containsKey(KeyEvent.selectedSpell))
        {
        	SpellBase spell = SpellRegistry.REGISTRY.get(KeyEvent.selectedSpell);
        	for(int i = 0; i < spell.description().size(); i++)
        	{
                this.drawString(mc.fontRenderer, spell.description().get(i), 10, height + 30 + 13 * i, 16777215);
        	}
        }
    }
	
	public static void selectSpell(int mx, int my)
	{
		MouseHelper mh = Minecraft.getMinecraft().mouseHelper;
		Minecraft mc = Minecraft.getMinecraft();
        
        Vec3d vecX = new Vec3d(1, 0, 0);
        Vec3d vecY = new Vec3d(0, 1, 0);
        Vec3d vecMouse = new Vec3d(mx, my, 0);
        
        if(vecMouse.lengthVector() > 0 && client_Proxy.userSpells != null && client_Proxy.userSpells.size() > 3)
        {
        	double d1 = UtilsVoids.dotProduct(vecX, vecMouse);
        	double d2 = UtilsVoids.dotProduct(vecY, vecMouse);
        	
        	if(Math.abs(d2) > Math.abs(d1))
        	{
        		if(d2 > 0)
        		{
        			KeyEvent.selectedSpell = client_Proxy.userSpells.get(0).spellName();
        		}
        		else
        		{
        			KeyEvent.selectedSpell = client_Proxy.userSpells.get(2).spellName();;
        		}
        	}
        	else
        	{
        		if(d1 > 0)
        		{
        			KeyEvent.selectedSpell = client_Proxy.userSpells.get(1).spellName();
        		}
        		else
        		{
        			KeyEvent.selectedSpell = client_Proxy.userSpells.get(3).spellName();
        		}
        	}
        }
	}
}