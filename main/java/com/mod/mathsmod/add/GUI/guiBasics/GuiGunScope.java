package com.mod.mathsmod.add.GUI.guiBasics;

import java.io.IOException;
import java.util.List;

import com.mod.mathsmod.Mathsmod;
import com.mod.mathsmod.add.World.UtilsVoids;
import com.mod.mathsmod.add.items.guns.ItemGun;
import com.mod.mathsmod.add.items.guns.ItemMachineGun;
import com.mod.mathsmod.add.items.guns.ItemShotGun;
import com.mod.mathsmod.utils.CustomScaledResolution;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class GuiGunScope extends Gui
{
	
	protected static final ResourceLocation SCOPE = new ResourceLocation(Mathsmod.MODID, "textures/gui/colored_button.png");
	protected static final ResourceLocation SCOPE2 = new ResourceLocation(Mathsmod.MODID, "textures/gui/assault_rifle_scope.png");
	
    public GuiGunScope(Minecraft mc)
    {
        ScaledResolution scaled = new ScaledResolution(mc);
        
        float e = scaled.getScaleFactor() / 4F;
        int width = scaled.getScaledWidth() / 2;
        int height = scaled.getScaledHeight() / 2;
        
        mc.getTextureManager().bindTexture(SCOPE);
        
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
        
        int u = 0;
        
        if(mc.player.getHeldItemMainhand().getItem() instanceof ItemMachineGun && !((ItemMachineGun) mc.player.getHeldItemMainhand().getItem()).keepBaseScope(mc.player.getHeldItemMainhand()))
        {
        	GlStateManager.translate(-x - r, -y - r, 0);
            GlStateManager.scale(1 / e , 1 / e, 0);
            
    		mc.getTextureManager().bindTexture(SCOPE2);
            this.drawTexturedModalRect(width - 128, height - 128, 0, 0, 256, 256);
            
            GlStateManager.scale(e, e, 0);
            GlStateManager.translate(x + r, y + r, 0);
        }
        else
        {
        	GlStateManager.translate(-x - r, -y - r, 0);
            GlStateManager.scale(1 / e , 1 / e, 0);
            
        	if(mc.player.getHeldItemMainhand().getItem() instanceof ItemGun)
            {
            	ItemGun gun = (ItemGun) mc.player.getHeldItemMainhand().getItem();
            	for(double i = 0; i <= gun.getReach(); i += 0.5)
            	{
            		List<Vec3d> list = UtilsVoids.doublelaserWithInnacuracy(i, gun.getPower(), mc.player.rotationYaw, mc.player.rotationPitch, 0, 0, 0);
            		Vec3d vec = list.get(0);
            		Vec3d vec2 = list.get(1);
                	if(UtilsVoids.getHitEntities(EntityLivingBase.class, mc.player.world, mc.player, mc.player.posX + vec.x, mc.player.posY + mc.player.getEyeHeight() + vec.y, mc.player.posZ + vec.z).size() > 0)
                    {
                    	u = 58;
                    }
            	}
            }
            
            if(mc.player.getHeldItemMainhand().getItem() instanceof ItemGun && !(mc.player.getHeldItemMainhand().getItem() instanceof ItemShotGun))
            {
            	ItemGun gun = (ItemGun) mc.player.getHeldItemMainhand().getItem();
            	ItemStack stack  = mc.player.getHeldItemMainhand();
            	
            	float i = (gun.getInnacuracy(stack, mc.player) + gun.getShootDefault(stack)) * 2;
            	i *= gun.innacuracyMultiplier(stack, mc.player);
            	int de = MathHelper.floor(i);
            	
            	this.drawTexturedModalRect((int) ((width) - 7 - de),(int) (height), 0 + u, 29, 7, 1);
            	this.drawTexturedModalRect((int) width, (int) ((height) - 7 - de), 7 + u, 22, 1, 7);
            	this.drawTexturedModalRect((int)((width) + de + 1),(int) (height), 8 + u, 29, 7, 1);
            	this.drawTexturedModalRect((int) width, (int) ((height) + de + 1), 7 + u, 30, 1, 7);
            }
            else if(mc.player.getHeldItemMainhand().getItem() instanceof ItemGun && mc.player.getHeldItemMainhand().getItem() instanceof ItemShotGun)
            {
            	ItemShotGun gun = (ItemShotGun) mc.player.getHeldItemMainhand().getItem();
            	ItemStack stack  = mc.player.getHeldItemMainhand();
            	float i = ((((ItemGun) gun).getInnacuracy(stack, mc.player) * ((ItemGun) gun).innacuracyMultiplier(stack, mc.player) + gun.getBuckshotDecal(mc.player, stack)) * ((gun.getBulletsSquareNumber() / 2) + ((ItemGun) gun).getShootDefault(stack) * ((ItemGun) gun).innacuracyMultiplier(stack, mc.player))) + 6;
            	int de = MathHelper.floor(i);
            	this.drawTexturedModalRect((int) ((width) - de - 1), (int) (height - de - 1), 28 + u, 17, 10, 10);
            	this.drawTexturedModalRect((int) ((width) + de - 8), (int) (height + de - 8), 37 + u, 26, 10, 10);
            	this.drawTexturedModalRect((int) ((width) + de - 8), (int) (height - de - 1), 37 + u, 17, 10, 10);
            	this.drawTexturedModalRect((int) (width - de - 1), (int) (height + de - 8), 28 + u, 26, 10, 10);
            }
            
            GlStateManager.scale(e, e, 0);
            GlStateManager.translate(x + r, y + r, 0);
        }
        
    }
    
}