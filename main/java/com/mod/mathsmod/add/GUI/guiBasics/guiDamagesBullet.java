package com.mod.mathsmod.add.GUI.guiBasics;

import java.util.List;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.ItemShotGun;
import com.mod.mathsmod.utils.DamageSourceGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class guiDamagesBullet extends Gui
{
	
	protected static final ResourceLocation DAMAGES = new ResourceLocation(Mathsmod.MODID, "textures/gui/damages.png");
	
    public guiDamagesBullet(Minecraft mc, Vec3d vec)
    {
        
        
        if(mc.player != null && vec != null)
        {
        	ScaledResolution scaled = new ScaledResolution(mc);
        	
            int width = scaled.getScaledWidth() / 2;
            int height = scaled.getScaledHeight() / 2;
            
            float rotP = mc.player.rotationYaw;
            float rotT = UtilsVoids.getYawFromEntity(vec, mc.player);
            
            mc.getTextureManager().bindTexture(DAMAGES);
            
            float a = rotP - rotT;
            GlStateManager.translate(width, height, 0);
        	GlStateManager.rotate(-a, 0, 0, 1);
            
            this.drawTexturedModalRect(-31, -36, 0, 0, 63, 36);
            
            GlStateManager.rotate(a, 0, 0, 1);
            GlStateManager.translate(-width, -height, 0);
        }
        
    }
    
}